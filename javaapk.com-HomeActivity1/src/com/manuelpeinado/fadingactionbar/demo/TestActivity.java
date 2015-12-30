package com.manuelpeinado.fadingactionbar.demo;


import android.app.Activity;
import android.os.Bundle;

public class TestActivity  extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_test);
      
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.mF1, new SampleFragment())
                    .commit();
        }

		
		
	}

}
