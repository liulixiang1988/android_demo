package liulixiang.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;


public class CriminalActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragement() {
        return new CrimeFragment();
    }

}
