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

import java.util.List;

public class adminctr extends AppCompatActivity {

    myDbAdapter helper;
    ListView adminfoodview;
    Button btnAdminfood, btnAdd;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminctr);

        btnAdd = findViewById(R.id.btnAdd);


        String restArray = helper.showRestaurant();
        String[] restSplit = restArray.split(" ");

        adminfoodview = (ListView)findViewById(R.id.adminfoodview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_adminfoodview, R.id.btnAdminfood, restSplit);
        adminfoodview.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(adminctr.this,add.class);
                startActivity(intent);
                finish();
            }
        });

        adminfoodview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id=id+1;
                Intent intent = new Intent(adminctr.this,edit.class);
                intent.putExtra("id",(int) id);
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
}
