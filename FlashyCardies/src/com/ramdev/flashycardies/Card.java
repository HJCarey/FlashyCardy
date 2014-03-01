package com.ramdev.flashycardies;

public class Card {
	private String cardName;
	private String cardDefinition;
	
	public Card(String name, String definition) {
		this.cardName = name;
		this.cardDefinition = definition;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardDefinition() {
		return cardDefinition;
	}

	public void setCardDefinition(String cardDefinition) {
		this.cardDefinition = cardDefinition;
	}
}
