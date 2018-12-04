package com.example.bigboss.wheretoeat;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class choosefood extends AppCompatActivity {

    myDbAdapter helper;
    CheckBox shabu, bbq, alacarte, noodle;
    String[] arrayType = new String[4];
    Context context;
    String resultToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosefood);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        shabu = (CheckBox) findViewById(R.id.shabu);
        bbq = (CheckBox) findViewById(R.id.bbq);
        alacarte = (CheckBox) findViewById(R.id.alacarte);
        noodle = (CheckBox) findViewById(R.id.noodle);
    }

    public void selected(View view) {
        arrayType[0] = null;
        arrayType[1] = null;
        arrayType[2] = null;
        arrayType[3] = null;

        if(shabu.isChecked() || bbq.isChecked() || alacarte.isChecked() || noodle.isChecked()) {
            if (shabu.isChecked()) {
                arrayType[0] = "Shabu";
            } if (bbq.isChecked()) {
                arrayType[1] = "BBQ";
            } if (alacarte.isChecked()) {
                arrayType[2] = "Order";
            } if (noodle.isChecked()) {
                arrayType[3] = "Noodle";
            }


            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                OkHttpClient client = new OkHttpClient();
                HttpUrl.Builder urlBuilder = HttpUrl.parse( testHelper.URL + "/eatwhere/choosefood.php").newBuilder();
                urlBuilder.addQueryParameter("type1", arrayType[0]);
                urlBuilder.addQueryParameter("type2", arrayType[1]);
                urlBuilder.addQueryParameter("type3", arrayType[2]);
                urlBuilder.addQueryParameter("type4", arrayType[3]);
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

                                        String allName="";

                                        String data = response.body().string();
                                        JSONArray jsonArray = new JSONArray(data);
                                        JSONObject jsonObject;

                                        for(int i=0;i<jsonArray.length();i++) {
                                            jsonObject = jsonArray.getJSONObject(i);
                                            allName = allName + jsonObject.getString("name") + "__";
                                        }

                                        Intent intent = new Intent(choosefood.this, choosewhere.class);
                                        intent.putExtra("result", allName);
                                        startActivity(intent);
                                        finish();

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

        } else {
            Message.message(getApplicationContext(),"PLEASE SELECT BEFORE PRESS THIS BUTTONNNNNNNNNNNNNNNNNN");
        }
    }

    public void noSelected(View view) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse( testHelper.URL + "/eatwhere/choosefood.php").newBuilder();
            urlBuilder.addQueryParameter("type1", "Shabu");
            urlBuilder.addQueryParameter("type2", "BBQ");
            urlBuilder.addQueryParameter("type3", "Order");
            urlBuilder.addQueryParameter("type4", "Noodle");
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

                                    String allName="";

                                    String data = response.body().string();
                                    JSONArray jsonArray = new JSONArray(data);
                                    JSONObject jsonObject;

                                    for(int i=0;i<jsonArray.length();i++) {
                                        jsonObject = jsonArray.getJSONObject(i);
                                        allName = allName + jsonObject.getString("name") + "__";
                                    }

                                    Intent intent = new Intent(choosefood.this, choosewhere.class);
                                    intent.putExtra("result", allName);
                                    startActivity(intent);
                                    finish();

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
