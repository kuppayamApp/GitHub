package com.example.muchu.imageslider2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;


import java.util.HashMap;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity
        implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener
{
    ImageView circleImageView;
    SliderLayout sliderLayout ;
    TextView goToStore;
    HashMap<String, String> HashMapForURL ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderLayout = (SliderLayout)findViewById(R.id.slider);
        goToStore = (TextView) findViewById(R.id.go_to_store);
        circleImageView = (ImageView) findViewById(R.id.profile_image);

        goToStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,shopProfile.class);
                startActivity(intent);
            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,shopProfile.class);
                startActivity(intent);
            }
        });

//        FadingActionBarHelper helper = new FadingActionBarHelper()
//                .actionBarBackground(R.drawable.watch)
//                .headerLayout(R.layout.activity_main)
//                .contentLayout(R.layout.activity_main);
//        setContentView(helper.createView(this));
//        helper.initActionBar(this);

        //Call this method if you want to add images from URL .
        AddImagesUrlOnline();

        //Call this method to add images from local drawable folder .
        //AddImageUrlFormLocalRes();

        //Call this method to stop automatic sliding.
       sliderLayout.stopAutoCycle();

        for(String name : HashMapForURL.keySet()){

            TextSliderView textSliderView = new TextSliderView(MainActivity.this);
            ImageView imageView = new ImageView(MainActivity.this);


            textSliderView
                    .image(HashMapForURL.get(name))
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);

       }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);

       // sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//
        //sliderLayout.setCustomAnimation(new DescriptionAnimation());

       // sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(MainActivity.this);
    }

    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImagesUrlOnline(){

        HashMapForURL = new HashMap<String, String>();

        HashMapForURL.put("a", "https://firebasestorage.googleapis.com/v0/b/saveandretrivedata.appspot.com/o/uploads%2F2015-New-Autumn-Fashion-Brand-font-b-Men-b-font-Clothes-Slim-Fit-font-b-Men.jpg?alt=media&token=2f055b97-ed45-4cbb-aa3c-d658899c5594");
        HashMapForURL.put("b","https://firebasestorage.googleapis.com/v0/b/saveandretrivedata.appspot.com/o/uploads%2F11460104200751-Highlander-Blue-Slim-Fit-Denim-Shirt-7421460104200117-1.jpg?alt=media&token=77e35f54-8fd9-495c-83fd-3f983f077aad");
        HashMapForURL.put("c", "https://firebasestorage.googleapis.com/v0/b/saveandretrivedata.appspot.com/o/uploads%2Fde4050e7d669a97dbc0838f0fe125d53--formal-shirts-for-men-shirt-collars.jpg?alt=media&token=1ae00e01-91dd-445e-a664-32f6db7e7104");

    }


}