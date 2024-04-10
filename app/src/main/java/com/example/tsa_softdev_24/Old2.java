package com.example.tsa_softdev_24;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Old2 extends AppCompatActivity {
    public TextView box1_2, box2_2, box3_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_old2);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(Old2.this, MainActivity.class);
                startActivity(intent);
            }
        };

        box1_2 = findViewById(R.id.old2_prompt1);
        box2_2 = findViewById(R.id.old2_prompt2);
        box3_2 = findViewById(R.id.old2_prompt3);
        OldChatsTemplateActivity.updateData(OldChatsTemplateActivity.Chat2, box1_2, box2_2, box3_2);
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         */
    }
}