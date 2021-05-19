package switch_game;

class Card 
{
	private String rank;
	private String suit;
	private String suitImage;
	
	Card(String rank, String suit, String suitImage) 
	{
		this.rank=rank;
		this.suit=suit;
		this.suitImage=suitImage;
	}
	String getRank() 
	{
		return rank;
	}
	void setRank(String rank) 
	{
		this.rank = rank;
	}
	String getSuit() 
	{
		return suit;
	}
	void setSuit(String suit) 
	{
		this.suit = suit;
	}
	public String getImg()
	{
		return suitImage;
	}
	public void setImg(String imageLocation)
	{
		this.suitImage = imageLocation;
	}	
}