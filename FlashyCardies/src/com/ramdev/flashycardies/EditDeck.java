package com.ramdev.flashycardies;

import java.util.ArrayList;

import android.os.Bundle;
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

public class EditDeck extends Activity {
	
	private Deck deck;
	private ArrayList<String> cards;
	private Card focusCard = new Card("default", "default");
	
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
		setTitle(deck.getDeckName());
		
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
				AlertDialog.Builder addDeckAlert = new AlertDialog.Builder(EditDeck.this);
				addDeckAlert.setTitle(R.string.home_alert_nameDeck);
				addDeckAlert.setCancelable(true);
				
				final EditText input1 = new EditText(EditDeck.this);
				final EditText input2 = new EditText(EditDeck.this);
				addDeckAlert.setView(input1);
				input1.setFocusable(true);
				input1.setFocusableInTouchMode(true);
				input1.requestFocus();
				input2.setFocusable(true);
				input2.setFocusableInTouchMode(true);
				
				//Ok Button interaction
				addDeckAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if(input1.getText().toString()==""){
							Toast.makeText(EditDeck.this, "Cards need a name", Toast.LENGTH_SHORT).show();
						} else {
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
			}
		});
		
		editdeck_button_editCard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder addDeckAlert = new AlertDialog.Builder(EditDeck.this);
				addDeckAlert.setTitle(R.string.home_alert_nameDeck);
				addDeckAlert.setCancelable(true);
				
				final EditText input1 = new EditText(EditDeck.this);
				final EditText input2 = new EditText(EditDeck.this);
				addDeckAlert.setView(input1);
				input1.setFocusable(true);
				input1.setFocusableInTouchMode(true);
				input1.requestFocus();
				input1.setText(focusCard.getCardName());
				input2.setFocusable(true);
				input2.setFocusableInTouchMode(true);
				input2.setText(focusCard.getCardDefinition());
				
				//Ok Button interaction
				addDeckAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if(input1.getText().toString()==""){
							Toast.makeText(EditDeck.this, "Cards need a name", Toast.LENGTH_SHORT).show();
						} else {
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
			}
		});
			
		editdeck_button_renameDeck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder addDeckAlert = new AlertDialog.Builder(EditDeck.this);
				addDeckAlert.setTitle(R.string.home_alert_nameDeck);
				addDeckAlert.setCancelable(true);
				
				final EditText input1 = new EditText(EditDeck.this);
				addDeckAlert.setView(input1);
				input1.setFocusable(true);
				input1.setFocusableInTouchMode(true);
				input1.requestFocus();
				
				//Ok Button interaction
				addDeckAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if(input1.getText().toString()==""){
							Toast.makeText(EditDeck.this, "Decks need a name", Toast.LENGTH_SHORT).show();
						} else {
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
			}
		});
		
		for(int i=0; i<cards.size(); i++)
			createNewCard(i);
	}
	
	private void createNewCard(int index){
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View newDeckView = inflater.inflate(R.layout.new_deck_layout, null);
		
		Button newButton = (Button) newDeckView.findViewById(R.id.newdeck_button_newdeck);
		newButton.setText(cards.get(index));
		newButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(EditDeck.this, this.toString(), Toast.LENGTH_SHORT).show();
			}
		});
		
		editdeck_tableview_cardView.addView(newDeckView);
	}
}
