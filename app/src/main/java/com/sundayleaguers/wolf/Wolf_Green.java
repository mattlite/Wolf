package com.sundayleaguers.wolf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class Wolf_Green extends Activity implements OnClickListener, OnGestureListener
{
	private GameManager m_manager;
	
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
	
	@Override
	public void onClick( View v ) 
	{
		switch( v.getId() ) 
		{
		case R.id.t1Button:
			m_manager.team1WinsHole();
			break;
		case R.id.t2Button:
			m_manager.team2WinsHole();
			break;
		case R.id.pushButton:
			m_manager.pushHole();
			break;
		}
		
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
		}
	}
	
	private void initLayout() 
	{
		setContentView( R.layout.wolf_green );
		
		TextView team1 = (TextView)findViewById( R.id.t1Button );
		TextView team2 = (TextView)findViewById( R.id.t2Button );
		TextView push =(TextView)findViewById( R.id.pushButton );
		team1.setOnClickListener( this );
		team2.setOnClickListener( this );
		push.setOnClickListener( this );
		
		initializeNames();
	}
	
	private void initializeNames()
	{
		Player wolf = m_manager.getCurrentWolf();
		TextView wolfTextView = (TextView)findViewById( R.id.t1p1 );
		TextView wolfTextView2 = (TextView)findViewById( R.id.greenActiveWolf );
		wolfTextView.setText( wolf.getName() );
		wolfTextView2.setText( wolf.getName() );
		
		Vector<Player> team1 = m_manager.getTeam1();
		Vector<Player> team2 = m_manager.getTeam2();
		
		TextView t1p2Edit = (TextView)findViewById( R.id.t1p2 );
		TextView t2p3Edit = (TextView)findViewById( R.id.t2p3 );
		TextView t2p4Edit = (TextView)findViewById( R.id.t2p4 );
		
		if( team1.size() == 2 )
		{
			assert( team2.size() == 2 );
			t1p2Edit.setText( team1.get(1).getName() );
			t2p3Edit.setText( team2.get(0).getName() );
			t2p4Edit.setText( team2.get(1).getName() );
		}
		else
		{
			assert( team1.size() == 1 );
			assert( team2.size() == 3 );
			t1p2Edit.setText( team2.get(0).getName() );
			t2p3Edit.setText( team2.get(1).getName() );
			t2p4Edit.setText( team2.get(2).getName() ); 	
		}
		
		TextView greenhole = (TextView)findViewById( R.id.greenHole );
		greenhole.setText( String.format( "%d", m_manager.getCurrentHole() ) );
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
                Toast.makeText(Wolf_Green.this, "Left Swipe", Toast.LENGTH_SHORT).show();
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Toast.makeText(Wolf_Green.this, "Right Swipe", Toast.LENGTH_SHORT).show();
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
