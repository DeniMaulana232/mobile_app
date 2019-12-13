package com.pikaboy.basah.lihatkategori;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pikaboy.basah.R;
import com.squareup.picasso.Picasso;

public class detailbarang extends AppCompatActivity {
    TextView judul,nama1,date1,deskripsi1,katagori;
    EditText pid1;
    ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailbarang);
        String pname = getIntent().getStringExtra("pname");
        String pid = getIntent().getStringExtra("pid");
        String date = getIntent().getStringExtra("date");
        String category = getIntent().getStringExtra("category");
        String image = getIntent().getStringExtra("image");
        String deskripsi = getIntent().getStringExtra("deskripsi");
        String nama = getIntent().getStringExtra("prace");
        Log.i("OUR VALUE",pname);
        Log.i("OUR VALUE 2",date);
        Log.i("OUR VALUE 3",category);
        Log.i("OUR VALUE 4",image);
        Log.i("OUR VALUE 5",deskripsi);
        Log.i("OUR VALUE 6",nama);
        Log.i("OUR VALUE",pid);
        Toast.makeText(this,""+pname, Toast.LENGTH_SHORT).show();
        //inisialisasi
        image1 = (ImageView) findViewById(R.id.imagegambar2);
        judul = (TextView) findViewById(R.id.judulberita);
        nama1 = (TextView) findViewById(R.id.namapenulis);
        date1 = findViewById(R.id.date);
        katagori = findViewById(R.id.katagoribarang);
        deskripsi1 = findViewById(R.id.deskripsi);
        pid1 = (EditText) findViewById(R.id.pidbarang);
        //implements
        Picasso.get().load(image).into(image1);
        judul.setText(pname);
        nama1.setText(nama);
        date1.setText(date);
        katagori.setText(category);
        deskripsi1.setText(deskripsi);
        pid1.setText(pid);


    }
}
