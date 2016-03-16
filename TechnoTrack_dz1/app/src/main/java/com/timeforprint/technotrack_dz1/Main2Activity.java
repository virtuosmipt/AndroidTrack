package com.timeforprint.technotrack_dz1;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Main2Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView listView = (ListView)findViewById(R.id.listView);
// используем адаптер данных
        listView.setAdapter(new MyAdapter(this));

    }
   /* @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Проверяем ориентацию экрана
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }*/
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Read values from the "savedInstanceState"-object and put them in your textview
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the values you need from your textview into "outState"-object
        super.onSaveInstanceState(outState);
        //outState.putStringArray("data",strings);
    }

    static class MyAdapter extends BaseAdapter {
        NumericParser numericParser;

        Context context;

        public static class ViewHolder {
            public TextView textView;
        }

        MyAdapter(Context context) {
            this.context = context;
            this.numericParser = new NumericParser(context.getResources());

        }

        @Override
        public int getCount() {
            return 1000000;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
          //  String str = (String) getItem(position);
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder();
                holder.textView = (TextView)convertView.findViewById(R.id.element_text);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.textView.setText(numericParser.digits2text(position+1));
            if ((position & 1) == 1) {
                convertView.setBackgroundColor(Color.rgb(170, 170, 170));
            } else {
                convertView.setBackgroundColor(Color.rgb(255, 255, 255));
            }
            return convertView;
        }
    }


}
