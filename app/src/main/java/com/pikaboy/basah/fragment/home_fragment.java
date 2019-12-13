package com.pikaboy.basah.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.pikaboy.basah.R;
import com.pikaboy.basah.slider.SliderAdapterExample;
import com.pikaboy.basah.view.kerajinan;
import com.pikaboy.basah.view.sembako;
import com.pikaboy.basah.view.sampah;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class home_fragment extends Fragment {


    private ImageView kerajinan, sembako, sampah;

    public home_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        //////////slider
        SliderView sliderView = view.findViewById(R.id.imageSlider);
        SliderAdapterExample adapter = new SliderAdapterExample(getContext());
        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        kerajinan = view.findViewById(R.id.menu_kerajinan);
        kerajinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kerajinan = new Intent(getContext(), kerajinan.class);
                startActivity(kerajinan);
            }
        });
        sembako = view.findViewById(R.id.menu_sembako);
        sembako.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sembako = new Intent(getContext(), sembako.class);
                startActivity(sembako);
            }
        });
        sampah = view.findViewById(R.id.menu_sampah);
        sampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sampah = new Intent(getContext(), sampah.class);
                startActivity(sampah);
            }
        });
        return view;
    }




}
