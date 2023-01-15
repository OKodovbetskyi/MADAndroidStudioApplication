package com.example.coursworkmadandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Model.BookDiary;
import Model.DiaryManagment;

public class EditDiary extends AppCompatActivity {
    EditText bookTitle , pagesRead, comments, teacher, date;
    Button saveChanges;
    Intent viewDiary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary);
        Intent intent = getIntent();
        int diaryId = intent.getIntExtra("diaryId", 0);
        BookDiary bdobj = DiaryManagment.getBookDiaryById(diaryId);
        bookTitle = (EditText) findViewById(R.id.EditbookTitle);
        pagesRead = (EditText) findViewById(R.id.EditpagesRead);
        comments = (EditText) findViewById(R.id.Editcomments);
        teacher = (EditText) findViewById(R.id.EditteacherName);
        date = (EditText) findViewById(R.id.EditdateRead);
        saveChanges = (Button) findViewById(R.id.btnSaveChanges);
        bookTitle.setText(bdobj.getTitleName());
        pagesRead.setText(bdobj.getPagesRead());
        comments.setText(bdobj.getBookComments());
        teacher.setText(bdobj.getParentName());
        date.setText(bdobj.getDateRead());
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bdobj.setTitleName(bookTitle.getText().toString());
                bdobj.setPagesRead(pagesRead.getText().toString());
                bdobj.setBookComments(comments.getText().toString());
                bdobj.setParentName(teacher.getText().toString());
                bdobj.setDateRead(date.getText().toString());
                viewDiary = new Intent(getApplicationContext(), ViewBookDiary.class);
                viewDiary.putExtra("diaryId",diaryId);
                startActivity(viewDiary);
            }
        });
    }
}