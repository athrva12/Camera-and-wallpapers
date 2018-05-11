package com.example.athrv.broadcastreciever;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button b;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.button2);
        im=(ImageView)findViewById(R.id.imageView2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it,0);
            }
        });

    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {

        Bundle b =data.getExtras();
        Bitmap bmp=(Bitmap)b.get("data");
        im.setImageBitmap(bmp);

    }
    public void setWall(View v)
    {
        @SuppressLint("ServiceCast") WallpaperManager wm = (WallpaperManager)getSystemService(WALLPAPER_SERVICE);

        Drawable d=im.getDrawable();
        BitmapDrawable bmd =(BitmapDrawable) d;
        Bitmap b=bmd.getBitmap();
        try
        {
            wm.setBitmap(b);

        }catch (IOException e)
        {
            Toast.makeText(this,"wallpaper does not set",Toast.LENGTH_LONG).show();
        }
    }
}
