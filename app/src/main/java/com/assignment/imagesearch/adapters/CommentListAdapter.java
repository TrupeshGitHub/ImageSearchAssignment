package com.assignment.imagesearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.imagesearch.R;
import com.assignment.imagesearch.model.ImageCommentEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    private Context mContext;
    private List<ImageCommentEntity> commentList;

    public CommentListAdapter(Context context) {
        mContext = context;
    }

    public void setListData(List<ImageCommentEntity> list) {
        commentList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_image_comments, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageCommentEntity data = commentList.get(position);

        holder.comment.setText(data.comment);

        String timeStamp = new SimpleDateFormat("dd MMM yyyy hh:mm aa").format(new Date(data.timestamp));
        holder.commentDate.setText(timeStamp);

    }

    @Override
    public int getItemCount() {
        return commentList != null ? commentList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView commentDate, comment;

        public ViewHolder(View view) {
            super(view);
            commentDate = view.findViewById(R.id.commentDate);
            comment = view.findViewById(R.id.comment);
        }
    }

}
