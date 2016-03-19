package com.utility;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ADMIN on 06-Mar-16.
 */
public class MyImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MovieBean> mMovieDetails;
    private String mPosterUrl = "http://image.tmdb.org/t/p/w185/";

    private String[] mImageURLs = {
            "http://image.tmdb.org/t/p/w185//inVq3FRqcYIRl2la8iZikYYxFNR.jpg",
            "http://image.tmdb.org/t/p/w185//inVq3FRqcYIRl2la8iZikYYxFNR.jpg",
            "http://image.tmdb.org/t/p/w185//inVq3FRqcYIRl2la8iZikYYxFNR.jpg",
            "http://image.tmdb.org/t/p/w185//inVq3FRqcYIRl2la8iZikYYxFNR.jpg",
            "http://image.tmdb.org/t/p/w185//inVq3FRqcYIRl2la8iZikYYxFNR.jpg",
            "http://image.tmdb.org/t/p/w185//inVq3FRqcYIRl2la8iZikYYxFNR.jpg"};


    public MyImageAdapter(Context context,ArrayList<MovieBean> m)
    {
        super();
        this.mContext= context;
        this.mMovieDetails =m;
    }

    //Shouldn't be null. Implement this. Or the whole code wont work!!
    @Override
    public int getCount() {
        return mMovieDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);

            //Tried setting the height and width. but couldn't find how to keep the
            //orginal size of the image. So commented the below line. But worked perfectly
            //imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT ));

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(4, 4, 4, 4);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(mContext).load(mPosterUrl + mMovieDetails.get(position).getPosterURL()).into(imageView);
        return imageView;

    }
}
