package com.example.tsa_softdev_24;

import static android.text.Html.fromHtml;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;
import java.util.Objects;


public class OldChatsTemplateActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    public static void updateData(Chat c, TextView b1, TextView b2, TextView b3) {
        b1.setText("Tone: " + c.getChatTone() + "\n\nRecipient: " + c.getChatRecipent() + "\n\nSetting: " + c.getChatSetting());
        b1.setTypeface(b1.getTypeface(), Typeface.BOLD);
        b2.setText("User: " + c.getChatPrompt());
        b2.setTypeface(b2.getTypeface(), Typeface.BOLD);
        String b3_parsed = c.getResponse().replace("\\\n", (System.getProperty("line.separator")));
        b3.setText(fromHtml(MessageFormat.format("Assistant: {0}", b3_parsed)));
        b3.setTypeface(b3.getTypeface(), Typeface.BOLD);
    }


    public static Chat Chat1 = new Chat("", new ChatData("", "", "", ""));
    public static Chat Chat2 = new Chat("", new ChatData("", "", "", ""));
    public static Chat Chat3 = new Chat("", new ChatData("", "", "", ""));

    public static void updateChats() {
        Chat3 = new Chat(Chat2.getResponse(), new ChatData(Chat2.getChatTone(), Chat2.getChatRecipent(), Chat2.getChatSetting(), Chat2.getChatPrompt()));
        Chat2 = new Chat(Chat1.getResponse(), new ChatData(Chat1.getChatTone(), Chat1.getChatRecipent(), Chat1.getChatSetting(), Chat1.getChatPrompt()));
        Chat1 = new Chat(NewChatsActivity.currentChat.getResponse(), new ChatData(NewChatsActivity.currentChat.getChatTone(), NewChatsActivity.currentChat.getChatRecipent(), NewChatsActivity.currentChat.getChatSetting(), NewChatsActivity.currentChat.getChatPrompt()));
    }

    public TextView box1, box2, box3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_old_chats_template);


        box1 = findViewById(R.id.current_prompt1);
        box2 = findViewById(R.id.current_prompt2);
        box3 = findViewById(R.id.current_prompt3);
        updateData(NewChatsActivity.currentChat, box1, box2, box3);
        updateChats();

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         */
    }
}