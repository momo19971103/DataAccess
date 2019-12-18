package com.example.dataaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.textView);
        Button button1 = (Button) findViewById(R.id.button2);
        final Button button2 = (Button) findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FileOutputStream out = null;
                try {
                    //在 getFilesDir() 目錄底下建立 test.txt 檔案用來進行寫入
                    out = openFileOutput("test.txt", Context.MODE_PRIVATE);

                    //將資料寫入檔案中
                    out.write("Hello! 大家好11\n".getBytes());
                    out.flush();
                } catch (Exception e) {
                    ;
                } finally {
                    try {
                        out.close();
                    } catch (Exception e) {
                        ;
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FileInputStream in = null;
                StringBuffer data = new StringBuffer();
                try {
                    //開啟 getFilesDir() 目錄底下名稱為 test.txt 檔案
                    in = openFileInput("test.txt");
                    if (in == null) {

                    }
                    //讀取該檔案的內容
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in, "utf-8"));
                    String line;

                    if ((line = reader.readLine()) != null) {
                        data.append(line);
                        textView.setText(line);
                    }

                } catch (Exception e) {
                    Log.e("ERROR", "" + e);
                    textView.setText("資料為空");
                } finally {
                    try {
                        in.close();
                    } catch (Exception e) {
                        ;
                    }
                }
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Context context = getApplicationContext();
                        File f = new File(context.getCacheDir(), "test.txt");
                        f.delete();

                        //刪除 getFilesDir() 目錄底下 test.txt 檔案
                        context.deleteFile("test.txt");
                    }
                });
            }
        });
    }
}
