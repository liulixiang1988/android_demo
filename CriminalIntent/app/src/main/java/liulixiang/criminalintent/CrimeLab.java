package liulixiang.criminalintent;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by liulixiang on 15/6/19.
 */
public class CrimeLab {
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";

    private ArrayList<Crime> crimes;
    private CriminalIntentJsonSerializer mSerializer;

    private static CrimeLab crimeLab;
    private Context appContext;

    private CrimeLab(Context appContext){
        this.appContext = appContext;
        mSerializer = new CriminalIntentJsonSerializer(appContext, FILENAME);
        try{
            crimes = mSerializer.loadCrimes();
        } catch (Exception e) {
            crimes = new ArrayList<>();
            Log.e(TAG, "Error loading crimes:", e);
        }
    }

    public static CrimeLab get(Context c) {
        if (crimeLab == null) {
            crimeLab = new CrimeLab(c.getApplicationContext());
        }
        return crimeLab;
    }

    //add crime
    public void addCrime(Crime c){
        crimes.add(c);
    }

    public ArrayList<Crime> getCrimes(){
        return crimes;
    }

    public Crime getCrime(UUID id){
        for(Crime c : crimes){
            if (c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public boolean saveCrimes(){
        try{
            mSerializer.saveCrimes(crimes);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes", e);
            return false;
        }
    }

    public void deleteCrime(Crime c){
        crimes.remove(c);
    }
}
