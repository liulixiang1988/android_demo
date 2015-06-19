package liulixiang.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by liulixiang on 15/6/19.
 */
public class CrimeListActivity extends  SingleFragmentActivity {

    @Override
    protected Fragment createFragement() {
        return new CrimeListFragment();
    }
}
