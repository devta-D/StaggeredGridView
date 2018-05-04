package devta.staggeredviewmaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import devta.staggeredview.StaggeredData;
import devta.staggeredview.StaggeredGridView;
import devta.staggeredview.StaggeredItemClickListener;

public class SampleActivity extends AppCompatActivity implements StaggeredItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        final StaggeredGridView staggeredGridView = findViewById(R.id.staggeredGridView);

        final List<StaggeredData> staggeredDataList = new ArrayList<>();

        staggeredDataList.add(new StaggeredData(0, "Men", "http://45.58.45.156:1080/e-commerce/male.png"));
        staggeredDataList.add(new StaggeredData(1, "Women", "http://45.58.45.156:1080/e-commerce/female.png"));
        staggeredDataList.add(new StaggeredData(2, "Kids", "http://45.58.45.156:1080/e-commerce/kids.png"));
        staggeredDataList.add(new StaggeredData(3, "Home", "http://45.58.45.156:1080/e-commerce/home.png"));
        staggeredDataList.add(new StaggeredData(4, "Gadgets", "http://45.58.45.156:1080/e-commerce/gadgets.png"));
        staggeredDataList.add(new StaggeredData(5, "Beauty", "http://45.58.45.156:1080/e-commerce/beauty.png"));

        staggeredGridView.setData(staggeredDataList);
        staggeredGridView.setClickListener(this);

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
                if(staggeredDataList.size()==7){
                    staggeredDataList.remove(staggeredDataList.size()-1);
                }else {
                    staggeredDataList.add(new StaggeredData(6, "Brandon",
                            "http://45.58.45.156:1080/e-commerce/brandone.png"));
                }
                staggeredGridView.setData(staggeredDataList);
            }
        });
    }

    private int getRandomInt(){
        Random r = new Random();
        return r.nextInt( 300 - 120 + 1) + 120;
    }

    @Override
    public void onStaggeredItemClick(StaggeredData item) {
        Toast.makeText(this, "Clicked on : "+item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
