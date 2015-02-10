package liulx.l03service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class EchoService extends Service {

	public class EchoBinder extends Binder{
		public EchoService getService(){
			return EchoService.this;
		}
	}
	
	public final EchoBinder echoBinder = new EchoBinder();
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("服务-绑定了服务");
		return echoBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("服务-取消服务绑定");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		System.out.println("Service Create");
		startTimer();
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		System.out.println("Service Destroy");
		stopTimer();
		super.onDestroy();
	}
	
	private int i = 0;
	
	public int getInt(){
		return i;
	}
	public void startTimer(){
		if (timer == null){
			timer = new Timer();
			timerTask = new TimerTask() {
				
				@Override
				public void run() {
					i++;
					System.out.println(i);
					
				}
			};
			
			timer.schedule(timerTask, 1000, 1000);
		}
	}
	
	public void stopTimer(){
		if(timer != null){
			timerTask.cancel();
			timer.cancel();
			timer = null;
			timerTask = null;
		}
	}
	private Timer timer;
	private TimerTask timerTask;

}
