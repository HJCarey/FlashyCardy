package com.ramdev.flashycardies;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.content.Context;

public class Deck {
	private String deckName;
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
	
	//Deletes the deck from memory then parses through the master.txt file and rebuilds it without the name of the deleted Deck
	public void deleteDeck(Context context) {
		File deck = new File(this.getDeckName());
		
		File master = new File("master.txt");
		try {
			String[] tokenizer = null;
			String receiveString = "testys";
			String delim = "~";
			
			InputStream inputStream = context.openFileInput(master.getName());
			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				StringBuilder stringBuilder = new StringBuilder();
				
				//Questionable Code
				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString);
					tokenizer = receiveString.split(delim);
				}//end while
			
				//tokenizer = receiveString.split(delim);
				
				for (int i=0; i<tokenizer.length; i++) {
					if (!tokenizer[i].equals(this.getDeckName()))
					writeToMaster(tokenizer[i] + delim, context);
				}//end for
				inputStreamReader.close();
				bufferedReader.close();
			}//end if
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end try
		
		deck.delete();
	}
	
	private void writeToMaster(String data, Context context) {
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("master.txt", Context.MODE_PRIVATE));
			outputStreamWriter.write(data);
			outputStreamWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveDeck(Context context) {
		File file = new File(this.getDeckName());
		String name;
		String def;
		String combinedInfo;
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
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
				bw.close();
				osw.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end try
		
	}//end saveDeck
	
	public void retrieveCards(Context context) {
	//	File file = new File()
	}//end retrieveCards
	
}//end class