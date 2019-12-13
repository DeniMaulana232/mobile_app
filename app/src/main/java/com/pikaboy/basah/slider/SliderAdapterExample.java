package com.pikaboy.basah.slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.pikaboy.basah.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;

    public SliderAdapterExample(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {


        switch (position) {
            case 0:

                viewHolder.imageViewBackground.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.sampah_slider));

                break;

            case 1:

                viewHolder.imageViewBackground.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.sampah_slider2));

                break;

            default:

                viewHolder.imageViewBackground.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.botol_slider));

                break;
//            case 1:
//                Glide.with(viewHolder.itemView)
//                        .load("@drawable/sampah_slider2")
//                        .into(viewHolder.imageViewBackground);
//                break;
//            default:
//                Glide.with(viewHolder.itemView)
//                        .load("@drawable/botol_slider")
//                        .into(viewHolder.imageViewBackground);
//                break;

        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 3;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.slider1);
            this.itemView = itemView;
        }
    }
}