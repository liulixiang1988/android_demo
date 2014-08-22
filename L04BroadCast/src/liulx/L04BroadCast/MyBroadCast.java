package liulx.L04BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2014/8/21.
 */
public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Receive Message:" + intent.getStringExtra("txt"));
    }
}
