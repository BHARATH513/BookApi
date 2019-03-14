package com.acer.bookapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    String bookurl="https://www.googleapis.com/books/v1/volumes?q=";

    EditText searchbook;
    Button getbookData;
    ProgressBar progressBar;
    RecyclerView recyclerView;


    ArrayList<BookModel> bookModels;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchbook=findViewById(R.id.bookname);
        getbookData=findViewById(R.id.getData);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recycler);

        bookModels=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerView.setAdapter(new MyAdapter(this,bookModels));


    }

    public void click(View view) {
        new BookTask().execute();
    }



    class BookTask extends AsyncTask<String,Void,String>{

        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url=new URL(bookurl+searchbook.getText().toString());

                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                        urlConnection.connect();
                InputStream inputStream=urlConnection.getInputStream();
                Scanner scanner=new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()){
                    return scanner.next();
                }else {
                    return null;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            try {
                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("items");
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject volume=jsonArray.getJSONObject(i);
                    JSONObject volumeinfo=volume.getJSONObject("volumeInfo");
                    String bookTitle=volumeinfo.getString("title");
                    String bookdescription=volumeinfo.getString("description");
                    JSONObject imagelinks=volumeinfo.getJSONObject("imageLinks");
                    String bookimage=imagelinks.getString("thumbnail");
                    bookModels.add(new BookModel(bookTitle,bookimage,bookdescription));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }
}
