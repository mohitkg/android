package com.example.add_seq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class menu_teacher extends Activity{
	Context ccontext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.menu_teacher);
	    
	   // setContentView(R.layout.test);
	  //  ImageView im;
	 //   im = (ImageView) findViewById(R.id.img_view1);
	    
	    ccontext=this;
	    
	    
	    
	    for(int j=3;j<6;j++){
		    Sql info = new Sql(this);
		    info.open();
		    String data = info.getData(""+j);
		    info.close();
		    String[] names = data.split("\n");
		    int l = names.length;
		    int layout_ids[] = {R.id.layout1, R.id.layout2, R.id.layout3};
		    /*Log.d("database length", "" + l);
		 	Log.d("database data", "first " + names[0]);
		    Log.d("database data", "second : " + names[1]);
		    Log.d("database data", "third : " + names[2]);*/
		   // if(j==3)
	    	LinearLayout ll = (LinearLayout) findViewById(layout_ids[j-3]);
	   
		    Log.d("","done l: "+l);	    	    
		    
		    for(int i=0; i < l ; i++){
		    	Log.d("","done names i: "+i+names[i]);
		    	LinearLayout ll2 = new LinearLayout(this);
		    	ll2.setOrientation(LinearLayout.VERTICAL);
		    	/*
		        CheckBox ch = new CheckBox(this);
		        ll2.addView(ch);
		        */
		    	
		    		ImageButton addIV = new ImageButton(this);
		    				String imm ="/mnt/sdcard/Seq/"+ names[i] +"/i1.jpg";
		    				BitmapDrawable d = (BitmapDrawable) BitmapDrawable.createFromPath(imm);
		    						Log.d("","done i: "+i);
		    				addIV.setImageDrawable(d);
		    						Log.d("","done i: "+i);
		    								//addIV.setMaxHeight(1);
		    								//addIV.setMaxWidth(1);
		    				LinearLayout.LayoutParams imageparams = new LinearLayout.LayoutParams(150, 180);
		    								//  LinearLayout.LayoutParams imageparams2 = new LinearLayout.LayoutParams(null);
		    				imageparams.setMargins(25, 20, 10, 0);
		    				addIV.setLayoutParams(imageparams);
			//   addIV.
			    ll2.addView(addIV);
			    
			    			Log.d("","done click 1st i: "+i);
			    			addIV.setOnClickListener(handleOnClick(addIV,names[i],j));
			    			Log.d("","done click 2nd i: "+i);
			    			TextView addTV = new TextView(this);
			    				addTV.setId(j*10+i);
			    					Log.d("Ids set", names[i] + " has id " + j*10+i);
			    				addTV.setText(names[i]);
								    LinearLayout.LayoutParams textparams = new LinearLayout.LayoutParams(180, 40);
								    addTV.setTextSize(25);
								    addTV.setGravity(Gravity.CENTER);
								    //addTV.
								   addTV.setLayoutParams(textparams);
								  ll2.addView(addTV);
				//ll.set
			    ll.addView(ll2);
		    }
        Animation translateY = AnimationUtils.loadAnimation(this, R.anim.tranlate_y);
        ll.startAnimation(translateY);
	  }
	 }
	
	View.OnClickListener handleOnClick(final ImageButton button,final String names,final int length) {
	  //  int click = 0;
		return new View.OnClickListener() {
			int click = 0;
			public void onClick(View v) {
	        	click = (click + 1) % 2;
	        	
	        	int id = button.getId();
	        	
	        	
	        	//button.setImageResource(resId);
	        	ImageButton imgbt = (ImageButton)findViewById(R.id.img);
	        	//imgbt.
	        	
	        	Animation blinking_animation = AnimationUtils.loadAnimation(menu_teacher.this, R.anim.tween);
	        	if(click == 1)
	        		v.startAnimation(blinking_animation);
	        	else if(click == 0){
	        		v.clearAnimation();
	        	}
	        //	animation();
	 	       
	        	//Log.d("IDs", "name: " + names + " id: " + id);
	        	//rotate_animation.
	        /*	rotate_animation.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(ccontext, arrange.class);
			        	intent.putExtra("names", names);
			        	intent.putExtra("length", length);
						startActivity(intent);
					}
				});
	        	if(rotate_animation.hasEnded())
	        		startActivity(intent);
*/	        }

			private void animation() {
				// TODO Auto-generated method stub
				 Animation ranim = AnimationUtils.loadAnimation(menu_teacher.this, R.anim.rotate);
		 	     button.setAnimation(ranim);
				
			}
	    };
	}
	
	
	public void add(View v)		// this function is for add button
	{
		Intent intent = new Intent(this, MainActivity.class);		
	    startActivity(intent);
	}
	
	
}
