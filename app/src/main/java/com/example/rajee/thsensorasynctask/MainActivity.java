package com.example.rajee.thsensorasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] names = {"Sai", "Rajeshwari", "Sai", "Pradeep",
            "Sairam", "Laxmi", "Priya", "Vishnu", "Shiva", "Ganesha",
            "SaiRajeshwari", "LaxmiPriya"};
    ListView listView;
    TextView tv_temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_temperature = (TextView) findViewById(R.id.temperature);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void, String, String>{

        ArrayAdapter<String> adapter;
        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) listView.getAdapter();
        }


        @Override
        protected String doInBackground(Void... params) {
            for(String s : names){
                publishProgress(s);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Successfully added all values";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            tv_temperature.setText(values[0]);
            adapter.add(values[0]);
            listView.setSelection(adapter.getCount()-1);
        }
    }


}
