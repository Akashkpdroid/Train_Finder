package com.app.learning.trainfinder;

public class Row_item {
    private String Train_Number;
    private String Train_Name;
    private String Arrival_Time;
    private String Departure_Time;
    private String Travel_Time;
    private String Code1;
    private String Code2;

    public Row_item(String Train_Number,String Train_Name,String Arrival_Time,String Departure_Time,String Travel_Time,String Code1,String Code2)
    {
        this.Train_Number=Train_Number;
        this.Train_Name=Train_Name;
        this.Arrival_Time=Arrival_Time;
        this.Departure_Time=Departure_Time;
        this.Travel_Time=Travel_Time;
        this.Code1=Code1;
        this.Code2=Code2;
    }

    public String getTrain_Number() {
        return Train_Number;
    }

    public String getTrain_Name() {
        return Train_Name;
    }

    public String getArrival_Time() {
        return Arrival_Time;
    }

    public String getDeparture_Time() {
        return Departure_Time;
    }

    public String getTravel_Time() {
        return Travel_Time;
    }

    public String getCode2() {
        return Code2;
    }

    public String getCode1() {
        return Code1;
    }
}
