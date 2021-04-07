package com.example.artikolevyinf;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.artikolevyinf.Controller.InfrastructureSurvey.ReadActivitiesInfrastructure;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;


public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;
    int intPbStatus = 0;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        progressBar = findViewById(R.id.ProgressBar);

        thread = new Thread() {
            @Override
            public void run() {
                while (!Globals.splashActivityFinish) {
                    progressBar.setProgress(intPbStatus);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(intPbStatus >= 100){
                        intPbStatus = 0;
                    }
                    intPbStatus++;
                }
            }
        };

        thread.start();
        getInfo();
    }

    public void getInfo(){
//


        //"121211451M"

        /** Aqui se llamara la información de la BD */
        //getReviewBT();
        getInfrastructureSurvey();

    }



    public void getReviewBT() {
        //Llenar esta información con datos de la BD


    }

    public void getInfrastructureSurvey(){
        new ReadActivitiesInfrastructure(this).execute();
    }



}
