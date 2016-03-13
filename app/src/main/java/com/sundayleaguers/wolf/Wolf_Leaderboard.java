package com.sundayleaguers.wolf;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Wolf_Leaderboard extends Activity implements OnClickListener 
{
	private GameManager m_manager;
	
	@Override
	public void onCreate(Bundle bundle) 
	{
		m_manager = Utils.getManager( this );	
		super.onCreate(bundle);
		initLayout();
	}
	
	@Override
	public void onClick( View v ) 
	{
	
		
	}
	
	private void initLayout() 
	{
		setContentView( R.layout.wolf_leaderboard );
		
		//custom font for title 		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Metamorphous-Regular.ttf");
	    TextView tv = (TextView) findViewById(R.id.CustomFontText);
	    tv.setTypeface(tf);
		
		Player p1 = m_manager.position1();
		Player p2 = m_manager.position2();
		Player p3 = m_manager.position3();
		Player p4 = m_manager.position4();
		
		// Player Names
		TextView p1View = (TextView)findViewById( R.id.playerInFirst );
		TextView p2View = (TextView)findViewById( R.id.playerInSecond );
		TextView p3View = (TextView)findViewById( R.id.playerInThird );
		TextView p4View = (TextView)findViewById( R.id.playerInFourth );
		p1View.setText( p1.getName() );
		p2View.setText( p2.getName() );
		p3View.setText( p3.getName() );
		p4View.setText( p4.getName() );
		
		// Player Score
		
		// Player Points
		TextView p1TotalView = (TextView)findViewById( R.id.p1Points );
		TextView p2TotalView = (TextView)findViewById( R.id.p2Points );
		TextView p3TotalView = (TextView)findViewById( R.id.p3Points );
		TextView p4TotalView = (TextView)findViewById( R.id.p4Points );
		p1TotalView.setText( String.format( "%.2f", p1.getScore() ) );
		p2TotalView.setText( String.format( "%.2f", p2.getScore() ) );
		p3TotalView.setText( String.format( "%.2f", p3.getScore() ) );
		p4TotalView.setText( String.format( "%.2f", p4.getScore() ) );
		
		// Player Bet (net money)
		
		
	}
}