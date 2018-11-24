package com.example.bigboss.wheretoeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnLogin,btnRegis;
    myDbAdapter helper;
    EditText Userame,Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegis = findViewById(R.id.btnRegis);

        btnRegis.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void login(View view)
    {
        Userame= (EditText) findViewById(R.id.wuname);
        Pass= (EditText) findViewById(R.id.wpass);
        String username = Userame.getText().toString();
        String password = Pass.getText().toString();
        String data = helper.login(username,password);

        if(username.equals("") && password.equals("")){
            Message.message(getApplicationContext(),"Welcome home, My master");
            Intent intent = new Intent(MainActivity.this,adminctr.class);
            startActivity(intent);
            finish();
        } //admin login
        else if(data.equals("0")){
            Message.message(getApplicationContext(),"Login Successful");
            Intent intent = new Intent(MainActivity.this,choosefood.class);
            startActivity(intent);
            finish();
        }
        else if(data.equals("1")){
            Message.message(getApplicationContext(),"Login Unsuccessful\n(Password Incorrect)");
            Pass.setText("");
        }
        else if(data.equals("2")){
            Message.message(getApplicationContext(),"Login Unsuccessful\n(No Username... I suppose.)");
        }
    }


}
