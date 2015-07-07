package liulixiang.criminalintent;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Liu Lixiang on 2015/7/7.
 */
public class CriminalIntentJsonSerializer {
    private Context mContext;
    private String mFileName;

    public CriminalIntentJsonSerializer(Context c, String f){
        mContext = c;
        mFileName = f;
    }

    public void saveCrimes(ArrayList<Crime> crimes) throws JSONException, IOException {
        JSONArray array = new JSONArray();
        for(Crime c: crimes){
            array.put(c.toJSON());
        }

        Writer writer = null;
        try{
            OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        }
        finally {
            if(writer != null)
                writer.close();
        }
    }

    public ArrayList<Crime> loadCrimes() throws IOException, JSONException {
        ArrayList<Crime> crimes = new ArrayList<Crime>();
        BufferedReader reader = null;
        try{
            //打开并且读取文件到StringBuilder
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                //去除空行
                jsonString.append(line);
            }
            //使用JSONTokener来解析JSON
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            //从JSONObjects中创建Crime数组
            for (int i = 0; i < array.length(); i++){
                crimes.add(new Crime(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            //忽略
        } finally {
            if (reader != null){
                reader.close();
            }
        }
        return crimes;
    }
}
