package com.sundayleaguers.wolf;

public class Player 
{
	public Player( int id, String name )
	{
		ID = id;
		m_name = name;
		m_buyin = 20.0;
		m_teamMate = null;
		m_score = 0;
	}
	
	//
	// An id for the player (gets set to the index in the master list).
	//
	public int ID;
	
	//
	// The player name
	//
	public String getName()
	{
		return m_name;
	}
	public void setName( String name )
	{
		m_name = name;
	}
	private String m_name;
	
	//
	// The player buyin
	//
	public Double getBuyin()
	{
		return m_buyin;
	}
	public void setBuyin( Double buyin )
	{
		m_buyin = buyin;
	}
	private Double m_buyin;
	
	//
	// The player's current teammate,
	// null if none (lone wolf or blind wolf).
	//
	public Player getTeamMate()
	{
		return m_teamMate;
	}
	public void setTeamMate( Player teamMate )
	{
		m_teamMate = teamMate;
	}
	private Player m_teamMate;
	
	//
	// Lone wolf?
	//
	public boolean isLoneWolf()
	{
		return m_loneWolf;
	}
	public void setLoneWolf()
	{
		m_loneWolf = true;
		m_blindWolf = false;
		m_teamMate = null;
	}
	private boolean m_loneWolf;
	
	//
	// Blind wolf?
	//
	public boolean isBlindWolf()
	{
		return m_blindWolf;
	}
	public void setBlindWolf()
	{
		m_blindWolf = true;
		m_loneWolf = false;
		m_teamMate = null;
	}
	private boolean m_blindWolf;
	
	//
	// Score
	//
	public double getScore()
	{
		return m_score;
	}
	public void incrementScore( double increment )
	{
		m_score += increment;
	}
	private double m_score;
	
	//
	// Money
	//


}

