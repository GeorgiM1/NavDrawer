package com.example.android.navdrawer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.navdrawer.Model.PhotoModel;
import com.example.android.navdrawer.Model.Photos;
import com.example.android.navdrawer.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 1/18/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context context;
    PhotoModel photoModel;

    public ItemAdapter(Context context, PhotoModel photoModel) {
        this.context = context;
        this.photoModel = photoModel;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_adapter,parent, false);
        ViewHolder viewHolder = new ViewHolder (view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        Photos photo = photoModel.getPhotos().get(position);

        Picasso.with(context).load(photo.getImage_url()).centerCrop().fit().into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return photoModel.getPhotos().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_img)
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
