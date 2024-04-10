package com.example.tsa_softdev_24;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class ToneTest extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;

    int toneScore;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
    public static String toneLabel;
    int score = 0;
    int totalQuestion = 5;
    public int currentQuestionIndex = 0;
    String selectedAnswer = "";


    String selectedanswers = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tone_test);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(ToneTest.this, MainActivity.class);
                startActivity(intent);
            }
        };

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : " + totalQuestion);

        loadNewQuestion();

        ansA.setBackgroundColor(Color.LTGRAY);
        ansB.setBackgroundColor(Color.LTGRAY);
        ansC.setBackgroundColor(Color.LTGRAY);
        ansD.setBackgroundColor(Color.LTGRAY);


    }


    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.submit_btn) {
            currentQuestionIndex++;


            if (!selectedAnswer.isEmpty()) {
                ansA.setBackgroundColor(Color.LTGRAY);
                ansB.setBackgroundColor(Color.LTGRAY);
                ansC.setBackgroundColor(Color.LTGRAY);
                ansD.setBackgroundColor(Color.LTGRAY);
                score += evaluateTone(selectedAnswer);
                loadNewQuestion();
            } else {
                // Show an alert indicating that the user needs to select an option
                new AlertDialog.Builder(this)
                        .setTitle("Please Select an Option")
                        .setMessage("You must select an option before proceeding to the next question.")
                        .setPositiveButton(android.R.string.ok, null)
                        .show();

                // Evaluate tone after the user selects an option

            }
        } else {
            // Choices button clicked
            ansA.setBackgroundColor(Color.LTGRAY);
            ansB.setBackgroundColor(Color.LTGRAY);
            ansC.setBackgroundColor(Color.LTGRAY);
            ansD.setBackgroundColor(Color.LTGRAY);
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.rgb(173, 216, 230));
        }
    }


    void loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
        } else {
            questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        }
    }


    String displayTone(int avg_Score) {
        // Evaluate the tone based on the selected answers
        // Determine the tone label based on the tone avg_score
        String tonelabel = "";
        if (avg_Score >= 16) {
            tonelabel = "Positive Tone";
        } else if (avg_Score >= 13) {
            tonelabel = "Optimistic Tone";
        } else if (avg_Score >= 12) {
            tonelabel = "Frustrated Tone";
        } else if (avg_Score >= 11) {
            tonelabel = "Active Tone";
        } else if (avg_Score >= 10) {
            tonelabel = "Ambivalent Tone";
        } else if (avg_Score >= 9) {
            tonelabel = "Neutral Tone";
        } else if (avg_Score >= 8) {
            tonelabel = "Indifferent Tone";
        } else if (avg_Score >= 7) {
            tonelabel = "Calm Tone";
        } else if (avg_Score >= 6) {
            tonelabel = "Reserved Tone";
        } else if (avg_Score >= 5) {
            tonelabel = "Reflective Tone";
        } else if (avg_Score >= 4) {
            tonelabel = "Negative Tone";
        } else if (avg_Score >= 3) {
            tonelabel = "Shy Tone";
        } else if (avg_Score >= 2) {
            tonelabel = "Anxious Tone";
        } else if (avg_Score >= 1) {
            tonelabel = "Introverted Tone";
        } else {tonelabel = String.valueOf(score);
        }
        return tonelabel;
    }

    void finishQuiz() {
        float avg_score = score/5;
        toneLabel = displayTone(Math.round(avg_score));
        // Show the tone score and label in the completion dialog
        String passStatus = "COMPLETED! Your tone: " + toneLabel;

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setPositiveButton("Start Breaking Ice!", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }


    // Evaluate the tone based on the selected answers
    int evaluateTone(String selectedAnswer) {
        int ans_Score = 0;

                switch (selectedAnswer) {
                    case "My day was great! I accomplished a lot of tasks and enjoyed some downtime as well.":
                        ans_Score += 16;
                        break;
                    case "It was a tough day. Nothing seemed to go right.":
                        ans_Score += 4;
                        break;
                    case "It was just another day, nothing special.":
                        ans_Score += 9;
                        break;
                    case "I’d rather not talk about it.":
                        ans_Score += 6;
                        break;

                    case "I love exploring the outdoors! Hiking, biking, you name it!":
                        ans_Score += 18;
                        break;
                    case "Doesn’t really matter to me. Weekends are just like any other day.":
                        ans_Score += 8;
                        break;
                    case "I enjoy spending time with my friends and family.":
                        ans_Score += 1;
                        break;
                    case "I prefer to have some quiet time alone.":
                        ans_Score += 13;
                        break;

                    case "I try to stay positive and look at the bright side of things.":
                        ans_Score += 16;
                        break;
                    case "I tend to get overwhelmed and anxious.":
                        ans_Score += 4;
                        break;
                    case "I usually go for a run or do some physical activity.":
                        ans_Score += 9;
                        break;
                    case "I don’t usually get stressed.":
                        ans_Score += 6;
                        break;

                    case "I tackle it head-on and try to find a solution as quickly as possible.":
                        ans_Score += 18;
                        break;
                    case "I take a step back and try to look at the bigger picture.":
                        ans_Score += 8;
                        break;
                    case "I get frustrated and sometimes avoid dealing with it.":
                        ans_Score += 1;
                        break;
                    case "I ask others for help or advice.":
                        ans_Score += 13;
                        break;

                    case "I love it! It’s always exciting to meet new people.":
                        ans_Score += 16;
                        break;
                    case "I’m a bit shy and reserved, so it can be challenging.":
                        ans_Score += 4;
                        break;
                    case "It depends on the situation and the people.":
                        ans_Score += 9;
                        break;
                    case "I prefer to stick with my close circle of friends.":
                        ans_Score += 6;
                        break;
                    default:
                        break;

        }return ans_Score;}
    // Average the total score



    public void restartQuiz() {
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
        Intent intent = new Intent(ToneTest.this, MainActivity.class);
        startActivity(intent);
    }



}