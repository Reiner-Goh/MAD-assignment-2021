package sg.edu.np.madapplcation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> ConceptList = new ArrayList<>();
        ConceptList.add("Introduction to Android");
        ConceptList.add("Introduction to java");
        ConceptList.add("Basic of activity");
        ConceptList.add("Android Activity Lifecycle");
        ConceptList.add("Visual and Audio");
        ConceptList.add("Event Handling");
        ConceptList.add("RecyclerView");
        ConceptList.add("Designing of Mobile User Experience");
        ConceptList.add("Data and File Storage");
        ConceptList.add("Managing Play Store Release");
        ConceptList.add("Introduction to Android");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ConceptAdaptor myAdaptor = new ConceptAdaptor(ConceptList);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdaptor);
    }


}