package com.example.hp.houseonrent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements TextWatcher {
    public EditText uname,password;
    public CheckBox me;
    public Intent goOwner;
    public Button Login;
    private CameraManager cameraManager;
    private CameraDevice cameraDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_login);
        me = (CheckBox) findViewById(R.id.Me);
        uname = (EditText) findViewById(R.id.Login_Uname);
        password = (EditText) findViewById(R.id.Login_Pass);
        Login = (Button) findViewById(R.id.login_button);
        Login.setEnabled(false);
        uname.addTextChangedListener(this);
        password.addTextChangedListener(this);


SharedPreferences preferences = getSharedPreferences("HoR_Login_Data", MODE_PRIVATE);
        if (preferences.contains("Username"))
        {
           uname.setText(preferences.getString("Username",""));
        }
if (preferences.contains("Username") && preferences.contains("Password"))
  {
     Intent i = new Intent(this, Owner.class);
      startActivity(i);
 }



    }

    public void onSignUp(View v) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    public void onLogin(View v) {

         goOwner = new Intent(this, Owner.class);
        if(me.isChecked())
        {
            SharedPreferences.Editor editor = getSharedPreferences("HoR_Login_Data", MODE_PRIVATE).edit();
            editor.putString("Username",uname.getText().toString());
            editor.putString("Password",password.getText().toString());
            editor.apply();

        }
        if(!(me.isChecked()))
        {goOwner.putExtra("Checkbox","No");}
        startActivity(goOwner);


    }
    public void onTest(View v){
        SharedPreferences preferences = getSharedPreferences("HoR_Login_Data",MODE_PRIVATE);
        String SP_Name= preferences.getString("Username","Not Found");
        String SP_Pass = preferences.getString("Password","NULL");
        Toast.makeText(this,SP_Name,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,SP_Pass,Toast.LENGTH_SHORT).show();
    }
    public void onClear(View v){
        SharedPreferences.Editor editor = getSharedPreferences("HoR_Login_Data", MODE_PRIVATE).edit();
        editor.remove("Username");
        editor.remove("Password");

    }

    @Override
    public void onBackPressed() {
        ShowAlert s = new ShowAlert();
        s.Alert(this);



    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if(uname.getText().toString().length()==0 || password.getText().toString().length()==0)
        {   if(uname.getText().toString().length()==0)
                {uname.setError("Empty Username field.");
                    Login.setEnabled(false);
                  }
                else
                {password.setError("Empty Password field."); Login.setEnabled(false);}

        }
        else {if(!(uname.getText().toString().matches("[a-zA-Z0-9_@]*")))
                {uname.setError("Invalid value for Username.");Login.setEnabled(false);}
                else if (!(password.getText().toString().matches("[a-zA-Z0-9_@]*"))){password.setError("Invalid value for Password."); Login.setEnabled(false);}
                else {
                Login.setEnabled(true);
                }
        }

    }
    public void Test(View v) {
        Intent i = new Intent(this,Camera_HoR.class);
        startActivity(i);

    }
}
