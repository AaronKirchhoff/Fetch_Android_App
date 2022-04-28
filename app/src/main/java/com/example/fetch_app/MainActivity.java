package com.example.fetch_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
//import android.widget.ArrayAdapter;import android.widget.ListView;


public class MainActivity extends AppCompatActivity {


    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//thisIsMyList is the name of my listview im using
        listView=(ListView)findViewById(R.id.thisIsMyList);

//        arrayList is the name of my new array here, but now need to fetch api and show info
//        1. fetch list api and show all data
//        2. sort list by group ID
//        3 then within each group, each name must be sorted
//        4. ignore or filter out where name is blank or null
//        5. display in listview only one group id, sorted
//        6. figure out how to make the listview conditional on which groupID you want to see

        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("my");
        arrayList.add("name");
        arrayList.add("is");
        arrayList.add("earl");
        arrayList.add("my");
        arrayList.add("name");
        arrayList.add("is");
        arrayList.add("earl");
        arrayList.add("my");

//customrow is the custom default edits for how i want my text in the listview to show up, and it works, must restart emulator for it to show
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.customrow,arrayList);

        listView.setAdapter(arrayAdapter);

    }
}