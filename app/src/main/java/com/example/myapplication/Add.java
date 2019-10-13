package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Add extends AppCompatActivity {
    public Button mainbutton;
    public Button buttoninsert;
    public Button buttondelete;
    private EditText nameinsert;
    private EditText priceinsert;
    private CheckBox isAdultCheckBox;
    private EditText stocknuminsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mainbutton = findViewById(R.id.mainbutton);
        buttoninsert = (Button) findViewById(R.id.buttoninsert);
        nameinsert = (EditText) findViewById(R.id.nameinsert);
        priceinsert = (EditText) findViewById(R.id.priceinsert);
        isAdultCheckBox = (CheckBox) findViewById(R.id.isAdult);
        stocknuminsert = (EditText) findViewById(R.id.stocknuminsert);
        buttondelete = (Button) findViewById(R.id.buttondelete);
        buttoninsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new JSONTask().execute("http://elvansoda.herokuapp.com/manager/insert"); //버튼을 눌렀을때 접속
            }
        });
        buttondelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new JSONTask().execute("http://elvansoda.herokuapp.com/manager/delete"); //버튼을 눌렀을때 접속
            }
        });
        mainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //메인으로가기
                Intent intent = new Intent(Add.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent); //액티비티 이동
            }

        });

    }

    public class JSONTask extends AsyncTask<String, String, String> {
        private String url = "";

        @Override
        protected String doInBackground(String... urls) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("ProductName", nameinsert.getText()); //json으로 보내는 텍스트 초기화
                jsonObject.accumulate("StockNum", stocknuminsert.getText());
                jsonObject.accumulate("Price", priceinsert.getText());
                jsonObject.accumulate("isAdult", isAdultCheckBox.isChecked());
                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
                    //URL url = new URL("http://192.168.11.39:3000/insert");
                    URL url = new URL(urls[0]);
                    this.url = urls[0];
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Cache-Control", "no-cache");
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestProperty("Accept", "text/html");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.connect();

                    OutputStream outStream = con.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();

                    InputStream stream = con.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    return buffer.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        public void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result.contains("No Data")) Toast.makeText(Add.this, "데이터가 없습니다.", Toast.LENGTH_SHORT).show();
            else if(this.url.contains("delete")) Toast.makeText(Add.this, "삭제가 성공적으로 진행됐습니다.", Toast.LENGTH_SHORT).show();
            else if(this.url.contains("insert")) Toast.makeText(Add.this, "추가가 성공적으로 진행됐습니다.", Toast.LENGTH_SHORT).show();


        }
    }
}



