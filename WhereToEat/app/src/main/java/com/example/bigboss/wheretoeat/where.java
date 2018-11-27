package com.example.bigboss.wheretoeat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class where extends AppCompatActivity {

    ImageButton btnMap;
    String showForm,nameForm;
    String[] datafromIntent;
    Context context;
    myDbAdapter helper;
    TextView restaurant,opentime,txtMenu,pricerate;
    Double lg,lt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where);

        restaurant = (TextView) findViewById(R.id.restaurant);
        opentime = (TextView) findViewById(R.id.opentime);
        txtMenu = (TextView) findViewById(R.id.recmenu);
        pricerate = (TextView) findViewById(R.id.pricerate);

        ImageButton btnMap = findViewById(R.id.btnMap);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            nameForm = bundle.getString("name");
        }

        showForm = helper.showWhere(nameForm);
        datafromIntent = showForm.split("__");

        restaurant.setText(datafromIntent[0]);
        opentime.setText(datafromIntent[1]);
        txtMenu.setText(datafromIntent[2]);
        pricerate.setText(datafromIntent[3]);
        lt = Double.valueOf(datafromIntent[4]);
        lg = Double.valueOf(datafromIntent[5]);


        btnMap.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(where.this,map.class);
                intent.putExtra("lat",lt);
                intent.putExtra("long",lg);
                intent.putExtra("name",datafromIntent[0]);
                startActivity(intent);
                finish();
            }
        });
    }


    public void backMain(View view){
        Intent intent = new Intent(where.this,choosefood.class);
        startActivity(intent);
        finish();
    }
}
