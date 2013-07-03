package com.example.add_seq;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class arrange extends Activity{

	public static final String LOG_TAG = "AudioRecordTest";
	View viewmoving;
	Rect hitRect;
	int length;
	String str;
	int correct=0;
	int droppedimage=0;
	MediaPlayer mPlayer=null;
	List<Integer> shufindex;
	LayoutParams params;
	View ll,ll2;
	String names;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.arrange);
	    Intent intent =getIntent();
	    names=intent.getStringExtra("names");
	    length = intent.getIntExtra("length", 3);
	    
	    hitRect=new Rect();
	    str = "/mnt/sdcard/Seq/"+ names;
	    ll =findViewById(R.id.ll);
	    ll2 =findViewById(R.id.ll2);	    
	    ll2.setOnDragListener(new MyDragListener());
	    params = new LinearLayout.LayoutParams(100,100);
	    int i=1;
	    
	    shufindex = new ArrayList<Integer>(length);
	    for(i=0;i<length;i++)
	    	shufindex.add(i);
    	Collections.shuffle(shufindex);
    	
    	Log.d("","im array1");
	    //ImageView[] im=new ImageView[length];
	    Log.d("","im array2");
	    int j;	    
	    for(i=0;i<length;i++)
	    {
	    	
	    	ImageView im=new ImageView(this);
	    	ImageView im2=new ImageView(this);
	    	
	    	j=shufindex.get((i));
	    	Log.d("","correct "+i+" to "+j);
		    File f=new File(str+"/i"+(j+1)+".jpg");		    
		    Log.d("","im array3i "+i);
		    Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
		    Log.d("","im array4j "+j);
		    params.setMargins(20, 10, 20, 10);		    
		    im.setLayoutParams(params);
		    im2.setLayoutParams(params);
		    im.setImageBitmap(bmp);
		    im2.setImageResource(R.drawable.border);
		    
		    im.setId(j);
		    im2.setId(i+length);
		    //im[i-1].setMaxHeight(i);
		    //im[i-1].setMaxWidth(i);
	    	im.setOnTouchListener(new MyTouchListener());
		    ((LinearLayout) ll).addView(im);
		    ((LinearLayout) ll2).addView(im2);		    
		    Log.d("","im array5");		    
	    }		    
	}

	public void readstory(View arg0)
	{
		if(correct==length)
		//if(true)
		{

			//put animation
			TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 150);
			animation.setDuration(1000);
			animation.setFillAfter(false);
			animation.setAnimationListener(new AnimListener(length));
			animation.setFillAfter(true);
			ImageView im = (ImageView) findViewById(length);
			ImageView im1 = (ImageView) findViewById(length+1);
			ImageView im2 = (ImageView) findViewById(length+2);
			if(length>3){
				ImageView im3 = (ImageView) findViewById(length+3);
				im3.startAnimation(animation);
			}
			if(length>4){
				ImageView im4 = (ImageView) findViewById(length+4);
				im4.startAnimation(animation);
			}
			
			
			im.startAnimation(animation);
			im1.startAnimation(animation);
			im2.startAnimation(animation);
			
			//finish();
		}
		else if(droppedimage==length)
		{
			//wrong answer
			int i,j;
			correct=0;
			droppedimage=0;
			shufindex = new ArrayList<Integer>(length);
			for(i=0;i<length;i++)
			{
				shufindex.add(i);
				ImageView im3=(ImageView) findViewById((i+length));
				ViewGroup owner = (ViewGroup) im3.getParent();
		        owner.removeView(im3);
			}
			Collections.shuffle(shufindex);
	    	
	    	Log.d("","im array1");
		    //ImageView[] im=new ImageView[length];
		    Log.d("","im array2");		    
		    for(i=0;i<length;i++)
		    {
		    	
		    	ImageView im=new ImageView(this);
		    	ImageView im2=new ImageView(this);
		    	
		    	j=shufindex.get((i));
		    	Log.d("","correct "+i+" to "+j);
			    File f=new File(str+"/i"+(j+1)+".jpg");		    
			    Log.d("","im array3i "+i);
			    Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
			    Log.d("","im array4j "+j);
			    params.setMargins(20, 10, 20, 10);		    
			    im.setLayoutParams(params);
			    im2.setLayoutParams(params);
			    im.setImageBitmap(bmp);
			    im2.setImageResource(R.drawable.border);
			    im.setId(j);
			    im2.setId(i+length);
			    //im[i-1].setMaxHeight(i);
			    //im[i-1].setMaxWidth(i);
		    	im.setOnTouchListener(new MyTouchListener());
			    ((LinearLayout) ll).addView(im);
			    ((LinearLayout) ll2).addView(im2);		    
			    Log.d("","im array5");		    
		    }			
		}
	}
	
	
