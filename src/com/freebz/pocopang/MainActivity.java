package com.freebz.pocopang;

import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freebz.pocopang.model.Animal;
import com.freebz.pocopang.model.AnimalAdapter;
import com.freebz.pocopang.model.AnimalList;
import com.freebz.pocopang.model.AnimalListDatabaseHelper;
import com.mocoplex.adlib.AdlibActivity;
import com.mocoplex.adlib.AdlibConfig;

public class MainActivity extends AdlibActivity {
	
	private AnimalList animals = new AnimalList();
	private AnimalAdapter animalAdapter;
	private ImageView cherry_back;
	private ListView listView;
	private TextView cherry;
	private RelativeLayout cherryStore;
	private ImageView btnGetAnimal;
	
	private RelativeLayout popupGetAnimal;
	private ImageView plant1;
	private ImageView plant2;
	private ImageView plant3;
	private ImageView plant4;
	private ImageView imgAnimal;
	
	private RelativeLayout popupAlert;
	
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
        
        cherry_back = (ImageView) findViewById(R.id.cherry_back);
        cherry = (TextView) findViewById(R.id.cherry);
        cherryStore = (RelativeLayout) findViewById(R.id.cherry_store);
        btnGetAnimal = (ImageView) findViewById(R.id.btn_get_animal);
        
        popupGetAnimal = (RelativeLayout) findViewById(R.id.popup_get_animal);
        plant1 = (ImageView) findViewById(R.id.plant1);
        plant2 = (ImageView) findViewById(R.id.plant2);
        plant3 = (ImageView) findViewById(R.id.plant3);
        plant4 = (ImageView) findViewById(R.id.plant4);
        imgAnimal = (ImageView) findViewById(R.id.animal);
        
        popupAlert = (RelativeLayout) findViewById(R.id.popup_alert);
        
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
    	openGatAnimalPopup();
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
    		databaseHelper.addCherry(86400);
    		refreshCherry();
    		break;
    	case R.id.btnCherry4:
    		databaseHelper.addCherry(195000);
    		refreshCherry();
    		break;
    	}
    	
    	closeCherryStore();
    }
    
    public void onClickGetAnimalPopupOk(View view) {
    	closeGatAnimalPopup();
	}
    
    public void onClickGetAnimalPopupContinue(View view) {
    	pushNewAnimal();
    }
    
    public void onClickAlertPopupClose(View view) {
    	closeAlertPopup();
    }
    
    public void onClickAlertPopupOk(View view) {
    	closeAlertPopup();
    	openCherryStore();
    }
    
    private void pushNewAnimal() {
    	if (!databaseHelper.hasCherry(6000)) {
    		closeGatAnimalPopup();
    		openAlertPopup();
    		return;
    	}
    	
    	Animal animal = animals.getRandom();
    	
    	int resId = this.getResources().getIdentifier("_" + animal.getId(), "drawable", "com.freebz.pocopang");
    	imgAnimal.setImageResource(resId);
    	removePlants();
    	
    	databaseHelper.saveAnimal(animal);
    	refresh();
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
    	int visible = (value) ? View.VISIBLE : View.INVISIBLE;
    	buttonClickable(!value);
    	cherryStore.setVisibility(visible);
    }
    
    private void openGatAnimalPopup() {
    	visibleGatAnimalPopup(true);
    }
    
    private void closeGatAnimalPopup() {
    	visibleGatAnimalPopup(false);
    }
    
    private void visibleGatAnimalPopup(boolean value) {
    	int visible = (value) ? View.VISIBLE : View.INVISIBLE;
    	buttonClickable(!value);
    	popupGetAnimal.setVisibility(visible);
    }
    
    private void openAlertPopup() {
    	visibleAlertPopup(true);
    }
    
    private void closeAlertPopup() {
    	visibleAlertPopup(false);
    }
    
    private void visibleAlertPopup(boolean value) {
    	int visible = (value) ? View.VISIBLE : View.INVISIBLE;
    	buttonClickable(!value);
    	popupAlert.setVisibility(visible);
    }
    
    private void buttonClickable(boolean state) {
    	cherry_back.setEnabled(state);
    	listView.setEnabled(state);
    	btnGetAnimal.setEnabled(state);
    }
    
    private void removePlants() {
		
		TranslateAnimation left1 = new TranslateAnimation(0, -1000, 0, 0);
		TranslateAnimation left2 = new TranslateAnimation(0, -1000, 0, 0);
		TranslateAnimation right1 = new TranslateAnimation(0, 1000, 0, 0);
		TranslateAnimation right2 = new TranslateAnimation(0, 1000, 0, 0);
		
		left1.setDuration(1000);
		left1.setFillAfter(true);
		left2.setDuration(1200);
		left2.setFillAfter(true);
		
		right1.setDuration(1000);
		right1.setFillAfter(true);
		right2.setDuration(1200);
		right2.setFillAfter(true);
		
		plant1.startAnimation(left2);
		plant2.startAnimation(right2);
		plant3.startAnimation(left1);
		plant4.startAnimation(right1);
	}
}
