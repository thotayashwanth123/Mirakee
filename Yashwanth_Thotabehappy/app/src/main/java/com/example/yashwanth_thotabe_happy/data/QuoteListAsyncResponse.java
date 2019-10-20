package com.example.yashwanth_thotabe_happy.data;

import com.example.yashwanth_thotabe_happy.models.Quote;

import java.util.ArrayList;

public interface QuoteListAsyncResponse {

    void processFinished(ArrayList<Quote>quotes);
}
