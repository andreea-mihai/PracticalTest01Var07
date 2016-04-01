package ro.pub.cs.systems.eim.practicaltest01var07;

import java.sql.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;

public class ProcessingThread extends Thread {

	Context context;
	private boolean isRunning = true;
	private Random random = new Random();
	private String[] actionTypes ={"first", "second"};
	private String first = null;
	private String second = null;

	public ProcessingThread(Context context, String first, String second) {
		this.context = context;
		this.first = first;
		this.second = second;
	}

	@Override
	public void run() {
//		Log.d("[ProcessingThread]", "Thread has started!");
		while (isRunning) {
			sendMessage();
			sleep();
		}
//		Log.d("[ProcessingThread]", "Thread has stopped!");
	}

	private void sendMessage() {
		Intent intent = new Intent();
		intent.setAction(actionTypes[random.nextInt(2)]);
		intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + "Mihai Andreea"+"343C1");
		intent.putExtra("first", first);
		intent.putExtra("second", second);
		context.sendBroadcast(intent);
	}

	private void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	public void stopThread() {
		isRunning = false;
	}
}
