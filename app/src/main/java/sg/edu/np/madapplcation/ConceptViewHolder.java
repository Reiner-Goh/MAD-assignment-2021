package sg.edu.np.madapplcation;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ConceptViewHolder extends RecyclerView.ViewHolder {
    TextView concepttxt;

    public ConceptViewHolder(View itemView){
        super(itemView);
        concepttxt = itemView.findViewById(R.id.ConceptText);
    }
}
