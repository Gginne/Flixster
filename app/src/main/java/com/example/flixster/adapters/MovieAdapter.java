package com.example.flixster.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder((movieView));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass the 'context' here
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.movie_dialog,null);
                //Set Dialog Components
                ImageView d_poster = (ImageView) view.findViewById(R.id.dialogPoster);
                Glide.with(context).load(movie.getBackdropPath()).into(d_poster);
                TextView d_title = (TextView) view.findViewById(R.id.dialogTitle) ;
                d_title.setText(movie.getTitle());
                TextView d_overview = (TextView) view.findViewById(R.id.dialogOverview);
                d_overview.setText(movie.getOverview());
                RatingBar d_rating = (RatingBar) view.findViewById(R.id.dialogRating);
                d_rating.setRating(movie.getRating());

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                //Set Custom Dialog Layout
                alertDialog.setView(view);
                AlertDialog dialog = alertDialog.create();




                dialog.show();
            }

        });
    }



    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView overview;
        ImageView poster;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.movieTitle);
            overview = itemView.findViewById(R.id.movieOverview);
            poster = itemView.findViewById(R.id.moviePoster);

        }

        public void bind(Movie movie) {
            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());
            String imageUrl;
            //Dynamically set image to poster or backdrop depending on phone orientation
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
            } else {
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context)
                    .load(imageUrl)
                    .into(poster);
        }
    }
}
