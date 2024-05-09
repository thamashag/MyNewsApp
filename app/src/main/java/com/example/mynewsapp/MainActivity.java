package com.example.mynewsapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Article> articleList = new ArrayList<>();
    NewsRecyclerAdapter adapter;

    RecyclerView secondRecyclerView;

    List<Article> secondArticleList = new ArrayList<>();


    NewsRecyclerAdapterNew secondAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.news_recycler_view);
        secondRecyclerView = findViewById(R.id.news_recycler_view1);

        setupRecyclerView();
        setupSecondRecyclerView();
        getNews();


    }

    void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewsRecyclerAdapter(articleList);
        recyclerView.setAdapter(adapter);
    }

    void setupSecondRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(layoutManager);
        secondAdapter = new NewsRecyclerAdapterNew(secondArticleList);
        secondRecyclerView.setAdapter(secondAdapter);
    }




    void getNews() {
        NewsApiClient newsApiClient = new NewsApiClient("55460c34c83744e3a26587081a1a7b1c");
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {

                        runOnUiThread(()->{
                            articleList = response.getArticles();
                            secondArticleList = response.getArticles();
                            //thirdArticleList = response.getArticles();
                            adapter.updateData(articleList);
                            secondAdapter.updateData(secondArticleList);
                            //thirdAdapter.updateData(thirdArticleList);
                            adapter.notifyDataSetChanged();
                            secondAdapter.notifyDataSetChanged();
                            //thirdAdapter.notifyDataSetChanged();

                        });

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.i("Got failure", throwable.getMessage());

                    }
                }
        );
    }



}