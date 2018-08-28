package com.muliamaulana.denius;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.muliamaulana.denius.adapter.CategoryAdapter;
import com.muliamaulana.denius.adapter.HeadlinesSourcesAdapter;
import com.muliamaulana.denius.api.ClientAPI;
import com.muliamaulana.denius.api.NewsInterface;
import com.muliamaulana.denius.model.Headlines;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourcesActivity extends AppCompatActivity {

    private RecyclerView recyclerHeadlines, recyclerAllNews;
    private HeadlinesSourcesAdapter headlinesAdapter;
    private CategoryAdapter categoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);

        recyclerHeadlines = findViewById(R.id.sources_headlines);
        recyclerAllNews = findViewById(R.id.sources_allnews);

        String name = getIntent().getStringExtra("name");
        String url = getIntent().getStringExtra("url");
        String id = getIntent().getStringExtra("id");

        getSupportActionBar().setTitle(name);
        getSupportActionBar().setSubtitle(url);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        headlinesAdapter = new HeadlinesSourcesAdapter();
        categoryAdapter = new CategoryAdapter();

        recyclerHeadlines.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerHeadlines.setAdapter(headlinesAdapter);

        LinearLayoutManager layoutAllnews = new LinearLayoutManager(this);
        recyclerAllNews.setLayoutManager(layoutAllnews);
        recyclerAllNews.setAdapter(categoryAdapter);
        recyclerAllNews.setNestedScrollingEnabled(false);

        loadHeadlines(id);
        loadAllNews(id);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadHeadlines(String id) {
        NewsInterface newsInterface = ClientAPI.getNewsAPI().create(NewsInterface.class);
        Call<Headlines> headlinesCall = newsInterface.getHeadlinesSource(id,BuildConfig.API_KEY);
        headlinesCall.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(@NonNull Call<Headlines> call, @NonNull Response<Headlines> response) {
                if (response.isSuccessful()){
                    Log.d("Headlines Sources", response.toString());
                    headlinesAdapter.addAll(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Headlines> call, @NonNull Throwable t) {
                Log.e("Headlines Failure", t.toString());
            }
        });
    }

    private void loadAllNews(String id) {
        NewsInterface newsInterface = ClientAPI.getNewsAPI().create(NewsInterface.class);
        Call<Headlines> headlinesCall = newsInterface.getEverythingSource(id,BuildConfig.API_KEY);
        headlinesCall.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(@NonNull Call<Headlines> call, @NonNull Response<Headlines> response) {
                if (response.isSuccessful()){
                    Log.d("All News Sources", response.toString());
                    categoryAdapter.addAll(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Headlines> call, @NonNull Throwable t) {
                Log.e("All News Failure", t.toString());
            }
        });
    }

}
