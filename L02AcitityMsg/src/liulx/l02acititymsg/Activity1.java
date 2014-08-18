package liulx.l02acititymsg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity1 extends Activity implements OnClickListener {

	private TextView tvMsg2;
	private Button btnBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
		tvMsg2 = (TextView) findViewById(R.id.tvMsg2);
		tvMsg2.setText(getIntent().getStringExtra("msg"));
		
		btnBack = (Button)findViewById(R.id.btn_back);
		btnBack.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		i.putExtra("msg_back", "回到主界面");
		setResult(0, i);
		finish();
		
	}
}
