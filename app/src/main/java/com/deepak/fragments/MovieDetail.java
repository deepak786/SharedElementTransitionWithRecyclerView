package com.deepak.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.deepak.R;
import com.deepak.bean.Movie;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetail extends Fragment {
    private ImageView movieImage;
    private TextView movieName, movieRating, movieYear, movieGenre;

    public MovieDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        movieImage = (ImageView) rootView.findViewById(R.id.movieImage);
        movieName = (TextView) rootView.findViewById(R.id.movieName);
        movieRating = (TextView) rootView.findViewById(R.id.movieRating);
        movieYear = (TextView) rootView.findViewById(R.id.movieYear);
        movieGenre = (TextView) rootView.findViewById(R.id.movieGenre);

        Bundle b = getArguments();
        if (b != null) {
            String transitionName = b.getString("transitionName");
            Movie movie = (Movie) b.getSerializable("movie");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                movieImage.setTransitionName(transitionName);
            }

            if (movie != null) {
                if (!TextUtils.isEmpty(movie.getImage()))
                    Picasso.with(getActivity()).load(movie.getImage()).into(movieImage);
                else
                    movieImage.setImageDrawable(null);

                movieName.setText(movie.getTitle());
                movieRating.setText(String.valueOf(movie.getRating()));
                movieYear.setText(String.valueOf(movie.getReleaseYear()));
                movieGenre.setText(movie.getGenre().toString());
            }
        }

        return rootView;
    }

}
