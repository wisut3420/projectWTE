package com.example.bigboss.wheretoeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    myDbAdapter helper;
    EditText Userame,Pass,rePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Userame= (EditText) findViewById(R.id.inputUname);
        Pass= (EditText) findViewById(R.id.inputPass);
        rePass= (EditText) findViewById(R.id.inputConfPass);
        helper = new myDbAdapter(this);
    }

    public void viewdata(View view)
    {
        String data = helper.getData();
        Message.message(this,data);
    }

    public void addUser(View view)
    {
        String t1 = Userame.getText().toString();
        String t2 = Pass.getText().toString();
        String t3 = rePass.getText().toString();
        if(t1.isEmpty() || t2.isEmpty() || t3.isEmpty())
        {
            Message.message(getApplicationContext(),"Please Enter Username and Password");
        }
        else if(!(t2.equals(t3))){
            Message.message(getApplicationContext(),"Password not equal");
        }
        else
        {
            long id = helper.register(t1,t2);
            if(id<=0)
            {
                Message.message(getApplicationContext(),"Register Unsuccessful");
            } else
            {
                Message.message(getApplicationContext(),"Register Successful");

                Intent intent = new Intent(Register.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        }
    }
}


