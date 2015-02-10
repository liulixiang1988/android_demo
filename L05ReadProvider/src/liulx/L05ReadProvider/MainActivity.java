package liulx.L05ReadProvider;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        while(c.moveToNext()){
            System.out.println(">>>>>>>" + c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)) +
                    "\t" + c.getString(c.getColumnIndex(ContactsContract.Contacts.PHONETIC_NAME)));
        }
    }
}
