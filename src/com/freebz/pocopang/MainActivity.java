package com.freebz.pocopang;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.freebz.pocopang.model.Animal;
import com.freebz.pocopang.model.AnimalAdapter;
import com.freebz.pocopang.model.AnimalList;
import com.freebz.pocopang.model.AnimalListDatabaseHelper;
import com.mocoplex.adlib.AdlibActivity;
import com.mocoplex.adlib.AdlibConfig;

public class MainActivity extends AdlibActivity {
	
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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
    protected void initAds() {
    	
    	AdlibConfig.getInstance().bindPlatform("CAULY", "adlib.ads.SubAdlibAdViewCauly");
    	AdlibConfig.getInstance().setAdlibKey("530f6fefe4b08300de8d9756");
    }
    
    public void onClickClear(View view) {
    	databaseHelper.clearAnimal();
    	refresh();
    }
    
    public void onClickGetAnimal(View view) {
    	
    	Animal animal = animals.getRandom();
    	
    	databaseHelper.saveAnimal(animal);
    	refresh();
    }
    
    private void refresh() {
    	animalAdapter.changeCursor(databaseHelper.getAnimalList());
    }
    
}
