package com.example.mynewsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerAdapterNew extends RecyclerView.Adapter<NewsRecyclerAdapterNew.NewsViewHolder>{

    List<Article> articleList;
    NewsRecyclerAdapterNew(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_new, parent, false);
        return new NewsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        // Calculate the indices of the articles for the current row
        int startIndex = position * 4;

        // Populate data for the first article
        if (startIndex < articleList.size()) {
            Article article1 = articleList.get(startIndex);
            holder.titleTextView1.setText(article1.getTitle());
            holder.sourceTextView1.setText(article1.getSource().getName());
            Picasso.get().load(article1.getUrlToImage())
                    .error(R.drawable.no_image_icon)
                    .placeholder(R.drawable.no_image_icon)
                    .into(holder.imageView1);
        }

        // Populate data for the second article
        int secondIndex = startIndex + 1;
        if (secondIndex < articleList.size()) {
            Article article2 = articleList.get(secondIndex);
            holder.titleTextView2.setText(article2.getTitle());
            holder.sourceTextView2.setText(article2.getSource().getName());
            Picasso.get().load(article2.getUrlToImage())
                    .error(R.drawable.no_image_icon)
                    .placeholder(R.drawable.no_image_icon)
                    .into(holder.imageView2);
        }

        // Populate data for the third article
        int thirdIndex = startIndex + 2;
        if (thirdIndex < articleList.size()) {
            Article article3 = articleList.get(thirdIndex);
            holder.titleTextView3.setText(article3.getTitle());
            holder.sourceTextView3.setText(article3.getSource().getName());
            Picasso.get().load(article3.getUrlToImage())
                    .error(R.drawable.no_image_icon)
                    .placeholder(R.drawable.no_image_icon)
                    .into(holder.imageView3);
        }

        // Populate data for the fourth article
        int fourthIndex = startIndex + 3;
        if (fourthIndex < articleList.size()) {
            Article article4 = articleList.get(fourthIndex);
            holder.titleTextView4.setText(article4.getTitle());
            holder.sourceTextView4.setText(article4.getSource().getName());
            Picasso.get().load(article4.getUrlToImage())
                    .error(R.drawable.no_image_icon)
                    .placeholder(R.drawable.no_image_icon)
                    .into(holder.imageView4);
        }
    }

    void updateData(List<Article> data) {
        articleList.clear();
        articleList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // Calculate the number of rows required to display all articles
        return (int) Math.ceil((double) articleList.size() / 4);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView1, titleTextView2, titleTextView3, titleTextView4;
        TextView sourceTextView1, sourceTextView2, sourceTextView3, sourceTextView4;
        ImageView imageView1, imageView2, imageView3, imageView4;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView1 = itemView.findViewById(R.id.article_title_1);
            titleTextView2 = itemView.findViewById(R.id.article_title_2);
            titleTextView3 = itemView.findViewById(R.id.article_title_3);
            titleTextView4 = itemView.findViewById(R.id.article_title_4);
            sourceTextView1 = itemView.findViewById(R.id.article_source_1);
            sourceTextView2 = itemView.findViewById(R.id.article_source_2);
            sourceTextView3 = itemView.findViewById(R.id.article_source_3);
            sourceTextView4 = itemView.findViewById(R.id.article_source_4);
            imageView1 = itemView.findViewById(R.id.article_image_view_1);
            imageView2 = itemView.findViewById(R.id.article_image_view_2);
            imageView3 = itemView.findViewById(R.id.article_image_view_3);
            imageView4 = itemView.findViewById(R.id.article_image_view_4);
        }
    }
}
