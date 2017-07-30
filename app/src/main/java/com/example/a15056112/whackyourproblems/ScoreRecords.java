package com.example.a15056112.whackyourproblems;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.annotation.IntegerRes;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreRecords extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<HashMap<String,Integer>> al;

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_records);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final MediaPlayer clickSound = MediaPlayer.create(this, R.raw.click2);

        btnBack = (Button)findViewById(R.id.buttonBack);
        lv = (ListView)findViewById(R.id.lvScoreRecord);

        DBHelper db = new DBHelper(ScoreRecords.this);
        al = db.getAllScores();

        aa = new ScoreAdapter(this, R.layout.scorerow, al);
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();
        db.close();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<HashMap<String,Integer>> gamePlayedSearch = new ArrayList<HashMap<String, Integer>>();
                ListView lvSearch;

                lvSearch = (ListView)findViewById(R.id.lvScoreRecord);
                for (HashMap<String,Integer> game: al) {
                    if (game.get("id").equals(newText)) {
                        gamePlayedSearch.add(game);
                    }
                }
                aa = new ScoreAdapter(ScoreRecords.this, R.layout.scorerow, gamePlayedSearch);
                lvSearch.setAdapter(aa);

               // aa.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
