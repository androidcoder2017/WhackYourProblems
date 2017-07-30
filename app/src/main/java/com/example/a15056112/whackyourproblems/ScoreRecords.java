package com.example.a15056112.whackyourproblems;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreRecords extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<HashMap<String,Integer>> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_records);

        lv = (ListView)findViewById(R.id.lvScoreRecord);

        DBHelper db = new DBHelper(ScoreRecords.this);
        al = db.getAllScores();

        aa = new ScoreAdapter(this, R.layout.scorerow, al);
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();
        db.close();
    }


}
