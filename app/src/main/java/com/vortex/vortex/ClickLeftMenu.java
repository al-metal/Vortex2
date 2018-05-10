package com.vortex.vortex;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by KonyshevAM on 15.11.2017.
 */

public class ClickLeftMenu extends AppCompatActivity {

    public static Intent getIntent(Activity activiry, MenuItem item) {
        Intent intent = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            intent = new Intent(activiry, ApkActivity2.class);
        } else if (id == R.id.nav_gallery) {
            intent = new Intent(activiry, ActivityPisheProm2.class);
        } else if (id == R.id.nav_slideshow) {
            intent = new Intent(activiry, ActivityAutoVybor2.class);
        } else if (id == R.id.nav_manage) {
            intent = new Intent(activiry, activity_klining.class);
        } else if (id == R.id.nav_spravochnik) {
            intent = new Intent(activiry, activity_spravka.class);
        } else if (id == R.id.nav_getDiscount) {
            intent = new Intent(activiry, ActivityGetDiscount.class);
        } else if (id == R.id.nav_sendMail) {
            intent = new Intent(activiry, CallMeActivity.class);
        } else if (id == R.id.nav_news) {
            intent = new Intent(activiry, testNews.class);
        } else if (id == R.id.nav_action) {
            intent = new Intent(activiry, CallMeActivity.class);
        } else if (id == R.id.nav_buy_product) {
            intent = new Intent(activiry, ActivityOrder.class);
        } else if (id == R.id.nav_show_message) {
            intent = new Intent(activiry, show_message.class);
        } else if (id == R.id.nav_site) {
            Uri uri = Uri.parse("https://www.pk-vortex.ru"); // missing 'http://' will cause crashed
            intent = new Intent(Intent.ACTION_VIEW, uri);
        }

        return intent;
    }
}

