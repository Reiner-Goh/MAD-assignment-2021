package sg.edu.np.madapplcation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConceptAdaptor extends RecyclerView.Adapter<ConceptViewHolder> {
    ArrayList<String> data;

    public ConceptAdaptor (ArrayList<String> input) { data = input; }

    public ConceptViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.conceptlayout,parent, false);
        return new ConceptViewHolder(item);
    }


    public void onBindViewHolder(ConceptViewHolder holder, int position) {
        String s = data.get(position);
        holder.concepttxt.setText(s);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
