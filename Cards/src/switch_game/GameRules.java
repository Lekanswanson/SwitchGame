package switch_game;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class GameRules
{
	int cardsToPickUp=0;
	
	boolean matchSuit(Card playerCard, Card middleCard)
	{
		boolean res=false;
		if(playerCard.getSuit().equals(middleCard.getSuit()))
			res=true;
		else if(playerCard.getRank().equals("J"))
			res=true;
		
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

	void pickUpTwoCards(Card card, String player, ArrayList<Integer> playerCards, CardDeck cd, CardsDesign c, Container ct)
	{
		boolean res=false;

		if(card==null)
			res=false;
		else if(card.getRank().equals("2"))
		{
			cardsToPickUp+=2;
			for(int i=0; i<playerCards.size(); i++)
			{
				if(player.equals("playerA"))
					if(cd.getPlayerACards(i).getRank().equals("2"))
						res=true;
				if(player.equals("playerB"))
					if(cd.getPlayerBCards(i).getRank().equals("2"))
						res=true;					
			}
		}
		if(res==false)
		{
			for(int i=0; i<cardsToPickUp; i++)
				c.drawTwoCards(ct, player);
			cardsToPickUp=0;
		}
	}
	
	void pickUpMultipleCards()
	{
//		else if(card.getRank().equals("A")&&card.getSuit().equals("Hearts"))
//		{
//			cardsToPickUp+=5;
//			for(int i=0; i<playerCards.size(); i++)
//			{
//				if(player.equals("playerA"))
//				{
//					if(cd.getPlayerACards(i).getRank().equals("2")&&cd.getPlayerACards(i).getSuit().equals("Hearts"))
//					{
//						cardsToPickUp+=2;
//						res=true;
//					}
//					else if(cd.getPlayerACards(i).getRank().equals("5")&&cd.getPlayerACards(i).getSuit().equals("Hearts"))
//					{
//						cardsToPickUp=0;
//						res=true;
//					}
//				}
//				
//				if(player.equals("playerB"))
//				{
//					if(cd.getPlayerBCards(i).getRank().equals("2")&&cd.getPlayerBCards(i).getSuit().equals("Hearts"))
//					{
//						cardsToPickUp+=2;
//						res=true;
//					}
//					else if(cd.getPlayerBCards(i).getRank().equals("5")&&cd.getPlayerBCards(i).getSuit().equals("Hearts"))
//					{
//						cardsToPickUp=0;
//						res=true;
//					}
//				}
//			}
//		}
	}
	void reverseGameRotation()
	{
		
	}
	void blockCardPickUp()
	{
		
	}
}
