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

public class Wolf_Tee extends Activity implements OnClickListener, OnGestureListener 
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
		Player wolf = m_manager.getCurrentWolf();
		
		switch( v.getId() ) 
		{
		case R.id.loneButton:
			wolf.setLoneWolf();
			break;
		case R.id.blindButton:
			wolf.setBlindWolf();
			break;
		case R.id.p1Button:
			wolf.setTeamMate( m_manager.getPlayers().get(1) );
			break;
		case R.id.p2Button:
			wolf.setTeamMate( m_manager.getPlayers().get(2) );
			break;
		case R.id.p3Button:
			wolf.setTeamMate( m_manager.getPlayers().get(3) );
			break;
		}
		
		Intent intent = new Intent( this, Wolf_Scorecard.class );
		this.startActivity( intent );
	}
	
	private void initLayout() 
	{
		setContentView(R.layout.wolf_tee);
		
		for( Player player : m_manager.getPlayers() )
		{
			if( player == m_manager.getCurrentWolf() )
				continue;
			
			TextView view = grabViewForPlayer( player );
			view.setOnClickListener( this );
		}
		
		TextView lone = (TextView)findViewById( R.id.loneButton );
		TextView blind = (TextView)findViewById( R.id.blindButton );
		lone.setOnClickListener( this );
		blind.setOnClickListener( this );
		
		initializeNames();
	}
	
	private void initializeNames()
	{
		for( Player player : m_manager.getPlayers() )
		{
			TextView edit = grabViewForPlayer( player );
			edit.setText( player.getName() );
		}
		
		TextView hole = (TextView)findViewById( R.id.hole );
		hole.setText( String.format( "%d", m_manager.getCurrentHole() ) );
	}
	
	// Grab the TextView object for a player index
	private TextView grabViewForPlayer( Player player )
	{
		switch( player.ID )
		{
		case 0:
			return (TextView)findViewById(R.id.activeWolf);
		case 1:
			return (TextView)findViewById(R.id.p1Button);
		case 2:
			return (TextView)findViewById(R.id.p2Button);
		case 3:
			return (TextView)findViewById(R.id.p3Button);
		}
		
		return null;
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
                Toast.makeText(Wolf_Tee.this, "Left Swipe", Toast.LENGTH_SHORT).show();
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Toast.makeText(Wolf_Tee.this, "Right Swipe", Toast.LENGTH_SHORT).show();
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
