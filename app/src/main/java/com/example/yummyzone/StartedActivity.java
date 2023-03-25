package com.example.yummyzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartedActivity extends AppCompatActivity {
    private Button start_button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started);
        getSupportActionBar().hide();

        start_button = (Button) findViewById(R.id.started_button);
        start_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                opensignUp();}});
        }
    public void opensignUp(){
        Intent mainIntent = new Intent(StartedActivity.this,signUp.class);
        startActivity(mainIntent);


    }
}