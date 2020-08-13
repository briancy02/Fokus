package com.example.fokus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class QuestionArrayAdapter extends ArrayAdapter<Response>{
    private Context context;
    private List<Response> responses;

    public QuestionArrayAdapter(Context context, List<Response> list)
    {
        super(context, R.layout.row_layout, list);
        this.context = context;
        this.responses = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView tvAssignmentName = convertView.findViewById(R.id.tvAssignmentName);
        TextView tvAssignmentDescription = convertView.findViewById(R.id.tvAssignmentDescription);
        TextView tvTeacher = convertView.findViewById(R.id.tvTeacher);
        ImageView ivVideo = convertView.findViewById(R.id.ivVideo);

        tvAssignmentName.setText(assignments.get(position).getAssignmentName() + "");
        tvTeacher.setText(assignments.get(position).getTeacherEmail() + "");
        tvAssignmentDescription.setText(assignments.get(position).getDescription());
        ivVideo.setImageResource(R.mipmap.video_player_foreground);

        return convertView;
    }
}