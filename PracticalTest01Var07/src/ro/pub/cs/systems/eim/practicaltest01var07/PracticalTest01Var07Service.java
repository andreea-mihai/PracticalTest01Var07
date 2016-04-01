package ro.pub.cs.systems.eim.practicaltest01var07;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var07Service extends Service {

private ProcessingThread processingThread = null;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		processingThread = new ProcessingThread(this, intent.getStringExtra("one"), intent.getStringExtra("two"));
		processingThread.start();
		return Service.START_REDELIVER_INTENT;
	}

	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onDestroy() {
		processingThread.stopThread();
	}

}
