package com.assignment.imagesearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.assignment.imagesearch.R;
import com.assignment.imagesearch.model.Image;

import java.util.List;

public class ImageDetailViewPagerAdapter extends RecyclerView.Adapter<ImageDetailViewPagerAdapter.ViewHolder> {

    private final Context mContext;
    private List<Image> imageList;

    public ImageDetailViewPagerAdapter(Context context) {
        mContext = context;
    }

    public void setListData(List<Image> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_viewpager_image_detail, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Image imageData = imageList.get(position);
        setImageToView(imageData, holder.image);
    }

    private void setImageToView(Image imageData, ImageView image) {
        Glide.with(mContext).load(imageData.getLink())
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(150, 250)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.progress_animation)
                .into(image);
    }

    @Override
    public int getItemCount() {
        return imageList != null ? imageList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
        }
    }

}