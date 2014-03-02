package com.ramdev.flashycardies;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {
	
	String[] deckNames;
	static String HOME_ADDDECK_EXTRA;
	
	private Button home_button_addDeck;
	private Button home_button_editDeckButton;
	private Button home_button_studyDeckButton;
	
	private TableLayout home_tablelayout_deckViewLayout;
	
//	private SharedPreferences decksToUse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		home_button_addDeck = (Button) findViewById(R.id.addDeck);
		home_button_editDeckButton = (Button) findViewById(R.id.home_button_editDeckButton);
		home_button_studyDeckButton = (Button) findViewById(R.id.home_button_studyDeckButton);
		
		home_tablelayout_deckViewLayout = (TableLayout) findViewById(R.id.home_tablelayout_deckViewLayout);
		
		//Create master file for Deck names and saving functions
		File masterList = new File("master.txt");
		if(!masterList.exists()) {
			try {
				masterList.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//end if
		
		populateDeckNames();
		populateDecks();
		
		setContentView(R.layout.activity_home);
		
		home_button_addDeck = (Button) findViewById(R.id.addDeck);
		home_button_editDeckButton = (Button) findViewById(R.id.home_button_editDeckButton);
		home_button_studyDeckButton = (Button) findViewById(R.id.home_button_studyDeckButton);
		
		home_tablelayout_deckViewLayout = (TableLayout) findViewById(R.id.home_tablelayout_deckViewLayout);
		
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
				// TODO Auto-generated method stub
				AlertDialog.Builder addDeckAlert = new AlertDialog.Builder(Home.this);
				addDeckAlert.setTitle(R.string.home_alert_nameDeck);
				addDeckAlert.setCancelable(true);
				
				final EditText input1 = new EditText(Home.this);
				addDeckAlert.setView(input1);
				
				//Ok Button interaction
				addDeckAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						addDeck(input1.toString());
						Toast.makeText(Home.this, "Positive Button Clicked", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(Home.this, EditDeck.class);
						intent.putExtra(HOME_ADDDECK_EXTRA, input1.toString());
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
				Toast.makeText(Home.this, "editDeck", Toast.LENGTH_SHORT);
			}
		});
		home_button_studyDeckButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(Home.this, "studyDeck", Toast.LENGTH_SHORT);
			}
		});
		
		for(int i=0; i<deckNames.length; i++)
			addNewDeckButton(i);
	}
	
	public void populateDeckNames() {
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
		
		deckNames = testString.split(delim);
	}
	
	public void populateDecks() {		
		String delim = "[~]";
		for (int i=0; i<deckNames.length; i++) {
			Deck deck = new Deck(deckNames[i]);

			try {
				String[] tokenizer;
				String receiveString = "";
				Scanner scanner = new Scanner(new File(deckNames[i]));
				
				while (scanner.hasNext()) {
					receiveString = scanner.nextLine();
					tokenizer = receiveString.split(delim);
					Card card = new Card(tokenizer[0], tokenizer[1]);
				}
				scanner.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}//end try
		}//end for
	}//end populateDecks
	
	private void addNewDeckButton(int index) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View newDeckButtonView = inflater.inflate(R.layout.new_deck_layout, null);
		
		Button newButton;
		
		home_tablelayout_deckViewLayout.addView(newDeckButtonView, index);
	}//end addNewDeckButton
	
	public void addDeck(String name) {
		Deck deck = new Deck(name);
		deck.saveDeck(Home.this);
		populateDeckNames();
		addNewDeckButton(deckNames.length-1);
	}

}//end Home
