package com.ramdev.flashycardies;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class Deck {
	private String deckName;
	private String delim = "[~]";
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	//Constructor
	public Deck(String name) {
		this.deckName = name;
	}

	public String getDeckName() {
		return deckName;
	}

	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void saveDeck(Context context) {
		//String filePath = context.getFilesDir().getPath() + "/" + this.getDeckName() + ".txt";
		String filePath = Environment.getDataDirectory().toString() + "/" + this.getDeckName() + ".txt";
		File file = new File(filePath);
		String name;
		String def;
		String combinedInfo;
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				Toast.makeText(context, "Deck.saveDeck(Context).IOException", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		} else {
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//end if

		try {
			OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(this.getDeckName(), Context.MODE_PRIVATE));
			BufferedWriter bw = new BufferedWriter(osw);
			for (int i=0; i<this.cards.size(); i++) {
				name = this.cards.get(i).getCardName();
				def = this.cards.get(i).getCardDefinition();
				combinedInfo = name + "~" + def;
				bw.write(combinedInfo);
				bw.newLine();
			}
			bw.close();
			osw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end try
		
	}//end saveDeck
	
	public ArrayList<String> retrieveCards(Context context) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			String[] tokenizer;
			String receiveString = "";
			Scanner scanner = new Scanner(new File(deckName));
			
			while (scanner.hasNext()) {
				receiveString = scanner.nextLine();
				tokenizer = receiveString.split(delim);
				cards.add(new Card(tokenizer[0], tokenizer[1]));
				result.add(tokenizer[0]);
			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}//end try
		
		return result;
	}//end retrieveCards
	
	
}//end class