public final class MyTouchListener implements OnTouchListener {
    private static final long ONE_SECOND = 1000;
    int clickCount = 0;
	long startTime = 0;
	long duration;
	Toast toast;
	View layout;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public boolean onTouch(View view, MotionEvent motionEvent) {
    	//for image zooming 
    	
    	
    	//static final int MAX_DURATION = 500;
    	switch(motionEvent.getAction() & MotionEvent.ACTION_MASK)
        {
    		
            /*case MotionEvent.ACTION_DOWN:
            	
                startTime = System.currentTimeMillis();
                Log.d("toast", "stat time of ACTION_DOWN :" + startTime);
                clickCount++;
                break;*/
    	case MotionEvent.ACTION_DOWN:
		    	ClipData data = ClipData.newPlainText("", "");
		        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
		        view.startDrag(data, shadowBuilder, view, 0);
		        
		        //view.setVisibility(View.INVISIBLE);
		        viewmoving=view;
		        if(mPlayer!=null)
		        {
		        	mPlayer.stop();
		        	mPlayer.release();
		        	mPlayer = null;
		        }
		        startPlaying();
		        Log.d("touching","touching");
		      //  return true;
       
        case MotionEvent.ACTION_UP:
            	
            	clickCount++;

                if (clickCount==1){
                    startTime = System.currentTimeMillis();
                    
                }

                else if(clickCount == 2)
                {	
                    duration =  System.currentTimeMillis() - startTime;
                    if(duration <= ONE_SECOND)
                    {   	LayoutInflater inflater = getLayoutInflater();
		                	layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_layout_root));
		                	ImageView img = (ImageView) layout.findViewById(R.id.timage);
		                	//str = "/mnt/sdcard/Seq/"+ names;
		                	int id = view.getId();
		                	File f=new File(str+"/i"+(id+1)+".jpg");		    
			    		    Log.d("","id: copied "+id);
			    		    Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
			    		    img.setImageBitmap(bmp);
			    		    Log.d("", "ho gya!!");
			    		    img.setTag("done");
		                	//img.setImageResource(R.id.plus);
		                	//img.setImageResource(view.getId());
		                	toast = new Toast(arrange.this);
		            		//toast.setGravity(Gravity.FILL, 0, 0);
		            		toast.setDuration(Toast.LENGTH_SHORT);
		            		toast.setView(layout);
		            		//toast.setView(viewmoving);
		            		toast.show();
		            		
		                    	
                    	
                            /*Toast.makeText(arrange.this, "double tap",Toast.LENGTH_SHORT).show();*/
                            Log.d("toast", "duration : " + "toast was made");
                            clickCount = 0;
                            duration = 0;
                    }else{
                        clickCount = 1;
                        startTime = System.currentTimeMillis();
                    }
                    break;             
                }
        }
    	return true;
		
		
	//Code for drag and drop	
    	/*	
      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
        
     */ 
    }
  }

public void startPlaying() {
    mPlayer = new MediaPlayer();
    Log.d("","audio enter play");
    try {
    	Log.d("","audio enter play try");
    	int id=viewmoving.getId();
        String f=str+"/audio"+(id+1)+".3gp";
        Log.d("","audio f: "+f);
        mPlayer.setDataSource(f);
        mPlayer.prepare();
        mPlayer.start();
    } catch (IOException e) {
    	Log.d("","audio enter play catch");
        Log.e(LOG_TAG, "prepare() failed");
    }
}
  @SuppressLint("NewApi")
class MyDragListener implements OnDragListener {
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
    public boolean onDrag(View v, DragEvent event) {
      //int action = event.getAction();
		int x = (int)event.getX();
	    int y = (int)event.getY();
      Log.d("","dragg");
      Log.d("x","point x: "+event.getX());
	  Log.d("y","point y: "+event.getX());
      switch (event.getAction()) {
      case DragEvent.ACTION_DRAG_STARTED:
        // Do nothing	    	  
        break;
      /*case DragEvent.ACTION_DRAG_ENTERED:
        v.setBackgroundDrawable(enterShape);
        break;
      case DragEvent.ACTION_DRAG_EXITED:
        v.setBackgroundDrawable(normalShape);
        break;*/
      case DragEvent.ACTION_DROP:
        // Dropped, reassign View to ViewGroup
    	  mPlayer.stop();
    	  mPlayer.release();
      	  mPlayer = null;
    	  float x1=event.getX();
    	  float y1=event.getY();
    	  boolean drop=false;
    	  Log.d("","enter x1 y1 "+x1+" "+y1);
    	  int i,j=0;
    	  for(i=0;i<length;i++)
    	  {
    		  ImageView im3=(ImageView) findViewById(i+length);
    		  im3.getHitRect(hitRect);
    		  if(hitRect.contains(x, y))
    		  {
    			  drop=true;
    			  j=i;
    		  }
    	  }
    	  Log.d("","drop img drop to : "+j);
    	  if(drop==true)
    	  {
    		  drop=false;
    		  	Log.d("enter cum","enter cum");
    		  	
    		  	int id=viewmoving.getId();
    		  	ImageView im3=(ImageView) findViewById(j+length);
    		  	if(im3.getTag()!="done")
    		  	{
	    		  	Log.d("","id: 1st im3 "+(j+length));
	    		  	File f=new File(str+"/i"+(id+1)+".jpg");		    
	    		    Log.d("","id: copied "+id);
	    		    Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
	    		    im3.setImageBitmap(bmp);
	    		    im3.setTag("done");
	    		    Log.d("","id: im3 last "+im3.getId());
			        View view = (View) event.getLocalState();
			        ViewGroup owner = (ViewGroup) view.getParent();
			        owner.removeView(view);
			        
			        //checking correctness
			        droppedimage++;
			        if(j==id)
			        	correct++;
			        Log.d("","correct: "+ correct);			        
    		  	}
    	  }	    	  
    	  
        break;
      /*case DragEvent.ACTION_DRAG_ENDED:
        v.setBackgroundDrawable(normalShape);*/
      default:
        break;
      }
      return true;
    }
}
  
}