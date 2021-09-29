package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity4 extends ListActivity implements Runnable{
    public Handler handler;
    private static final String TAG="MainActivity4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread t=new Thread(this);
        t.start();
        handler=new Handler(Looper.myLooper()){
            public void handleMessage(Message msg){
                if(msg.what==7){
                    List<String> l1=(List<String>) msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(MainActivity4.this,
                            android.R.layout.simple_list_item_1,
                            l1);
                    setListAdapter(adapter);


                }
                super.handleMessage(msg);
            }
        };
    }
    public void run(){

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(7);
        List<String> ls=new ArrayList();

        try {
            Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/").get();
            Elements rate_info  = doc.getElementsByTag("table").get(1).getElementsByTag("tr");
            rate_info.remove(0);
            for(Element info:rate_info){

                Elements tds = info.getElementsByTag("td");
                String Currency = tds.get(0).text();
                float ex_rate = 100/Float.valueOf(tds.get(5).text());
                ls.add(Currency+": "+ex_rate);
                Log.i(TAG, "run: Currency ： " + Currency + "" + (ex_rate));
            }
            msg.obj=ls;
            handler.sendMessage(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}