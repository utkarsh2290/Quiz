package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class first extends AppCompatActivity {


    public void clickFunction(View view) {

        startActivity(new Intent(first.this, MainActivity.class));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);

        Button start=findViewById(R.id.start);
        ImageView imageView=findViewById(R.id.firstimage);
        Button button=findViewById(R.id.button2);


    }
}

