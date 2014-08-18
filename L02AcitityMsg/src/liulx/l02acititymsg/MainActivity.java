package liulx.l02acititymsg;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements android.view.View.OnClickListener {

	private Button btnClick;
	private TextView tvOut;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnClick = (Button) findViewById(R.id.btnShow);
		btnClick.setOnClickListener(this);
		tvOut = (TextView)findViewById(R.id.tvMsg);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	public void onClick(View v) {
		Intent i = new Intent(this, Activity1.class);
		i.putExtra("msg", "来自前面的消息");
		startActivityForResult(i, 0);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
		tvOut.setText(intent.getStringExtra("msg_back"));
		
		super.onActivityResult(requestCode, resultCode, intent);
	}
}
