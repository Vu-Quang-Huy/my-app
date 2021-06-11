package com.example.vuquanghuy_b17dcat098_btl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuquanghuy_b17dcat098_btl.databinding.ItemRank1Binding;
import com.example.vuquanghuy_b17dcat098_btl.model.User;

import java.util.ArrayList;

public class RankAdapter  extends RecyclerView.Adapter<RankAdapter.RankViewHolder> {
    Context context;
    ArrayList<User> users;
    public RankAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public RankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_rank1,parent,false);

        return new RankViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RankViewHolder holder, int position) {
        User user=users.get(position);
        holder.binding.txtName.setText(user.getName());
        holder.binding.DiemSo.setText(user.getCoins()+"");
        holder.binding.txtSTT.setText(String.format("#%d",position+1));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class RankViewHolder extends RecyclerView.ViewHolder{

        ItemRank1Binding binding;
        public RankViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= ItemRank1Binding.bind(itemView);
        }
    }




}
