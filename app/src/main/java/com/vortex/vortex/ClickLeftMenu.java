package com.vortex.vortex;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by KonyshevAM on 15.11.2017.
 */

public class ClickLeftMenu extends AppCompatActivity {

    public static Intent getIntent(Activity activiry, int id) {
        Intent intent = null;
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
        }

        return intent;
    }

    public static Intent getIntentWebSite() {
        Intent intent = null;
        Uri uri = Uri.parse("http://www.pk-vortex.ru"); // missing 'http://' will cause crashed
        intent = new Intent(Intent.ACTION_VIEW, uri);
        return intent;
    }
}

