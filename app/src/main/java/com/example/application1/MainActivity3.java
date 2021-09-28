package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button bts=(Button) findViewById(R.id.save);
        TextView drt=(EditText) findViewById(R.id.drt);
        TextView urt=(EditText) findViewById(R.id.urt);
        TextView jrt=(EditText) findViewById(R.id.jrt);

        Bundle config =getIntent().getExtras();
        double drs=config.getDouble("dr");
        double urs=config.getDouble("ur");
        double jrs=config.getDouble("jr");
        drt.setText(String.format("%.2f",drs));
        urt.setText(String.format("%.2f",urs));
        jrt.setText(String.format("%.2f",jrs));

        Intent intent = new Intent();

        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double ndr=Double.valueOf(drt.getText().toString().trim());
                double nur=Double.valueOf(urt.getText().toString().trim());
                double njr=Double.valueOf(jrt.getText().toString().trim());

                intent.putExtra("ndr", ndr);
                intent.putExtra("nur", nur);
                intent.putExtra("njr", njr);
                SharedPreferences sh=getSharedPreferences("my_rate", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sh.edit();
                editor.putFloat("dr",(float) ndr);
                editor.putFloat("ur",(float) nur);
                editor.putFloat("jr",(float) njr);
                editor.apply();


                setResult(RESULT_OK,intent);
                finish();
            }


        });
    }
}