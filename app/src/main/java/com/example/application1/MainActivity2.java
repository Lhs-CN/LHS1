package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private static final String Tag="MainActivity2";
    public double dr= 0.15;
    public double jr= 125.5;
    public double ur= 0.125;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button bt1=(Button) findViewById(R.id.button);
        Button bt2=(Button) findViewById(R.id.button3);
        Button bt3=(Button) findViewById(R.id.button4);
        Button btr=(Button) findViewById(R.id.button5);

        TextView pt=(EditText) findViewById(R.id.input);
        TextView tv=(TextView) findViewById(R.id.textView);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    double yuan=Double.valueOf(pt.getText().toString().trim());
                    double a=yuan*dr;
                    tv.setText(String.format("%.2f",a)+"美元");
                }
                catch (Exception e){
                    Toast.makeText(MainActivity2.this,"输入错误！",Toast.LENGTH_SHORT).show();
                }

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double yuan=Double.valueOf(pt.getText().toString().trim());
                    double a=yuan*ur;
                    tv.setText(String.format("%.2f",a)+"欧元");
                }
                catch (Exception e){
                    Toast.makeText(MainActivity2.this,"输入错误！",Toast.LENGTH_SHORT).show();
                }

            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    double yuan=Double.valueOf(pt.getText().toString().trim());
                    double a=yuan*jr;
                    tv.setText(String.format("%.2f",a)+"日元");
                }
                catch (Exception e){
                    Toast.makeText(MainActivity2.this,"输入错误！",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent config=new Intent(MainActivity2.this,MainActivity3.class);
                config.putExtra("dr",dr);
                config.putExtra("ur",ur);
                config.putExtra("jr",jr);
                startActivityForResult(config, 110);
            }


        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 110){
            if(resultCode == RESULT_OK){
                Bundle b=data.getExtras();
                dr = b.getDouble("ndr");
                ur = b.getDouble("nur");
                jr = b.getDouble("njr");
                Toast.makeText(MainActivity2.this,"保存成功！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}