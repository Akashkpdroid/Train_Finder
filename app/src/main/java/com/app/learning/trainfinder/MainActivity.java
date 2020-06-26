package com.app.learning.trainfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private static final String[] places =new String[]{
      "Kole","Kolkata","Kolad","Kolda","Kolayat"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
