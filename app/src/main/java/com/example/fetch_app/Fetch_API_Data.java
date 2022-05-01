package com.example.fetch_app;

import android.content.Context;
import android.os.Build;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
                    JSONObject city_info = response.getJSONObject(Integer.parseInt(cityName));
                    cityId = city_info.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    }


    //    new code for creating model and showing full index information
    public interface VolleyResponseListener2 {
        void onError(String message);

//        callback is a list of models
        void onResponse(List<FetchDataModel> fetchAppList);
    }
    public void getArrayIndexById(String thisid, VolleyResponseListener2 volleyResponseListener2) {
        List<FetchDataModel> fetchAppList = new ArrayList<>();

        String url = COM_HIRING_JSON;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(context, "user input: " + thisid, Toast.LENGTH_SHORT).show();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        FetchDataModel dataModel = new FetchDataModel();
                        JSONObject justOneIndexFetchAPI = (JSONObject) response.get(i);
                        if(thisid.equals("" + justOneIndexFetchAPI.getInt("listId")) && !"".equals(justOneIndexFetchAPI.getString("name")) && !"null".equals(justOneIndexFetchAPI.getString("name"))) {
                                dataModel.setId(justOneIndexFetchAPI.getInt("id"));
                                dataModel.setListId(justOneIndexFetchAPI.getInt("listId"));
                                dataModel.setName(justOneIndexFetchAPI.getString("name"));
                                fetchAppList.add(dataModel);
//                                sorting here using Comparable in FetchDataModel, must pass argument, and @override method sorts by id, which is the same as name
                            Collections.sort(fetchAppList);

                            volleyResponseListener2.onResponse(fetchAppList);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, thisid, Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
