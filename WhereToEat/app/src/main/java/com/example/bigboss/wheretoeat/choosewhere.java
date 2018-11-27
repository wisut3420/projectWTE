package com.example.bigboss.wheretoeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class choosewhere extends AppCompatActivity {

    // Array of strings...
    ListView foodview;
    String[] listName;
    String allName;

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosewhere);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            allName = bundle.getString("result");
            listName = allName.split("__");
        }

        foodview = (ListView)findViewById(R.id.foodview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, listName);
        foodview.setAdapter(arrayAdapter);

        foodview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.textView);
                String text = textView.getText().toString();

                Intent intent = new Intent(choosewhere.this,where.class);
                intent.putExtra("name",text);
                startActivity(intent);
                finish();
            }
        });


        foodview.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    public void noWhere(View view){
        int rnd = new Random().nextInt(listName.length);
        String rndResult = listName[rnd];

        Intent intent = new Intent(choosewhere.this,where.class);
        intent.putExtra("name",rndResult);
        startActivity(intent);
        finish();
    }

}
