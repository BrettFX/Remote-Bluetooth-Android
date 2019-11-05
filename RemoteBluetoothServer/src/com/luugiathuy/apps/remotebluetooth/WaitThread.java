package com.luugiathuy.apps.remotebluetooth;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class WaitThread implements Runnable {
	
	// Hyphenated UUID: 04c6093b-0000-1000-8000-00805f9b34fb
	private static final String SERVER_UUID = "04c6093b00001000800000805f9b34fb";
	
	private LocalDevice local;
	private StreamConnectionNotifier notifier;

	/** Constructor */
	public WaitThread() {
		local = null;
	}
	
	@Override
	public void run() {
		waitForConnection();		
	}
	
	public void close() throws IOException {
		if (local != null) {
			System.out.println("Setting local device to NOT_DISCOVERABLE");
			local.setDiscoverable(DiscoveryAgent.NOT_DISCOVERABLE);
		}
		
		if (notifier != null) {
			System.out.println("Closing StreamConnectionNotifier.");
			notifier.close();
		}
		
	}

	/** Waiting for connection from devices */
	private void waitForConnection() {
		// retrieve the local Bluetooth device object
		LocalDevice local = null;
		StreamConnection connection = null;
		
		// setup the server to listen for connection
		try {
			local = LocalDevice.getLocalDevice();
			local.setDiscoverable(DiscoveryAgent.GIAC);
			
			UUID uuid = new UUID(SERVER_UUID, false);
			System.out.println(uuid.toString());
			
            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier)Connector.open(url);
        } catch (BluetoothStateException e) {
        	System.out.println("Bluetooth is not turned on.");
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		// waiting for connection
		try {
			System.out.println("waiting for connection...");
            connection = notifier.acceptAndOpen();
            
            ProcessConnectionThread processThread = new ProcessConnectionThread(connection);
            AsyncUtils.executeAsync(processThread);
            
		} catch (Exception e) {
			System.out.println("Notifier has been closed; Connection is closed");
			return;
		}
		
		// waiting for connection
//		while(true) {
//			try {
//				System.out.println("waiting for connection...");
//	            connection = notifier.acceptAndOpen();
//	            
//	            Thread processThread = new Thread(new ProcessConnectionThread(connection));
//	            processThread.start();
//	            
//			} catch (Exception e) {
//				e.printStackTrace();
//				return;
//			}
//		}
	}
}
