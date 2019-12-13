package com.pikaboy.basah.fragment_profile;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pikaboy.basah.R;
import com.pikaboy.basah.view.kategori;

/**
 * A simple {@link Fragment} subclass.
 */
public class PelapakFragment extends Fragment {


    private int PICK_MEDIA_GALLERY = 121;
    private int RESULT_CODE = 111;

    public PelapakFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pelapak, container, false);
        FloatingActionButton fab = view.findViewById(R.id.shortcut_post);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext(), kategori.class);
                startActivity(i);
            }
        });

        return view;
    }

//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_MEDIA_GALLERY && resultCode == RESULT_OK)
//        {
//
//            if (data.getClipData() != null)
//            {
//                int totalImage = data.getClipData().getItemCount();
//
//                if (totalImage > 5){
//                    Toast.makeText(getContext(), "Jumlah Maksimum Gambar 5", Toast.LENGTH_SHORT).show();
//                }else {
//
//                    for (int i = 0; i < totalImage; i++) {
//                        Uri fileUri = data.getClipData().getItemAt(i).getUri();
//
//                        try {
//
//                            InputStream is = getContentResolver().openInputStream(fileUri);
//
//                            Bitmap bitmap = BitmapFactory.decodeStream(is);
//                            fileDonelist.add(bitmap);
//                            fileUriList.add(fileUri);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//
//                        }
//
//                        imageListAdapter.notifyDataSetChanged();
//
//                    }
//                }
//            }else if(data.getData() != null){
//
//                Uri fileUri = data.getData();
//                try {
//
//                    InputStream is = getContentResolver().openInputStream(fileUri);
//
//                    Bitmap bitmap = BitmapFactory.decodeStream(is);
//                    fileDonelist.add(bitmap);
//                    fileUriList.add(fileUri);
//
//                }catch (Exception e){
//                    e.printStackTrace();
//
//                }
//                imageListAdapter.notifyDataSetChanged();
//            }
//
//
//        }

//    }
}