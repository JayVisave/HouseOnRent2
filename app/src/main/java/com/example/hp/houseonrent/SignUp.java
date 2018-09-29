package com.example.hp.houseonrent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements TextWatcher {
    String Username,Password,Aadhar,Address,Contact,Type;
    EditText username,password,confirm,aadhar;
    EditText contact,address;
    Button done;
    RadioButton owner,rentee;
    RadioGroup rg;
    int b=0;
    Toast p,u,a,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        owner = (RadioButton) findViewById(R.id.Owner);
        rentee =(RadioButton) findViewById(R.id.Rentee);
        username = (EditText) findViewById(R.id.Uname);
        password = (EditText) findViewById(R.id.Pass);
        confirm = (EditText) findViewById(R.id.Conf);
        aadhar = (EditText) findViewById(R.id.Id);
        contact = (EditText) findViewById(R.id.Phone);
        address = (EditText) findViewById(R.id.Addr);
        done = (Button) findViewById(R.id.SignUp);
        contact.addTextChangedListener(this);
        address.addTextChangedListener(this);
        username.addTextChangedListener(this);
        password.addTextChangedListener(this);
        confirm.addTextChangedListener(this);
        aadhar.addTextChangedListener(this);
        p=Toast.makeText(this, "Password In Both Fields Does Not Match!", Toast.LENGTH_SHORT);
        u=Toast.makeText(this,"Invalid Username",Toast.LENGTH_SHORT);
        a=Toast.makeText(this,"Invalid Aadhar Number",Toast.LENGTH_SHORT);
        c=Toast.makeText(this,"Invalid Contact Number",Toast.LENGTH_SHORT);
    }



    @Override
    protected void onResume() {
        super.onResume();
        b=0;
        if(username.length() == 0  && password.length() == 0 && confirm.length() == 0 && aadhar.length() == 0 && contact.length() == 0  && address.length() == 0)
        {

            done.setEnabled(false);
        }


    }

    public void Done(View v)
    {
        Username = username.getText().toString();
        Password = password.getText().toString();
        Aadhar = aadhar.getText().toString();
        Address = address.getText().toString();
        Contact = contact.getText().toString();
        if(owner.isChecked())
        { Type = owner.toString();}
        else if(rentee.isChecked())
        { Type = rentee.toString();}
        Intent i  = new Intent(this,Owner.class);
        startActivity(i);


   }
   public void inputCheck()
   {    b=0;
       if(username.length() == 0  && password.length() == 0 && confirm.length() == 0 && aadhar.length() == 0 && contact.length() == 0   && address.length() == 0)
   {

       done.setEnabled(false);
   }
       if(username.getText().toString().trim().equalsIgnoreCase("")  && password.getText().toString().trim().equalsIgnoreCase("") && confirm.getText().toString().trim().equalsIgnoreCase("") && aadhar.getText().toString().trim().equalsIgnoreCase("") && contact.getText().toString().trim().equalsIgnoreCase("")  && address.getText().toString().trim().equalsIgnoreCase(""))
       {
           if(username.getText().toString().matches("[a-zA-Z0-9_@]*")  )
                { u.cancel();b+=1;}
           else
           { u.show();  done.setEnabled(false); }
           if(password.getText().toString().equals(confirm.getText().toString()) && password.getText().toString().matches("[a-zA-Z0-9_@]*"))
           { p.cancel(); b+=1; }
            else {
               p.show();
               done.setEnabled(false);}

           if(aadhar.length() == 12)
           {   a.cancel();
                b+=1;}
           else
           {
               a.show();done.setEnabled(false);
           }
           if(contact.length() == 10)
           {
               c.cancel();
               b+=1;
           }
           else
           {
               c.show();done.setEnabled(false);
           }
       }
       /*if( )
       {

           done.setEnabled(false);
       }
       if( )
       {

           done.setEnabled(true);

       }*/
       if(b==4)
       {
           done.setEnabled(true);
       }

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

            inputCheck();
    }

}
