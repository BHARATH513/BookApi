package com.acer.bookapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<BookModel> books;


    public MyAdapter(MainActivity bookTask, ArrayList<BookModel> bookModels) {

        this.context=bookTask;
        this.books=bookModels;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.rowdesign,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, final int i) {
        BookModel model=books.get(i);
        myViewHolder.bookTitle.setText(model.title);
        Picasso.with(context).load(model.bookimage).into(myViewHolder.bookImage);
        myViewHolder.bookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] book=new String[3];
                book[0]=books.get(i).getTitle();
                book[1]=books.get(i).getBookimage();
                book[2]=books.get(i).getDescription();
                Intent in=new Intent(context,BookDeatail.class);
                in.putExtra("data",book);
                context.startActivity(in);

            }
        });


    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage=itemView.findViewById(R.id.bookimage);
            bookTitle=itemView.findViewById(R.id.booktitle);
        }
    }
}
