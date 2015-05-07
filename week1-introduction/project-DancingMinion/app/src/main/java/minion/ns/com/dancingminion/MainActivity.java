package minion.ns.com.dancingminion;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


public class MainActivity extends Activity {

    private ImageView minion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minion = (ImageView) findViewById(R.id.minion);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int screenHeight = metrics.heightPixels;
        int screenWidth = metrics.widthPixels;

        Log.d("AAAAA", String.valueOf(screenHeight));
        Log.d("AAAAA", String.valueOf(screenWidth));

        Rect minionPos = new Rect();
        minion.getGlobalVisibleRect(minionPos);

//        int[] minionLocation = new int[2];
//        minion.getLocationInWindow(minionLocation);

        Log.d("AAAAA", String.valueOf(minionPos.top));
        Log.d("AAAAA", String.valueOf(minionPos.left));

        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(2000);


        TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 200);
        translateAnimation.setDuration(2000);
        translateAnimation.setStartOffset(2000);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        minion.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Rect minionPos = new Rect();
                minion.getGlobalVisibleRect(minionPos);

                Log.d("AAAAA", String.valueOf(minionPos.top));
                Log.d("AAAAA", String.valueOf(minionPos.left));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }


}
