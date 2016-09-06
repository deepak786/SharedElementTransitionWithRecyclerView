package com.deepak.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.deepak.R;
import com.deepak.bean.Movie;
import com.deepak.fragments.MovieList;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Rockstar on Sep 06,2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {
    private Context context;
    private Movie[] movieList;
    private MovieList fragment;

    public MovieAdapter(Context context, Movie[] movies, MovieList movieListFragment) {
        this.context = context;
        this.movieList = movies;
        this.fragment = movieListFragment;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Movie movie = movieList[position];
        holder.pos = position;
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if ((bitmap != null) && (holder.pos == position))
                    holder.movieImage.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if ((errorDrawable != null) && (holder.pos == position))
                    holder.movieImage.setImageDrawable(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if ((placeHolderDrawable != null) && (holder.pos == position))
                    holder.movieImage.setImageDrawable(placeHolderDrawable);
            }
        };

        holder.movieImage.setTag(target);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.movieImage.setTransitionName("transition" + position);
        }

        if (!TextUtils.isEmpty(movie.getImage())) {
            Picasso.with(context).load(movie.getImage()).into(target);
        } else {
            holder.movieImage.setImageDrawable(null);
        }

        holder.movieName.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.length;
    }

    class Holder extends RecyclerView.ViewHolder {
        private ImageView movieImage;
        private TextView movieName;
        private int pos = -1;

        public Holder(View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.movieImage);
            movieName = (TextView) itemView.findViewById(R.id.movieName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.openMovieDetailFragment(getAdapterPosition(), v.findViewById(R.id.movieImage));
                }
            });
        }
    }
}
