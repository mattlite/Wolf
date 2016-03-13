package com.sundayleaguers.wolf;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Wolf_Setup extends Activity implements OnClickListener 
{
	
	private GameManager m_manager;
	private Button m_button;
	
	@Override
	public void onCreate(Bundle bundle) 
	{
		m_manager = Utils.getManager( this );
		super.onCreate(bundle);
     
		initializeLayout();
	}

	private void initializeLayout() 
	{
		setContentView(R.layout.wolf_setup);
		m_button = (Button)findViewById(R.id.startGame);		
		m_button.setOnClickListener(this);
		
	//custom font for title 		
	//	Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Metamorphous-Regular.ttf");
	//    TextView tv = (TextView) findViewById(R.id.CustomFontText);
	//    tv.setTypeface(tf);
		
		initializeNames();
	}

	@Override
	public void onClick( View v ) 
	{
		switch(v.getId()) 
		{
		case R.id.startGame:
			saveNames();
			
			Intent intent = new Intent( this, Wolf_Tee.class );
			this.startActivity( intent );
			break;
		}
	}
	
	private void initializeNames()
	{
		for( Player player : m_manager.getPlayers() )
		{
			EditText edit = grabEditTextForPlayer( player );
			edit.setHint( player.getName() );	
		}
	}
	
	private void saveNames()
	{	
		for( Player player : m_manager.getPlayers() )
		{
			EditText edit = grabEditTextForPlayer( player );
			String name = edit.getText().toString();
			if( !name.equals( "" ) )
				player.setName( name );	
		}
	}
	
	// Grab the EditText object for a player index
	private EditText grabEditTextForPlayer( Player player )
	{
		switch( player.ID )
		{
		case 0:
			return ( EditText )findViewById( R.id.playerName1 );
		case 1:
			return ( EditText )findViewById( R.id.playerName2 );
		case 2:
			return ( EditText )findViewById( R.id.playerName3 );
		case 3:
			return ( EditText )findViewById( R.id.playerName4 );
		}
		
		return null;
	}
}
