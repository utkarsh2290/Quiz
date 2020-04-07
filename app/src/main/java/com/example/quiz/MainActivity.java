package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    CheckBox c1,c2,c3,c4;
    TextView one,two,three,four,question1,count,quesno;
    int correct=0;
    int qcount=0;
    int i;
    CountDownTimer mCountDownTimer;
    Button next;
    int j=10;
    String answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ONLY SELECTING ONE OUT OF ALL
        c1=findViewById(R.id.option1);
        c2=findViewById(R.id.option2);
        c3=findViewById(R.id.option3);
        c4=findViewById(R.id.option4);

        quesno=findViewById(R.id.textView);
        question1=findViewById(R.id.textView2);
        one=findViewById(R.id.textView3);
        two=findViewById(R.id.textView4);
        three=findViewById(R.id.textView5);
        four=findViewById(R.id.textView6);
        count=findViewById(R.id.count);

        updateQuestion();


        //NEXT ACTIVITY
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });



    }
    public void updateQuestion(){
        qcount++;
        i=30;
        j=10;
        c1.setChecked(false);
        c2.setChecked(false);
        c3.setChecked(false);
        c4.setChecked(false);
        one.setBackground(getResources().getDrawable(R.drawable.shape));
        two.setBackground(getResources().getDrawable(R.drawable.shape));
        three.setBackground(getResources().getDrawable(R.drawable.shape));
        four.setBackground(getResources().getDrawable(R.drawable.shape));

        if(qcount>10){
            Intent intent=new Intent(MainActivity.this,score.class);
            intent.putExtra("correct",correct);
            startActivity(intent);
        }

        else{

            //VARIATION OF SIZES WITHIN A TEXTVIEW
            String s="Question"+ String.valueOf(qcount)+"/10";
            SpannableString spannableString=new SpannableString(s);
            spannableString.setSpan(new RelativeSizeSpan(1.6f),0,10,0);
            TextView textView=(TextView)findViewById(R.id.textView);
            textView.setText(spannableString);


            DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child(String.valueOf(qcount));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                        if(qcount==10){
                            next.setText("Finish");
                        }

                        GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                        Map<String,String>map=dataSnapshot.getValue(genericTypeIndicator);
                        String question=map.get("Question");
                        String option1=map.get("Option1");
                        String option2=map.get("Option2");
                        String option3=map.get("Option3");
                        String option4=map.get("Option4");
                        answer=map.get("Answerno");

                        question1.setText(question);
                        one.setText(option1);
                        two.setText(option2);
                        three.setText(option3);
                        four.setText(option4);




                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            mCountDownTimer=new CountDownTimer(30000,1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                    i--;
                    j=j+8;
                    count.getBackground().setAlpha(j);
                    count.setText(String.valueOf(i));

                }

                @Override
                public void onFinish() {
                    //Do what you want
                    updateQuestion();
                    i--;
                }
            };
            mCountDownTimer.start();

            c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        c2.setChecked(false);
                        c3.setChecked(false);
                        c4.setChecked(false);
                        if(answer.equals("Option1")) {
                            one.setBackground(getResources().getDrawable(R.drawable.right));
                            Log.d("it","worked");
                            correct++;
                            updateQuestion();
                        }
                        else if(answer.equals("Option2")){
                            two.setBackground(getResources().getDrawable(R.drawable.right));
                            one.setBackground(getResources().getDrawable(R.drawable.wrong));
                            Log.d("it","worked");
                            updateQuestion();
                        }
                        else if(answer.equals("Option3")){
                            three.setBackground(getResources().getDrawable(R.drawable.right));
                            one.setBackground(getResources().getDrawable(R.drawable.wrong));
                            Log.d("it","worked");
                            updateQuestion();
                        }
                        else if(answer.equals("Option4")){
                            four.setBackground(getResources().getDrawable(R.drawable.right));
                            one.setBackground(getResources().getDrawable(R.drawable.wrong));
                            Log.d("it","worked");
                            updateQuestion();
                        }

                    }
                }
            });

            c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        c1.setChecked(false);
                        c3.setChecked(false);
                        c4.setChecked(false);
                        if(answer.equals("Option2")) {
                            two.setBackground(getResources().getDrawable(R.drawable.right));
                            correct++;
                            updateQuestion();
                        }
                        else if(answer.equals("Option1")){
                            one.setBackground(getResources().getDrawable(R.drawable.right));
                            two.setBackground(getResources().getDrawable(R.drawable.wrong));
                            updateQuestion();
                        }
                        else if(answer.equals("Option3")){
                            three.setBackground(getResources().getDrawable(R.drawable.right));
                            two.setBackground(getResources().getDrawable(R.drawable.wrong));
                            updateQuestion();

                        }
                        else if(answer.equals("Option4")){
                            four.setBackground(getResources().getDrawable(R.drawable.right));
                            two.setBackground(getResources().getDrawable(R.drawable.wrong));
                            updateQuestion();
                        }

                    }
                }
            });
            c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        c2.setChecked(false);
                        c1.setChecked(false);
                        c4.setChecked(false);
                        if(answer.equals("Option3")) {
                            three.setBackground(getResources().getDrawable(R.drawable.right));
                            correct++;
                            updateQuestion();
                        }
                        else if(answer.equals("Option1")){
                            one.setBackground(getResources().getDrawable(R.drawable.right));
                            three.setBackground(getResources().getDrawable(R.drawable.wrong));

                            updateQuestion();
                        }
                        else if(answer.equals("Option2")){
                            two.setBackground(getResources().getDrawable(R.drawable.right));
                            three.setBackground(getResources().getDrawable(R.drawable.wrong));
                            updateQuestion();
                        }
                        else if(answer.equals("Option4")){
                            four.setBackground(getResources().getDrawable(R.drawable.right));
                            three.setBackground(getResources().getDrawable(R.drawable.wrong));
                            updateQuestion();
                        }

                    }
                }
            });
            c4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        c2.setChecked(false);
                        c3.setChecked(false);
                        c1.setChecked(false);
                        if(answer.equals("Option4")) {
                            four.setBackground(getResources().getDrawable(R.drawable.right));
                            correct++;
                            updateQuestion();
                        }
                        else if(answer.equals("Option2")){
                            two.setBackground(getResources().getDrawable(R.drawable.right));
                            four.setBackground(getResources().getDrawable(R.drawable.wrong));
                            updateQuestion();
                        }
                        else if(answer.equals("Option3")){
                            three.setBackground(getResources().getDrawable(R.drawable.right));
                            four.setBackground(getResources().getDrawable(R.drawable.wrong));
                            updateQuestion();
                        }
                        else if(answer.equals("Option1")){
                            one.setBackground(getResources().getDrawable(R.drawable.right));
                            four.setBackground(getResources().getDrawable(R.drawable.wrong));
                            updateQuestion();
                        }

                    }
                }
            });

        }


    }

    }

