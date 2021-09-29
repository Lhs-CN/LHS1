package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        final Bundle bundle=getIntent().getExtras();    //接收Extras
        String Currency = bundle.getString("Currency");
        String ex_rate = bundle.getString("ex_rate");

        TextView tvv1 = (TextView) findViewById(R.id.tvv1);
        tvv1.setText(Currency);

        EditText inv = (EditText) findViewById(R.id.inv);
        TextView tvv2 = (TextView) findViewById(R.id.tvv2);

        Button btt=(Button)findViewById(R.id.btt);
        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r=ex_rate.split(":")[1];
                float rate = Float.parseFloat(r);
                float RMB = Float.parseFloat(inv.getText().toString());
                float convert_result = rate * RMB;
                tvv2.setText(inv.getText().toString() + "RMB = " + convert_result + Currency);
            }
        });
    }
}