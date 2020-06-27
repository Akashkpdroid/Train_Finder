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

import java.io.*;


public class MainActivity extends AppCompatActivity {

    private static String[] places =new String[4602];
    private Button search;

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
            int i=1;
                String s;
                while(i<=places.length)
                {
                    try{
                        s=reader.readLine();
                        System.out.println(s);
                        places[i-1]=s;
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
        Intent intent=new Intent(this,Train_list_activity.class);
        startActivity(intent);
    }
}
