package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt2=(Button) findViewById(R.id.button2);
        EditText text1=(EditText) findViewById(R.id.Text1);
        EditText text2=(EditText) findViewById(R.id.Text2);
        TextView tv4=(TextView) findViewById(R.id.textView4);
        TextView tv2=(TextView) findViewById(R.id.textView2);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String w=text1.getText().toString();
                String h=text2.getText().toString();
                double bmi=Float.parseFloat(w)/(Float.parseFloat(h)*Float.parseFloat(h));
                tv2.setText(String.format("%.2f", bmi));

                if(bmi<18.5){
                    tv4.setText("您的体重偏低，建议多吃饭。");
                }
                else if(bmi>=18.5 && bmi<24){
                    tv4.setText("您的体重正常，请保持。");
                }
                else{
                    tv4.setText("您超重了，请多运动。");
                }

            }
        });
    }


}