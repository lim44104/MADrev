package sg.edu.np.madrevision;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle;
    TextView txtDesc;
    ImageView img;

    public ItemViewHolder(View itemView){
        super(itemView);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtDesc = itemView.findViewById(R.id.txtDesc);
        img = itemView.findViewById(R.id.img_profile);
    }
}
