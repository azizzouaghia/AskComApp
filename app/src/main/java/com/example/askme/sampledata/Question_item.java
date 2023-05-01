package com.example.askme.sampledata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.askme.R;

import java.util.ArrayList;
import java.util.Date;

public class Question_item extends AppCompatActivity {

    public static ArrayList<Question_item> questionList = new ArrayList<>();
    private int id;
    private int UserId;
    private String QuestionText;
    private int CatId;
    private int LikesNum;
    private Date date;


    public Question_item(String questionText, Date date) {
        this.id = id;
        UserId = 0;
        QuestionText = questionText;
        CatId = 0;
        LikesNum = 0;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }

    public int getCatId() {
        return CatId;
    }

    public void setCatId(int catId) {
        CatId = catId;
    }

    public int getLikesNum() {
        return LikesNum;
    }

    public void setLikesNum(int likesNum) {
        LikesNum = likesNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}