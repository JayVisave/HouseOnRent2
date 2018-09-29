package com.example.hp.houseonrent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Owner extends AppCompatActivity {
    private static final String House_URL = "http://192.168.1.4/API/House.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager1;
    private List<OwnerItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.owner);
        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.HousePanel);
        recyclerView.setHasFixedSize(true);
       // layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new CenterScrollListener());
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        list = new ArrayList<>();
        loadHouse();
        //  Log.d("Test 1", "method out");
        //  adapter = new MyAdapter(list,this);
        //Log.d("Test 1", "created adapter");
    //   recyclerView.setLayoutManager(new LinearLayoutManager(Owner.this, LinearLayout.HORIZONTAL,false));
        //    recyclerView.setAdapter(adapter);
        //
        //   Log.d("Test 1", "set adapter");




    }
    public void onRegister(View v)
    {
        Intent i = new Intent(this,RegisterHouse.class);
        startActivity(i);
    }
    public void onLogout(View v)
    {     SharedPreferences.Editor editor = getSharedPreferences("HoR_Login_Data", MODE_PRIVATE).edit();
        Intent goOwner = getIntent();
        if(goOwner.hasExtra("Checkbox")) {
        if (goOwner.getStringExtra("Checkbox").equals("No")) {
            editor.remove("Username");
        }
        }
        editor.remove("Password");
        editor.apply();
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {

        ShowAlert s = new ShowAlert();
        s.Alert(this);

    }

    public void loadHouse()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, House_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray house = new JSONArray(response);
                    for(int i =0; i < house.length(); i++)
                    {   JSONObject jsonObject = house.getJSONObject(i);
                        String locality = jsonObject.getString("Locality");
                        String avail= jsonObject.getString("Available");
                        String image = jsonObject.getString("Img");
                        String review = jsonObject.getString("Review");

                        OwnerItem ownerItem = new OwnerItem(avail,review,locality,image);

                        list.add(ownerItem);



                    }
                    adapter = new MyAdapter(list,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                                    } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Json error",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Owner.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
