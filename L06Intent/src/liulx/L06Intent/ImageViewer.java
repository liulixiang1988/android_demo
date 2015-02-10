package liulx.L06Intent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Administrator on 2014/8/27.
 */
public class ImageViewer extends Activity {
    ImageView iv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv = new ImageView(this);
        setContentView(iv);

        iv.setImageURI(getIntent().getData());
    }
}