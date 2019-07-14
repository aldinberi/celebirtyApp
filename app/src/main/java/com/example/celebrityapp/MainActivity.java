package com.example.celebrityapp;


import android.graphics.Bitmap;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private  List<Celebrity> celebrities = new ArrayList<Celebrity>();

    private String list=" ";
    private ImageView celebImage;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Celebrity lookingFor;
    private ConstraintLayout gameLayout;
    private Button platButton;
    private TextView startText;


    private void generate(){
        Random rand = new Random();

        lookingFor = celebrities.get(rand.nextInt(celebrities.size()));
        donwloadImage(lookingFor.getImage());

        int locationOfWin = rand.nextInt(4);

        List<String> names = new ArrayList<String>();

        for(int i =0; i<4; i++)
            if(i == locationOfWin)
                names.add(lookingFor.getName());
            else
                names.add(celebrities.get(rand.nextInt(celebrities.size())).getName());

        button0.setText(names.get(0));
        button1.setText(names.get(1));
        button2.setText(names.get(2));
        button3.setText(names.get(3));


    }

    public void check(View view){
        Button name = (Button) view;


        if(name.getText().toString().compareTo(lookingFor.getName())==0)
            Toast.makeText(this, "Correct :)", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Wrong :(", Toast.LENGTH_SHORT).show();
        generate();
    }

    public void play(View view){
        platButton.setVisibility(View.INVISIBLE);
        startText.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

    }

    private void donwloadImage(String url){
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {
            myImage = task.execute(url).get();

            celebImage.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       DownloadContent task = new DownloadContent();
        celebImage = findViewById(R.id.celebImage);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        platButton = findViewById(R.id.playButton);
        startText = findViewById(R.id.startTextView);
        gameLayout = findViewById(R.id.gameLayout);

        try {
            list =task.execute().get();
        } catch (Exception e){
            e.printStackTrace();
        }

        Document celebs = Jsoup.parse(list);

        Elements links = celebs.select("img");

        for (Element link : links) {
           Celebrity celebrity = new Celebrity();
           celebrity.setImage(link.attr("src"));
           celebrity.setName(link.attr("alt"));
           celebrities.add(celebrity);
        }

        generate();



    }
}
