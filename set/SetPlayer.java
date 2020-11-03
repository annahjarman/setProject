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
	
	public static SetPlayer highScore(SetPlayer[] players)
	{
		// return the top scorer from an array of SetPlayers
		return null;
	}

}
