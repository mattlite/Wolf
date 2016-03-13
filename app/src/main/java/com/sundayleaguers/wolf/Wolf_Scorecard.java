package com.sundayleaguers.wolf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Wolf_Scorecard extends Activity implements OnClickListener, OnGestureListener {
	private GameManager m_manager;
	private Button m_button;
	
	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector m_gestureDetector;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle bundle) 
	{
		m_manager = Utils.getManager( this );	
		super.onCreate(bundle);
		initLayout();
		m_gestureDetector = new GestureDetector( this );
	}
	
	private void initLayout() 
	{
		setContentView( R.layout.wolf_scorecard );
		
		m_button = (Button)findViewById(R.id.nextHoleButton);		
		m_button.setOnClickListener(this);		
		
		Player p1 = m_manager.position1();
		Player p2 = m_manager.position2();
		Player p3 = m_manager.position3();
		Player p4 = m_manager.position4();
		
		// Player Names
		TextView p1View = (TextView)findViewById( R.id.T2P4 );
		TextView p2View = (TextView)findViewById( R.id.T2P3 );
		TextView p3View = (TextView)findViewById( R.id.T1P2 );
		TextView p4View = (TextView)findViewById( R.id.T1P1 );
		p1View.setText( p1.getName() );
		p2View.setText( p2.getName() );
		p3View.setText( p3.getName() );
		p4View.setText( p4.getName() );
		
		initializeNames();
	}
	
	private void initializeNames()
	{
						
		TextView greenhole = (TextView)findViewById( R.id.greenHole );
		greenhole.setText( String.format( "%d", m_manager.getCurrentHole() ) );
		
	}
	
	@Override
	public void onClick( View v ) //once clicked app needs to calc what team won the hole by lowest score and apply points...
	{
		switch(v.getId()) 
		{
		case R.id.nextHoleButton:
			//m_manager.calcScores();
			m_manager.cyclePlayers();
			m_manager.nextHole();
			
			//if the next hole is greater than 18 it needs to end the game and go to the leader board
			int hole = m_manager.getCurrentHole();
			
			if( hole < 19 ) {
				Intent intent = new Intent( this, Wolf_Tee.class );
				this.startActivity( intent );}
			else {
				Intent intent = new Intent ( this, Wolf_Leaderboard.class );
				this.startActivity( intent );
				break;
			}
		}
	}
	
	@Override
    public boolean onTouchEvent( MotionEvent event ) 
	{
        return m_gestureDetector.onTouchEvent( event );
    }
	
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

        try 
        {
            if( Math.abs(e1.getY() - e2.getY() ) > SWIPE_MAX_OFF_PATH )
                return false;
            
            // right to left swipe
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Toast.makeText(Wolf_Scorecard.this, "Left Swipe", Toast.LENGTH_SHORT).show();
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Toast.makeText(Wolf_Scorecard.this, "Right Swipe", Toast.LENGTH_SHORT).show();
            }
            
			Intent intent = new Intent ( this, Wolf_Leaderboard.class );
			this.startActivity( intent );
        } 
        catch( Exception e ) {}
        
        return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
}
