package com.example.askme;

import static androidx.core.content.ContextCompat.startActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
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
            TextView categoryText = convertView.findViewById(R.id.category);
            ImageView userPicture = convertView.findViewById(R.id.user_picture);

            /*****************************Press Reply Btn**********************************/
            ImageView replyButton = convertView.findViewById(R.id.reply_button);
            replyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent replyIntent = new Intent(getContext(), Reply.class);
                    replyIntent.putExtra("question", question.getQuestionText());
                    replyIntent.putExtra("category", question.getCategoryText());
                    replyIntent.putExtra("username", question.getusername());
                    replyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(replyIntent);
                }
            });
            /*****************************Press Reply Btn**********************************/
            /*****************************Press Like Btn**********************************/
            ImageView likeButton = convertView.findViewById(R.id.like_button);

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeButton.setImageResource(R.drawable.liked);

                }
            });
            /*****************************Press Like Btn**********************************/

            userName.setText(question.getusername());
            questionText.setText(question.getQuestionText());
            categoryText.setText(question.getCategoryText());
            userPicture.setImageResource(R.drawable.user_icon);

        }

        return convertView;
    }
}

