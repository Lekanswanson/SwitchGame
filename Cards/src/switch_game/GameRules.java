package switch_game;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class GameRules
{
	boolean matchSuit(Card playerCard, Card middleCard)
	{
		boolean res=false;
		if(playerCard.getSuit().equals(middleCard.getSuit()))
		{
			res=true;
		}
		return res;
	}
	boolean matchRank(Card playerCard, Card middleCard)
	{
		boolean res=false;
		if(playerCard.getRank().equals(middleCard.getRank()))
		{
			res=true;
		}
		return res;	
	}
	void pickUpSingleCard(CardsDesign cs, ArrayList<Integer> storeIndexOfCards, ArrayList<Integer> availableCards, JPanel[] getP, Container ct, CardDeck cd, int p)
	{
		for(int i=0; i<storeIndexOfCards.size(); i++)
			ct.remove(getP[i]);
		
		Random rand = new Random();
		int num = rand.nextInt(availableCards.size());
		storeIndexOfCards.add(availableCards.get(num));
		
		if(p==1)
		{
			cs.reorderPlayerCards(ct, "playerA", storeIndexOfCards);
			cd.addPlayerACards(availableCards.get(num));
		}
		else if(p==2)
		{
			cs.reorderPlayerCards(ct, "playerB", storeIndexOfCards);
			cd.addPlayerBCards(availableCards.get(num));
		}
		
		ct.repaint();
		ct.revalidate();
		availableCards.remove(num);
	}
	ArrayList<Integer> reloadEmptyDeck(ArrayList<Integer> availableCards, ArrayList<Integer> usedCards)
	{
		System.out.println("Deck Empty, Reload......");
		availableCards=usedCards;
		return availableCards;
	}
	
	void pickUpMultipleCards()
	{
		
	}
	void reverseGameRotation()
	{
		
	}
	void blockCardPickUp()
	{
		
	}
}
