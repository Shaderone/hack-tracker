package com.jdtech.nettracker;


    import android.content.Context;
    import android.graphics.drawable.Drawable;
    import android.os.Build;
    import android.os.Handler;
    import android.text.Spannable;
    import android.text.SpannableString;
    import android.text.Spanned;
    import android.text.style.ImageSpan;
    import android.util.AttributeSet;
    import android.view.GestureDetector;
    import android.view.MotionEvent;
    import android.view.View;
    import android.widget.LinearLayout;
    import android.widget.ToggleButton;

public class ClickLayout extends LinearLayout{

    int initClickCount;

    private GestureDetector gestureDetector;
    public  int clickCount;
    LinearLayout.LayoutParams clickParam = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f);



    public ClickLayout(Context context) {
        super(context);
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public ClickLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public ClickLayout(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public void initialize(final int initClickCount) {
        clickCount = initClickCount;
        this.initClickCount = initClickCount;




        for (int i = 0; i < clickCount; i++) {

            final ToggleButton click = new ToggleButton(getContext());
            click.setId(i);

            Drawable img = getResources().getDrawable(R.drawable.clickfinal);
            img.setBounds(0,0,340,202);
            ImageSpan imgSpan = new ImageSpan(img);
            SpannableString content = new SpannableString("X");
            content.setSpan(imgSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            click.setTransformationMethod(null);
            click.setText(content);
            click.setTextOn(content);
            click.setTextOff(content);

            if (initClickCount == 3) {
                click.setBackgroundResource(R.drawable.toggle_selector);
            }
            else {
                click.setBackgroundResource(R.drawable.toggle_selector_runner);
            }
            click.setHeight(450);

            /*if (i == 0) {
                click.setBackgroundResource(R.drawable.toggle_selector_first);
            }
            else if (i == clickCount-1) {
                click.setBackgroundResource(R.drawable.toggle_selector_last);
            }
            else {
                click.setBackgroundResource(R.drawable.toggle_selector);
            }
            */
            click.setLayoutParams(clickParam);


            this.addView(click);


            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int buttonId = click.getId();


                    for (int j = 0; j < clickCount; j++) {
                        ToggleButton currentButton = (ToggleButton)findViewById(j);

                        if (j <= buttonId) {
                            currentButton.setChecked(true);


                        } else {
                            currentButton.setChecked(false);
                        }
                    }

                    if (clickCount - 1 == buttonId) {

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int k = 0; k < clickCount; k++) {
                                    ToggleButton currentButton = (ToggleButton) findViewById(k);
                                    currentButton.setChecked(false);
                                }
                            }
                        }, 200);
                    }

                }
            });

        }
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight();
                else
                    onSwipeLeft();
                return true;
            }
            return false;
        }
    }



    public void onSwipeRight() {
        if (clickCount > 1) {
            ToggleButton btnToRemove = (ToggleButton) findViewById(clickCount - 1);
            this.removeView(btnToRemove);
            clickCount = getChildCount();
            ToggleButton newLastBtn= (ToggleButton) findViewById(clickCount - 1);
            //newLastBtn.setBackgroundResource(R.drawable.toggle_selector_last);
        }
    }


    public void onSwipeLeft() {



        clickCount = getChildCount();

        if (clickCount < 9) {

            final ToggleButton oldLastClick = (ToggleButton) findViewById(clickCount-1);
            //oldLastClick.setBackgroundResource(R.drawable.toggle_selector);


            clickCount++;

            final ToggleButton click = new ToggleButton(getContext());
            click.setId(clickCount - 1);

            Drawable img = getResources().getDrawable(R.drawable.clickfinal);
            img.setBounds(0,0,340,202);

            ImageSpan imgSpan = new ImageSpan(img);
            SpannableString content = new SpannableString("X");
            content.setSpan(imgSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            click.setTransformationMethod(null);
            click.setText(content);
            click.setTextOn(content);
            click.setTextOff(content);
            //click.setBackgroundResource(R.drawable.toggle_selector_last);
            click.setLayoutParams(clickParam);
            if (initClickCount == 3) {
                click.setBackgroundResource(R.drawable.toggle_selector);
            }
            else {
                click.setBackgroundResource(R.drawable.toggle_selector_runner);
            }
            click.setHeight(450);


            final int id_ = click.getId();

            ClickLayout layout;

            if (initClickCount == 3) {
                layout = (ClickLayout) findViewById(R.id.clickTrack);
            }
            else {
                layout = (ClickLayout) findViewById(R.id.runnerclickTrack);
            }


            layout.addView(click);

            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int buttonId = click.getId();


                    for (int j = 0; j < clickCount; j++) {
                        if (j <= buttonId) {
                            ToggleButton currentButton = (ToggleButton) findViewById(j);
                            currentButton.setChecked(true);
                        } else {
                            ToggleButton currentButton = (ToggleButton) findViewById(j);
                            currentButton.setChecked(false);
                        }
                    }

                    if (clickCount - 1 == buttonId) {

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int k = 0; k < clickCount; k++) {
                                    ToggleButton currentButton = (ToggleButton) findViewById(k);
                                    currentButton.setChecked(false);
                                }
                            }
                        }, 200);
                    }
                }
            });
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return gestureDetector.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return onTouchEvent(e);
    }

}