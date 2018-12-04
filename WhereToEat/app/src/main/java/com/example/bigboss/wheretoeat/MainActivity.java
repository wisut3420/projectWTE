package com.example.bigboss.wheretoeat;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnRegis;
    myDbAdapter helper;
    EditText Userame, Pass;
    myDbAdapter mySQLConnect;

    String i, txtPID, txtPass, st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*List<String> b = mySQLConnect.getData();
        StringBuilder strB = new StringBuilder();
        for(String f : b){
            strB.append(f);
            strB.append(",");
        }
        String o = String.valueOf(strB);*/

        helper = new myDbAdapter(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegis = findViewById(R.id.btnRegis);

        Userame = (EditText) findViewById(R.id.wuname);
        Pass = (EditText) findViewById(R.id.wpass);


        mySQLConnect = new myDbAdapter(MainActivity.this);
        mySQLConnect.getData();

        btnRegis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void login(View view) {

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse( testHelper.URL + "/eatwhere/login.php").newBuilder();
            urlBuilder.addQueryParameter("Username", Userame.getText().toString());
            urlBuilder.addQueryParameter("Password", Pass.getText().toString());
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

                                    if (data.equals("Success")) {
                                        Message.message(getApplicationContext(),"Login Successful");

                                        Intent intent = new Intent(MainActivity.this, choosefood.class);
                                        startActivity(intent);

                                    } else if (data.equals("admin")) {
                                        Intent intent = new Intent(MainActivity.this, adminctr.class);
                                        startActivity(intent);

                                    } else {
                                        Message.message(getApplicationContext(),"Login Unsuccessful");
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
