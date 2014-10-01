package liulx.blogreader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainListActivity extends ListActivity {
	
	protected String[] blogPostTitles ;
	public static final int NUMBER_OF_POSTS = 20;
	public static final String TAG = MainListActivity.class.getSimpleName();
	protected JSONObject blogData;
	protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        
        
        if (isNetworkAvailable()) {
        	progressBar.setVisibility(View.VISIBLE);
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
    
    public void handleBlogResponse(){
    	progressBar.setVisibility(View.INVISIBLE);
    	if(blogData == null){
    		updateDispalyForError();
    	} else {
    		try {
    			JSONArray jsonPosts = blogData.getJSONArray("posts");
    			blogPostTitles = new String[jsonPosts.length()];
    			for (int i = 0; i < jsonPosts.length(); i++){
    				JSONObject post = jsonPosts.getJSONObject(i);
    				String title = post.getString("title");
    				title = Html.fromHtml(title).toString();
    				blogPostTitles[i] = title;
    			}
    			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, blogPostTitles);
    			setListAdapter(adapter);
			} catch (JSONException e) {
				Log.e(TAG, "Exception caught", e);
			}
    	}
    }

	private void updateDispalyForError() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.error_title);
		builder.setMessage(R.string.error_message);
		builder.setPositiveButton(android.R.string.ok, null);
		AlertDialog dialog = builder.create();
		dialog.show();
		
		TextView textView = (TextView) getListView().getEmptyView();
		textView.setText(getString(R.string.no_data));
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
	        	String url = "http://blog.teamtreehouse.com/api/get_recent_summary/?count="+NUMBER_OF_POSTS;
	        	Log.i(TAG, url);
	        	URL blogFeedUrl = new URL(url);
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
	        		int count = jsonResponse.getInt("count");
	        		Log.v(TAG, "status: " + status + " count:" + count);
	        		
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
			handleBlogResponse();
		}
    }
}
