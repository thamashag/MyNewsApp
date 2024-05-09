package com.example.mynewsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>{

    List<Article> articleList;
    public NewsRecyclerAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_row, parent, false);
        return new NewsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        // Calculate the indices of the articles for the current row
        int startIndex = position * 3;

        // Populate data for the first article
        Article article1 = articleList.get(startIndex);
        holder.titleTextView1.setText(article1.getTitle());
        holder.sourceTextView1.setText(article1.getSource().getName());
        Picasso.get().load(article1.getUrlToImage())
                .error(R.drawable.no_image_icon)
                .placeholder(R.drawable.no_image_icon)
                .into(holder.imageView1);

        // Populate data for the second article
        if (startIndex + 1 < articleList.size()) {
            Article article2 = articleList.get(startIndex + 1);
            holder.titleTextView2.setText(article2.getTitle());
            holder.sourceTextView2.setText(article2.getSource().getName());
            Picasso.get().load(article2.getUrlToImage())
                    .error(R.drawable.no_image_icon)
                    .placeholder(R.drawable.no_image_icon)
                    .into(holder.imageView2);
        }

        // Populate data for the third article
        if (startIndex + 2 < articleList.size()) {
            Article article3 = articleList.get(startIndex + 2);
            holder.titleTextView3.setText(article3.getTitle());
            holder.sourceTextView3.setText(article3.getSource().getName());
            Picasso.get().load(article3.getUrlToImage())
                    .error(R.drawable.no_image_icon)
                    .placeholder(R.drawable.no_image_icon)
                    .into(holder.imageView3);
        }

        holder.itemView.setOnClickListener((v -> {
            Intent intent = new Intent(v.getContext(), NewsFullActivity.class);
            intent.putExtra("url", article1.getUrl());
            v.getContext().startActivity(intent);

        }));

    }


    void updateData(List<Article> data) {
        articleList.clear();
        articleList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // Calculate the number of rows required to display all articles
        return (int) Math.ceil((double) articleList.size() / 3);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView1, titleTextView2, titleTextView3;
        TextView sourceTextView1, sourceTextView2, sourceTextView3;
        ImageView imageView1, imageView2, imageView3;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView1 = itemView.findViewById(R.id.article_title_1);
            titleTextView2 = itemView.findViewById(R.id.article_title_2);
            titleTextView3 = itemView.findViewById(R.id.article_title_3);
            sourceTextView1 = itemView.findViewById(R.id.article_source_1);
            sourceTextView2 = itemView.findViewById(R.id.article_source_2);
            sourceTextView3 = itemView.findViewById(R.id.article_source_3);
            imageView1 = itemView.findViewById(R.id.article_image_view_1);
            imageView2 = itemView.findViewById(R.id.article_image_view_2);
            imageView3 = itemView.findViewById(R.id.article_image_view_3);
        }
    }
}

