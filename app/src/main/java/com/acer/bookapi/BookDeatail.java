package com.acer.bookapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookDeatail extends AppCompatActivity {

    ImageView iv;
    TextView tvtitle,tvdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_deatail);
        iv=findViewById(R.id.imageview);
        tvdesc=findViewById(R.id.desc);
        tvtitle=findViewById(R.id.textview);
        Bundle b= getIntent().getExtras();
        String[] s=b.getStringArray("data");
        tvtitle.setText(s[0]);
        Picasso.with(this).load(s[1]).into(iv);
        tvdesc.setText(s[2]);

    }
}
