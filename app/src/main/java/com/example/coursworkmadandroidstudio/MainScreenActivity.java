package com.example.coursworkmadandroidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import Model.BookDiary;
import Model.CustomAdapter;
import Model.DialogBox;
import Model.DiaryManagment;
import Model.SelectListener;

public class MainScreenActivity extends AppCompatActivity implements SelectListener {
    ListView listView;
    TextView textView;
    FloatingActionButton btnSearch, btnClear;
    Button addNewDiary, btn_search;
    Intent addDiaryScreen;
    Intent viewBookDiary;
    EditText searchText;
    List<BookDiary> filteredBooks = DiaryManagment.getListOfBooks();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView displayNoEntries;
        // ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        searchText = (EditText) findViewById(R.id.searchInputText);


       // DiaryManagment.addDiaryEntry(new BookDiary("Naxis", "2","asd","asdd","12/12/2022"));
        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        displayNoEntries = (TextView) findViewById(R.id.diaryStatus);
        if (DiaryManagment.getListOfBooks().size() == 0){
            displayNoEntries.setText("Currently you don't have any book diaries recorded.");
        } else {

        }
        // Initialize contacts
        // Create adapter passing in the sample user data
        CustomAdapter adapter = new CustomAdapter(filteredBooks, this);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
        btnSearch = (FloatingActionButton) findViewById(R.id.btnsearch);

        btnClear = (FloatingActionButton) findViewById(R.id.btnclear);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Search");
                if (searchText.getText().length() > 0){
                    filteredBooks = DiaryManagment.findBookIfAnyCharacterMatch(searchText.getText().toString());
                    // Create adapter passing in the sample user data
                    CustomAdapter adapter = new CustomAdapter(filteredBooks, MainScreenActivity.this);
                    // Attach the adapter to the recyclerview to populate items
                    rvContacts.setAdapter(adapter);
                    rvContacts.setLayoutManager(new LinearLayoutManager(MainScreenActivity.this));
                }


            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredBooks = DiaryManagment.getListOfBooks();
                // Create adapter passing in the sample user data
                CustomAdapter adapter = new CustomAdapter(filteredBooks, MainScreenActivity.this);
                // Attach the adapter to the recyclerview to populate items
                rvContacts.setAdapter(adapter);
                rvContacts.setLayoutManager(new LinearLayoutManager(MainScreenActivity.this));
            }
        });
        addNewDiary = (Button) findViewById(R.id.addNewDiaryEntry);
        addNewDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiaryScreen =  new Intent(getApplicationContext(), FormScreen.class);
                startActivity(addDiaryScreen);
            }
        });
    }
    @Override
    public void onItemClicked(BookDiary model) {
        viewBookDiary = new Intent(getApplicationContext(), ViewBookDiary.class);
        viewBookDiary.putExtra ("diaryId", model.getId());
        startActivity(viewBookDiary);

    }

    @Override
    public void onRemoveClicked(BookDiary model) {
        DialogBox db = new DialogBox(MainScreenActivity.this, "Warning", "Are you sure you want to remove entry?");
        if (db.getResult()){
            DiaryManagment.removeDiaryEntry(model);
        }
      //  DiaryManagment.removeDiaryEntry(model.getId());

    }
}