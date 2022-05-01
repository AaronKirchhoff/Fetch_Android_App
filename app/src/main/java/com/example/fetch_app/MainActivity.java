package com.example.fetch_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    //        capture button, listview, and spinner names here, set it as variable, these are class member variables
    Button btn_get_byTheID;
    ListView thisIsMyList;
    EditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        assign class member variables to a value
        btn_get_byTheID = findViewById(R.id.get_byListId);
        thisIsMyList = findViewById(R.id.thisIsMyList);
        myEditText = findViewById(R.id.myEditText);

//        create button listener here, so we click the button, then this happens...
        btn_get_byTheID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new class instance,
                Fetch_API_Data fetch_api_data = new Fetch_API_Data(MainActivity.this);

                fetch_api_data.getArrayIndexById(myEditText.getText().toString(), new Fetch_API_Data.VolleyResponseListener2() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Did not work", Toast.LENGTH_SHORT).show();
                        
                    }

                    @Override
                    public void onResponse(List<FetchDataModel> fetchDataModelList) {
//                        time to put things into our listview
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, fetchDataModelList);
                        thisIsMyList.setAdapter(arrayAdapter);

                    }
                });
            }
        });
    }

}