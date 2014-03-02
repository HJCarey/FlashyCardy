package com.ramdev.flashycardies;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Home extends Activity {
	
	Button home_button_addDeck;
	Button home_button_editDeckButton;
	Button home_button_studyDeckButton;
	String[] deckNames;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		//Create master file for Deck names and saving functions
		File masterList = new File("master.txt");
		if(!masterList.exists()) {
			try {
				masterList.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//end if
		
		populateDecks();
		
		setContentView(R.layout.activity_home);
		
		home_button_addDeck = (Button) findViewById(R.id.addDeck);
		home_button_editDeckButton = (Button) findViewById(R.id.home_button_editDeckButton);
		home_button_studyDeckButton = (Button) findViewById(R.id.home_button_studyDeckButton);
		
		initializeButtons();
	}
	
	private void initializeButtons(){
		home_button_addDeck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		home_button_editDeckButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		home_button_studyDeckButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void populateDecks() {
		File masterList = new File("master.txt");
		String testString = "";
		String delim = "[~]";
		
		//Pull all text from masterList.txt
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
			//addNewDeckButton(i);
		}//end for
	}//end populateDecks

}
