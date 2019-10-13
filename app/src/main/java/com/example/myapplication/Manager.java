package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Manager extends AppCompatActivity {
    public ImageButton buttonclick1, buttonclick2;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        buttonclick1 = findViewById(R.id.manager_button1);
        buttonclick2 = findViewById(R.id.manager_button2);
        button = findViewById(R.id.mainbutton5);
        buttonclick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manager.this, Add.class);
                startActivity(intent); //Add 액티비티 이동
            }
        });
        buttonclick2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Manager.this, Look.class);
                startActivity(intent); //Look 액티비티 이동
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //메인으로가기
                Intent intent = new Intent(Manager.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent); //액티비티 이동
            }

        });
    }
}
