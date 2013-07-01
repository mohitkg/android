package com.example.add_seq;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

public class AnimListener implements AnimationListener {
	int length;

	public AnimListener(int l) {
		length = l;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		  /* ImageView im = (ImageView) findViewById(length);
		   im.clearAnimation();
	       LayoutParams lp = new LayoutParams(im.getWidth(), im.getHeight());
	       lp.setMargins(50, 100, 0, 0);
	       im.setLayoutParams(lp);*/

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}
