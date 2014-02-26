package com.freebz.pocopang;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.freebz.pocopang.model.Animal;
import com.freebz.pocopang.model.AnimalAdapter;
import com.freebz.pocopang.model.AnimalList;
import com.freebz.pocopang.model.AnimalListDatabaseHelper;

public class MainActivity extends Activity {
	
	private AnimalList animals = new AnimalList();
	AnimalAdapter animalAdapter;
	
	private AnimalListDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        databaseHelper = new AnimalListDatabaseHelper(this);
        
        ListView listView = (ListView) findViewById(R.id.animal_list);
        animalAdapter = new AnimalAdapter(this, databaseHelper.getAnimalList());
        listView.setAdapter(animalAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onClickGetAnimal(View view) {
    	
    	Animal animal = animals.getRandom();
    	
    	databaseHelper.saveAnimal(animal);
    	animalAdapter.changeCursor(databaseHelper.getAnimalList());    	
    }
    
}
