package xiaohei.example.com.autosizetest;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

           setCustomDestisy(MainActivity.this,getApplication());
        setContentView(R.layout.activity_main);
    }
    private static float sNoncompatDestiny;
    private static float sNoncompatScaledDestiny;
    private static void setCustomDestisy( Activity activity, final Application application){
        final DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if(sNoncompatDestiny== 0){
            sNoncompatDestiny = displayMetrics.density;
            sNoncompatScaledDestiny = displayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig!= null && newConfig.fontScale>0){
                        sNoncompatScaledDestiny= application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }


                @Override
                public void onLowMemory() {

                }
            });
        }
        final float  targetDenstiny = displayMetrics.widthPixels/360;
        final  float targetScaleDentiny = targetDenstiny*(sNoncompatScaledDestiny/sNoncompatDestiny);
        final  int targetDestinyDpi= (int) (160*targetDenstiny);
        displayMetrics.density = targetDenstiny;
        displayMetrics.scaledDensity = targetScaleDentiny;
        displayMetrics.densityDpi = targetDestinyDpi;
        final DisplayMetrics activityDisplayMertics = activity.getResources().getDisplayMetrics();
        activityDisplayMertics.density = targetDenstiny;
        activityDisplayMertics.scaledDensity = targetScaleDentiny;
        activityDisplayMertics.densityDpi = targetDestinyDpi;
    }
}
