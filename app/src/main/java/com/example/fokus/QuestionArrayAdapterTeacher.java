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

public class QuestionArrayAdapterTeacher extends ArrayAdapter<Response>{
    private Context context;
    private List<Response> responses;

    public QuestionArrayAdapterTeacher(Context context, List<Response> list)
    {
        super(context, R.layout.row_teacher_question_layout, list);
        this.context = context;
        this.responses = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row_teacher_question_layout, parent, false);
        TextView tvQuestionNumberTeacher = convertView.findViewById(R.id.tvQuestionNumberTeacher);
        TextView tvQuestionTeacher = convertView.findViewById(R.id.tvQuestionTeacher);
        TextView tvCorrectAnswer = convertView.findViewById(R.id.tvCorrectAnswer);

        tvQuestionNumberTeacher.setText("Question " + (position+1));
        tvQuestionTeacher.setText(responses.get(position).getQuestion()+ "");
        tvCorrectAnswer.setText(responses.get(position).getAnswerCorrect()+ "");




        return convertView;
    }
}