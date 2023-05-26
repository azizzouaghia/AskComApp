package com.example.askme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.askme.sampledata.Question_item;
import com.example.askme.sampledata.cmnt_item;

import java.util.List;

public class CmntAdapter  extends ArrayAdapter<cmnt_item> {

    public CmntAdapter(Context context, List<cmnt_item> cmnts) {
        super(context, 0, cmnts);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        cmnt_item cmnt = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_cmnt_item,parent,false);
            TextView username = convertView.findViewById(R.id.user_name);
            TextView date = convertView.findViewById(R.id.date);
            TextView cmntText = convertView.findViewById(R.id.replyText);
            ImageView userPicture = convertView.findViewById(R.id.user_picture);


            username.setText(cmnt.getUsername());
            date.setText(cmnt.getDate());
            cmntText.setText(cmnt.getCmnt());
            userPicture.setImageResource(R.drawable.user_icon);


        }

        return convertView;
    }
}
