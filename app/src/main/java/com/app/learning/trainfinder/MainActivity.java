package com.app.learning.trainfinder;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static String[] places =new String[4602];
    private static String[] codes=new String[4602];
    private Button search;
    String STATION_NAME="";
    private TextView Start_text;
    private TextView Dest_text;
    public String CODE_1,CODE_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=(Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpage2();
            }
        });

        Start_text=(TextView)findViewById(R.id.start_place);
        Dest_text=(TextView)findViewById(R.id.dest_place);



        Station_suggest();

        {//Auto Complete for Starting Place Text
            AutoCompleteTextView editText_start = findViewById(R.id.start_place);
            ArrayAdapter<String> adapter_start = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, places);
            editText_start.setAdapter(adapter_start);
            editText_start.setThreshold(1);
        }

        {//Auto Complete for Destination Place Text
            AutoCompleteTextView editText_dest = findViewById(R.id.dest_place);
            ArrayAdapter<String> adapter_dest = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, places);
            editText_dest.setAdapter(adapter_dest);
            editText_dest.setThreshold(1);
        }
    }

    private void Station_suggest() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.stations);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            InputStream inputStream1 = getResources().openRawResource(R.raw.stations_code);
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream1));
            int i=1;
                String s,s1;
                while(i<=places.length)
                {
                    try{
                        s=reader.readLine();
                        s1=reader1.readLine();

                        places[i-1]=s;
                        codes[i-1]=s1;
                        i++;
                    }
                    catch(Exception e)
                    {
                        break;
                    }
                }
            }
        catch (Exception e)
        {
            Log.e("Train_Finder", "FileNotFound");
        }
    }
    private void openpage2(){
            STATION_NAME=Start_text.getText().toString();
            CODE_1=Find_Code(STATION_NAME);
            Log.d("Train_Finder",CODE_1);


            STATION_NAME=Dest_text.getText().toString();
            CODE_2=Find_Code(STATION_NAME);
            Log.d("Train_Finder",CODE_2);

            if((!CODE_1.equals("FAIL"))&&(!CODE_2.equals("FAIL"))) {

                Intent intent = new Intent(this, Train_list_activity.class);
                intent.putExtra("CODE_1",CODE_1);
                intent.putExtra("CODE_2",CODE_2);
                startActivity(intent);
            }

    }
    public String Find_Code(String s)
    {
        for(int i=0;i<4602;i++)
        {
            if(places[i].equals(s))
                return codes[i];
        }
        Toast.makeText(MainActivity.this,"Invalid Station Name ",Toast.LENGTH_SHORT).show();
        return "FAIL";
    }
}
