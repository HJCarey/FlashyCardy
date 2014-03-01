package com.ramdev.flashycardies;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;

public class Deck {
	private String deckName;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
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
		}
		
		try {
			FileOutputStream fos = context.openFileOutput(this.getDeckName(), Context.MODE_PRIVATE);
			for (int i=0; i<this.cards.size(); i++) {
				name = this.cards.get(i).getCardName();
				def  = this.cards.get(i).getCardDefinition();
				combinedInfo = name + "~ " + def;
				fos.write(combinedInfo.getBytes());
			}
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end try
		
	}//end saveDeck
	
}//end class