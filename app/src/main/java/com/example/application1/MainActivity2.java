package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity2 extends AppCompatActivity implements Runnable {
    private static final String Tag="MainActivity2";
    public double dr;
    public double jr;
    public double ur;
    public Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        SharedPreferences sh=getSharedPreferences("my_rate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);

        dr=(double) sh.getFloat("dr",0.15f);
        jr=(double) sh.getFloat("jr",120.5f);
        ur=(double) sh.getFloat("ur",0.125f);

        Button bt1=(Button) findViewById(R.id.button);
        Button bt2=(Button) findViewById(R.id.button3);
        Button bt3=(Button) findViewById(R.id.button4);
        Button btr=(Button) findViewById(R.id.button5);

        TextView pt=(EditText) findViewById(R.id.input);
        TextView tv=(TextView) findViewById(R.id.textView);

        Thread t=new Thread(this);
        t.start();
        handler=new Handler(Looper.myLooper()){
            public void handleMessage(Message msg){
                if(msg.what==5){
                    String str=(String) msg.obj;
                    Log.i(Tag,"handleMessae: getMessage="+str);
                    tv.setText(str);
                }
                super.handleMessage(msg);
            }
        };






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

    private void loadFromSP() {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.setting){
            Intent config=new Intent(MainActivity2.this,MainActivity3.class);
            config.putExtra("dr",dr);
            config.putExtra("ur",ur);
            config.putExtra("jr",jr);
            startActivityForResult(config, 110);

        }
        return super.onOptionsItemSelected(item);
    }

    public void run(){
        Log.i(Tag,"run:run......");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(5);
        msg.obj="Hello from run()";
        handler.sendMessage(msg);

        URL url=null;
        try {
            url= new URL("https://www.usd-cny.com/bankofchina.htm");
            HttpsURLConnection http=(HttpsURLConnection)url.openConnection();
            InputStream in =http.getInputStream();

            String html=Stream2String(in);
            Log.i(Tag,"run:html"+html);
        }
        catch (Exception e){
            e.printStackTrace();
        }




    }

    public String Stream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"gb2312");
        while(true){
            int rsz = in.read(buffer,0,buffer.length);
            if (rsz < 0 )
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }

}