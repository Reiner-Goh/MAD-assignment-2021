package sg.edu.np.madapplcation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {



    ArrayList<Concept> conceptList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        EditText searchbar = findViewById(R.id.search_bar); // set up the respective widgets and buttons on the layout file


        // adding concepts to a list
        conceptList.add(new Concept("Introduction to Android","Learning about android and android studio"));
        conceptList.add(new Concept("Introduction to Java","Learning about java programming language"));
        conceptList.add(new Concept("Basic of Activity","Learn what are activities, the building blocks of any app"));
        conceptList.add(new Concept("Android Activity Lifecycle","Learn about how activities get created, started, and resumed as well as paused, stopped and destroyed"));
        conceptList.add(new Concept("Visual and Audio","Learn how to add videos and audio clips into your apps"));
        conceptList.add(new Concept("Event Handling","Learn new event listeners and respective code to run on actions like clicking"));
        conceptList.add(new Concept("RecyclerView","Learn how to make a recyclerView"));
        conceptList.add(new Concept("Designing of Mobile User Experience","Learn how to design for users in mind"));
        conceptList.add(new Concept("Data and File Storage","Learn shared preferences, SQL database and more"));
        conceptList.add(new Concept("Managing Play Store Release","Learn how to publish your app to the Google Play Store"));

        // setting up the recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ConceptAdaptor myAdaptor = new ConceptAdaptor(conceptList, this);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdaptor);
        searchbar.addTextChangedListener(new TextWatcher() { //check for text change for search bar
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString(), myAdaptor); //call filter function
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        Button LogOutButton = findViewById(R.id.LogOut);
        LogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginPageIntent = new Intent(ListActivity.this, LoginActivity.class);
                startActivity(LoginPageIntent);
                finish();
            }
        });
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void filter(String text, ConceptAdaptor myAdaptor){
        ArrayList<Concept> filteredList = new ArrayList<>();
        for(Concept item : conceptList){
            if (item.getConcept().toLowerCase().contains(text.toLowerCase())){ //check if letters in search bar match concept
                filteredList.add(item);
            }
        }
        myAdaptor.filterList(filteredList);
    }



}