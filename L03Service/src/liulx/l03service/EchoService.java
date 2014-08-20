package liulx.l03service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class EchoService extends Service {

	public class EchoBinder extends Binder{
		
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
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		System.out.println("Service Destroy");
		super.onDestroy();
	}

}
