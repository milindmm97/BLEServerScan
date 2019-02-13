package com.example.riya.notif_060219;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;

import android.os.Bundle;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import android.text.Html;

import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TableLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = (TableLayout)findViewById(R.id.tab);
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
    }


    private BroadcastReceiver onNotice= new BroadcastReceiver() {
        String oldtitle="abc";
        @Override
        public void onReceive(Context context, Intent intent) {
            String pack = intent.getStringExtra("package");
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");
            int abc=oldtitle.compareTo(title);
            int def=pack.compareTo("com.google.android.apps.maps");
            if(abc!=0 &&def==0) {
                Log.d("myTag", "blehhhhhhhhhhhhhhhhhhhhhhhhhh");
                Log.d(title, text);
                TableRow tr = new TableRow(getApplicationContext());
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                TextView textview = new TextView(getApplicationContext());
                textview.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
                textview.setTextSize(20);
                textview.setTextColor(Color.parseColor("#0B0719"));

                String [] res= title.split("\\-");
              // +res[0]  + "</b></b>"+res[1]  + "</b></b>"
                int len=res.length;
                textview.setText(Html.fromHtml( title  + "</b></br>"+res[0]+"</b></br>"+res[len-1]+"</b></br>"));
                tr.addView(textview);
                tab.addView(tr);


            }
             oldtitle=title;


        }
    };


}