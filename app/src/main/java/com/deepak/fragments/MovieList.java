package com.deepak.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepak.MainActivity;
import com.deepak.R;
import com.deepak.adapter.MovieAdapter;
import com.deepak.bean.Movie;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieList extends Fragment {
    private RecyclerView movieList;
    private String moviesDummyJsonString = "";
    private Movie[] mList;
    private MovieAdapter adapter;
    private Context context;

    public MovieList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        context = getActivity();
        movieList = (RecyclerView) rootView.findViewById(R.id.movieList);

        moviesDummyJsonString = readMovieJson();
        mList = new Gson().fromJson(moviesDummyJsonString, Movie[].class);
        adapter = new MovieAdapter(context, mList, this);
        movieList.setLayoutManager(new LinearLayoutManager(context));
        movieList.setHasFixedSize(true);
        movieList.setAdapter(adapter);

        return rootView;
    }


    /**
     * function to read the dummy json
     *
     * @return json string of movie list
     */
    private String readMovieJson() {
        StringBuilder buf = new StringBuilder();
        InputStream json = null;
        try {
            json = context.getAssets().open("movies.json");
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * function to open the movie detail fragment
     *
     * @param position Movie list position
     */
    public void openMovieDetailFragment(int position, View view) {
        if (context instanceof MainActivity) {
            Movie movie = mList[position];
            MovieDetail movieDetail = new MovieDetail();
            Bundle bundle = new Bundle();
            bundle.putString("transitionName", "transition" + position);
            bundle.putSerializable("movie", movie);

            movieDetail.setArguments(bundle);
            ((MainActivity) context).showFragmentWithTransition(this, movieDetail, "movieDetail", view, "transition" + position);
        }
    }

}
