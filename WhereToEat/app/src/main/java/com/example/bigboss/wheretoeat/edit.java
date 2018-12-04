package com.example.bigboss.wheretoeat;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class edit extends AppCompatActivity {

    myDbAdapter helper;
    private Spinner spinner;
    private ArrayList<String> type = new ArrayList<String>();
    EditText name,time,recommend,price,lat,longt;
    TextView id;
    int sSpinner;
    long idSpinner;
    String strSpinner;
    String idMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        id = (TextView) findViewById(R.id.idRest);
        name = (EditText) findViewById(R.id.wresname);
        time = (EditText) findViewById(R.id.wresopen);
        recommend = (EditText) findViewById(R.id.wresmenu);
        price = (EditText) findViewById(R.id.wprincerate);
        lat = (EditText) findViewById(R.id.wreslati);
        longt = (EditText) findViewById(R.id.wresloti);

        type.add("Shabu");
        type.add("BBQ");
        type.add("Order");
        type.add("Noodle");

        spinner = (Spinner) findViewById(R.id.wrestype);
        ArrayAdapter<String> menu = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, type);
        spinner.setAdapter(menu);

        Bundle bundle = getIntent().getExtras();
        String nameEdit;

        if(bundle != null){
            nameEdit = bundle.getString("name");
            Message.message(getApplicationContext(),"Receive!!:"+nameEdit);

            setShow(nameEdit);


        }
    }


    public void edit(View view){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sSpinner = position;
                idSpinner = id;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(sSpinner==0){
            strSpinner="Shabu";
        }
        else if(sSpinner==1){
            strSpinner="BBQ";
        }
        else if(sSpinner==2){
            strSpinner="Order";
        }
        else if(sSpinner==3){
            strSpinner="Noodle";
        }

        String id2 = id.getText().toString();
        String name2 = name.getText().toString();
        String time2 = time.getText().toString();
        String recommend2 = recommend.getText().toString();
        String price2 = price.getText().toString();
        String lat2 = lat.getText().toString();
        String longt2 = longt.getText().toString();
        ///

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse( testHelper.URL + "/eatwhere/editRestaurant.php").newBuilder();
            urlBuilder.addQueryParameter("id", id2);
            urlBuilder.addQueryParameter("name", name2);
            urlBuilder.addQueryParameter("time", time2);
            urlBuilder.addQueryParameter("recommend", recommend2);
            urlBuilder.addQueryParameter("price", price2);
            urlBuilder.addQueryParameter("lat", lat2);
            urlBuilder.addQueryParameter("longt", longt2);
            String url = urlBuilder.build().toString();

            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final okhttp3.Response response) throws IOException {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {

                                try {

                                    String data = response.body().string();
                                    Message.message(getApplicationContext(),data);

                                } catch (IOException e) {
                                    Message.message(getApplicationContext(),e.toString());
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

                ;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        ///
        Intent intent = new Intent(edit.this,adminctr.class);
        startActivity(intent);
        finish();
    }


    public void delete(View view){

        String id2 = id.getText().toString();
        ///

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse( testHelper.URL + "/eatwhere/deleteRestaurant.php").newBuilder();
            urlBuilder.addQueryParameter("id", id2);
            String url = urlBuilder.build().toString();

            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final okhttp3.Response response) throws IOException {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {

                                try {

                                    String data = response.body().string();
                                    Message.message(getApplicationContext(),data);

                                } catch (IOException e) {
                                    Message.message(getApplicationContext(),e.toString());
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

                ;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        ///
        Intent intent = new Intent(edit.this,adminctr.class);
        startActivity(intent);
        finish();
    }

    public void backControl(View view){
        Intent intent = new Intent(edit.this,adminctr.class);
        startActivity(intent);
        finish();
    }

    public void setShow(String nameEdit){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse( testHelper.URL + "/eatwhere/edit.php").newBuilder();
            urlBuilder.addQueryParameter("nameEdit", nameEdit);
            String url = urlBuilder.build().toString();

            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final okhttp3.Response response) throws IOException {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {

                                try {

                                    id = (TextView) findViewById(R.id.idRest);
                                    name = (EditText) findViewById(R.id.wresname);
                                    time = (EditText) findViewById(R.id.wresopen);
                                    recommend = (EditText) findViewById(R.id.wresmenu);
                                    price = (EditText) findViewById(R.id.wprincerate);
                                    lat = (EditText) findViewById(R.id.wreslati);
                                    longt = (EditText) findViewById(R.id.wresloti);

                                    String data = response.body().string();

                                    JSONArray jsonArray = new JSONArray(data);
                                    JSONObject jsonObject;

                                    jsonObject = jsonArray.getJSONObject(0);
                                    String gid = (jsonObject.getString("id"));
                                    String gname =(jsonObject.getString("name"));
                                    String gtype =(jsonObject.getString("type"));
                                    String gtime =(jsonObject.getString("time"));
                                    String grecommend =(jsonObject.getString("recommend"));
                                    String gprice =(jsonObject.getString("price"));
                                    String glat =(jsonObject.getString("lat"));
                                    String glongt =(jsonObject.getString("longt"));

                                    Message.message(getApplicationContext(),"NAME : "+gname);

                                    if(gtype.equals("Shabu")){
                                        spinner.setSelection(0);
                                    } else if(gtype.equals("BBQ")){
                                        spinner.setSelection(1);
                                    } else if(gtype.equals("Order")){
                                        spinner.setSelection(2);
                                    } else if(gtype.equals("Noodle")){
                                        spinner.setSelection(3);
                                    }

                                    try {
                                        id.setText(gid);
                                        name.setText(gname);
                                        time.setText(gtime);
                                        recommend.setText(grecommend);
                                        price.setText(gprice);
                                        lat.setText(glat);
                                        longt.setText(glongt);
                                    }catch (Exception e){
                                        Message.message(getApplicationContext(), "Something wrong...\n"+e);
                                    }

                                } catch (IOException e) {
                                    Message.message(getApplicationContext(),e.toString());
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

                ;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
