package com.example.a15056112.whackyourproblems;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 15056112 on 30/7/2017.
 */

public class ScoreAdapter extends ArrayAdapter<HashMap<String,Integer>> {

    Context context;
    ArrayList<HashMap<String,Integer>> alScore;
    int resource;
    TextView tvScore, tvGameNumber;

    public ScoreAdapter(Context context, int resource, ArrayList<HashMap<String,Integer>> scores) {
        super(context, resource, scores);
        this.context = context;
        this.resource = resource;
        this.alScore = scores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.scorerow, parent, false);

        tvScore = (TextView)view.findViewById(R.id.scores);
        tvGameNumber = (TextView)view.findViewById(R.id.gameNumber);

        HashMap<String,Integer> hashmap = alScore.get(position);
        tvGameNumber.setText("Game Number: " + String.valueOf(hashmap.get("id")));
        tvScore.setText("Score: " + String.valueOf(hashmap.get("score")));

        return  view;
    }
}
