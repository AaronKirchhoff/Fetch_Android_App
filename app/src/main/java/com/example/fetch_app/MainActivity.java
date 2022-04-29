package com.example.fetch_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import android.widget.ArrayAdapter;import android.widget.ListView;


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

//        STATIC ******* this is the string array that's static for testing listview
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Jim");
        arrayList.add("Holden");
        arrayList.add("is");
        arrayList.add("my");
        arrayList.add("hero");

        // this is the drop down string array, thats static for testing spinner
        String[] arraySpinner = new String[] {
                "GroupId1", "GroupId2", "GroupId3", "GroupId4",
        };

//        this is my drop down menu code
        Spinner s = (Spinner) findViewById(R.id.mymenuSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

// this is the listview scroll bar code
//        STATIC***** arrayList is the string array i built above and am passing into my arrayadapter. ok, it knows to loop through it and display all info. i imagine the arrayadapter instance will need to be conditional.
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.customrow,arrayList);
        listView.setAdapter(arrayAdapter);
    }
}