package devta.staggeredviewmaster;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.LinkedHashMap;
import java.util.Random;

import devta.staggeredview.StaggeredGridView;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        final StaggeredGridView staggeredGridView = findViewById(R.id.staggeredGridView);

        final LinkedHashMap<String, Uri> dataMap = new LinkedHashMap<>();

        dataMap.put("Men", Uri.parse("http://45.58.45.156:1080/e-commerce/male.png"));
        dataMap.put("Women", Uri.parse("http://45.58.45.156:1080/e-commerce/female.png"));
        dataMap.put("Kids", Uri.parse("http://45.58.45.156:1080/e-commerce/kids.png"));
        dataMap.put("Home", Uri.parse("http://45.58.45.156:1080/e-commerce/home.png"));
        dataMap.put("Gadgets", Uri.parse("http://45.58.45.156:1080/e-commerce/gadgets.png"));
        dataMap.put("Beauty", Uri.parse("http://45.58.45.156:1080/e-commerce/beauty.png"));

        staggeredGridView.setData(dataMap);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staggeredGridView.setViewDimensions(30,70);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staggeredGridView.setViewMaxWidthInDP(getRandomInt());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataMap.containsKey("Brandon")){
                    dataMap.remove("Brandon");
                }else {
                    dataMap.put("Brandon", Uri.parse("http://45.58.45.156:1080/e-commerce/brandone.png"));
                }
                staggeredGridView.setData(dataMap);
            }
        });
    }

    private int getRandomInt(){
        Random r = new Random();
        return r.nextInt( 300 - 120 + 1) + 120;
    }
}
