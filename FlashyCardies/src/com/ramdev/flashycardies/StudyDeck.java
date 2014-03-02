package com.ramdev.flashycardies;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ViewSwitcher;

public class StudyDeck extends Activity {
	
	private Deck deck;
	
	private ViewSwitcher currentCard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_deck);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.study_deck, menu);
		return true;
	}

}
