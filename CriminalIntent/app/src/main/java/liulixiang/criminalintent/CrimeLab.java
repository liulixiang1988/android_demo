package liulixiang.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by liulixiang on 15/6/19.
 */
public class CrimeLab {
    private ArrayList<Crime> crimes;
    private static CrimeLab crimeLab;
    private Context appContext;

    private CrimeLab(Context appContext){
        this.appContext = appContext;
        crimes = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            Crime c = new Crime();
            c.setTitle("Crime #"+i);
            c.setSolved(i % 2 == 0);
            crimes.add(c);
        }
    }

    public static CrimeLab get(Context c) {
        if (crimeLab == null) {
            crimeLab = new CrimeLab(c.getApplicationContext());
        }
        return crimeLab;
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
}
