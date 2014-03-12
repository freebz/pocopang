package com.freebz.pocopang;

import com.freebz.pocopang.model.Constant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class PopupGetAnimalActivity extends Activity {
	
	private ImageView plant1;
	private ImageView plant2;
	private ImageView plant3;
	private ImageView plant4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        setContentView(R.layout.popup_get_animal);
        
//        WebView webView = (WebView)findViewById(R.id.webPopup);
//        webView.setWebViewClient(new myWebViewClient());
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBuiltInZoomControls(true);
//          webView.loadUrl("http://www.google.com");
        
        plant1 = (ImageView) findViewById(R.id.plant1);
        plant2 = (ImageView) findViewById(R.id.plant2);
        plant3 = (ImageView) findViewById(R.id.plant3);
        plant4 = (ImageView) findViewById(R.id.plant4);
        
        int id = (Integer) getIntent().getExtras().get("id");
        
//        int id = cursor.getInt(cursor.getColumnIndex("_id"));
		int resId = this.getResources().getIdentifier("_" + id, "drawable", "com.freebz.pocopang");
		ImageView image = (ImageView) findViewById(R.id.animal);
		image.setImageResource(resId);
        
        removePlants();
	}
	
	private void removePlants() {
		
		TranslateAnimation left = new TranslateAnimation(0, -1000, 0, 0);
		TranslateAnimation right = new TranslateAnimation(0, 1000, 0, 0);
		
		left.setDuration(1000);
		left.setFillAfter(true);
		
		right.setDuration(1000);
		right.setFillAfter(true);
		
		plant1.startAnimation(left);
		plant2.startAnimation(right);
		plant3.startAnimation(left);
		plant4.startAnimation(right);
	}
	
	public void onClickOk(View view) {
		this.setResult(Constant.RESULT_OK);
		finish();
	}
	
	public void onClickContinue(View view) {
		this.setResult(Constant.RESULT_CONTINUE);
		finish();
	}
}
