package com.kvikesh800gmail.relativlayoutjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;
import java.util.Collections;

public class Questions extends AppCompatActivity {
    DonutProgress donutProgress;
    int variable =0;
    TextView ques;
    Button OptA, OptB, OptC, OptD;
    Button play_button;
    String get;
    //Objects of different classes


    public int visibility = 0, c1 = 0, c8 = 0, i, j = 0, k = 0, l = 0;
    String global = null, Ques, Opta, Optb, Optc, Optd;
    ArrayList<Question> questions = new ArrayList<Question>();
    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences shared = getSharedPreferences("Score", Context.MODE_PRIVATE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();//recieving the intent send by the Navigation activity
        get = intent.getStringExtra(Navigation_Activity.Message);//converting that intent message to string using the getStringExtra() method
        toast = new Toast(this);
        //attribute of the circular progress bar
        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        donutProgress.setMax(100);
        donutProgress.setFinishedStrokeColor(Color.parseColor("#FFFB385F"));
        donutProgress.setTextColor(Color.parseColor("#FFFB385F"));
        donutProgress.setKeepScreenOn(true);


        QuestionDatabase questionDB=new QuestionDatabase(this);
        questions=questionDB.getAllQuestion();



//source code send kr dyna fb pr check krna pry ga kha bhnd hy baqi kl dekhy gy... agr kuch pochna hy to ta do...
      //  nhe khr h kr lo aram se check tm ...

        //Till here we are linking the database file
        OptA = (Button) findViewById(R.id.OptionA);
        OptB = (Button) findViewById(R.id.OptionB);
        OptC = (Button) findViewById(R.id.OptionC);
        OptD = (Button) findViewById(R.id.OptionD);
        ques = (TextView) findViewById(R.id.Questions);
        play_button = (Button) findViewById(R.id.play_button);//Play button to start the game

    }// kr k run kr
//nhe chl re
   // sourrce code sent kro

    public void onClick(View v) {//When this method is executed then there will be new question came and also same method for play button
        final SharedPreferences shared = getSharedPreferences("Score", Context.MODE_PRIVATE);
        k++;
        if(questions.size()>j){
        if (visibility == 0) {
            //showing the buttons which were previously invisible
            OptA.setVisibility(View.VISIBLE);
            OptB.setVisibility(View.VISIBLE);
            OptC.setVisibility(View.VISIBLE);
            OptD.setVisibility(View.VISIBLE);
            play_button.setVisibility(View.GONE);
            donutProgress.setVisibility(View.VISIBLE);
            visibility = 1;
            new CountDownTimer(50000, 1000) {//countdowntimer
                int i = 100;

                @Override
                public void onTick(long millisUntilFinished) {
                    i = i - 2;
                    donutProgress.setProgress(i);


                }

                @Override
                public void onFinish() {
                    toast.cancel();
                    SharedPreferences.Editor editor = shared.edit();//here we are saving the data when the countdown timer will finish and it is saved in shared prefrence file that is defined in onCreate method as score
                    editor.putInt("Questions", k).commit();
                    if (get.equals("c1") && shared.getInt("Computer", 0) < l)
                        editor.putInt("Computer", l * 10).apply();


                    else if (get.equals("c8") && shared.getInt("Maths", 0) < l)
                        editor.putInt("Maths", l * 10).apply();

                    donutProgress.setProgress(0);
                    if(variable==0) {
                        Intent intent = new Intent(Questions.this, Result.class);
                        intent.putExtra("correct", l);
                        intent.putExtra("attemp", k);
                        startActivity(intent);
                        finish();
                    }
                }
            }.start();
        }

        if (global != null) {
            if (global.equals("A")) {
                if (v.getId() == R.id.OptionA) {
                    //Here we use the snackbar because if we use the toast then they will be stacked an user cannot idetify which questions answer is it showing
                    Snackbar.make(v, "         Correct ☺", Snackbar.LENGTH_SHORT).show();

                    l++;
                } else {
                    Snackbar.make(v, "Incorrect\t      Answer :" + Opta + "", Snackbar.LENGTH_SHORT).show();
                }

            } else if (global.equals("B")) {
                if (v.getId() == R.id.OptionB) {
                    Snackbar.make(v, "          Correct ☺", Snackbar.LENGTH_SHORT).show();
                    l++;
                } else {
                    Snackbar.make(v, "Incorrect\t      Answer :" + Optb + "", Snackbar.LENGTH_SHORT).show();
                }

            } else if (global.equals("C")) {
                if (v.getId() == R.id.OptionC) {

                    Snackbar.make(v, "         Correct ☺", Snackbar.LENGTH_SHORT).show();
                    l++;
                } else {
                    Snackbar.make(v, "Incorrect\tAnswer :" + Optc + "", Snackbar.LENGTH_SHORT).show();
                }
            } else if (global.equals("D")) {
                if (v.getId() == R.id.OptionD) {
                    Snackbar.make(v, "        Correct ☺", Snackbar.LENGTH_SHORT).show();
                    l++;
                } else {

                    Snackbar.make(v, "Incorrect\tAnswer :" + Optd + "", Snackbar.LENGTH_SHORT).show();
                }
            }
        }



            Ques = questions.get(j).getQuestion();
            Opta = questions.get(j).getOptionA();
            Optb = questions.get(j).getOptionB();
            Optc = questions.get(j).getOptionC();
            Optd = questions.get(j).getOptionD();
            global = questions.get(j).getAnswer();

        ques.setText("" + Ques);
        OptA.setText(Opta);
        OptB.setText(Optb);
        OptC.setText(Optc);
        OptD.setText(Optd);}
        else{
            Toast.makeText(this, "question ended", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        variable =1;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        variable =1;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        variable = 1;
        finish();
    }
}
