package com.vortex.vortex.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vortex.vortex.Interface.ItemClickListener;
import com.vortex.vortex.Model.RSSObject;
import com.vortex.vortex.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView txtTitle, txtContent, txtDate;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FeedViewHolder(View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtDate = itemView.findViewById(R.id.txtDate);
        txtContent = itemView.findViewById(R.id.txtContent);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), true);
        return true;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private RSSObject rssObject;
    private Context context;
    private LayoutInflater inflater;

    public FeedAdapter(RSSObject rssObject, Context context) {
        this.rssObject = rssObject;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_news, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtContent.setText(rssObject.getItems().get(position).getContent());
        String date = rssObject.getItems().get(position).getPubDate().toString();

        DateFormat originDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", new Locale("ru", "RU"));
        String finalDate=null;
        try {
            finalDate = dateFormat.format(originDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.txtDate.setText(finalDate);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (!isLongClick) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink())).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (rssObject == null)
            return 0;
        return rssObject.items.size();
    }
}
