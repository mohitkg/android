package com.example.add_seq;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final int SELECT_PICTURE = 1;

    public String selectedImagePath;
    //ADDED
    public String filemanagerstring;
    ImageView im;
    public static final String LOG_TAG = "AudioRecordTest";
    public static String mFileName = null;

    public RecordButton mRecordButton = null;
    public MediaRecorder mRecorder = null;

    public PlayButton   mPlayButton = null;
    public MediaPlayer   mPlayer = null;
    Context ccontext;
    public int audbutclick=0;
    File file;
    String root;
    int image_num = 1;    
    int done=0;
    String name;
    boolean inpimg=false;
    boolean inpaud=false;
    boolean inptxt=false;
    LinearLayout newll;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ccontext=this;
        readname(0);
        root = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
        File createDir = new File(root+"Seq"+File.separator);
        if(!createDir.exists()) {
            createDir.mkdir();
        }        
        newll = new LinearLayout(this);
        newll.setOrientation(Configuration.ORIENTATION_PORTRAIT);
        /*
	        ((Button) findViewById(R.id.img))
	        .setOnClickListener(new OnClickListener() {
	
	            public void onClick(View arg0) {
	            	
	                // in onCreate or any event where your want the user to
	                // select a file
	            	
	            	//file = new File(root + "Seq" + File.separator +"img"+image_take+".jpg");
	            	if(image_num<6 && inpimg==false)
	                {	           
	                
			                Intent intent = new Intent();
			                intent.setType("image/*");
			                intent.setAction(Intent.ACTION_GET_CONTENT);
			                startActivityForResult(Intent.createChooser(intent,
			                        "Select Picture"), SELECT_PICTURE);
			           
	                }
	            	else if(inpimg==true)
	            		Toast.makeText(ccontext, "Click next!!!", Toast.LENGTH_LONG).show();
	            	else
	            		Toast.makeText(ccontext, "Maximum 5 images allowed!!!", Toast.LENGTH_LONG).show();
	            }
	        });
	*//*
	        ((Button) findViewById(R.id.audio))
	        .setOnClickListener(new OnClickListener() {
	
	            public void onClick(View arg0) {
	
	                // in onCreate or any event where your want the user to
	                // select a file
	            	if(image_num<6 && inpaud==false)
	                {
	                	
	                		if(audbutclick==0)
			            	{
			            		audbutclick=1;
			            		
				                mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
				                mFileName += "/Seq/"+name+"/audio"+image_num+".3gp";
				                View ll=findViewById(R.id.audll);
				                mRecordButton = new RecordButton(ccontext);
				                ((LinearLayout) ll).addView(mRecordButton,
				                    new LinearLayout.LayoutParams(
				                        ViewGroup.LayoutParams.WRAP_CONTENT,
				                        ViewGroup.LayoutParams.WRAP_CONTENT,
				                        0));
				                mPlayButton = new PlayButton(ccontext);
				
				                ((LinearLayout) ll).addView(mPlayButton,
				                    new LinearLayout.LayoutParams(
				                        ViewGroup.LayoutParams.WRAP_CONTENT,
				                        ViewGroup.LayoutParams.WRAP_CONTENT,
				                        0));
				                //setContentView(ll);
				            }
	                }
	            	else if(inpaud==true)
	            		Toast.makeText(ccontext, "Already has audio for this image!!!", Toast.LENGTH_LONG).show();
	            	else
	            		Toast.makeText(ccontext, "Maximum 5 images allowed!!!", Toast.LENGTH_LONG).show();
	            }
	        });*/
	        
	       /* ((Button) findViewById(R.id.txt))
	        .setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(image_num<6 && inptxt==false)
					{
						AlertDialog.Builder alert2 = new AlertDialog.Builder(ccontext);
						alert2.setTitle("Text for Image");
						final EditText input_text = new EditText(ccontext);
						alert2.setView(input_text);
						alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								String input_value = input_text.getText().toString();
								saveText(input_value);
								done++;
								inptxt=true;
							 }
	
							
						});
						alert2.show();
					}
					else if(inpaud==true)
	            		Toast.makeText(ccontext, "Already has text for this image!!!", Toast.LENGTH_LONG).show();
	            	else
	            		Toast.makeText(ccontext, "Maximum 5 images allowed!!!", Toast.LENGTH_LONG).show();
				}
				
				});*/
	        
	        /*((Button) findViewById(R.id.next))
	        .setOnClickListener(new OnClickListener() {
	
	            public void onClick(View arg0) {
	            	Log.d("","next done: "+done);
	            	if(done==3)
                	{
	            		newll= new LinearLayout(ccontext);
		                newll.setOrientation(Configuration.ORIENTATION_PORTRAIT);
	            		
                		done=0;                		
                		audbutclick=0;
	                    image_num++;
	                    inpimg=false;
	                    inpaud=false;
	                    inptxt=false;
	                    //View ll=findViewById(R.id.ll_aud);
				        //View view = (View) event.getLocalState();
				        ViewGroup owner = (ViewGroup) mRecordButton.getParent();
				        owner.removeView(mRecordButton);
				        owner.removeView(mPlayButton);
	                    //mRecordButton.setVisibility(View.INVISIBLE);
	                    //mPlayButton.setVisibility(View.INVISIBLE);
	            	}
	            	else if(image_num==6)
	            	{
	            		finish();
	            	}
	            	else
	            	{
	            		Toast.makeText(ccontext, "All inputs required!!!", Toast.LENGTH_LONG).show();
	            	}
	            }
	        });*/
	        
	        ((Button) findViewById(R.id.save))
	        .setOnClickListener(new OnClickListener() {
	
	            public void onClick(View arg0) {
	            	if(image_num>3 && image_num<=6)
	            	{	//database        
	                    Sql entry = new Sql(ccontext);
	                    entry.open();
	                    entry.createEntry(name,""+(image_num-1));
	                    Log.d("put in database", name + " and " + (image_num - 1));
	                    entry.close();
	            		
	            		finish();
	            	}
	            	else
	            	{
	            		Toast.makeText(ccontext, "Three to five images allowed!!!", Toast.LENGTH_LONG).show();
	            	}
	            }
	        });
	       
	        
    }
    
    public void imageButton(View arg0) {
    	
        // in onCreate or any event where your want the user to
        // select a file
    	
    	//file = new File(root + "Seq" + File.separator +"img"+image_take+".jpg");
    	if(image_num<6 && inpimg==false)
        {	           
        
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
           
        }
    	else if(inpimg==true)
    		Toast.makeText(ccontext, "Click next!!!", Toast.LENGTH_LONG).show();
    	else
    		Toast.makeText(ccontext, "Maximum 5 images allowed!!!", Toast.LENGTH_LONG).show();
    }
    
    public void audioButton(View arg0) {
    	
        // in onCreate or any event where your want the user to
        // select a file
    	if(image_num<6 && inpaud==false)
        {
        	
        		if(audbutclick==0)
            	{
            		audbutclick=1;
            		
	                mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
	                mFileName += "/Seq/"+name+"/audio"+image_num+".3gp";
	                View ll=findViewById(R.id.audll);
	                mRecordButton = new RecordButton(ccontext);
	                ((LinearLayout) ll).addView(mRecordButton,
	                    new LinearLayout.LayoutParams(
	                        ViewGroup.LayoutParams.WRAP_CONTENT,
	                        ViewGroup.LayoutParams.WRAP_CONTENT,
	                        0));
	                mPlayButton = new PlayButton(ccontext);
	
	                ((LinearLayout) ll).addView(mPlayButton,
	                    new LinearLayout.LayoutParams(
	                        ViewGroup.LayoutParams.WRAP_CONTENT,
	                        ViewGroup.LayoutParams.WRAP_CONTENT,
	                        0));
	                //setContentView(ll);
	            }
        }
    	else if(inpaud==true)
    		Toast.makeText(ccontext, "Already has audio for this image!!!", Toast.LENGTH_LONG).show();
    	else
    		Toast.makeText(ccontext, "Maximum 5 images allowed!!!", Toast.LENGTH_LONG).show();
    }
    
	public void txtButton(View arg0) {
		// TODO Auto-generated method stub
		if(image_num<6 && inptxt==false)
		{
			AlertDialog.Builder alert2 = new AlertDialog.Builder(ccontext);
			alert2.setTitle("Text for Image");
			final EditText input_text = new EditText(ccontext);
			alert2.setView(input_text);
			alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					String input_value = input_text.getText().toString();
					saveText(input_value);
					done++;
					inptxt=true;
				 }

				
			});
			alert2.show();
		}
		else if(inpaud==true)
    		Toast.makeText(ccontext, "Already has text for this image!!!", Toast.LENGTH_LONG).show();
    	else
    		Toast.makeText(ccontext, "Maximum 5 images allowed!!!", Toast.LENGTH_LONG).show();
	}
    
    public void nextButton(View arg0) {
    	Log.d("","next done: "+done);
    	
    	if(done==3)
    	{
    		newll= new LinearLayout(ccontext);
            newll.setOrientation(Configuration.ORIENTATION_PORTRAIT);
    		
    		done=0;                		
    		audbutclick=0;
            image_num++;
            inpimg=false;
            inpaud=false;
            inptxt=false;
            //View ll=findViewById(R.id.ll_aud);
	        //View view = (View) event.getLocalState();
	        ViewGroup owner = (ViewGroup) mRecordButton.getParent();
	        owner.removeView(mRecordButton);
	        owner.removeView(mPlayButton);
	        
            //mRecordButton.setVisibility(View.INVISIBLE);
            //mPlayButton.setVisibility(View.INVISIBLE);
    	}
    	else if(image_num==6)
    	{
    		finish();
    	}
    	else
    	{
    		Toast.makeText(ccontext, "All inputs required!!!", Toast.LENGTH_LONG).show();
    	}
    }
	public void readname(int a) {
		// TODO Auto-generated method stub
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);
    	alert.setCancelable(false);
    	if(a==0)
    		alert.setTitle("Sequence Name");
    	else
    		alert.setTitle("Please enter a Sequece Name");
		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
			String value = input.getText().toString();
			if(value.equals("")){
				readname(1);
			}
			else{
				File createDir2 = new File(root+"Seq/"+value+File.separator);
				Log.d("new dir", root+"Seq/"+value+File.separator);  
				if(!createDir2.exists()) {
					createDir2.mkdir();	            
					//Log.d("new dir", root+"Seq"+File.separator );
				}        
				setname(value);
			}
		 }
		});
		alert.show();
	}
    public void TextButton(View arg0) {

		// TODO Auto-generated method stub
		AlertDialog.Builder alert2 = new AlertDialog.Builder(ccontext);
		alert2.setTitle("Text for Image");
		final EditText input_text = new EditText(ccontext);
		alert2.setView(input_text);
		alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String input_value = input_text.getText().toString();
				saveText(input_value);
			 }

			
		});
		alert2.show();
	}
    public void setname(String value) {
		name = value;
		TextView nm = (TextView) findViewById(R.id.seq_name);
        nm.setText("Sequence: " + name);
 

        Log.d("dialog","dialog " + name);
	}
    
    public void saveText(String input_text){
    	 try
	        {
	        	File root2 = new File(Environment.getExternalStorageDirectory(),"Seq/"+name);
	            if (!root2.exists()) {
	                root2.mkdirs();
	                Log.d("new", "new dir created");
	            }
	            
	            TextView txtv=new TextView(this);
	            View topll=findViewById(R.id.topll);
                Log.d("error", "msg");                
                LayoutParams params = new LinearLayout.LayoutParams(250,100);                
                txtv.setLayoutParams(params);                               
                txtv.append(input_text);
                
                if(inpimg==true)
                {
                	ViewGroup owner = (ViewGroup) newll.getParent();
			        owner.removeView(newll);			        	                
	                newll.addView(txtv);
	                ((LinearLayout) topll).addView(newll);
                }                
                else
                {
                	Log.d("","work inptxt");
                	((LinearLayout) newll).addView(txtv);
                    Log.d("","work inptxt");
                	((LinearLayout) topll).addView(newll);                    
                }
                
	            File gpxfile = new File(root2, "t"+image_num+".txt");
	            FileWriter writer = new FileWriter(gpxfile);
	            writer.append(input_text);
	            writer.flush();
	            writer.close();
	            Log.d("write", "writeen");
	            Toast.makeText(ccontext, "Saved", Toast.LENGTH_SHORT).show();
	        }
	        catch(IOException e)
	        {
	             e.printStackTrace();
	            // importError = e.getMessage();
	            // iError();
	             Log.d("erro1", "error in file saving");
	        }
    	
    	
    	
    }
    
       public void onRecord(boolean start) {
            if (start) {
                startRecording();
            } else {
                stopRecording();
            }
        }

        public void onPlay(boolean start) {
            if (start) {
                startPlaying();
            } else {
                stopPlaying();
            }
        }

        public void startPlaying() {
            mPlayer = new MediaPlayer();
            try {
                mPlayer.setDataSource(mFileName);
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }
        }

        public void stopPlaying() {
            mPlayer.release();
            mPlayer = null;
        }

        public void startRecording() {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(mFileName);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }

            mRecorder.start();
        }

        public void stopRecording() {
            mRecorder.stop();
            done++;
            inpaud=true;
            mRecorder.release();
            mRecorder = null;
        }

        class RecordButton extends Button {
            boolean mStartRecording = true;

            OnClickListener clicker = new OnClickListener() {
                public void onClick(View v) {
                    onRecord(mStartRecording);
                    if (mStartRecording) {
                        setText("Stop recording");
                    } else {
                        setText("Start recording");
                    }
                    mStartRecording = !mStartRecording;
                }
            };

            public RecordButton(Context ctx) {
                super(ctx);
                setText("Start recording");
                setOnClickListener(clicker);
            }
        }

        class PlayButton extends Button {
            boolean mStartPlaying = true;

            OnClickListener clicker = new OnClickListener() {
                public void onClick(View v) {
                    onPlay(mStartPlaying);
                    if (mStartPlaying) {
                        setText("Stop playing");
                    } else {
                        setText("Start playing");
                    }
                    mStartPlaying = !mStartPlaying;
                }
            };

            public PlayButton(Context ctx) {
                super(ctx);
                setText("Start playing");
                setOnClickListener(clicker);
            }
        }

        @Override
        public void onPause() {
            super.onPause();
            if (mRecorder != null) {
                mRecorder.release();
                mRecorder = null;
            }

            if (mPlayer != null) {
                mPlayer.release();
                mPlayer = null;
            }
        }

    
    //UPDATED
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                //OI FILE Manager
                filemanagerstring = selectedImageUri.getPath();

                //MEDIA GALLERY
                selectedImagePath = getPath(selectedImageUri);
                
                //converting to bitmap for saving in sd card
                Bitmap bitmap = BitmapFactory.decodeFile(filemanagerstring);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Log.d("log", "worked till here");
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos); 
                byte[] byte_img_data = baos.toByteArray();  
                
