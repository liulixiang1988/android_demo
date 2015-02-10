package liulx.L06Intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.btnStartAty1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //显示创建
//                Intent i = new Intent();
//                i.setComponent(new ComponentName("liulx.L06Intent", "liulx.L06Intent.Aty1"));

                //隐式创建
                Intent i = new Intent("liulx.L06Intent.intent.action.Aty1");
                startActivity(i);
            }
        });

        findViewById(R.id.btnOpenImage).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                File f = new File("/storage/sdcard0/001.jpg");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.fromFile(f), "image/*");
                startActivity(i);
            }
        });

        findViewById(R.id.btnDial).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("tel:10086"));
                startActivity(i);
            }
        });

        findViewById(R.id.btnOpenBaidu).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
                startActivity(i);
            }
        });
    }
}
