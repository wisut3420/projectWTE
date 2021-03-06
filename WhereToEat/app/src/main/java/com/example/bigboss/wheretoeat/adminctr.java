package com.example.bigboss.wheretoeat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class adminctr extends AppCompatActivity {

    testHelper helper;
    ListView adminfoodview;
    Button btnAdd;
    TextView btnAdminfood;

    public String name;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new testHelper(this);
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminctr);

        btnAdd = findViewById(R.id.btnAdd);


        getList();

        adminfoodview = (ListView)findViewById(R.id.adminfoodview);
        /*String[] restSplit = restArray.split("__");

        adminfoodview = (ListView)findViewById(R.id.adminfoodview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_adminfoodview, R.id.btnAdminfood, restSplit);
        adminfoodview.setAdapter(arrayAdapter);*/


        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminctr.this, add.class);
                startActivity(intent);
                finish();
            }
        });

        adminfoodview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.btnAdminfood);
                String text = textView.getText().toString();

                Intent intent = new Intent(adminctr.this,edit.class);
                intent.putExtra("name",text);
                startActivity(intent);
                finish();
            }
        });

        adminfoodview.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    public void backLogin(View view){
        Intent intent = new Intent(adminctr.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void setList(String restArray){
        String[] restSplit = restArray.split("__");

        adminfoodview = (ListView)findViewById(R.id.adminfoodview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_adminfoodview, R.id.btnAdminfood, restSplit);
        adminfoodview.setAdapter(arrayAdapter);
    }

    public void getList(){

        AndroidNetworking.get(testHelper.URL+"/eatwhere/getRest.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;
                        try
                        {
                            name="";
                            for(int i=0;i<response.length();i++)
                            {
                                jo=response.getJSONObject(i);

                                String nameGet=jo.getString("name");

                                name=name+nameGet+"__";


                            }


                            setList(name);


                            //SET TO SPINNER

                        }catch (JSONException e)
                        {
                            Toast.makeText(getApplicationContext(), "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(getApplicationContext(), "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();
                    }

                });
    }

}
