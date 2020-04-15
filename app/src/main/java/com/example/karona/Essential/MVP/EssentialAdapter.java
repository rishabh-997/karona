package com.example.karona.Essential.MVP;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karona.Essential.Model.EssentialList;
import com.example.karona.R;

import org.w3c.dom.Text;

import java.util.List;

public class EssentialAdapter extends RecyclerView.Adapter<EssentialAdapter.ViewHolder> {
    Context context;
    List<EssentialList> essentialLists;
    noteListener listener;

    public EssentialAdapter(Context context, List<EssentialList> essentialLists, noteListener listener) {
        this.context = context;
        this.essentialLists = essentialLists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EssentialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_essential, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EssentialAdapter.ViewHolder holder, int position) {
        final EssentialList list = essentialLists.get(position);
        holder.name.setText(list.getName());
        holder.contact.setText(list.getContact());
        holder.desc.setText(list.getDescription());
    }

    @Override
    public int getItemCount() {
        return essentialLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, contact, desc;
        noteListener listener;
        public ViewHolder(@NonNull View itemView, final noteListener listener) {
            super(itemView);
            this.listener = listener;
            name = itemView.findViewById(R.id.name);
            contact = itemView.findViewById(R.id.contact);
            desc = itemView.findViewById(R.id.description);

            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onContactClicked(getAdapterPosition());
                }
            });
        }
    }

    public interface noteListener {
        void onContactClicked(int position);
    }
}
