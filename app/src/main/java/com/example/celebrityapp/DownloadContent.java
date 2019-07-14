package com.example.celebrityapp;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadContent extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        String content = "";
        try {
            Document document = Jsoup.connect("http://www.posh24.se/kandisar").get();
            content = document.getElementById("webx_center").outerHtml();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}

