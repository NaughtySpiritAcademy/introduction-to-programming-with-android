package com.example.ns.dancingminion;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    private ImageView minion;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minion = (ImageView) findViewById(R.id.minion);
        text = (TextView) findViewById(R.id.text);

        minion.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    minion.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    minion.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }


                int duration = Minion.getDuration();
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, Minion.rotate(),
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);
                rotateAnimation.setInterpolator(new LinearInterpolator());
                rotateAnimation.setDuration(duration);


                float x = Minion.goToRightBananas(minion.getX());
                float y = Minion.goToBottomRightBanana(minion.getY());

                TranslateAnimation animGoToRight = new TranslateAnimation(0, x, 0, 0);
                animGoToRight.setDuration(duration);
                animGoToRight.setStartOffset(duration);

                TranslateAnimation animGoToBottom = new TranslateAnimation(0, 0, 0, y);
                animGoToBottom.setDuration(duration);
                animGoToBottom.setStartOffset(duration * 2);

                TranslateAnimation animGoToLeft = new TranslateAnimation(0, Minion.goToBottomLeftBanana(x), 0, 0);
                animGoToLeft.setDuration(duration);
                animGoToLeft.setStartOffset(duration * 3);

                TranslateAnimation animGoToMiddle = new TranslateAnimation(0, 200, 0, -300);
                animGoToMiddle.setDuration(duration);
                animGoToMiddle.setStartOffset(duration * 4);

                final ScaleAnimation animScale = new ScaleAnimation(1, Minion.zoomMinionWidth(0.5f), 1, Minion.zoomMinionHeight(0.5f));
                animScale.setDuration(duration);
                animScale.setFillAfter(true);
                animScale.setFillEnabled(true);
                animScale.setStartOffset(duration * 5);


                AnimationSet animationSet = new AnimationSet(false);
                animationSet.addAnimation(rotateAnimation);
                animationSet.addAnimation(animGoToRight);
                animationSet.addAnimation(animGoToBottom);
                animationSet.addAnimation(animGoToLeft);
                animationSet.addAnimation(animGoToMiddle);
                animationSet.addAnimation(animScale);
                animationSet.setFillAfter(true);
                animationSet.setFillEnabled(true);
                minion.startAnimation(animationSet);

                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        text.setText(Minion.minionAwesomeText(text.getText().toString()));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
