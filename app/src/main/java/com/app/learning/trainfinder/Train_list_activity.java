package com.app.learning.trainfinder;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.app.learning.trainfinder.R.layout.train_list;

public class Train_list_activity extends AppCompatActivity {

    private Button back;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Row_item> RowList;
    private TextView mTextView;

    String URL="https://indianrailapi.com/api/v2/TrainBetweenStation/apikey/317f3d1fa71471530d198fd12f9009bd/From/";
    private String Code1,Code2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(train_list);
        back=(Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpage1();
            }
        });

        mTextView=(TextView) findViewById(R.id.textView);

        Intent intnt=getIntent();
        Code1=intnt.getStringExtra("CODE_1");
        Code2=intnt.getStringExtra("CODE_2");

        RowList=new ArrayList<>();

        URL+=Code1+"/To/"+Code2;
        try {
            letsDoSomeNetworking();

        }
        catch (Exception e)
        {
            Toast.makeText(Train_list_activity.this,"Request Failed",Toast.LENGTH_SHORT).show();
        }

    }
    private void letsDoSomeNetworking() {


        try {
            HttpsTrustManager.allowAllSSL();
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    URL,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Train_Finder", "SUCCESS! JSON: " + response.toString());
                            TrainDataModel trainData = TrainDataModel.fromJSON(response);
                            if(trainData==null) {
                                openpage1();
                                Toast.makeText(Train_list_activity.this,"No Currently Available Trains",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                int len = Integer.parseInt(trainData.Tot_Trains);
                                for (int i = 0; i < len; i++) {
                                    RowList.add(trainData.arr[i]);
                                }
                                mRecyclerView = findViewById(R.id.RecyclerView);
                                mRecyclerView.setHasFixedSize(true);
                                mLayoutManager = new LinearLayoutManager(Train_list_activity.this);
                                mAdapter = new ItemAdapter(RowList);

                                mRecyclerView.setLayoutManager(mLayoutManager);
                                mRecyclerView.setAdapter(mAdapter);
                                mTextView.setText(R.string.list_train);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Train_Finder", "Fail " + error.toString());
                            Toast.makeText(Train_list_activity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            requestQueue.add(objectRequest);
        }
        catch(Exception e)
        {
            Toast.makeText(Train_list_activity.this,"Request Failed",Toast.LENGTH_SHORT).show();
            openpage1();
        }

    }
    private void openpage1()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

