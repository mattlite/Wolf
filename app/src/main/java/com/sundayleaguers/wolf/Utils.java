package com.sundayleaguers.wolf;

import android.app.Activity;

public class Utils
{
	public static GameManager getManager( Activity activity )
	{
		GamesOfGolf app = (GamesOfGolf)activity.getApplication();
		GameManager manager = app.getGameManager();
		return manager;
	}
}