package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity5 extends AppCompatActivity implements Runnable, AdapterView.OnItemClickListener {
    public Handler handler;
    public ListView myList2;
    private static final String TAG="MainActivity5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Thread t=new Thread(this);
        t.start();
        ProgressBar pb=findViewById(R.id.pb);
        handler=new Handler(Looper.myLooper()){
            public void handleMessage(Message msg){
                if(msg.what==7){
                    ArrayList<HashMap<String,String>> l1=(ArrayList<HashMap<String,String>>) msg.obj;
                    //SimpleAdapter la=new SimpleAdapter(MainActivity5.this,l1,R.layout.list_item,new String[]{"ItemTitle","ItemDetail"},new int[]{R.id.itemTitle,R.id.itemDetail});
                    MyAdapter la=new MyAdapter(MainActivity5.this,R.layout.list_item,l1);
                    myList2=findViewById(R.id.lv1);
                    myList2.setOnItemClickListener(MainActivity5.this);
                    myList2.setAdapter(la);
                    pb.setVisibility(View.GONE);
                    myList2.setVisibility(View.VISIBLE);
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

        ArrayList<HashMap<String,String>> listItems =new ArrayList<HashMap<String,String>>();
        try {
            Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/").get();
            Elements rate_info  = doc.getElementsByTag("table").get(1).getElementsByTag("tr");
            rate_info.remove(0);
            for(Element info:rate_info){

                Elements tds = info.getElementsByTag("td");
                String Currency = tds.get(0).text();
                float ex_rate = 100/Float.valueOf(tds.get(5).text());
                HashMap<String,String> map=new HashMap<String,String>();
                map.put("ItemTitle","币种:  "+Currency);
                map.put("ItemDetail","汇率:  "+String.valueOf(ex_rate));
                listItems.add(map);
                Log.i(TAG, "run: Currency ： " + Currency + "" + (ex_rate));
            }
            msg.obj=listItems;
            handler.sendMessage(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Object itemAtPosition = myList2.getItemAtPosition(i);
        HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;
        String Currency = map.get("ItemTitle");
        String ex_rate = map.get("ItemDetail");
        Intent show_interface = new Intent(MainActivity5.this,MainActivity6.class);
        show_interface.putExtra("Currency",Currency);
        show_interface.putExtra("ex_rate",ex_rate);
        startActivity(show_interface);
    }
}