package com.example.user_photo_retrofit_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.Holder> {
    MainActivity mainActivity;
    ArrayList<Photos_Model_class> productList;
    public Recyclerview_Adapter(MainActivity mainActivity, ArrayList<Photos_Model_class> productList) {
        this.mainActivity=mainActivity;
        this.productList=productList;
    }

    @NonNull
    @Override
    public Recyclerview_Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.recyclerview_item_file,parent,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerview_Adapter.Holder holder, int position) {
        holder.title.setText(""+productList.get(position).getTitle());

        Glide
                .with(mainActivity)
                .load(productList.get(position).getUrl())
                .centerCrop()
                .placeholder(R.drawable.rotation)
                .into(holder.url);
        Glide
                .with(mainActivity)
                .load(productList.get(position).getThumbnailUrl())
                .centerCrop()
                .placeholder(R.drawable.rotation)
                .into(holder.thumbnailurl);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView title;
        CircleImageView url;
        ImageView thumbnailurl;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            url=itemView.findViewById(R.id.url);
            thumbnailurl=itemView.findViewById(R.id.thumbnailurl);
        }
    }
}
