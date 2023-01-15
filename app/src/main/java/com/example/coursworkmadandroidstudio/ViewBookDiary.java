package com.example.coursworkmadandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Model.BookDiary;
import Model.DiaryManagment;

public class ViewBookDiary extends AppCompatActivity implements View.OnClickListener {
    TextView bookTitle , pagesRead, comments, teacher, date;
    Button backButton, sendEmail;
    Intent homeScr;
    FloatingActionButton editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int diaryId = intent.getIntExtra("diaryId", 0);
        BookDiary bdobj = DiaryManagment.getBookDiaryById(diaryId);
        setContentView(R.layout.activity_view_book_diary);
        bookTitle = (TextView) findViewById(R.id.contentBookTitle);
        pagesRead = (TextView) findViewById(R.id.textContentPages);
        comments = (TextView) findViewById(R.id.textContentComments);
        teacher = (TextView) findViewById(R.id.textContentTeacher);
        date = (TextView) findViewById(R.id.textContentDate);
        editButton= (FloatingActionButton) findViewById(R.id.editButton);
        backButton = (Button) findViewById(R.id.BtnbackToMain);
        sendEmail = (Button) findViewById(R.id.sendEmailTo);
        bookTitle.setText(bdobj.getTitleName());
        pagesRead.setText(bdobj.getPagesRead());
        comments.setText(bdobj.getBookComments());
        teacher.setText(bdobj.getParentName());
        date.setText(bdobj.getDateRead());
        Toast ehraw = Toast.makeText (this,"No email app installed!",Toast.LENGTH_LONG);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScr = new Intent(getApplicationContext(), MainScreenActivity.class);
                startActivity(homeScr);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScr = new Intent(getApplicationContext(), EditDiary.class);
                homeScr.putExtra("diaryId",diaryId);
                startActivity(homeScr);
            }
        });
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent emailIntent = new Intent( android.content.Intent.ACTION_SEND);
                String bookBody =
                        "Book Title: " + bdobj.getTitleName() + System.lineSeparator() +
                        "Pages read: " + bdobj.getPagesRead() + System.lineSeparator() +
                        "Comments: " + bdobj.getBookComments() + System.lineSeparator() +
                        "Teacher: " + bdobj.getParentName() + System.lineSeparator() +
                        "Date read: " + bdobj.getDateRead();

                emailIntent.setType("plain/text");

                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[] { "k2054852@kingston.ac.uk" });

                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        "Diary Entry Copy");

                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        bookBody);

                startActivity(Intent.createChooser(
                        emailIntent, "Send mail..."));
                /* Uri mailUri = Uri.parse("mailto:test@mail.com");
                String subject = "This is a test email";
                String body = "This is my wonderful test email.\n\n";
                body += "This is not spam at all, but very very useful.\n\n";
                body += "I could click the button many times and send lots of them.";
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,mailUri);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_, body);
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                } else {
                    ehraw.show();
                }*/

                }
        });

    }

    @Override
    public void onClick(View view) {

    }
}