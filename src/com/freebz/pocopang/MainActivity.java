package com.freebz.pocopang;

import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
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
	private ImageView diamond_back;
	private ImageView cherry_back;
	private ListView listView;
	private TextView cherry;
	private TextView diamond;
	private TextView usedCherry;
	private Button btnClear;
	private RelativeLayout cherryStore;
	private RelativeLayout diamondStore;
	private ImageView btnGetAnimal;
	private ImageView btnComposite;
	
	private RelativeLayout popupGetAnimal;
	private ImageView plant1;
	private ImageView plant2;
	private ImageView plant3;
	private ImageView plant4;
	private ImageView imgAnimal;
	
	private RelativeLayout popupComposite;
	
	private RelativeLayout popupAlertCherry;
	private RelativeLayout popupAlertDiamond;
	
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
        
        diamond_back = (ImageView) findViewById(R.id.diamond_back);
        cherry_back = (ImageView) findViewById(R.id.cherry_back);
        cherry = (TextView) findViewById(R.id.cherry);
        diamond = (TextView) findViewById(R.id.diamond);
        usedCherry = (TextView) findViewById(R.id.used_cherry);
        btnClear = (Button) findViewById(R.id.btn_clear);
        cherryStore = (RelativeLayout) findViewById(R.id.cherry_store);
        diamondStore = (RelativeLayout) findViewById(R.id.diamond_store);
        btnGetAnimal = (ImageView) findViewById(R.id.btn_get_animal);
        btnComposite = (ImageView) findViewById(R.id.btn_composite);
        
        popupGetAnimal = (RelativeLayout) findViewById(R.id.popup_get_animal);
        plant1 = (ImageView) findViewById(R.id.plant1);
        plant2 = (ImageView) findViewById(R.id.plant2);
        plant3 = (ImageView) findViewById(R.id.plant3);
        plant4 = (ImageView) findViewById(R.id.plant4);
        imgAnimal = (ImageView) findViewById(R.id.animal);
        
        popupComposite = (RelativeLayout) findViewById(R.id.popup_composite);
        
        popupAlertCherry = (RelativeLayout) findViewById(R.id.popup_alert_cherry);
        popupAlertDiamond = (RelativeLayout) findViewById(R.id.popup_alert_diamond);
        
        refreshDiamond();
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
    
    public void onClickComposite(View view) {
    	openAnimalComposite();
    }
    
    public void onClickCherry(View view) {
    	openCherryStore();
    }
    
    public void onClickDiamond(View view) {
    	openDiamondStore();
    }
    
    public void onClickCherryStore(View view) {
    	
    	switch(view.getId()) {
    	case R.id.btnCherry1:
    		addCherry(10, 6000);
    		break;
    	case R.id.btnCherry2:
    		addCherry(50, 33000);
    		break;
    	case R.id.btnCherry3:
    		addCherry(120, 86400);
    		refreshCherry();
    		break;
    	case R.id.btnCherry4:
    		addCherry(250, 195000);
    		break;
    	}
    	
    	closeCherryStore();
    }
    
    private void addCherry(long diamond, long cherry) {
    	
    	if (!databaseHelper.hasDiamond(diamond)) {
    		closeCherryStore();
    		openAlertDiamondPopup();
    		return;
    	}
    	
    	databaseHelper.subDiamond(diamond);
    	databaseHelper.addCherry(cherry);
    	refreshDiamond();
    	refreshCherry();
    }
    
    public void onClickDiamondStore(View view) {
    
    	switch(view.getId()) {
    	case R.id.btnDiamond1:
    		addDiamond(20);
    		break;
    	case R.id.btnDiamond2:
    		addDiamond(55);
    		break;
    	case R.id.btnDiamond3:
    		addDiamond(120);
    		break;
    	case R.id.btnDiamond4:
    		addDiamond(390);
    		break;
    	case R.id.btnDiamond5:
    		addDiamond(900);
    		break;
    	}
    	
    	closeDiamondStore();
    }
    
    public void onClickCloseComposite(View view) {
    	closeAnimalComposite();
    }
    
    private void addDiamond(long diamond) {
    	databaseHelper.addDiamond(diamond);
    	refreshDiamond();
    }
    
    public void onClickGetAnimalPopupOk(View view) {
    	closeGatAnimalPopup();
	}
    
    public void onClickGetAnimalPopupContinue(View view) {
    	pushNewAnimal();
    }
    
    public void onClickAlertCherryPopupClose(View view) {
    	closeAlertCherryPopup();
    }
    
    public void onClickAlertCherryPopupOk(View view) {
    	closeAlertCherryPopup();
    	openCherryStore();
    }
    
    public void onClickAlertDiamondPopupClose(View view) {
    	closeAlertDiamondPopup();
    }
    
    public void onClickAlertDiamondPopupOk(View view) {
    	closeAlertDiamondPopup();
    	openDiamondStore();
    }
    
    private void pushNewAnimal() {
    	if (!databaseHelper.hasCherry(6000)) {
    		closeGatAnimalPopup();
    		openAlertCherryPopup();
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
    	refreshDiamond();
    	refreshCherry();
    	refreshList();
    }
    
    private void refreshList() {
    	animalAdapter.changeCursor(databaseHelper.getAnimalList());
    }
    
    private void refreshCherry() {
    	cherry.setText(Long.toString(databaseHelper.getCherry()));
    	usedCherry.setText(Long.toString(databaseHelper.getUsedCherry()));
    }
    
    private void refreshDiamond() {
    	diamond.setText(Long.toString(databaseHelper.getDiamond()));
    }
    
    private void openDiamondStore() {
    	setVisibleView(diamondStore, true);
    }
    
    private void closeDiamondStore() {
    	setVisibleView(diamondStore, false);
    }
    
    private void openCherryStore() {
    	setVisibleView(cherryStore, true);
    }
    
    private void closeCherryStore() {
    	setVisibleView(cherryStore, false);
    }
    
    private void openGatAnimalPopup() {
    	setVisibleView(popupGetAnimal, true);
    }
    
    private void closeGatAnimalPopup() {
    	setVisibleView(popupGetAnimal, false);
    }
    
    private void openAlertCherryPopup() {
    	setVisibleView(popupAlertCherry, true);
    }
    
    private void closeAlertCherryPopup() {
    	setVisibleView(popupAlertCherry, false);
    }
    
    private void openAlertDiamondPopup() {
    	setVisibleView(popupAlertDiamond, true);
    }
    
    private void closeAlertDiamondPopup() {
    	setVisibleView(popupAlertDiamond, false);
    }
    
    private void openAnimalComposite() {
    	setVisibleView(popupComposite, true);
    }
    
    private void closeAnimalComposite() {
    	setVisibleView(popupComposite, false);
    }
    
    private void setVisibleView(View target, boolean value) {
    	int visible = (value) ? View.VISIBLE : View.INVISIBLE;
    	buttonClickable(!value);
    	target.setVisibility(visible);
    }
    
    private void buttonClickable(boolean state) {
    	diamond_back.setEnabled(state);
    	cherry_back.setEnabled(state);
    	listView.setEnabled(state);
    	btnGetAnimal.setEnabled(state);
    	btnComposite.setEnabled(state);
    	btnClear.setEnabled(state);
    }
    
    private void removePlants() {
		
		TranslateAnimation left1 = new TranslateAnimation(0, -1000, 0, 0);
		TranslateAnimation left2 = new TranslateAnimation(0, -1000, 0, 0);
		TranslateAnimation right1 = new TranslateAnimation(0, 1000, 0, 0);
		TranslateAnimation right2 = new TranslateAnimation(0, 1000, 0, 0);
		
		left1.setDuration(1000);
		left1.setFillAfter(true);
		left2.setDuration(1400);
		left2.setFillAfter(true);
		
		right1.setDuration(1000);
		right1.setFillAfter(true);
		right2.setDuration(1400);
		right2.setFillAfter(true);
		
		plant1.startAnimation(left1);
		plant2.startAnimation(right1);
		plant3.startAnimation(left2);
		plant4.startAnimation(right2);
	}
}
