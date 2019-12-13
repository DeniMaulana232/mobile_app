package com.pikaboy.basah.lihatkategori;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pikaboy.basah.R;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {
    public TextView namab,catb,dateb,name;
    public ImageView image;
    public ImageView image1;


    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);

        namab = itemView.findViewById(R.id.namabarang);
        catb = itemView.findViewById(R.id.categorybarang);
        dateb = itemView.findViewById(R.id.datebarang);
        image = (ImageView) itemView.findViewById(R.id.imageViewbarang);
        image1 = (ImageView) itemView.findViewById(R.id.imageVprofil);
        name = (TextView) itemView.findViewById(R.id.textVname);
    }
}
