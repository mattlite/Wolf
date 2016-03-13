package com.sundayleaguers.wolf;

import android.app.Application;

public class GamesOfGolf extends Application {

	public GamesOfGolf()
	{
		m_gameManager = new GameManager();
	}
	
	private GameManager m_gameManager;
	
	public GameManager getGameManager() { return m_gameManager; }
}
