package com.example.askme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.askme.sampledata.Question_item;
import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question_item> {

    public QuestionAdapter(Context context, List<Question_item> questions) {
        super(context, 0, questions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Question_item question = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_question_item,parent,false);

            TextView userName = convertView.findViewById(R.id.user_name);
            TextView questionText = convertView.findViewById(R.id.question_text);
            ImageView userPicture = convertView.findViewById(R.id.user_picture);

            userName.setText(question.getusername());
            questionText.setText(question.getQuestionText());
            userPicture.setImageResource(R.drawable.user_icon);


        }

        return convertView;
    }
}

