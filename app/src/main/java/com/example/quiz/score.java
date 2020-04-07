package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class score extends AppCompatActivity {

    int score;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        Intent mIntent = getIntent();
        score = mIntent.getIntExtra("correct", 0);


        ImageView imageView=findViewById(R.id.firstimage);
        TextView textView=findViewById(R.id.score);

        textView.setText("Score:"+  (String.valueOf(score)+"/10"));
    }
}
