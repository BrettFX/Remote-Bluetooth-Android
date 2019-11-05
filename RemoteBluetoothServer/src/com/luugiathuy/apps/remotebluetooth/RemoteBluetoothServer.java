package com.luugiathuy.apps.remotebluetooth;

import java.util.Scanner;

public class RemoteBluetoothServer{
	
	public static void main(String[] args) {
//		Thread waitThread = new Thread(new WaitThread());
//		AsyncUtils.executeAsync(waitThread);
//		
//		try {
//			Thread.sleep(3000);
//			AsyncUtils.shutdownNow();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.exit(0);
//		waitThread.start();
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String cmd = scanner.nextLine();
			if (cmd.toLowerCase().equals("exit")) {
				System.out.println("Closing application...");
//				AsyncUtils.shutdownNow();
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Closing application...");
		AsyncUtils.shutdownNow();
	}
	
	
}
