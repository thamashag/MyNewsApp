package com.example.mynewsapp;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class NewsFullActivity extends AppCompatActivity {

    WebView webView;
    RecyclerView thirdRecyclerView;
    List<Article> thirdArticleList = new ArrayList<>();
    RelatedNewsAdapter thirdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_full);

        String url = getIntent().getStringExtra("url");
        webView = findViewById(R.id.web_view);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        getNews();

        thirdRecyclerView = findViewById(R.id.related_news_recycler_view);
        setupThirdRecyclerView();


    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }

    void setupThirdRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        thirdRecyclerView.setLayoutManager(layoutManager);
        thirdAdapter = new RelatedNewsAdapter(thirdArticleList);
        thirdRecyclerView.setAdapter(thirdAdapter);
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

                            thirdArticleList = response.getArticles();
                            thirdAdapter.updateData(thirdArticleList);
                            thirdAdapter.notifyDataSetChanged();

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