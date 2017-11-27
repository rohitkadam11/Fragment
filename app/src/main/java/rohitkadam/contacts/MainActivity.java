package rohitkadam.contacts;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ContactAdapter baseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
        permissionCheck();

        baseAdapter=new ContactAdapter(MainActivity.this);
        listView.setAdapter(baseAdapter);


        ArrayList<Contact> contacts=readAllContacts();
        for(int i=0;i<contacts.size();i++){
            baseAdapter.addContact(contacts.get(i));
        }

        baseAdapter.notifyDataSetChanged();
        contacts=null;
    }

    public ArrayList<Contact> readAllContacts(){
        ArrayList<Contact> list=new ArrayList<>();
        Uri uri=ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        ContentResolver contentResolver=getContentResolver();

        Cursor cursor=contentResolver.query(uri,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Contact contact=new Contact();
            contact.setName(name);
            contact.setNumber(number);

            list.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public void permissionCheck(){
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            String[] permission={Manifest.permission.READ_CONTACTS};
            ActivityCompat.requestPermissions(MainActivity.this,permission,123);
            return;
        }

    }
}
