package com.app.learning.trainfinder;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class TrainDataModel {

    String Tot_Trains;
    Row_item arr[];

    public static TrainDataModel fromJSON(JSONObject jsonObject)
    {
        TrainDataModel trainData=new TrainDataModel();

        try {
            trainData.Tot_Trains = jsonObject.getString("TotalTrains");
            int responseCode=Integer.parseInt(jsonObject.getString("ResponseCode"));
            if(responseCode==201)
            {
                return null;
            }
            int len=Integer.parseInt(trainData.Tot_Trains);

            trainData.arr=new Row_item[len];
            int i;
            String tr_no,tr_name,arrv_time,dep_time,trv_time,Code1,Code2;

            for(i=0;i<len;i++)
            {
                tr_no =  "#"+jsonObject.getJSONArray("Trains").getJSONObject(i).getString("TrainNo");
                tr_name= jsonObject.getJSONArray("Trains").getJSONObject(i).getString("TrainName");
                arrv_time=jsonObject.getJSONArray("Trains").getJSONObject(i).getString("ArrivalTime");
                dep_time=jsonObject.getJSONArray("Trains").getJSONObject(i).getString("DepartureTime");
                trv_time=jsonObject.getJSONArray("Trains").getJSONObject(i).getString("TravelTime");
                Code1=jsonObject.getJSONArray("Trains").getJSONObject(i).getString("Source");
                Code2=jsonObject.getJSONArray("Trains").getJSONObject(i).getString("Destination");

                trainData.arr[i] = new Row_item(tr_no,tr_name,arrv_time,dep_time,trv_time,Code1,Code2);
                Log.d("Train_Finder","\n"+tr_no+" "+tr_name+" "+arrv_time+" "+dep_time+" "+trv_time);
            }
        }
        catch (JSONException e)
        {
            //e.printStackTrace();
            return null;
        }

        return trainData;
    }

}
