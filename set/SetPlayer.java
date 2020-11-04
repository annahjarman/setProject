package set;

public class SetPlayer {
	
	private int score;
	private String name;
	
	public SetPlayer(int score,String name)
	{
		this.score = score;
		this.name = name;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name+": "+score;
	}
	
	public static SetPlayer highScore(SetPlayer[] players,int numPlayers)
	{
		// return the top scorer from an array of SetPlayers
		SetPlayer max = players[0];
		for(int i = 0; i < numPlayers; i++)
		{
			if(players[i].score > max.score)
			{
				max = players[i];
			}
		}
		return max;
	}

}
