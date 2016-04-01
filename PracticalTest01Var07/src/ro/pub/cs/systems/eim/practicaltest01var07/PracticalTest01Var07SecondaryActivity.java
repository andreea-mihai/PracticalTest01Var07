package ro.pub.cs.systems.eim.practicaltest01var07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var07SecondaryActivity extends Activity {
	
	private Button buttonOk = null;
	private Button buttonCancel = null;
	private ButtonListener listener = new ButtonListener();
	
	private EditText text1 = null;
	private EditText text2 = null;
	
	private class ButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.ok:
				setResult(RESULT_OK, null);
				break;
			case R.id.cancel:
				setResult(RESULT_CANCELED, null);
				break;
			}
			finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var07_secondary);
		
		 buttonOk = (Button)findViewById(R.id.ok);
		 buttonCancel = (Button)findViewById(R.id.cancel);
		 text1 = (EditText)findViewById(R.id.text11);
		 text2 = (EditText)findViewById(R.id.text22);
		 
		 buttonOk.setOnClickListener(listener);
		 buttonCancel.setOnClickListener(listener);
		 
		 Intent intent = getIntent();
			 text1.setText(intent.getStringExtra("one"));
			 text2.setText(intent.getStringExtra("two"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var07_secondary, menu);
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
}
