package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    public int a=0;
    public int b=0;
    private static final String Tag="MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button bt1=(Button) findViewById(R.id.button);
        Button bt2=(Button) findViewById(R.id.button3);
        Button bt3=(Button) findViewById(R.id.button4);
        Button btr=(Button) findViewById(R.id.button5);

        TextView tvs=(TextView) findViewById(R.id.textView6);
        TextView tv=(TextView) findViewById(R.id.textView);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a+=1;
                tvs.setText(String.valueOf(a));

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Tag,"click:");
                a+=2;
                tvs.setText(String.valueOf(a));
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a+=3;
                tvs.setText(String.valueOf(a));
            }
        });

        btr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=0;
                tvs.setText("0");
            }
        });


    }
}