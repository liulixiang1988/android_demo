package liulx.L04BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2014/8/21.
 */
public class MyBroadCast2 extends BroadcastReceiver {

    //Action格式：
    public static final String ACTION="liulx.L04BroadCast.intent.action.MyBroadCast2";

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("接收数据：" + intent.getStringExtra("txt"));
    }
}