//create new folder and copy the file to this folder                
                OutputStream out;                
                /*File createDir = new File(root+"CopiedResources"+File.separator);
                if(!createDir.exists()) {
                    createDir.mkdir();
                    Log.d("new dir", root+"CopiedResources"+File.separator );
                }*/
                File file = new File(root + "Seq/"+name+ File.separator +"i"+image_num+".jpg");
                try {
					file.createNewFile();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
                try {
					out = new FileOutputStream(file);
					out.write(byte_img_data);
					out.close();
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
                View topll=findViewById(R.id.topll);
                inpimg=true;
                Log.d("error", "msg");
                Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);
                ImageView ig = new ImageView(this);
                LayoutParams params = new LinearLayout.LayoutParams(145,145);
                
                ig.setImageURI(selectedImageUri);
                ig.setLayoutParams(params);
                ig.setScaleType(ScaleType.FIT_XY);  
              //  ig.setId(45);
                ig.setAnimation(shake);
                if(inptxt==true)
                {
                	ViewGroup owner = (ViewGroup) newll.getParent();
			        owner.removeView(newll);
			        LinearLayout templl=new LinearLayout(this);
			        templl.addView(newll);
			        newll=new LinearLayout(this);
			        LayoutParams params_linear = new LinearLayout.LayoutParams(150,150);
			        params_linear.setMargins(5, 10, 5, 10);
			        newll.setOrientation(Configuration.ORIENTATION_PORTRAIT);
			        //newll.setBackgroundResource(R.id.img);
			        newll.setLayoutParams(params_linear);
	                newll.addView(ig);
	                newll.setAnimation(translate);
	                newll.addView(templl);
	               // newll.startAnimation(translate);
	                Log.d("animation", "animation added");

	                ((LinearLayout) topll).addView(newll);
	                
                }
                else
                {   
                	
             //   	((LinearLayout) newll).addView(ig);
			     //   LinearLayout =new LinearLayout(this);

                	LayoutParams params_linear = new LinearLayout.LayoutParams(150,150);
			        params_linear.setMargins(10, 10, 10, 10);
			        newll.setLayoutParams(params_linear);
			        ((LinearLayout) newll).addView(ig);
                	//newll.setBackgroundResource(R.id.img);
                	
                	newll.startAnimation(translate);
                	((LinearLayout) topll).addView(newll);
                	Log.d("animation", "animatoin added in else");
                }
               // topll.setAnimation (translate);
                Log.d("","work inptxt");
                done++;
                
                //DEBUG PURPOSE - you can delete this if you want
                if(selectedImagePath!=null)
                    System.out.println(selectedImagePath);
                else System.out.println("selectedImagePath is null");
                if(filemanagerstring!=null)
                    System.out.println(filemanagerstring);
                else System.out.println("filemanagerstring is null");

                //NOW WE HAVE OUR WANTED STRING
                if(selectedImagePath!=null)
                    System.out.println("selectedImagePath is the right one for you!");
                else
                    System.out.println("filemanagerstring is the right one for you!");
            }
        }
    }

    //UPDATED!
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if(cursor!=null)
        {
            //HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            //THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        else return null;
    }
}