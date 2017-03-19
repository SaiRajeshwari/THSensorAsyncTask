package com.example.rajee.thsensorasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SensorReading sensorReading;
    ListView listView;
    TextView tv_temperature;
    TextView tv_humidity;
    TextView tv_activity;
    EditText number_of_readings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_temperature = (TextView) findViewById(R.id.temperature);
        tv_humidity = (TextView) findViewById(R.id.humidity);
        tv_activity = (TextView) findViewById(R.id.activity);
        number_of_readings = (EditText) findViewById(R.id.number_of_readings);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<SensorReading>(this, android.R.layout.simple_list_item_1, new ArrayList<SensorReading>()));


        Button btn_generate = (Button) findViewById(R.id.generate);
        btn_generate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new MyTask().execute(number_of_readings.getText().toString());
            }
        });


        Button btn_cancel = (Button) findViewById(R.id.cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });

    }

    class MyTask extends AsyncTask<String, String, String>{

        Random r = new Random();
        ArrayAdapter<SensorReading> adapter;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<SensorReading>) listView.getAdapter();
        }


        @Override
        protected String doInBackground(final String... params) {
            int count = Integer.parseInt(params[0]);
            for(int i=0; i<count; i++){
                publishProgress(Integer.toString(i+1), Integer.toString(r.nextInt(110 - 20)+20),
                        Integer.toString(r.nextInt(100 - 10)+10), Integer.toString(r.nextInt(400 - 110)+110));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Successfully generated all values";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            tv_temperature.setText(values[1] + " F");
            tv_humidity.setText(values[2] + " %");
            tv_activity.setText(values[3]);
            adapter.add(new SensorReading(values[0], values[1], values[2], values[3]));
            listView.setSelection(adapter.getCount()-1);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
