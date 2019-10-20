package com.example.yashwanth_thotabe_happy;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.concurrent.ThreadLocalRandom;

import static android.content.Context.MODE_PRIVATE;


public class QuoteFragment extends Fragment {

Context ctxt;
    public QuoteFragment() {
        // Required empty public constructor
    }

    SQLiteDatabase database;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        try
        {
             database = getActivity().openOrCreateDatabase("USERS",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS users (quotess VARCHAR,authorss VARCHAR)");}
catch (Exception e){
            e.printStackTrace();
        }



        // Inflate the layout for this fragment
        View quoteView = inflater.inflate(R.layout.fragment_quote, container, false);




        final TextView quoteText = quoteView.findViewById(R.id.quoteText);
        final TextView byAuthor = quoteView.findViewById(R.id.byAuthor);
        CardView cardView = quoteView.findViewById(R.id.cardview);
        ImageView share =quoteView.findViewById(R.id.sharing);
        final LikeButton likeButton =quoteView.findViewById(R.id.star_button);



//
//        likeButton.setOnLikeListener(new OnLikeListener() {
//            @Override
//            public void liked(LikeButton likeButton) {
//
//                database.execSQL("INSERT INTO newnotes (quotess,authorss) VALUES('" + quoteText.getText().toString() + "', '" + byAuthor.getText().toString() + "');");
//                Toast.makeText(getActivity(),"Favourites",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void unLiked(LikeButton likeButton) {
//
//                Toast.makeText(getActivity(),"UnLiked Quote",Toast.LENGTH_LONG).show();
//            }
//        });


        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

                Toast.makeText(getActivity(),"Favourites",Toast.LENGTH_LONG).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {

                Toast.makeText(getActivity(),"UnLiked Quote",Toast.LENGTH_LONG).show();
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareBody0 =quoteText.getText().toString() ;
                String shareBody1 =byAuthor.getText().toString() ;
                String shareBody="Quote: "+shareBody0+"\n"+" Author: "+shareBody1;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share Quote"));
            }
        });



        final String quote = getArguments().getString("quote");
        String name = getArguments().getString("author");

        int colors[]= new int[] {R.color.medium_blue,R.color.LightPurple,R.color.blueGray,R.color.chocolate,
        R.color.salmon,R.color.fuchsia,R.color.colorAccent,R.color.amber_900,R.color.lightGreen,R.color.dark_gray};

        quoteText.setText(quote);
        byAuthor.setText(" - "+name);

        cardView.setBackgroundResource(getRandomQuote(colors));
        return quoteView;



    }

    public static final QuoteFragment newInstance(String quote, String author){

        QuoteFragment fragment = new QuoteFragment();
        Bundle bundle =new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author",author );
        fragment.setArguments(bundle);

        return fragment;
    }

// Random background for cardView
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    int getRandomQuote(int [] colorArray){
        int color;
        int quotesArrayLen = colorArray.length;
        int RandomNum = ThreadLocalRandom.current().nextInt(quotesArrayLen);
        color=colorArray[RandomNum];
        return  color;
    }

}
