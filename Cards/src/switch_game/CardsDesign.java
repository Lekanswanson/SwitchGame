package switch_game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class CardsDesign extends JFrame
{
	GameRules gameRules = new GameRules();
	ArrayList<Integer> availableCards;
	
	int playerATotalCards;
	int playerBTotalCards;

	ArrayList<Integer>storeIndexOfPlayerACards = new ArrayList<Integer>();
	ArrayList<Integer>storeIndexOfPlayerBCards = new ArrayList<Integer>();
	ArrayList<Integer>storeUsedCards = new ArrayList<Integer>();
	
	Card currentCard;
	JPanel[] getP;

	CardDeck cd=new CardDeck();

	JPanel startingCardPanel;
	private CardLayout cardLayout;
	
	boolean mousePressedPlayerA;
	boolean mousePressedPlayerB;
	
	int x=300;
	int y=250;
	int sizeX=80;
	int sizeY = 100;
	
	JPanel dummPanel = new JPanel();
	
	public CardsDesign() 
	{
		Container ct = getContentPane();
		ct.setBackground(new Color(12, 133, 44));
		ct.setLayout(null);
		
		availableCards = new ArrayList<Integer>();
		for(int i=0; i<52; i++)
			availableCards.add(i);
	
		ImageIcon ic = new ImageIcon("imgs/draw.png");
		JButton drawSingleCardButton = new JButton(ic);
		drawSingleCardButton.setBounds(10, 170, 30, 100);
		drawSingleCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				if(availableCards.size()>0)
				{
					deckAnimation(ct);
					gameRules.pickUpSingleCard(CardsDesign.this, storeIndexOfPlayerACards, availableCards, getP, ct, cd);	
					x=300;y=250;sizeX=80;sizeY=100;
				}
				else
				{
					availableCards = gameRules.reloadEmptyDeck(availableCards, storeUsedCards);
				}
		
			}
		});
		
		startingCard(ct);
		setPlayerACards(ct);
		setPlayerBCards(ct);
		remainingDeck(ct);
		
		ct.add(drawSingleCardButton);
		ct.add(startingCardPanel);
	
		setVisible(true);
		setTitle("Table");
		setBounds(900, 300, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	MouseListener enlargePanel(JPanel[] remainingCards, JPanel playerCard, int panelNumber, int position, String player, Container ct)
	{
		MouseListener ml = new MouseListener() 
		{
			public void mousePressed(MouseEvent e)
			{
				if(playerCard.getX()==50)
				{
					if(gameRules.matchSuit(cd.getPlayerACards(panelNumber), currentCard) || gameRules.matchRank(cd.getPlayerACards(panelNumber), currentCard))
					{
						currentCard=cd.getPlayerACards(panelNumber);	
						int index = storeIndexOfPlayerACards.get(panelNumber);
						storeUsedCards.add(index);
						startingCardPanel.add(playerCard);
						cardLayout.next(startingCardPanel);
						mousePressedPlayerA=true;
					}
				}
				else if(playerCard.getX()==550)
				{
					if(gameRules.matchSuit(cd.getPlayerBCards(panelNumber), currentCard) || gameRules.matchRank(cd.getPlayerBCards(panelNumber), currentCard))
					{
						currentCard=cd.getPlayerBCards(panelNumber);
						int index = storeIndexOfPlayerBCards.get(panelNumber);
						storeUsedCards.add(index);
						startingCardPanel.add(cd.createDeckOfCardsFront().get(index));
						cardLayout.next(startingCardPanel);
						mousePressedPlayerB=true;
					}
				}
			}
			public void mouseReleased(MouseEvent e)
			{		
				if(mousePressedPlayerA)
				{
					if(gameRules.matchSuit(cd.getPlayerACards(panelNumber), currentCard) || gameRules.matchRank(cd.getPlayerACards(panelNumber), currentCard))
					{	
						for(int i=0; i<storeIndexOfPlayerACards.size(); i++)
							ct.remove(remainingCards[i]);
						storeIndexOfPlayerACards.remove(panelNumber);
						cd.removePlayerACard(panelNumber);
						reorderPlayerCards(ct, "playerA", storeIndexOfPlayerACards);
						ct.repaint();
						ct.revalidate();
						mousePressedPlayerA=false;
					}
				}
				else if(mousePressedPlayerB)
				{
					if(gameRules.matchSuit(cd.getPlayerBCards(panelNumber), currentCard) || gameRules.matchRank(cd.getPlayerBCards(panelNumber), currentCard))
					{	
						for(int i=0; i<storeIndexOfPlayerBCards.size(); i++)
							ct.remove(remainingCards[i]);
						storeIndexOfPlayerBCards.remove(panelNumber);
						cd.removePlayerBCard(panelNumber);
						reorderPlayerCards(ct, "playerB", storeIndexOfPlayerBCards);
						ct.repaint();
						ct.revalidate();
						mousePressedPlayerB=false;
					}
				}
			}
			public void mouseClicked(MouseEvent e){}
			public void mouseExited(MouseEvent e) 
			{
				if(player.equals("playerA")&&playerCard.getX()==50)
					playerCard.setBounds(50, position, 80, 100);
				else if(player.equals("playerB")&&playerCard.getX()==550)
					playerCard.setBounds(550, position, 80, 100);
			}
			public void mouseEntered(MouseEvent e) 
			{
				if(player.equals("playerA")&&playerCard.getX()==50)
					playerCard.setBounds(50, position-10, 80, 100);
				else if(player.equals("playerB")&&playerCard.getX()==550)
					playerCard.setBounds(550, position-10, 80, 100);
			}
		};
		return ml;
	}
	
	void setPlayerACards(Container ct)
	{
		JPanel[] playerACards = new JPanel[5];
		Random randomIndex = new Random();
		
		int limit=0; 
		int cardIndex=0;
		int currentIndex=0;
		int cardPosition=70;
		for(int i=0; i<playerACards.length; i++)
		{
			limit=availableCards.size();
			cardIndex=randomIndex.nextInt(limit);
			currentIndex=cardIndex;
			cardIndex=availableCards.get(cardIndex);
			storeIndexOfPlayerACards.add(cardIndex);
			playerACards[i] = new JPanel(new BorderLayout());
			playerACards[i].setBounds(50, cardPosition, 80, 100);
			playerACards[i].add(cd.createDeckOfCardsFront().get(cardIndex));
			cd.addPlayerACards(cardIndex);
			playerACards[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			playerACards[i].addMouseListener(enlargePanel(playerACards, playerACards[i], i, cardPosition, "playerA", ct));
			getP=playerACards;
			availableCards.remove(currentIndex);
			cardPosition+=50;
			ct.add(playerACards[i]);
			ct.setComponentZOrder(playerACards[i], 0);
		}

	}
	void setPlayerBCards(Container ct)
	{
		JPanel[] playerBCards = new JPanel[5];
		Random randomIndex = new Random();
		int limit=0; 
		int cardIndex=0;
		int currentIndex=0;
		
		int cardPosition=70;
		for(int i=0; i<playerBCards.length; i++)
		{
			limit=availableCards.size();
			cardIndex=randomIndex.nextInt(limit);
			currentIndex=cardIndex;
			cardIndex=availableCards.get(cardIndex);
			storeIndexOfPlayerBCards.add(cardIndex);
			playerBCards[i] = new JPanel(new BorderLayout());
			playerBCards[i].setBounds(550, cardPosition, 80, 100);
			playerBCards[i].add(cd.createDeckOfCardsBack().get(cardIndex));
			//playerBCards[i].add(cd.createDeckOfCardsFront().get(cardIndex));
			cd.addPlayerBCards(cardIndex);
			playerBCards[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			playerBCards[i].addMouseListener(enlargePanel(playerBCards, playerBCards[i], i, cardPosition, "playerB", ct));
			availableCards.remove(currentIndex);
			cardPosition+=50;
			ct.add(playerBCards[i]);
			ct.setComponentZOrder(playerBCards[i], 0);
		}
	}
	void startingCard(Container ct)
	{
		JPanel startingCard = new JPanel(new BorderLayout());
		
		cardLayout=new CardLayout();
		startingCardPanel = new JPanel(cardLayout);
		startingCardPanel.setBounds(300, 100, 80, 100);
		
		Random randomIndex = new Random();
		int limit=0;
		int cardIndex=0;
		int currentIndex=0;
		
		limit=availableCards.size();
		cardIndex=randomIndex.nextInt(limit);
		currentIndex=cardIndex;
		cardIndex=availableCards.get(cardIndex);
		storeUsedCards.add(cardIndex);
		startingCard.add(cd.createDeckOfCardsFront().get(cardIndex));
		startingCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		currentCard=cd.getCardsDeck().get(cardIndex);
		availableCards.remove(currentIndex);
		startingCardPanel.add(startingCard);
	}
	void remainingDeck(Container ct)
	{
		JPanel deck = new JPanel(new BorderLayout());
		deck.setBounds(300, 250, 80, 100);
		
		int cardIndex=0;
		for(int i=0; i<availableCards.size(); i++)
		{
			cardIndex=availableCards.get(i);
			deck.add(cd.createDeckOfCardsBack().get(cardIndex));
			deck.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			ct.add(deck);
		}
	}
	void reorderPlayerCards(Container ct, String player, ArrayList<Integer> storeIndexOfPlayerCards)
	{
		JPanel[] playerCards = new JPanel[storeIndexOfPlayerCards.size()]; 
		
		int cardIndex=0;
		int cardPosition=70;
		
		for(int i=0; i<storeIndexOfPlayerCards.size(); i++)
		{
			cardIndex=storeIndexOfPlayerCards.get(i);
			playerCards[i] = new JPanel(new BorderLayout());
			if(player.equals("playerA"))
			{
				playerCards[i].setBounds(50, cardPosition, 80, 100);
				playerCards[i].add(cd.createDeckOfCardsFront().get(cardIndex));
				getP=playerCards;
			}
			else 
			{
				playerCards[i].setBounds(550, cardPosition, 80, 100);
				playerCards[i].add(cd.createDeckOfCardsBack().get(cardIndex));
			}
			playerCards[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			playerCards[i].addMouseListener(enlargePanel(playerCards, playerCards[i], i, cardPosition, player, ct));
			cardPosition+=50;
			ct.add(playerCards[i]);
			ct.setComponentZOrder(playerCards[i], 0);
		}
	}
	void deckAnimation(Container ct)
	{
		ActionListener taskPerformer = new ActionListener() { 
			public void actionPerformed(ActionEvent evt)
			{ 
				if(x != 120)
				{
					dummPanel.add(cd.createDeckOfCardsBack().get(0));
					dummPanel.setBounds(x, y, sizeX, sizeY);
					x-=30; y+=10;sizeX-=10;sizeY-=10;
					ct.add(dummPanel);
					ct.repaint();
					ct.revalidate();
					deckAnimation(ct);
					
					if(x==120)
						ct.remove(dummPanel);
				}
		    }};
	    Timer timer = new Timer(1, taskPerformer);
	    timer.setRepeats(false); 
	    timer.start(); 
	}
}