package com.example.fetch_app;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;


public class Fetch_API_Data {

    public static final String COM_HIRING_JSON = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    String cityId;
    EditText myEditText;

    Context context;

    public Fetch_API_Data(Context context) {
        this.context = context;
    }

    //    part of callback with volley
    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cityId);
    }

    //    define types of methods this class will provide, getCityId will give a toast of index id value, getArrayIndexById is a model from object values
    public void getCityId(String cityName, VolleyResponseListener volleyResponseListener) {
        String url = COM_HIRING_JSON;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                cityId = "";
                try {
//                    Integer.parseInt(myEditText.getText().toString()
                    JSONObject city_info = response.getJSONObject(Integer.parseInt(cityName));
                    cityId = city_info.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(context, "Fetch id is " + cityId, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(cityId);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "error uh oh", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("uh oh something wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
//    return cityId;
    }


    //    new code for creating model and showing full index information
    public interface VolleyResponseListener2 {
        void onError(String message);

//        callback is a list of models
        void onResponse(List<FetchDataModel> fetchAppList);
    }
    public void getArrayIndexById(String thisid, VolleyResponseListener2 volleyResponseListener2) {
        List<FetchDataModel> fetchAppList = new ArrayList<>();

//        first need to get JSONArray from api
        String url = COM_HIRING_JSON;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                int justThisIndex = response.getInt(id);
//                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

//                create a list of JSONobject so we dont print whole array, just the part we want
//                try {
//                    int consolidated_fetchapp_data = response.getInt();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                to get first item in array... create empty constructor in model

                for (int i = 0; i < response.length(); i++) {
//code for filtering through array and displaying list based on user input goes here
                    try {
                        FetchDataModel dataModel = new FetchDataModel();

                        JSONObject justOneIndexFetchAPI = (JSONObject) response.get(i);
                        dataModel.setId(justOneIndexFetchAPI.getInt("id"));
                        dataModel.setListId(justOneIndexFetchAPI.getInt("listId"));
                        dataModel.setName(justOneIndexFetchAPI.getString("name"));
                        fetchAppList.add(dataModel);

                        volleyResponseListener2.onResponse(fetchAppList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
//        assign each property in array to our model

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "spaghetti", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);



    }
}
