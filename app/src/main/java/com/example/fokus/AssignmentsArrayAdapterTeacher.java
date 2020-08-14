package com.example.fokus;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AssignmentsArrayAdapterTeacher extends ArrayAdapter<Assignment> {
    private Context context;
    private List<Assignment> assignments;

    public AssignmentsArrayAdapterTeacher(Context context, List<Assignment> list)
    {
        super(context, R.layout.row_teacher_layout, list);
        this.context = context;
        this.assignments = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView tvAssignmentNameTeacher = convertView.findViewById(R.id.tvAssignmentNameTeacher);
        TextView tvAssignmentDescriptionTeacher = convertView.findViewById(R.id.tvAssignmentDescriptionTeacher);
        ImageView ivVideoTeacher = convertView.findViewById(R.id.ivVideoTeacher);

        tvAssignmentNameTeacher.setText(assignments.get(position).getAssignmentName() + "");
        tvAssignmentDescriptionTeacher.setText(assignments.get(position).getTeacherEmail() + "");
        ivVideoTeacher.setImageResource(R.mipmap.video_player_foreground);

        return convertView;
    }
}
