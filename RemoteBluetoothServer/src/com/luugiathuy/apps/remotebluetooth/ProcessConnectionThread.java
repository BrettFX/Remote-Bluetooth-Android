package com.luugiathuy.apps.remotebluetooth;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread implements Runnable{

	private StreamConnection mConnection;
	
	// Constant that indicate command from devices
	private static final int EXIT_CMD = -1;
	private static final int KEY_RIGHT = 1;
	private static final int KEY_LEFT = 2;
	
	public ProcessConnectionThread(StreamConnection connection)
	{
		mConnection = connection;
	}
	
	@Override
	public void run() {
		try {
			RemoteDevice dev = RemoteDevice.getRemoteDevice(mConnection);
			System.out.println("Device connected.");
	        System.out.println("Remote device address: " + dev.getBluetoothAddress());
	        System.out.println("Remote device name: " + dev.getFriendlyName(true));
			
			// prepare to receive data
			InputStream inputStream = mConnection.openInputStream();
			BufferedReader bReader=new BufferedReader(new InputStreamReader(inputStream));

			System.out.println("waiting for input");
	        
	        while (true) {
	        	String data = bReader.readLine();
	        	
	        	if (data != null) {
	        		System.out.println("Client disconnected.");
	        		System.out.println("Bluetooth Server disconnected.");
	        		break;
	        	}
	        	
	        	System.out.println(data);
	        	
	        	
//	        	int command = inputStream.read();
//	        	
//	        	if (command == EXIT_CMD)
//	        	{	
//	        		System.out.println("finish process");
//	        		break;
//	        	}
//	        	
//	        	processCommand(command);
        	}
        } catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	/**
	 * Process the command from client
	 * @param command the command code
	 */
	private void processCommand(int command) {
		try {
			Robot robot = new Robot();
			switch (command) {
	    	case KEY_RIGHT:
	    		robot.keyPress(KeyEvent.VK_RIGHT);
	    		System.out.println("Right");
	    		// release the key after it is pressed. Otherwise the event just keeps getting trigged	    		
	    		robot.keyRelease(KeyEvent.VK_RIGHT);
	    		break;
	    	case KEY_LEFT:
	    		robot.keyPress(KeyEvent.VK_LEFT);
	    		System.out.println("Left");
	    		// release the key after it is pressed. Otherwise the event just keeps getting trigged	    		
	    		robot.keyRelease(KeyEvent.VK_LEFT);
	    		break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
