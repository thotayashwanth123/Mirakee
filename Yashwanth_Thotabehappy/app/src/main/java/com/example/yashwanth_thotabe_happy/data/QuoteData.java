package com.example.yashwanth_thotabe_happy.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.yashwanth_thotabe_happy.controller.AppController;
import com.example.yashwanth_thotabe_happy.models.Quote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuoteData {
    ArrayList<Quote> quoteArrayList = new ArrayList<>();
//    https://raw.githubusercontent.com/thotayashwanth123/Database-Quotes-JSON/master/quotes.json
    public void getQuotes(final QuoteListAsyncResponse callBack){
        String url="https://raw.githubusercontent.com/thotayashwanth123/Database-Quotes-JSON/master/quotes.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject quoteObject = response.getJSONObject(i);
                        Quote quote = new Quote();
                        quote.setQuote(quoteObject.getString("quoteText"));
                        quote.setAuthor(quoteObject.getString("quoteAuthor"));
                        Log.d("STUFF:: ",quoteObject.getString("quoteAuthor"));
                        quoteArrayList.add(quote);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
//                if whole data is processed from internet hen only we will pass the quoteArrayList
                if (null!= callBack) callBack.processFinished(quoteArrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}
