package com.example.yashwanth_thotabe_happy;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yashwanth_thotabe_happy.data.QuoteData;
import com.example.yashwanth_thotabe_happy.data.QuoteListAsyncResponse;
import com.example.yashwanth_thotabe_happy.data.QuoteViewPagerAdapter;
import com.example.yashwanth_thotabe_happy.models.Quote;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuoteViewPagerAdapter quoteViewPagerAdapter;
    private ViewPager viewPager;
    private ImageView resume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteViewPagerAdapter = new QuoteViewPagerAdapter(getSupportFragmentManager(), getFragments());
        resume = findViewById(R.id.resume);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Resume.class);
                startActivity(intent);
            }
        });
        viewPager= findViewById(R.id.viewPager);
        viewPager.setAdapter(quoteViewPagerAdapter);

    }

//method
    private List<Fragment> getFragments(){
        final List<Fragment> fragmentList = new ArrayList<>();

        new QuoteData().getQuotes(new QuoteListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Quote> quotes) {


                for (int i=0;i<quotes.size();i++){

                    QuoteFragment quoteFragment = QuoteFragment.newInstance(
                            quotes.get(i).getQuote(),
                            quotes.get(i).getAuthor()
                    );
                    fragmentList.add(quoteFragment);
//                    Toast.makeText(MainActivity.this,quotes.get(i).getQuote(), Toast.LENGTH_SHORT).show();

//                    Log.d("data",quotes.get(i).getAuthor());


                }

                quoteViewPagerAdapter.notifyDataSetChanged();  //tell Adapter there is a change in the system
            }


        });

//
//        for (int i=0;i<;i++){
//            QuoteFragment quoteFragment = QuoteFragment.newInstance(
//                    "Agents are Best",
//                    "James Bond(007)"
//            );
//        }

        return fragmentList;
    }



}
