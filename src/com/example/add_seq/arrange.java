package com.example.add_seq;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class arrange extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.arrange);
	    Intent intent =getIntent();
	    String names=intent.getStringExtra("names");
	    int length = intent.getIntExtra("length", 3);
	    String str = "/mnt/sdcard/Seq/"+ names;
	    View ll =findViewById(R.id.ll);
	    LayoutParams params = new LinearLayout.LayoutParams(100,100);
	    int i=1;
	    
	    for(i=1;i<=length;i++)
	    {
		    File f=new File(str+"/i"+i+".jpg");
		    ImageView im=new ImageView(this);
		    Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
		    params.setMargins(20, 10, 20, 10);
		    im.setLayoutParams(params);
		    im.setImageBitmap(bmp);
		    im.setMaxHeight(i);
		    im.setMaxWidth(i);
		    
		    ((LinearLayout) ll).addView(im);
	    }
	    	    	    
	}
}