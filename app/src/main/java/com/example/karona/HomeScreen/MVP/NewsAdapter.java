package com.example.karona.HomeScreen.MVP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karona.HomeScreen.Model.NewsList;
import com.example.karona.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<NewsList> newsLists;
    Context context;
    ClickListener listener;

    public NewsAdapter(List<NewsList> newsLists, Context context, ClickListener listener) {
        this.newsLists = newsLists;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_news, parent, false);
        return new NewsAdapter.ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        NewsList news = newsLists.get(position);
        holder.author.setText("Source : "+news.getAuthor()+" ->");
        holder.heading.setText(news.getTitle());
        holder.desc.setText(news.getDesc());
        Picasso.get()
                .load(news.getUrlToImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsLists.size();
    }

    interface ClickListener {
        void newsClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ClickListener listener;
        TextView heading, desc, author;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView, final ClickListener listener) {
            super(itemView);
            this.listener = listener;

            heading = itemView.findViewById(R.id.heading);
            desc = itemView.findViewById(R.id.description);
            author = itemView.findViewById(R.id.link);
            imageView = itemView.findViewById(R.id.imageview);

            author.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.newsClicked(getAdapterPosition());
                }
            });

        }
    }
}
