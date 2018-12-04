package com.example.bigboss.wheretoeat;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class where extends AppCompatActivity {

    String showForm, nameForm;
    String[] datafromIntent;
    Context context;
    myDbAdapter helper;
    TextView restaurant, opentime, txtMenu, pricerate;
    Double lg, lt;
    ImageButton btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where);

        restaurant = (TextView) findViewById(R.id.restaurant);
        opentime = (TextView) findViewById(R.id.opentime);
        txtMenu = (TextView) findViewById(R.id.recmenu);
        pricerate = (TextView) findViewById(R.id.pricerate);

        btnMap = findViewById(R.id.btnMap);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            nameForm = bundle.getString("name");
        }

        ///////////////

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse(testHelper.URL + "/eatwhere/where.php").newBuilder();
            urlBuilder.addQueryParameter("name", nameForm);
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
                                    btnMap = findViewById(R.id.btnMap);

                                    String data = response.body().string();
                                    final String gName;
                                    JSONArray jsonArray = new JSONArray(data);
                                    JSONObject jsonObject;

                                    jsonObject = jsonArray.getJSONObject(0);

                                    gName = jsonObject.getString("restaurant");
                                    restaurant.setText(jsonObject.getString("restaurant"));
                                    opentime.setText(jsonObject.getString("opentime"));
                                    txtMenu.setText(jsonObject.getString("txtMenu"));
                                    pricerate.setText(jsonObject.getString("pricerate"));
                                    lt = Double.valueOf(jsonObject.getString("lt"));
                                    lg = Double.valueOf(jsonObject.getString("lg"));


                                    btnMap.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(where.this, map.class);
                                            intent.putExtra("lat", lt);
                                            intent.putExtra("long", lg);
                                            intent.putExtra("name", gName);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                                } catch (IOException e) {
                                    Message.message(getApplicationContext(), e.toString());
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
        ///////////////


    }


    public void backMain(View view) {
        Intent intent = new Intent(where.this, choosefood.class);
        startActivity(intent);
        finish();
    }
}
