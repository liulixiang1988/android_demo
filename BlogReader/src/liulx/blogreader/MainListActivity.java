package liulx.blogreader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	protected JSONObject blogData;

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
    
    public void updateList(){
    	if(blogData == null){
    		// TODO: handle error
    		
    	} else {
    		try {
				Log.d(TAG, blogData.toString(2));
			} catch (JSONException e) {
				Log.e(TAG, "Exception caught", e);
			}
    	}
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
    
    private class GetBlogPostTasks extends AsyncTask<Object, Void, JSONObject>{

		@Override
		protected JSONObject doInBackground(Object... params) {
			int responseCode = 1;
			JSONObject jsonResponse = null;
	        try{
	        	URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count="+NUMBER_OF_POSTS);
	        	HttpURLConnection connection = (HttpURLConnection) blogFeedUrl.openConnection();
	        	connection.connect();
	        	
	        	responseCode = connection.getResponseCode();
	        	
	        	if (responseCode == HttpURLConnection.HTTP_OK){
	        		InputStream inputStream = connection.getInputStream();
	        		Reader reader = new InputStreamReader(inputStream);
	        		int contentLength = connection.getContentLength();
	        		char[] charArray = new char[contentLength];
	        		reader.read(charArray);
	        		String responseData = new String(charArray);
	        		
	        		jsonResponse = new JSONObject(responseData);
	        		String status = jsonResponse.getString("status");
	        		Log.v(TAG, status);
	        		
	        		JSONArray jsonPosts = jsonResponse.getJSONArray("posts");
	        		
	        		for (int i = 0; i < jsonPosts.length(); i++){
	        			JSONObject jsonPost = jsonPosts.getJSONObject(i);
	        			String title = jsonPost.getString("title");
	        			Log.i(TAG, "Post " + i + ": " + title);
	        		}
	        	} else {
		        	Log.i(TAG, "Unsuccessful HTTP Response Code: "+responseCode);
	        	}
	        } catch(MalformedURLException e){
	        	Log.e(TAG, "Eception Caught:", e);
	        } catch (IOException e) {
	        	Log.e(TAG, "Eception Caught:", e);
			} catch (Exception e){
				Log.e(TAG, "Eception Caught:", e);
			}
	        
	        return jsonResponse;
		}
		
	   @Override
		protected void onPostExecute(JSONObject result) {
			blogData = result;
			updateList();
		}
    }
}
