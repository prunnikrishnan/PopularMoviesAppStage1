package com.myudacity.app.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.utility.MovieBean;

public class MovieInDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_in_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Adding Up Action
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Getting the movieBean with the details of the Movie
        MovieBean movie = getIntent().getParcelableExtra("movie");
        String details = movie.toString();

        //Getting the values from the Intent and set it in the UI
        ImageView imageView = (ImageView)findViewById(R.id.movie_poster);
        Picasso.with(this).load(getString(R.string.movie_poster_url)+movie.getPosterURL()).into(imageView);

        //Set the movie details
        TextView orginalTitle = (TextView)findViewById(R.id.movie_orginal_title);
        orginalTitle.setText(movie.getOriginalTitle());
        TextView releaseDate = (TextView)findViewById(R.id.movie_releasedate);
        releaseDate.setText(movie.getReleaseDate());
        TextView userRating = (TextView)findViewById(R.id.movie_userrating);
        userRating.setText(movie.getUserRating());
        TextView overView = (TextView)findViewById(R.id.movie_overview);
        //Setting scroll
        overView.setMovementMethod(new ScrollingMovementMethod());
        overView.setText(movie.getOverView());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_in_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings_movie_in_detail) {
            Intent settingsIntent = new Intent(this,SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
