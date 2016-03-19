package com.utility;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ADMIN on 08-Mar-16.
 */
public class MovieBean implements Parcelable
{
     String mPosterURL;



    String mOriginalTitle;
     String mOverView;
     String mUserRating;
     String mreleaseDate;

    public MovieBean(String posterURL,String originalTitle, String overView,String userRating, String releaseDate)
    {
        this.mPosterURL = posterURL;
        this.mOriginalTitle = originalTitle;
        this.mOverView = overView;
        this.mUserRating = userRating;
        this.mreleaseDate = releaseDate;
    }

    public String getPosterURL()
    {
        return mPosterURL;
    }

    public String getReleaseDate() {
        return mreleaseDate;
    }

    public String getUserRating() {
        return mUserRating;
    }

    public String getOverView() {
        return mOverView;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String toString()
    {
       return mOriginalTitle+" "+mPosterURL+" "+mOverView+" "+mreleaseDate+" "+mUserRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPosterURL);
        dest.writeString(mOriginalTitle);
        dest.writeString(mOverView);
        dest.writeString(mUserRating);
        dest.writeString(mreleaseDate);

    }

    private MovieBean(Parcel in)
    {
        this.mPosterURL = in.readString();
        this.mOriginalTitle = in.readString();
        this.mOverView = in.readString();
        this.mUserRating = in.readString();
        this.mreleaseDate = in.readString();
    }

    public static final Parcelable.Creator<MovieBean> CREATOR = new Parcelable.Creator<MovieBean>() {

        @Override
        public MovieBean createFromParcel(Parcel source) {
            return new MovieBean(source);
        }

        @Override
        public MovieBean[] newArray(int size) {
            return new MovieBean[size];
        }
    };
}
