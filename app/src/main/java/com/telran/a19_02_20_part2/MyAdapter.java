package com.telran.a19_02_20_part2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Person> list;
    private OnRowClickListener listener;

    public MyAdapter(OnRowClickListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
        for (int i = 0; i < 100; i++) {
            list.add(new Person("Person " + i, "Phone " + i));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MY_TAG", "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("MY_TAG", "onBindViewHolder: ");
        Person p = list.get(position);
        holder.nameTxt.setText(p.name);
        holder.phoneTxt.setText(p.phone);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(Person p){
        list.add(1,p);
        notifyItemInserted(1);
    }

    public void remove(int index){
        list.remove(index);
        notifyItemRemoved(index);
    }

    public void move(int from, int to){
        Person p = list.remove(from);
        list.add(to,p);
        notifyItemMoved(from, to);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameTxt;
        TextView phoneTxt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            phoneTxt = itemView.findViewById(R.id.phoneTxt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int pos = getAdapterPosition();
                        listener.onClick(pos,list.get(pos));
                    }
                }
            });
        }
    }

    interface OnRowClickListener{
        void onClick(int position,Person p);
    }
}
