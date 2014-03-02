package com.ramdev.flashycardies;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

public class Home extends Activity {
	
	String[] deckNames;
	
	private final String DEFAULT_MASTER_FILE = "master.txt";
	public  final static String EXTRA_MESSAGE = "com.ramdev.FlashyCardies";
	private String focusDeck;
	
	private ArrayList<Deck> deckList = new ArrayList<Deck>();
	
	private Button home_button_addDeck;
	private Button home_button_editDeckButton;
	private Button home_button_studyDeckButton;
	
	private TableLayout home_tablelayout_deckViewLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		//Create master file for Deck names and saving functions

		String filePath = Environment.getDataDirectory().toString() + "/" + DEFAULT_MASTER_FILE;
		File masterList = new File(filePath);
		
		home_button_addDeck = (Button) findViewById(R.id.addDeck);
		home_button_editDeckButton = (Button) findViewById(R.id.home_button_editDeckButton); 
		home_button_studyDeckButton = (Button) findViewById(R.id.home_button_studyDeckButton);
		
		home_tablelayout_deckViewLayout = (TableLayout) findViewById(R.id.home_tablelayout_deckViewLayout);
		
		//If there is no master file, this will create one.
		if(!masterList.exists()) {
			try {
				masterList.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} //end if
		
		refreshDecks();
//		populateDecks();
		
		initializeButtons();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	private void initializeButtons(){
		home_button_addDeck.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				AlertDialog.Builder addDeckAlert = new AlertDialog.Builder(Home.this);
				addDeckAlert.setTitle(R.string.home_alert_nameDeck);
				addDeckAlert.setCancelable(true);
				
				final EditText input1 = new EditText(Home.this);
				addDeckAlert.setView(input1);
				input1.setFocusable(true);
				input1.setFocusableInTouchMode(true);
				input1.requestFocus();
				
				//Ok Button interaction
				addDeckAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if(input1.getText().toString()==""){
							Toast.makeText(Home.this, "Decks need a name", Toast.LENGTH_SHORT).show();
						} else {
							addDeck(input1.getText().toString());
						}
					}//end onclick
				});//end setPositiveButton
				
				//Cancel Button interaction
				addDeckAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}//end onclick
				});//end setNegativeButton
				
				addDeckAlert.show();
			}//end onClick (home_button_addDeck)
		});//end OnClickListener
				
		home_button_editDeckButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(focusDeck==null){
					Toast.makeText(Home.this, "Add a deck", Toast.LENGTH_SHORT).show();
				} else {
					editDeck(focusDeck);
				}
			}
		});
		
		home_button_studyDeckButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		for(int i=0; i<deckNames.length-1; i++) {
			createNewDeck(i);
		}
	}
	
	public void refreshDecks() {
		File masterList = new File("master.txt");
		String testString = "";
		String delim = "[~]";
		
		//Pull all deck names from masterList.txt
		try {
			FileInputStream fis = openFileInput(masterList.getName());
			
			byte[] dataArray = new byte[fis.available()];
			while (fis.read(dataArray) != -1) {
				testString = new String(dataArray);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Toast.makeText(this, testString, Toast.LENGTH_SHORT).show();
		
		deckNames = testString.split(delim);
	}
	
	private void createNewDeck(int index){
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View newDeckView = inflater.inflate(R.layout.new_deck_layout, null);
				
		Button newButton = (Button) newDeckView.findViewById(R.id.newdeck_button_newdeck);
		newButton.setText(focusDeck);
		newButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
		
		home_tablelayout_deckViewLayout.addView(newDeckView);
	}
	
	public void addDeck(String name) {
		Deck deck = new Deck(name);
		deck.saveDeck(Home.this);
		focusDeck = deck.getDeckName();
		//deckList.add(deck);
		refreshDecks();
		createNewDeck(deckNames.length-1);
	}
	
	private void editDeck(String deck){
		Intent intent = new Intent(this, EditDeck.class);
		
    	intent.putExtra(EXTRA_MESSAGE, deck);
    	startActivity(intent);
	}
	
	private void studyDeck(String deck){
		Intent intent = new Intent(this, StudyDeck.class);
		
    	intent.putExtra(EXTRA_MESSAGE, deck);
    	startActivity(intent);
	}

}//end Home
