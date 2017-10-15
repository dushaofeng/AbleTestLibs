package com.able.libs;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.github.nisrulz.sensey.PinchScaleDetector;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.TouchTypeDetector;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Able on 2017/10/15.
 */
@EActivity(R.layout.sensy_activity_touch)
public class SenseyTouchActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    @ViewById(R.id.textView_result)
    TextView txtResult;
    @ViewById(R.id.Switch6)
    SwitchCompat swt6;
    @ViewById(R.id.Switch7)
    SwitchCompat swt7;

    @AfterViews
    void afterViews() {
        Sensey.getInstance().init(this);

        swt6.setOnCheckedChangeListener(this);
        swt6.setChecked(false);

        swt7.setOnCheckedChangeListener(this);
        swt7.setChecked(false);
    }

    @Override
    public void onCheckedChanged(CompoundButton switchbtn, boolean isChecked) {
        switch (switchbtn.getId()) {
            case R.id.Switch6:
                if (isChecked) {
                    startTouchTypeDetection();
                } else {
                    Sensey.getInstance().stopTouchTypeDetection();
                }
                break;
            case R.id.Switch7:
                if (isChecked) {
                    startPinchDetection();
                } else {
                    Sensey.getInstance().stopPinchScaleDetection();
                }
                break;

            default:
                // Do nothing
                break;
        }
    }

    private void resetResultInView(final TextView txt) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txt.setText(getString(R.string.results_show_here));
            }
        }, 3000);
    }

    private void startPinchDetection() {
        Sensey.getInstance().startPinchScaleDetection(this, new PinchScaleDetector.PinchScaleListener() {
            @Override
            public void onScale(ScaleGestureDetector scaleGestureDetector, boolean isScalingOut) {
                if (isScalingOut) {
                    setResultTextView("Scaling Out");
                } else {
                    setResultTextView("Scaling In");
                }
            }

            @Override
            public void onScaleStart(ScaleGestureDetector scaleGestureDetector) {
                setResultTextView("Scaling : Started");
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                setResultTextView("Scaling : Stopped");
            }
        });
    }

    private void startTouchTypeDetection() {
        Sensey.getInstance().startTouchTypeDetection(this, new TouchTypeDetector.TouchTypListener() {
            @Override
            public void onTwoFingerSingleTap() {
                setResultTextView("Two Finger Tap");
            }

            @Override
            public void onThreeFingerSingleTap() {
                setResultTextView("Three Finger Tap");
            }

            @Override
            public void onDoubleTap() {
                setResultTextView("Double Tap");
            }

            @Override
            public void onScroll(int scrollDirection) {
                switch (scrollDirection) {
                    case TouchTypeDetector.SCROLL_DIR_UP:
                        setResultTextView("Scrolling Up");
                        break;
                    case TouchTypeDetector.SCROLL_DIR_DOWN:
                        setResultTextView("Scrolling Down");
                        break;
                    case TouchTypeDetector.SCROLL_DIR_LEFT:
                        setResultTextView("Scrolling Left");
                        break;
                    case TouchTypeDetector.SCROLL_DIR_RIGHT:
                        setResultTextView("Scrolling Right");
                        break;
                    default:
                        // Do nothing
                        break;
                }
            }

            @Override
            public void onSingleTap() {
                setResultTextView("Single Tap");
            }

            @Override
            public void onSwipe(int swipeDirection) {
                switch (swipeDirection) {
                    case TouchTypeDetector.SWIPE_DIR_UP:
                        setResultTextView("Swipe Up");
                        break;
                    case TouchTypeDetector.SWIPE_DIR_DOWN:
                        setResultTextView("Swipe Down");
                        break;
                    case TouchTypeDetector.SWIPE_DIR_LEFT:
                        setResultTextView("Swipe Left");
                        break;
                    case TouchTypeDetector.SWIPE_DIR_RIGHT:
                        setResultTextView("Swipe Right");
                        break;
                    default:
                        //do nothing
                        break;
                }
            }

            @Override
            public void onLongPress() {
                setResultTextView("Long press");
            }
        });
    }

    private void setResultTextView(String text) {
        if (txtResult != null) {
            txtResult.setText(text);
            resetResultInView(txtResult);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // Setup onTouchEvent for detecting type of touch gesture
        Sensey.getInstance().setupDispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Stop Detections
        Sensey.getInstance().stopTouchTypeDetection();
        Sensey.getInstance().stopPinchScaleDetection();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // *** IMPORTANT ***
        // Stop Sensey and release the context held by it
        Sensey.getInstance().stop();
    }
}
