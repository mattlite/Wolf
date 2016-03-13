package com.sundayleaguers.wolf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class GameManager 
{
	public GameManager()
	{
		m_hole = 1;
		
		m_players = new Vector<Player>();
		int id = 0;
		m_players.add( new Player( id++, "Player 1" ) );
		m_players.add( new Player( id++, "Player 2" ) );
		m_players.add( new Player( id++, "Player 3" ) );
		m_players.add( new Player( id++, "Player 4" ) );
	}
	
	private void setIDs()
	{
		int id = 0;
		for( Player player : getPlayers() )
			player.ID = id++;
	}
	
	public void cyclePlayers()
	{
		Player lastWolf = getCurrentWolf();
		m_players.remove( 0 );
		m_players.add( lastWolf );
		setIDs();
	}
	
	public Player getCurrentWolf()
	{
		return m_players.get(0);
	}
	
	//
	// Our list of players.
	//
	public Vector<Player> getPlayers()
	{
		return m_players;
	}
	private Vector<Player> m_players;
	
	public Vector<Player> getTeam1()
	{
		Vector<Player> result = new Vector<Player>();
		
		Player wolf = getCurrentWolf();
		result.add( wolf );
		if( wolf.getTeamMate() != null )
			result.add( wolf.getTeamMate() );
		
		return result;
	}
	

	public Vector<Player> getTeam2()
	{
		Vector<Player> result = new Vector<Player>();
		
		Vector<Player> team1 = getTeam1();
		for( Player player : getPlayers() )
		{
			if( !inTeam( team1, player ) )
				result.add( player );
		}
		
		return result;
	}
	
	private static boolean inTeam( Vector<Player> team, Player p )
	{
		for( Player teamPlayer : team )
		{
			if( p == teamPlayer )
				return true;
		}
		return false;
	}
	
	//
	// Information about the hole we are on.
	//
	public int getCurrentHole()
	{
		return m_hole;
	}
	public void nextHole()
	{
		m_hole++;
		
		if( m_hole >= 17 )
		{
			Player lastPlace = lastPlace();
			while( getCurrentWolf() != lastPlace )
				cyclePlayers();
		}
	}
	private int m_hole;
	
	//
	// Ranking
	//
	
	public Player position1()
	{
		return position( 1 );
	}
	
	public Player position2()
	{
		return position( 2 );
	}
	
	public Player position3()
	{
		return position( 3 );	
	}
	
	public Player position4()
	{
		return position( 4 );
	}
	
	private Player position( int pos )
	{
		List<Player> list = new ArrayList<Player>( m_players );
		Collections.sort( list, new PlayerComparator() );
		return list.get( 4 - pos );
	}
	
	private class PlayerComparator implements Comparator<Player> 
	{
	    @Override
	    public int compare( Player p1, Player p2 ) 
	    {
	    	double s1 = p1.getScore();
	    	double s2 = p2.getScore();
	    	if( s1 < s2 ) return -1;
	        if( s1 > s2 ) return 1;
	        return 0;
	    }
	}
	
	private Player lastPlace()
	{
		return position4();
	}
	
	//
	// Scoring
	//
	
	private double m_pushedPoints = 0;
	
	public void calcScores()
	{
		//Vector<Player> p1 = getP1HoleScore;
	}
	
	public void team1WinsHole()
	{
		double points = pointsUpForGrabs();
		Vector<Player> t1 = getTeam1();
		for( Player p : t1 )
			p.incrementScore( points / t1.size() );
		m_pushedPoints = 0;
	}
	
	public void team2WinsHole()
	{
		double points = pointsUpForGrabs();
		Vector<Player> t2 = getTeam2();
		for( Player p : t2 )
			p.incrementScore( points / t2.size() );
		m_pushedPoints = 0;
	}
	
	public void pushHole()
	{
		m_pushedPoints = pointsUpForGrabs();
	}
	
	private double pointsUpForGrabs()
	{		
		double points = 0;
		Player wolf = getCurrentWolf();
		if( wolf.isBlindWolf() )
			points = 6;
		else if( wolf.isLoneWolf() )
			points = 3;
		else
			points = 2;
		
		return points + m_pushedPoints;
	}
}
