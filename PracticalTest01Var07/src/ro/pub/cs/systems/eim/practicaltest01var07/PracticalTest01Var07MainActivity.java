package ro.pub.cs.systems.eim.practicaltest01var07;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07MainActivity extends Activity {

	private myListener checkedListener = new myListener();

	private CheckBox check1 = null;
	private CheckBox check2 = null;
	
	private IntentFilter intentFilter = new IntentFilter();

	private EditText text1 = null;
	private EditText text2 = null;

	private Button next;

	private buttonListener listener = new buttonListener();
	
	private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
	private class MessageBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d("[Message]", intent.getStringExtra("message"));
			
		}
	}

	private class buttonListener implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.button_navigate:
				Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
				String texta = text1.getText().toString();
				String textb = text2.getText().toString();
				intent.putExtra("one", texta);
				intent.putExtra("two", textb);
				startActivityForResult(intent, 1);
				break;

			}
		}
	}

	private class myListener implements CompoundButton.OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.checkbox1:
				// TODO: Code for checked one...
				System.out.println("bum lalalalalal");
				if (isChecked) {

					text1.setEnabled(true);
				} else
					text1.setEnabled(false);

				break;
			case R.id.checkbox2:
				// TODO: Code for checked two...
				if (isChecked) {
					text2.setEnabled(true);
				} else
					text2.setEnabled(false);
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var07_main);

		check1 = (CheckBox) findViewById(R.id.checkbox1);
		check2 = (CheckBox) findViewById(R.id.checkbox2);

		text1 = (EditText) findViewById(R.id.text1);
		text2 = (EditText) findViewById(R.id.text2);

		check1.setOnCheckedChangeListener(checkedListener);
		check2.setOnCheckedChangeListener(checkedListener);

		next = (Button) findViewById(R.id.button_navigate);
		next.setOnClickListener(listener);
		
		text1.setEnabled(false);
		text2.setEnabled(false);
		
		if (!text1.isEnabled() && !text2.isEnabled())
		{
			Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
			intent.putExtra("one",text1.getText().toString());
			intent.putExtra("two", text2.getText().toString());
			getApplicationContext().startService(intent);

		}
		
//		for (int index = 0; index < Constants.actionTypes.length; index++) {
		      intentFilter.addAction("first");
		      intentFilter.addAction("second");
//		    }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var07_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putString("text1", text1.getText().toString());
		savedInstanceState.putString("text2", text2.getText().toString());
		savedInstanceState.putBoolean("check1", check1.isChecked());
		savedInstanceState.putBoolean("check2", check2.isChecked());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if (savedInstanceState.containsKey("text1")) {
			text1.setText(savedInstanceState.getString("text1"));
		}
		if (savedInstanceState.containsKey("text2")) {
			text2.setText(savedInstanceState.getString("text2"));
		}
		if (savedInstanceState.containsKey("check1")) {
			check1.setChecked(savedInstanceState.getBoolean("check1"));
		}
		if (savedInstanceState.containsKey("check2")) {
			check2.setChecked(savedInstanceState.getBoolean("check2"));
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 1) {
			Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	  protected void onResume() {
	    super.onResume();
	    registerReceiver(messageBroadcastReceiver, intentFilter);
	  }
	 
	  @Override
	  protected void onPause() {
	    unregisterReceiver(messageBroadcastReceiver);
	    super.onPause();
	  }
	
	@Override
	protected void onDestroy() {
		Intent intent = new Intent(this, PracticalTest01Var07Service.class);
		stopService(intent);
		super.onDestroy();
	}
}
