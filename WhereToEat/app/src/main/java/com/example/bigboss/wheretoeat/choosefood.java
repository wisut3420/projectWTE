package com.example.bigboss.wheretoeat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;

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
                arrayType[0] = "ชาบู";
            } if (bbq.isChecked()) {
                arrayType[1] = "หมูกระทะ";
            } if (alacarte.isChecked()) {
                arrayType[2] = "อาหารตามสั่ง";
            } if (noodle.isChecked()) {
                arrayType[3] = "ก๋วยเตี๋ยว";
            }
            resultToShow = helper.showRestauranttoChoose(arrayType);

            Intent intent = new Intent(choosefood.this, choosewhere.class);
            intent.putExtra("result", resultToShow);
            startActivity(intent);
            finish();
        } else {
            Message.message(getApplicationContext(),"PLEASE SELECT BEFORE PRESS THIS BUTTONNNNNNNNNNNNNNNNNN");
        }
    }

    public void noSelected(View view) {
        resultToShow = helper.showRestaurantNoSelected();

        Intent intent = new Intent(choosefood.this,choosewhere.class);
        intent.putExtra("result",resultToShow);
        startActivity(intent);
        finish();
    }

}
