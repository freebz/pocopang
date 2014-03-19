package com.freebz.pocopang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freebz.pocopang.model.Animal;
import com.freebz.pocopang.model.AnimalAdapter;
import com.freebz.pocopang.model.AnimalList;
import com.freebz.pocopang.model.AnimalListDatabaseHelper;
import com.freebz.pocopang.model.Constant;
import com.mocoplex.adlib.AdlibActivity;
import com.mocoplex.adlib.AdlibConfig;

public class MainActivity extends AdlibActivity {
	
	private AnimalList animals = new AnimalList();
	AnimalAdapter animalAdapter;
	ListView listView;
	TextView cherry;
	RelativeLayout cherryStore;
	ImageView btnGetAnimal;
	
	private AnimalListDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initAds();
        this.setAdsContainer(R.id.ads);
        
        databaseHelper = new AnimalListDatabaseHelper(this);
        
        listView = (ListView) findViewById(R.id.animal_list);
        animalAdapter = new AnimalAdapter(this, databaseHelper.getAnimalList());
        listView.setAdapter(animalAdapter);
        
        cherry = (TextView) findViewById(R.id.cherry);
        cherryStore = (RelativeLayout) findViewById(R.id.cherry_store);
        btnGetAnimal = (ImageView) findViewById(R.id.btn_get_animal);
        
        refreshCherry();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
    protected void initAds() {
    	AdlibConfig.getInstance().bindPlatform("ADAM", "adlib.ads.SubAdlibAdViewAdam");
    	AdlibConfig.getInstance().bindPlatform("ADMOB", "adlib.ads.SubAdlibAdViewAdmob");
    	AdlibConfig.getInstance().bindPlatform("CAULY", "adlib.ads.SubAdlibAdViewCauly");
    	AdlibConfig.getInstance().bindPlatform("NAVER", "adlib.ads.SubAdlibAdViewNaverAdPost");
    	AdlibConfig.getInstance().bindPlatform("ADHUB", "adlib.ads.SubAdlibAdViewAdHub");
    	AdlibConfig.getInstance().setAdlibKey("5314e977e4b08300de8daf11");
    }
    
    public void onClickClear(View view) {
    	databaseHelper.clearAnimal();
    	refresh();
    }
    
    public void onClickGetAnimal(View view) {
    	pushNewAnimal();
    }
    
    public void onClickCherry(View view) {
    	openCherryStore();
    }
    
    public void onClickCherryStore(View view) {
    	
    	switch(view.getId()) {
    	case R.id.btnCherry1:
    		databaseHelper.addCherry(6000);
    		refreshCherry();
    		break;
    	case R.id.btnCherry2:
    		databaseHelper.addCherry(33000);
    		refreshCherry();
    		break;
    	case R.id.btnCherry3:
    		databaseHelper.addCherry(864000);
    		refreshCherry();
    		break;
    	case R.id.btnCherry4:
    		databaseHelper.addCherry(195000);
    		refreshCherry();
    		break;
    	}
    	
    	closeCherryStore();
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch (requestCode) {
    	case Constant.CONTINUE_GET_ANIAML:
    		if (resultCode == Constant.RESULT_CONTINUE) {
    			pushNewAnimal();
    		}
    		break;
    	}
    }
    
    private void pushNewAnimal() {
    	if (!databaseHelper.hasCherry(6000)) {
    		return;
    	}
    	
    	Animal animal = animals.getRandom();
    	databaseHelper.saveAnimal(animal);
    	refresh();
    	
    	Intent intent = new Intent(this, PopupGetAnimalActivity.class);
    	intent.putExtra("id", animal.getId());
    	startActivityForResult(intent, Constant.CONTINUE_GET_ANIAML);
    }
    
    private void refresh() {
    	refreshCherry();
    	refreshList();
    }
    
    private void refreshList() {
    	animalAdapter.changeCursor(databaseHelper.getAnimalList());
    }
    
    private void refreshCherry() {
    	cherry.setText(Long.toString(databaseHelper.getCherry()));
    }
    
    private void openCherryStore() {
    	visibleCherryStore(true);
    }
    
    private void closeCherryStore() {
    	visibleCherryStore(false);
    }
    
    private void visibleCherryStore(boolean value) {
    	boolean state = !value;
    	int visible = (value) ? View.VISIBLE : View.INVISIBLE;
    	
    	listView.setEnabled(state);
    	btnGetAnimal.setEnabled(state);
    	cherryStore.setVisibility(visible);
    }
}
