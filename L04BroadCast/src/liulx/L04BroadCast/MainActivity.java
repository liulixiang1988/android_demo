package liulx.L04BroadCast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btnSendBroadCast, btnRegisterBC, btnUnregisterBC, btnSendBroadCast2;
    private final MyBroadCast2 mybc = new MyBroadCast2() ;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnSendBroadCast = (Button) findViewById(R.id.btnSendBroadCast);
        btnSendBroadCast.setOnClickListener(this);

        btnRegisterBC = (Button) findViewById(R.id.btnRegisterBC);
        btnRegisterBC.setOnClickListener(this);

        btnUnregisterBC = (Button) findViewById(R.id.btnUnregisterBC);
        btnUnregisterBC.setOnClickListener(this);

        btnSendBroadCast2 = (Button) findViewById(R.id.btnSendBC2);
        btnSendBroadCast2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSendBroadCast:
                btnSendBroadCast_click();
                break;
            case R.id.btnRegisterBC:
                btnRegisterBC_click();
                break;
            case R.id.btnUnregisterBC:
                btnUnregisterBC_click();
                break;
            case R.id.btnSendBC2:
                btnSendBroadCast2_click();
                break;
        }
    }

    private void btnSendBroadCast_click(){
        Intent i = new Intent(this, MyBroadCast.class);
        i.putExtra("txt", "理想，你好");
        sendBroadcast(i);
    }

    private void btnRegisterBC_click(){
        registerReceiver(mybc, new IntentFilter(MyBroadCast2.ACTION));
    }

    private void btnUnregisterBC_click(){
        unregisterReceiver(mybc);
    }

    private void btnSendBroadCast2_click(){
        Intent i = new Intent(MyBroadCast2.ACTION);
        i.putExtra("txt", "Hello, 理想");
        sendBroadcast(i);
    }
}
