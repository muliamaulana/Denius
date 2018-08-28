package com.muliamaulana.denius.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.muliamaulana.denius.R;
import com.muliamaulana.denius.SourcesActivity;
import com.muliamaulana.denius.model.AllSources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muliamaulana on 8/19/2018.
 */
public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.SourcesViewHolder> {

    private ArrayList<AllSources> allSources = new ArrayList<>();

    public SourcesAdapter() {

    }

    @NonNull
    @Override
    public SourcesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sources_item,parent,false);

        return new SourcesViewHolder(view);
    }

    public void addAll(List<AllSources> list) {
        allSources.clear();
        allSources.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final SourcesViewHolder holder, final int position) {

        final String id = allSources.get(position).getId();
        holder.name.setText(allSources.get(position).getName());
        holder.url.setText(allSources.get(position).getUrl());
        holder.desc.setText(allSources.get(position).getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = allSources.get(position).getName();
                String url = allSources.get(position).getUrl();

                Toast.makeText(v.getContext(),id,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), SourcesActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("url",url);
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return allSources.size();
    }

    public class SourcesViewHolder extends RecyclerView.ViewHolder {
        TextView name, url, desc;
        CardView cardView;

        public SourcesViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sources_name);;
            url = itemView.findViewById(R.id.sources_url);
            desc = itemView.findViewById(R.id.sources_desc);
            cardView = itemView.findViewById(R.id.cardview_sources);
        }
    }
}
