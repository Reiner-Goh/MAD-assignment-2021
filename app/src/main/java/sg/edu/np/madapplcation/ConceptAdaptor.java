package sg.edu.np.madapplcation;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConceptAdaptor extends RecyclerView.Adapter<ConceptViewHolder> {
    private Context context;
    ArrayList<Concept> data;

    public ConceptAdaptor (ArrayList<Concept> input, Context context) {
        this.context = context;
        this.data = input;
    }

    public ConceptViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.conceptlayout,parent, false);
        return new ConceptViewHolder(item);
    }


    public void onBindViewHolder(ConceptViewHolder holder, int position) {
        Concept current_concept = data.get(position);
        holder.concepttxt.setText(Html.fromHtml("<u>" + current_concept.getConcept() + "</u>"));
        holder.conceptdesc.setText(current_concept.getDescription());
        holder.concepttxt.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebsiteOfConcept.class);
            intent.putExtra("Concept", current_concept.getConcept());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void filterList(ArrayList<Concept> filteredList){
        data = filteredList;
        notifyDataSetChanged(); //notify adaptor that dataset changed
    }
}
