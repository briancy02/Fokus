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

public class QuestionArrayAdapter extends ArrayAdapter<Response>{
    private Context context;
    private List<Response> responses;

    public QuestionArrayAdapter(Context context, List<Response> list)
    {
        super(context, R.layout.row_question_layout, list);
        this.context = context;
        this.responses = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row_question_layout, parent, false);
        TextView tvQuestionNumber = convertView.findViewById(R.id.tvQuestionNumber);
        TextView tvQuestion = convertView.findViewById(R.id.tvQuestion);
        EditText etStudentAnswer = convertView.findViewById(R.id.etStudentAnswer);

        tvQuestionNumber.setText("Question " + (position+1));
        tvQuestion.setText(responses.get(position).getQuestion()+ "");
        responses.get(position).setAnswerStudent(etStudentAnswer.getText().toString().trim());




        return convertView;
    }
}