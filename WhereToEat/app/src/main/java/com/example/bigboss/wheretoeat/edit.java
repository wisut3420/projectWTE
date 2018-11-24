package com.example.bigboss.wheretoeat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle bundle = getIntent().getExtras();
        int id;
        if(bundle != null){
            id = bundle.getInt("id");
            Message.message(getApplicationContext(),String.valueOf(id));
        }
    }
}
