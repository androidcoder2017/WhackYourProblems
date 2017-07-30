package com.example.a15056112.whackyourproblems;

/**
 * Created by 15056112 on 30/7/2017.
 */

public class Scores {
    private int id;
    private int score;

    public Scores() {

    }

    public Scores(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
