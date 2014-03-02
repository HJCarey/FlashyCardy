package com.ramdev.flashycardies;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;

public class EditDeck extends Activity {
	
	private Deck deck;
	private ArrayList<String> cards;
	
	private Button editdeck_button_addCard;
	private Button editdeck_button_editCard;
	private Button editdeck_button_renameDeck;
	
	private TableLayout editdeck_tableview_cardView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_deck);

		Intent intent = getIntent();
		deck = new Deck(intent.getStringExtra(Home.EXTRA_MESSAGE));
		
		editdeck_tableview_cardView = (TableLayout) findViewById(R.id.editdeck_tablelayout_cardview);
		
		cards = deck.retrieveCards(this);
		initializeButtons();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_deck, menu);
		return true;
	}
	
	private void initializeButtons(){
		editdeck_button_addCard = (Button) findViewById(R.id.editdeck_button_addCard);
		editdeck_button_editCard = (Button) findViewById(R.id.editdeck_button_editCard);
		editdeck_button_renameDeck = (Button) findViewById(R.id.editdeck_button_renameDeck);
		
		editdeck_button_addCard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		editdeck_button_editCard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
			
		editdeck_button_renameDeck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		for(int i=0; i<cards.size(); i++)
			addNewCardButton(i);
	}
	
	private void addNewCardButton(int index) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View newDeckButtonView = inflater.inflate(R.layout.new_deck_layout, null);
		
		Button newButton = (Button) newDeckButtonView.findViewById(R.id.newdeck_button_deckButton);
		
		newButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		editdeck_tableview_cardView.addView(newDeckButtonView, index);
	}//end addNewCardButton
}
