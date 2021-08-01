package sg.edu.np.madapplcation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class CommentAdapter extends FirebaseRecyclerAdapter<Comments, CommentAdapter.MyViewHolder> {

    /*private Context mContext;
    private List<Comments> mComment;


    private FirebaseUser firebaseUser;*/

    public CommentAdapter(@NonNull FirebaseRecyclerOptions<Comments> options) {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull MyViewHolder holder,
                     int position, @NonNull Comments model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.userid.setText(model.getUserid());
        /*getUserInfo(holder.userid);*/

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.comment.setText(model.getComment());

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public MyViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new CommentAdapter.MyViewHolder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class MyViewHolder
            extends RecyclerView.ViewHolder {
        TextView userid, comment;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            userid = itemView.findViewById(R.id.username);
            comment = itemView.findViewById(R.id.comment);

        }
    }

    /*private void getUserInfo(final TextView userid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("UserData");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData user = dataSnapshot.getValue(UserData.class);
                userid.setText(user.getUsername());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
}


    /*@NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.comment_item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentAdapter.MyViewHolder holder, int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final Comments comment = mComment.get(position);

        holder.comment.setText(comment.getComment());
        getUserInfo(holder.image_profile, holder.username, comment.getUserid());

        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("publisherid", comment.getUserid());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView image_profile;
        public TextView username, comment;

        public MyViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            comment = itemView.findViewById(R.id.comment);
        }
    }

    private void getUserInfo(final ImageView imageView, final TextView username, String publisherid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(publisherid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData user = dataSnapshot.getValue(UserData.class);
                username.setText(user.getUsername());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/



    /*private Context mContext;
    private List<Comments> mComment;
    private String postid;

    private FirebaseUser firebaseUser;

    public CommentAdapter(Context context, List<Comments> comments){
        mContext = context;
        mComment = comments;

    }

    @NonNull
    @Override
    public CommentAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_item, parent, false);
        return new CommentAdapter.ImageViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final CommentAdapter.ImageViewHolder holder, final int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final Comments comment = mComment.get(position);

        holder.comment.setText(comment.getComment());
        getUserInfo(holder.username, comment.getPublisher());

        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("publisherid", comment.getPublisher());
                mContext.startActivity(intent);
            }
        });

        holder.image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("publisherid", comment.getPublisher());
                mContext.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (comment.getPublisher().equals(firebaseUser.getUid())) {

                    AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                    alertDialog.setTitle("Do you want to delete?");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    FirebaseDatabase.getInstance().getReference("Comments")
                                            .child(postid).child(comment.getCommentid())
                                            .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(mContext, "Deleted!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageView image_profile;
        public TextView username, comment;

        public ImageViewHolder(View itemView) {
            super(itemView);


            username = itemView.findViewById(R.id.username);
            comment = itemView.findViewById(R.id.comment);
        }
    }
    private void getUserInfo(final TextView username, String publisherid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("UserData").child(publisherid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserData user = snapshot.getValue(UserData.class);
                username.setText(user.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }*/


