package com.codexpedia.memoryleaksdemo;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListActivity;
import android.widget.Toast;

import com.codexpedia.memoryleaksdemo.samples.AsyncTaskLeak;
import com.codexpedia.memoryleaksdemo.samples.HandlerRunnableOne;
import com.codexpedia.memoryleaksdemo.samples.HandlerRunnableTwo;
import com.codexpedia.memoryleaksdemo.samples.ListenerLeak;

public class MainActivity extends ListActivity {

    private List<String> listValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listValues = new ArrayList<String>();
        listValues.add("HandlerRunnableOne");
        listValues.add("HandlerRunnableTwo");
        listValues.add("AsyncTaskLeak");
        listValues.add("ListenerLeak");

        // initiate the listadapter
        ArrayAdapter<String> myAdapter = new ArrayAdapter <String>(this,
                R.layout.row_layout, R.id.listText, listValues);

        // assign the list adapter
        setListAdapter(myAdapter);

    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        String selectedItem = (String) getListView().getItemAtPosition(position);

        Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show();
        Intent intent = null;
        switch (selectedItem) {
            case "HandlerRunnableOne":
                intent = new Intent(this, HandlerRunnableOne.class);
                break;
            case "HandlerRunnableTwo":
                intent = new Intent(this, HandlerRunnableTwo.class);
                break;
            case "AsyncTaskLeak":
                intent = new Intent(this, AsyncTaskLeak.class);
                break;
            case "ListenerLeak":
                intent = new Intent(this, ListenerLeak.class);
                break;
            default:
                break;
        }
        if (intent != null)
            startActivity(intent);
    }

}
