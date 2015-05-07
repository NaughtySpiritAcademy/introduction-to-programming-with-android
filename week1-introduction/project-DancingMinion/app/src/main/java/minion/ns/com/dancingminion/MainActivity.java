package minion.ns.com.dancingminion;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
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
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, Minion.getDegrees(),
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);
                rotateAnimation.setInterpolator(new LinearInterpolator());
                rotateAnimation.setDuration(duration);


                float x = Minion.getXPosition(50);
                float y = Minion.getYPosition(100);
                TranslateAnimation translateAnimation = new TranslateAnimation(0, x, 0, 0);
                translateAnimation.setDuration(duration);
                translateAnimation.setStartOffset(duration);

                TranslateAnimation translate2Animation = new TranslateAnimation(0, 0, 0, y);
                translate2Animation.setDuration(duration);
                translate2Animation.setStartOffset(duration * 2);

                x = Minion.getXPosition(x);
                TranslateAnimation translate3Animation = new TranslateAnimation(0, x, 0, 0);
                translate3Animation.setDuration(duration);
                translate3Animation.setStartOffset(duration * 3);

                final ScaleAnimation scaleAnimation = new ScaleAnimation(1, Minion.getXScale(0.5f), 1, Minion.getYScale(0.5f));
                scaleAnimation.setDuration(duration);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setFillEnabled(true);
                scaleAnimation.setStartOffset(duration * 4);


                AnimationSet animationSet = new AnimationSet(false);
                animationSet.addAnimation(rotateAnimation);
                animationSet.addAnimation(translateAnimation);
                animationSet.addAnimation(translate2Animation);
//                animationSet.addAnimation(translate3Animation);
                animationSet.addAnimation(scaleAnimation);
                animationSet.setFillAfter(true);
                animationSet.setFillEnabled(true);
                minion.startAnimation(animationSet);

                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        text.setText(Minion.getText(text.getText().toString()));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

    }


}
