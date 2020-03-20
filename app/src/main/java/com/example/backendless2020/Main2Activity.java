package com.example.backendless2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);





        Backendless.Data.of("DemoTable").getObjectCount(new AsyncCallback<Integer>() {
            @Override
            public void handleResponse(Integer response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
