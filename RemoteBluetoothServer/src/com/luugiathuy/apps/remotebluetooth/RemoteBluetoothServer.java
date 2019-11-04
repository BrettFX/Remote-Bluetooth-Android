package com.luugiathuy.apps.remotebluetooth;

public class RemoteBluetoothServer{
	
	public static void main(String[] args) {
		Thread waitThread = new Thread(new WaitThread());
		AsyncUtils.executeAsync(waitThread);
//		waitThread.start();
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Closing application...");
		AsyncUtils.shutdownNow();
	}
	
	
}
