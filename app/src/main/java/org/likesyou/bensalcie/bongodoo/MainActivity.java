package org.likesyou.bensalcie.bongodoo;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import org.likesyou.bensalcie.bongodoo.common.Shared;
import org.likesyou.bensalcie.bongodoo.engine.Engine;
import org.likesyou.bensalcie.bongodoo.engine.ScreenController;
import org.likesyou.bensalcie.bongodoo.events.EventBus;
import org.likesyou.bensalcie.bongodoo.events.ui.BackGameEvent;
import org.likesyou.bensalcie.bongodoo.ui.PopupManager;
import org.likesyou.bensalcie.bongodoo.utils.Utils;

public class MainActivity extends FragmentActivity {

    private ImageView mBackgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Shared.context = getApplicationContext();
        Shared.engine = Engine.getInstance();
        Shared.eventBus = EventBus.getInstance();

        setContentView(R.layout.activity_main);
        mBackgroundImage = (ImageView) findViewById(R.id.background_image);

        Shared.activity = this;
        Shared.engine.start();
        Shared.engine.setBackgroundImageView(mBackgroundImage);

        // set background
        setBackgroundImage();

        // set menu
        ScreenController.getInstance().openScreen(ScreenController.Screen.MENU);


    }

    @Override
    protected void onDestroy() {
        Shared.engine.stop();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (PopupManager.isShown()) {
            PopupManager.closePopup();
            if (ScreenController.getLastScreen() == ScreenController.Screen.GAME) {
                Shared.eventBus.notify(new BackGameEvent());
            }
        } else if (ScreenController.getInstance().onBack()) {
            super.onBackPressed();
        }
    }

    private void setBackgroundImage() {
        Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
        bitmap = Utils.crop(bitmap, Utils.screenHeight(), Utils.screenWidth());
        bitmap = Utils.downscaleBitmap(bitmap, 2);
        mBackgroundImage.setImageBitmap(bitmap);
    }

}
