package com.muliamaulana.denius.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.muliamaulana.denius.R;
import com.muliamaulana.denius.WebViewActivity;
import com.muliamaulana.denius.model.Article;
import com.muliamaulana.denius.model.Source;
import com.muliamaulana.denius.model.TimeAgo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by muliamaulana on 8/18/2018.
 */
public class HeadlinesAdapter extends RecyclerView.Adapter<HeadlinesAdapter.HeadlinesViewHolder> {

    private ArrayList<Article> articleList = new ArrayList<>();
    private Context context;

    public HeadlinesAdapter() {
    }

    @NonNull
    @Override
    public HeadlinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.headlines_item, parent, false);

        return new HeadlinesViewHolder(view);
    }

    public void addAll(List<Article> list) {
        articleList.clear();
        articleList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final HeadlinesViewHolder holder, int position) {
        Source source = articleList.get(position).getSource();

        final String title = articleList.get(position).getTitle();
        final String source_name = source.getName();
        final String url = articleList.get(position).getUrl();

        holder.title.setText(title);
        holder.source.setText(source_name);
        holder.published.setText(articleList.get(position).getPublishedAt());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateString = articleList.get(position).getPublishedAt();
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long milisec = Objects.requireNonNull(date).getTime();
        final String timeAgo = TimeAgo.getTimeAgo(milisec);
        holder.published.setText(timeAgo);


        String img_url = articleList.get(position).getUrlToImage();
        Glide.with(holder.itemView.getContext())
                .load(img_url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.thumbnail)
                        .centerCrop())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                intent.putExtra("time", source_name + " | " + timeAgo);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class HeadlinesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title, source, published;
        private ProgressBar progressBar;
        private CardView cardView;

        public HeadlinesViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.headlines_thumbnail);
            title = itemView.findViewById(R.id.headlines_title);
            published = itemView.findViewById(R.id.headlines_published);
            source = itemView.findViewById(R.id.headlines_source);
            progressBar = itemView.findViewById(R.id.headlines_progressBar);
            cardView = itemView.findViewById(R.id.cardview_headlines);

        }
    }
}
