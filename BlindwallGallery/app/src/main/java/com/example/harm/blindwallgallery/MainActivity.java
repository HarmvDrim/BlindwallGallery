package com.example.harm.blindwallgallery;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements BlindwallListener {

    private ListView listView;
    private ArrayAdapter<Blindwall> lvAdapter;
    private ArrayList<Blindwall> galaryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        galaryItems = new ArrayList<>();
        //galaryItems.add(new Blindwall("Banaan", "d", "d", "d", "d", "d", 5));
        Log.i(TAG,"ArrayList gemaakt");

        listView = findViewById(R.id.listView_id);

        lvAdapter = new ArrayAdapter<Blindwall>(this,
                R.layout.lv_row,galaryItems);

        lvAdapter = new ListViewAdapter(this,galaryItems);

        listView.setAdapter(lvAdapter);

        getBlindwalls();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("BLINDITEM", galaryItems.get(i));
                Log.i(TAG, "Detailscherm berijkt");
                startActivity(intent);
            }
        });
    }

    private void getBlindwalls(){
        BlindwallAsyncTask task = new BlindwallAsyncTask(this);
        String[] urls = new String[]{"https://api.blindwalls.gallery/apiv2/murals"};
        task.execute(urls);
        Log.i(TAG, "Connectie met api gelegt");
    }

    public void onBlindwallAvailable(Blindwall blindwall){
        galaryItems.add(blindwall);
        Log.d(TAG,"runUI");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lvAdapter.notifyDataSetChanged();
            }
        });
    }
}
