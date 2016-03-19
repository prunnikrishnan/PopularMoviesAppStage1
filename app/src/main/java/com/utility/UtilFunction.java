package com.utility;

import android.net.Uri;

import com.myudacity.app.popularmoviesapp.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by ADMIN on 06-Mar-16.
 */
public class UtilFunction {

  public static String getMovieJSONString (String sortBy)
  {
      HttpURLConnection urlConnection = null;
      BufferedReader reader = null;

      // Will contain the raw JSON response as a string.
      String movieDetailsJSON = null;
      String mMovieDBUrl = "http://api.themoviedb.org/3/discover/movie";

      try {
          //Construct the URL to make a call to themovidb.org
          final String SORT_BY = "sort_by";
          final String API_KEY = "api_key";
          String theMOvieDBApiKey = BuildConfig.OPEN_WEATHER_MAP_API_KEY;

          Uri movieDBBaseUrl = Uri.parse(mMovieDBUrl).buildUpon()
                  .appendQueryParameter(SORT_BY, sortBy)
                  .appendQueryParameter(API_KEY, theMOvieDBApiKey)
                  .build();


          URL url = new URL(movieDBBaseUrl.toString());
          // Create the request to themovidb.org, and open the connection
          urlConnection = (HttpURLConnection) url.openConnection();
          urlConnection.setRequestMethod("GET");
          urlConnection.connect();


          // Read the input stream into a String
          InputStream inputStream = urlConnection.getInputStream();
          StringBuffer buffer = new StringBuffer();
          if (inputStream == null) {
              // Nothing to do.
             return null;
          }
          reader = new BufferedReader(new InputStreamReader(inputStream));
          String line;
          while ((line = reader.readLine()) != null) {
              // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
              // But it does make debugging a *lot* easier if you print out the completed
              // buffer for debugging.
              buffer.append(line + "\n");
          }
          if (buffer.length() == 0) {
              // Stream was empty.  No point in parsing.
              return null;
          }

          // Returning the movie details
          return movieDetailsJSON = buffer.toString();

      } catch (ProtocolException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }

      return  null;
  }

}
