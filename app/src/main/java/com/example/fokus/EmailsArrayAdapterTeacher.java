package com.example.fokus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EmailsArrayAdapterTeacher extends ArrayAdapter<String>{
    private Context context;
    private List<String> emails;

    public EmailsArrayAdapterTeacher(Context context, List<String> list)
    {
        super(context, R.layout.row_email_layout_teachers, list);
        this.context = context;
        this.emails = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row_email_layout_teachers, parent, false);
        TextView tvStudentEmail = convertView.findViewById(R.id.tvStudentEmail);
        tvStudentEmail.setText(emails.get(position));
        return convertView;
    }
}
