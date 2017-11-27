package rohitkadam.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 170840521012 on 27-11-2017.
 */

public class ContactAdapter extends BaseAdapter {
    ArrayList<Contact> contacts=new ArrayList<>();
    Context context;

    public ContactAdapter(Context context){
        this.context=context;
        contacts= new ArrayList<>();
    }
    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View listView=layoutInflater.inflate(R.layout.row_item,viewGroup,false);

        TextView textViewName=listView.findViewById(R.id.textViewName);
        TextView textViewNumber=listView.findViewById(R.id.textViewNumber);

        Contact contact=contacts.get(position);
        textViewName.setText(contact.getName());
        textViewNumber.setText(contact.getNumber());

        return listView;
    }

    public void addContact(Contact newContact){
        contacts.add(newContact);

    }
}
