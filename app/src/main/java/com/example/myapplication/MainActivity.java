package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_move, btn_move2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_move = findViewById(R.id.btn_move);
        btn_move2 = findViewById(R.id.btn_move2);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CCTV.class);
                startActivity(intent); //CCTV 액티비티 이동
            }

        });
        btn_move2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Manager.class);
                startActivity(intent); //재고관리 액티비티 이동
            }
        });
    }
}
