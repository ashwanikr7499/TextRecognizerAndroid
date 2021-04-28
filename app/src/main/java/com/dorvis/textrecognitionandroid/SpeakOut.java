package com.dorvis.textrecognitionandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SpeakOut extends AppCompatActivity {

    TextView tv_curText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_out);
        Intent intent = getIntent();
        String curText = intent.getStringExtra("curText");
        tv_curText=findViewById(R.id.tv_curText);
        tv_curText.setText(curText);
    }
}