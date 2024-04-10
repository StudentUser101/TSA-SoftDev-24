package com.example.tsa_softdev_24;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Old1 extends AppCompatActivity {
    public TextView box1_1, box2_1, box3_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_old1);


        box1_1 = findViewById(R.id.old1_prompt1);
        box2_1 = findViewById(R.id.old1_prompt2);
        box3_1 = findViewById(R.id.old1_prompt3);
        OldChatsTemplateActivity.updateData(OldChatsTemplateActivity.Chat1, box1_1, box2_1, box3_1);

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         */
    }
}