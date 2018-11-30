package com.example.bigboss.wheretoeat;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class testHelper {

    public final Activity main;
    private List<String> list;
    private String idReturn;
    Context context;
    private JSONArray result;
    testHelper helper;
    private StringBuffer buffer;
    private String URL = "http://192.168.88.133/", GET_URL = "eatwhere/get_user.php", Register_URL = "eatwhere/register.php";



    public testHelper(Activity mainA) {
        main = mainA;
        list = new ArrayList<String>();
    }

    public List<String> getData() {
        String url = URL + GET_URL;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
                Toast.makeText(main, list.get(0), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
        requestQueue.add(stringRequest);

        return list;
    }

    public void showJSON(String response) {
        String show = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject collectData = result.getJSONObject(i);
                show = collectData.getString("Username");
                list.add(show);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }


    public int register(String username, String password) {
        StrictMode.enableDefaults();
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            nameValuePairs.add(new BasicNameValuePair("Username", username));
            nameValuePairs.add(new BasicNameValuePair("Password", password));
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(URL + Register_URL);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpClient.execute(httpPost);

            Toast.makeText(main, "Completed.", Toast.LENGTH_LONG).show();
            return 1;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*public String login(String username, String password) {

    }*/

    public long addRestaurant(String name, String type, String time, String recommend, String price, String lat, String longt) {

        StrictMode.enableDefaults();
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            nameValuePairs.add(new BasicNameValuePair("name", name));
            nameValuePairs.add(new BasicNameValuePair("type", type));
            nameValuePairs.add(new BasicNameValuePair("time", time));
            nameValuePairs.add(new BasicNameValuePair("recommend", recommend));
            nameValuePairs.add(new BasicNameValuePair("price", price));
            nameValuePairs.add(new BasicNameValuePair("lat", lat));
            nameValuePairs.add(new BasicNameValuePair("longt", longt));
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(URL +"eatwhere/AddRestaurant.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpClient.execute(httpPost);

            Toast.makeText(main, "Completed.", Toast.LENGTH_LONG).show();
            return 1;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public String showRestaurant() {

            String url = URL + "eatwhere/getRest.php";

            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    buffer = new StringBuffer();

                    String show = "";

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray result = jsonObject.getJSONArray("result");

                        for (int i = 0; i < result.length(); i++) {
                            JSONObject collectData = result.getJSONObject(i);
                            show = collectData.getString("name");
                            list.add(show);
                            buffer.append(show+"__");
                        }
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }


                    Toast.makeText(main, list.get(0), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(main, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
            requestQueue.add(stringRequest);

            return buffer.toString();

        }




}
