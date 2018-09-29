package com.example.hp.houseonrent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<OwnerItem> ownerItemList;
    private Context context;

    public MyAdapter(List<OwnerItem> ownerItemList, Context context) {
        this.ownerItemList = ownerItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.owner_items,parent,false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OwnerItem item = ownerItemList.get(position);
        holder.Avail.setClickable(false);
        holder.Booked.setClickable(false);
        holder.Locality.setText("Locality : "+item.getLocality());
        holder.Review.setText("Review : "+item.getReview());
        if(item.getAvail().equals("No"))
        holder.Booked.toggle();
        else
            holder.Avail.toggle();
        Glide.with(context)
                .load(item.getHimage())
                .into(holder.Himage);
    }

    @Override
    public int getItemCount() {
        return ownerItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView Locality;
         public TextView Review;
         public RadioButton Booked;
        public RadioButton Avail;
        public ImageView Himage;

        public ViewHolder(View itemView) {
                super(itemView);
                Locality = (TextView) itemView.findViewById(R.id.locality);
            Review = (TextView) itemView.findViewById(R.id.review);
            Himage = (ImageView) itemView.findViewById(R.id.houseImage);
            Booked = (RadioButton) itemView.findViewById(R.id.booked);
            Avail = (RadioButton) itemView.findViewById(R.id.available);

        }
        }
}
