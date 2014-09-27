package liulx.blogreader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.conn.ConnectTimeoutException;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainListActivity extends ListActivity {
	
	protected String[] names ;
	public static final int NUMBER_OF_POSTS = 20;
	public static final String TAG = MainListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        
        if (isNetworkAvailable()) {
			GetBlogPostTasks getBlogPostTask = new GetBlogPostTasks();
			getBlogPostTask.execute();
		}else{
			Toast.makeText(this, "网络连接不可用", Toast.LENGTH_LONG).show();
		}
    }

    public boolean isNetworkAvailable(){
    	ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkinfo = manager.getActiveNetworkInfo();
    	
    	boolean isAvailable = false;
    	if (networkinfo != null && networkinfo.isConnected()){
    		isAvailable = true;
    	}
    	return isAvailable;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_list, menu);
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
    
    private class GetBlogPostTasks extends AsyncTask<Object, Void, String>{

		@Override
		protected String doInBackground(Object... params) {
			int responseCode = 1;
	        try{
	        	URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count="+NUMBER_OF_POSTS);
	        	HttpURLConnection connection = (HttpURLConnection) blogFeedUrl.openConnection();
	        	connection.connect();
	        	
	        	responseCode = connection.getResponseCode();
	        	Log.i(TAG, "Response Code:"+responseCode);
	        	
	        } catch(MalformedURLException e){
	        	Log.e(TAG, "Eception Caught:", e);
	        } catch (IOException e) {
	        	Log.e(TAG, "Eception Caught:", e);
			} catch (Exception e){
				Log.e(TAG, "Eception Caught:", e);
			}
	        
	        return "Response Status"+responseCode;
		}
    	
    }
}
