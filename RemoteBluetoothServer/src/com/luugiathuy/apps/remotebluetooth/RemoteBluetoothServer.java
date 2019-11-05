package com.luugiathuy.apps.remotebluetooth;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class RemoteBluetoothServer{
	
	public static void main(String[] args) {
		WaitThread waitThread = new WaitThread();
		AsyncUtils.executeAsync(waitThread);
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
		HashSet<String> sentinals = new HashSet<String>();
		sentinals.add("exit");
		sentinals.add("quit");
		while(true) {
			System.out.print(">>> ");
			String cmd = scanner.nextLine();
			if (sentinals.contains(cmd.toLowerCase())) {
				System.out.println("Closing application...");
				
				try {
					waitThread.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					AsyncUtils.shutdownNow();
					scanner.close();
				}
				
				break;
			}
		}
	}
}
