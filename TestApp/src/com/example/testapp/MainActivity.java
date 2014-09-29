package com.example.testapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.example.testapp.MESSAGE";
	
	/** Called when the user clicks the open button */	
	public void openFile(View view){
		PopupMenu menu = new PopupMenu(this, view);

		if(!isExternalStorageReadable())			
		{
			//The device is not readable, so give alert dialog
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			
			alertDialogBuilder.setTitle("Not Readable");
			
			alertDialogBuilder
							.setMessage("Device is not readable. Click OK to continue")
							.setCancelable(false)
							.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									// if this button is clicked, close
									// current activity
									dialog.dismiss();
								}
							  });
			
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		}
		else
		{			
			
			File dir= Environment.getExternalStorageDirectory();
			
			String[] fileNames = dir.list();
			final ArrayList<String> txtFiles = new ArrayList<String>();
			
			for (int i=0; i < fileNames.length; i++)
			{
			    if(fileNames[i].matches(".*?[.]txt$"))
			    {
			    	txtFiles.add(fileNames[i]);
			    }
			}
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Load a File");
			
			CharSequence[] items = txtFiles.toArray(new CharSequence[txtFiles.size()]);
			
			builder.setItems(items, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					File sdcard= Environment.getExternalStorageDirectory();
					//Start up new activity to display file
					File file = new File(sdcard, txtFiles.get(which));
					
					StringBuilder text = new StringBuilder();
					
					try{
						BufferedReader br = new BufferedReader(new FileReader(file));
						String line;
						
						while ((line = br.readLine()) != null) {
					        text.append(line);
					        text.append('\n');
					    }
					    br.close();
					    
					    //Send this text to the new activity
						Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
						String message = text.toString();
						intent.putExtra(EXTRA_MESSAGE, message);
						startActivity(intent);
					    
					}
					catch (IOException e){
						
						//The device is not readable, so give alert dialog
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
						
						alertDialogBuilder.setTitle("Error");
						
						alertDialogBuilder
										.setMessage("Error Reading File. Click OK to continue")
										.setCancelable(false)
										.setPositiveButton("OK", new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog,int id) {
												// if this button is clicked, close
												// current activity
												dialog.dismiss();
											}
										  });
						
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();
						
					}
					
					
					
				}
			});
			
			AlertDialog alert = builder.create();
			alert.show();
						
		}
		
	}
	
	public void openGraphics(View view){		
		//Start the new activity
		Intent intent = new Intent(this, GraphicsActivity.class);
		
		startActivity(intent);
	}
	
	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		if(id == R.id.action_load)
		{
			//read the file in
			//for now, it's just a normal text file
			//Ask user to read in file
			
			if(!isExternalStorageReadable())			
			{
				//The device is not readable, so give alert dialog
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
				
				alertDialogBuilder.setTitle("Not Readable");
				
				alertDialogBuilder
								.setMessage("Device is not readable. Click OK to continue")
								.setCancelable(false)
								.setPositiveButton("OK", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, close
										// current activity
										dialog.dismiss();
									}
								  });
				
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
			else
			{
				File sdcard = Environment.getExternalStorageDirectory();
				
				File file = new File(sdcard, "test.txt");
				
				StringBuilder text = new StringBuilder();
				
				try{
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line;
					
					while ((line = br.readLine()) != null) {
				        text.append(line);
				        text.append('\n');
				    }
				    br.close();
				    
				    //Send this text to the new activity
					Intent intent = new Intent(this, DisplayMessageActivity.class);
					String message = text.toString();
					intent.putExtra(EXTRA_MESSAGE, message);
					startActivity(intent);
				    
				}
				catch (IOException e){
					
					//The device is not readable, so give alert dialog
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
					
					alertDialogBuilder.setTitle("Error");
					
					alertDialogBuilder
									.setMessage("Error Reading File. Click OK to continue")
									.setCancelable(false)
									.setPositiveButton("OK", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,int id) {
											// if this button is clicked, close
											// current activity
											dialog.dismiss();
										}
									  });
					
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
					
				}
				
				
				
			}
			
		}
		
		if (id == R.id.exit)
		{
			//Ask user to quit
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			
			alertDialogBuilder.setTitle("Exit?");
			
			alertDialogBuilder
							.setMessage("Are you sure you want to exit?")
							.setCancelable(false)
							.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									// if this button is clicked, close
									// current activity
									MainActivity.this.finish();
								}
							  })
							  .setNegativeButton("No",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
									}
								});
			
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();			
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}
