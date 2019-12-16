package com.example.birthdayrem;


import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfosActivity extends AppCompatActivity {
    private TextView textView;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
        textView = (TextView)findViewById(R.id.info_textview);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        textView.setText(name);
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
    }
}
