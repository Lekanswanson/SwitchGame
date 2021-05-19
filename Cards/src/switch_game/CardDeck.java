package switch_game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class CardDeck
{
	private ArrayList<JPanel> frontOfCards;
	private ArrayList<JPanel> backOfCards;
	
	private ArrayList<Card> playingCards;
	private ArrayList<Card> playerACards = new ArrayList<Card>();
	private ArrayList<Card> playerBCards = new ArrayList<Card>();
	
	CardDeck()
	{
		createCards();
	}
	
	void createCards()
	{
		playingCards = new ArrayList<Card>();

		String[] cardRank = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		String[] cardImages = {"hearts","spades","clubs","diamond"};
		Card[] diamondCards=new Card[13];
		Card[] heartCards=new Card[13];
		Card[] spadeCards=new Card[13];
		Card[] clubCards=new Card[13];
		String diamond="Diamonds";
		String hearts="Hearts";
		String spades="Spades";
		String clubs="Clubs";
		
		HashMap<Integer, Card> diamondSuits = new HashMap<>(13);
		HashMap<Integer, Card> heartSuits = new HashMap<>(13);
		HashMap<Integer, Card> spadeSuits = new HashMap<>(13);
		HashMap<Integer, Card> clubSuits = new HashMap<>(13);
		
		for(int i=0; i<cardRank.length; i++)
		{
			heartCards[i] = new Card(cardRank[i], hearts, cardImages[0]);
			heartSuits.put(i, heartCards[i]);
			
			spadeCards[i] = new Card(cardRank[i], spades, cardImages[1]);
			spadeSuits.put(i, spadeCards[i]);
			
			clubCards[i] = new Card(cardRank[i], clubs, cardImages[2]);
			
			diamondCards[i] = new Card(cardRank[i], diamond, cardImages[3]);
			diamondSuits.put(i, diamondCards[i]);
			
			clubSuits.put(i, clubCards[i]);
		}
		playingCards.addAll(diamondSuits.values());
		playingCards.addAll(heartSuits.values());
		playingCards.addAll(spadeSuits.values());
		playingCards.addAll(clubSuits.values());
	}
	ArrayList<Card> getCardsDeck()
	{
		return playingCards;
	}
	
	
	void addPlayerACards(int index)
	{
		playerACards.add(getCardsDeck().get(index));
	}
	void addPlayerBCards(int index)
	{
		playerBCards.add(getCardsDeck().get(index));
	}
	
	Card getPlayerACards(int i)
	{
		return playerACards.get(i);
	}
	void removePlayerACard(int i)
	{
		playerACards.remove(i);
	}
	Card getPlayerBCards(int i)
	{
		return playerBCards.get(i);
	}
	void removePlayerBCard(int i)
	{
		playerBCards.remove(i);
	}
	
	
	ArrayList<JPanel> createDeckOfCardsFront()
	{
		frontOfCards = new ArrayList<JPanel>();
		ArrayList<Card> deckOfCards = getCardsDeck();
		
		Card singleCard=null;
		JPanel singleCardPanel = null;
		for(int i=0; i<deckOfCards.size(); i++)
		{
			singleCard=deckOfCards.get(i);
			singleCardPanel = createFrontOfCard(singleCard);
			frontOfCards.add(singleCardPanel);
		}
		return frontOfCards;
	}
	
	ArrayList<JPanel> createDeckOfCardsBack()
	{
		backOfCards = new ArrayList<JPanel>();
		ArrayList<Card> deckOfCards = getCardsDeck();

		Card singleCard=null;
		JPanel singleCardPanel = null;
		for(int i=0; i<deckOfCards.size(); i++)
		{
			singleCard=deckOfCards.get(i);
			singleCardPanel = createBackOfCard(singleCard);
			backOfCards.add(singleCardPanel);
		}
		return backOfCards;
	}
	JPanel createFrontOfCard(Card card)
	{
		JPanel frontOfCardPanel = new JPanel(new BorderLayout());
		
		JPanel cardTopNumberPanel = new JPanel(new FlowLayout(0,5,5));
		JLabel topNumberLabel = new JLabel(card.getRank());
		cardTopNumberPanel.add(topNumberLabel);
		
		ImageIcon cardImageIcon = new ImageIcon("imgs/" + card.getImg() + ".png");
		JLabel cardImageLabel = new JLabel(cardImageIcon);
		
		JPanel cardBottomNumberPanel = new JPanel(new FlowLayout(2,5,5));
		JLabel bottomNumberLabel = new JLabel(card.getRank());
		cardBottomNumberPanel.add(bottomNumberLabel);
		
		frontOfCardPanel.add(cardTopNumberPanel, BorderLayout.NORTH);
		frontOfCardPanel.add(cardImageLabel, BorderLayout.CENTER);
		frontOfCardPanel.add(cardBottomNumberPanel, BorderLayout.SOUTH);
		return frontOfCardPanel;
	}
	JPanel createBackOfCard(Card card)
	{
		JPanel backOfCardPanel = new JPanel(new BorderLayout());		
		ImageIcon backOfCardImage = new ImageIcon("imgs/cardback.png");
		JLabel backOfCardLabel = new JLabel(backOfCardImage);
	
		backOfCardPanel.add(backOfCardLabel);
		return backOfCardPanel;
	}
}