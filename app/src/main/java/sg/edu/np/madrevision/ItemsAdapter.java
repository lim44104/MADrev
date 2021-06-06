package sg.edu.np.madrevision;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    ArrayList<User> data;

    public ItemsAdapter(ArrayList<User> input){
        data = input;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        //Modify the RecyclerView such that if the last digit of the name
        // contains 7, a different layout is used as shown below.
        // This new layout will have an additional ImageView that
        // occupies the width of the screen and has a width-to-height ratio of 1:1.
        if(viewType == 7){
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.rv_item2,
                    parent,
                    false
            );
        }
        else {
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.rv_item,
                    parent,
                    false
            );
        }

        return new ItemViewHolder(item);
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(data.get(position).name.substring(data.get(position).name.length()-1));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        User u = data.get(position);
        holder.txtTitle.setText(u.name);
        holder.txtDesc.setText(u.description);

        //Add an onClickListener for the image such that
        // it will show an Alert Dialog with the corresponding name.
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Debug", "Image clicked");

                new AlertDialog.Builder(holder.img.getContext())
                        .setTitle("Profile")
                        .setMessage(u.name)
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            // Clicking on the view button will direct the user to the MainActivity
                            // to show the profile page.
                            public void onClick(DialogInterface dialog, int which) {
                                Intent viewProfile = new Intent(holder.img.getContext(), MainActivity.class);
                                viewProfile.putExtra("id",position);
                                holder.img.getContext().startActivity(viewProfile);
                            }
                        })
                        .setNegativeButton("Close",null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
