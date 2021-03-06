package liulx.l03service;

import liulx.l03service.EchoService.EchoBinder;
import android.support.v7.app.ActionBarActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener, ServiceConnection{

	private Button btnStartService, btnStopService, btnBindService, btnUnbindService, btnGetCurrentNumber;
	private Intent serviceIntent;
	private EchoService echoService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnStartService = (Button) findViewById(R.id.btnStartService);
		btnStopService = (Button) findViewById(R.id.btnStopService);
		btnBindService = (Button) findViewById(R.id.btnBindService);
		btnUnbindService = (Button) findViewById(R.id.btnUnbindService);
		btnGetCurrentNumber = (Button) findViewById(R.id.btnGetCurrentNumber);
		
		serviceIntent = new Intent(this, EchoService.class);
		
		btnStartService.setOnClickListener(this);
		btnStopService.setOnClickListener(this);
		btnBindService.setOnClickListener(this);
		btnUnbindService.setOnClickListener(this);
		btnGetCurrentNumber.setOnClickListener(this);
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
		switch(v.getId()){
		case R.id.btnStartService:
			startService(serviceIntent);
			break;
		case R.id.btnStopService:
			stopService(serviceIntent);
			break;
		case R.id.btnBindService:
			bindService(serviceIntent, this, BIND_AUTO_CREATE);
			break;
		case R.id.btnUnbindService:
			unbindService(this);
			echoService = null;
			break;
		case R.id.btnGetCurrentNumber:
			if (echoService != null){
				System.out.println("当前获取的数字是："+echoService.getInt());
			}
			break;
			
		}
		
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		System.out.println("应用已经绑定服务");
		echoService = ((EchoService.EchoBinder)binder).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		
	}
}
