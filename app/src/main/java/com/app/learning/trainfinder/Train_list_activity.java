package com.app.learning.trainfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import static com.app.learning.trainfinder.R.layout.train_list;

public class Train_list_activity extends AppCompatActivity {

    private Button back;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        ArrayList<Row_item> RowList=new ArrayList<>();
        RowList.add(new Row_item("Train Number: 02303","Train Name: Poorva Express","Arrival Time: 06:05","Departure Time: 07:45","Travel Time: 22:20H"));
        RowList.add(new Row_item("Train Number: 02381","Train Name: Poorva Express","Arrival Time: 06:05","Departure Time: 08:00","Travel Time: 22:05H"));
        RowList.add(new Row_item("Train Number: 02385","Train Name: Chennai Express","Arrival Time: 16:05","Departure Time: 08:00","Travel Time: 32:05H"));
        RowList.add(new Row_item("Train Number: 02395","Train Name: Mumbai Mail","Arrival Time: 16:45","Departure Time: 08:00","Travel Time: 31:25H"));
        RowList.add(new Row_item("Train Number: 02301","Train Name: HWH NDLS AC SPL","Arrival Time: 10:00","Departure Time: 16:45","Travel Time: 17:15H"));

        mRecyclerView=findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ItemAdapter(RowList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }
    private void openpage1()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

