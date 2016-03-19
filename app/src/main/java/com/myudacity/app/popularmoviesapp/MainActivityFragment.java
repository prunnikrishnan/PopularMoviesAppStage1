package com.myudacity.app.popularmoviesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.utility.MovieBean;
import com.utility.MyImageAdapter;
import com.utility.UtilFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<MovieBean> mMovieDetails;
    private  MyImageAdapter myImageAdapter;
    public MainActivityFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        GridView movieGrid = (GridView)rootView.findViewById(R.id.movie_Grid);

        //Make a call to the themoviedb.org and form
        //an array of MovieBean that can be passed to the MyImageAdapter
        //It has to done by an AsyncTask
        FetchMovieDetails movieDetailsTask = new FetchMovieDetails();
        movieDetailsTask.execute();

        mMovieDetails = new ArrayList<MovieBean>();
        mMovieDetails.add(new MovieBean("","","","",""));
        myImageAdapter = new MyImageAdapter(getContext(),mMovieDetails);
        movieGrid.setAdapter(myImageAdapter);

        //Setting onClickItem Listener
        //Starts an Intent to Details Activity
        movieGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Adding the Position data to Intent
                Intent detailIntent = new Intent(getContext(),MovieInDetailActivity.class).putExtra("movie",mMovieDetails.get(position));
                startActivity(detailIntent);
            }
        });

        return rootView;

    }

    public class FetchMovieDetails extends AsyncTask<Void,Void,ArrayList<MovieBean>>
    {
        @Override
        protected ArrayList<MovieBean> doInBackground(Void... params) {
            ArrayList<MovieBean> movieDetails=  new ArrayList<MovieBean>();

            //Getting the value for sort_by from the shared preference
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
            String sortBy = settings.getString(getString(R.string.sort_by_key),getString(R.string.sort_by_default_value));

            //Getting the movie JSON
            String movieDetailsJSONString = UtilFunction.getMovieJSONString(sortBy);
            //Parse it to form a MovieBean[]
            if(movieDetailsJSONString!=null)
            {
                try
                {
                    JSONObject movieDetailsJSON = new JSONObject(movieDetailsJSONString);
                    JSONArray resultsArray=movieDetailsJSON.getJSONArray("results");
                    for(int i=0;i<resultsArray.length();i++)
                    {
                        JSONObject movieObject = resultsArray.getJSONObject(i);
                        MovieBean movieBean = new MovieBean(
                                movieObject.getString("poster_path"),
                                movieObject.getString("original_title"),
                                movieObject.getString("overview"),
                                movieObject.getString("vote_average"),
                                movieObject.getString("release_date"));
                        movieDetails.add(movieBean);
                    }
                    return movieDetails;
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieBean> movieBeans) {
            mMovieDetails.clear();
            for (MovieBean bean:movieBeans)
            {
               mMovieDetails.add(bean);
            }
            myImageAdapter.notifyDataSetChanged();
        }
    }


}
