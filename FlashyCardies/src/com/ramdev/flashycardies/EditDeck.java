package com.ramdev.flashycardies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;

public class EditDeck extends Activity {
	private Button edit_button_addCard;
	private Button edit_button_editCard;
	private Button edit_button_renameDeck;

	private String deck;
	
	private TableLayout edit_tablelayout_cardView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		Intent intent = getIntent();
		
		deck = intent.getStringExtra(Home.HOME_ADDDECK_EXTRA);
		
		initializeButtons();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	private void initializeButtons(){
		edit_button_addCard = (Button) findViewById(R.id.edit_button_addCard);
		edit_button_editCard = (Button) findViewById(R.id.edit_button_editCard);
		edit_button_renameDeck = (Button) findViewById(R.id.edit_button_rename_deck);
		
		edit_tablelayout_cardView = (TableLayout) findViewById(R.id.edit_tablelayout_cardViewInner);
		
		edit_button_addCard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		edit_button_editCard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		edit_button_renameDeck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void addNewCardButton(int index) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View newDeckButtonView = inflater.inflate(R.layout.new_deck_layout, null);
		
		Button newCardButton = (Button) newDeckButtonView.findViewById(R.id.newdeck_button_newdeck);
		
//		newDeckButton.setText(cardNames[index]);
		
		// Sets whether the gravity should be left or right based on the index of the button
		newCardButton.setGravity(index%2==1?0:1);
		
		newCardButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}//end onClick
		});//end OnClickListener
		
		edit_tablelayout_cardView.addView(newDeckButtonView, index);
	}//end addNewCardButton
}