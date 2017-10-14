package com.able.libs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nisrulz.sensey.ChopDetector;
import com.github.nisrulz.sensey.FlipDetector;
import com.github.nisrulz.sensey.LightDetector;
import com.github.nisrulz.sensey.MovementDetector;
import com.github.nisrulz.sensey.OrientationDetector;
import com.github.nisrulz.sensey.ProximityDetector;
import com.github.nisrulz.sensey.RotationAngleDetector;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;
import com.github.nisrulz.sensey.SoundLevelDetector;
import com.github.nisrulz.sensey.TiltDirectionDetector;
import com.github.nisrulz.sensey.WaveDetector;
import com.github.nisrulz.sensey.WristTwistDetector;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.text.DecimalFormat;
import java.util.List;


/**
 * Created by Able on 2017/10/14.
 */
@EActivity(R.layout.sensy_activity_main)
public class SenseyTestActivity extends AppCompatActivity
        implements ShakeDetector.ShakeListener, FlipDetector.FlipListener, LightDetector.LightListener, OrientationDetector.OrientationListener, ProximityDetector
        .ProximityListener, WaveDetector.WaveListener, SoundLevelDetector.SoundLevelListener, MovementDetector.MovementListener, ChopDetector.ChopListener, WristTwistDetector
        .WristTwistListener, RotationAngleDetector.RotationAngleListener, TiltDirectionDetector.TiltDirectionListener {

    @ViewsById({R.id.Switch1, R.id.Switch2, R.id.Switch3, R.id.Switch4, R.id.Switch5, R.id.Switch6, R.id.Switch7, R.id.Switch8, R.id.Switch9, R.id.Switch10, R.id.Switch11, R.id
            .Switch12})
    List<SwitchCompat> swtList;
    @ViewById(R.id.textView_result)
    TextView txtViewResult;

    @AfterViews
    void afterViews() {
        // Init Sensey
        Sensey.getInstance().init(this);

        for (SwitchCompat swt : swtList) {
            swt.setChecked(false);
        }
    }

    @Click(R.id.btn_touchevent)
    void onClick() {
        //                startActivity(new Intent(SenseyTestActivity.this, TouchActivity.class));
    }


    @CheckedChange({R.id.Switch1, R.id.Switch2, R.id.Switch3, R.id.Switch4, R.id.Switch5, R.id.Switch6, R.id.Switch7, R.id.Switch8, R.id.Switch9, R.id.Switch10, R.id.Switch11, R
            .id.Switch12})
    void onCheckChange(CompoundButton swt, boolean isChecked) {
        switch (swt.getId()) {
            case R.id.Switch1:
                if (isChecked) {
                    Sensey.getInstance().startShakeDetection(10, 2000, this);
                } else {
                    Sensey.getInstance().stopShakeDetection(this);
                }
                break;
            case R.id.Switch2:
                if (isChecked) {
                    Sensey.getInstance().startFlipDetection(this);
                } else {
                    Sensey.getInstance().stopFlipDetection(this);
                }

                break;
            case R.id.Switch3:
                if (isChecked) {
                    Sensey.getInstance().startOrientationDetection(this);
                } else {
                    Sensey.getInstance().stopOrientationDetection(this);
                }

                break;
            case R.id.Switch4:
                if (isChecked) {
                    Sensey.getInstance().startProximityDetection(this);
                } else {
                    Sensey.getInstance().stopProximityDetection(this);
                }
                break;
            case R.id.Switch5:
                if (isChecked) {
                    Sensey.getInstance().startLightDetection(10, this);
                } else {
                    Sensey.getInstance().stopLightDetection(this);
                }
                break;

            case R.id.Switch6:
                if (isChecked) {
                    Sensey.getInstance().startWaveDetection(this);
                } else {
                    Sensey.getInstance().stopWaveDetection(this);
                }
                break;

            case R.id.Switch7:
                if (isChecked) {
                    Sensey.getInstance().startSoundLevelDetection(this);
                } else {
                    Sensey.getInstance().stopSoundLevelDetection();
                }
                break;
            case R.id.Switch8:
                if (isChecked) {
                    Sensey.getInstance().startMovementDetection(this);
                } else {
                    Sensey.getInstance().stopMovementDetection(this);
                }
                break;
            case R.id.Switch9:
                if (isChecked) {
                    Sensey.getInstance().startChopDetection(30f, 500, this);
                } else {
                    Sensey.getInstance().stopChopDetection(this);
                }
                break;
            case R.id.Switch10:
                if (isChecked) {
                    Sensey.getInstance().startWristTwistDetection(this);
                } else {
                    Sensey.getInstance().stopWristTwistDetection(this);
                }
                break;

            case R.id.Switch11:
                if (isChecked) {
                    Sensey.getInstance().startRotationAngleDetection(this);
                } else {
                    Sensey.getInstance().stopRotationAngleDetection(this);
                }
                break;

            case R.id.Switch12:
                if (isChecked) {
                    Sensey.getInstance().startTiltDirectionDetection(this);
                } else {
                    Sensey.getInstance().stopTiltDirectionDetection(this);
                }
                break;

            default:
                // Do nothing
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        // Stop Detections
        Sensey.getInstance().stopShakeDetection(this);
        Sensey.getInstance().stopFlipDetection(this);
        Sensey.getInstance().stopOrientationDetection(this);
        Sensey.getInstance().stopProximityDetection(this);
        Sensey.getInstance().stopLightDetection(this);
        Sensey.getInstance().stopWaveDetection(this);
        Sensey.getInstance().stopSoundLevelDetection();
        Sensey.getInstance().stopMovementDetection(this);
        Sensey.getInstance().stopChopDetection(this);
        Sensey.getInstance().stopWristTwistDetection(this);
        Sensey.getInstance().stopRotationAngleDetection(this);
        Sensey.getInstance().stopTiltDirectionDetection(this);

        // Set the all switches to off position
        for (SwitchCompat swt : swtList) {
            swt.setChecked(false);
        }
        // Reset the result view
        txtViewResult.setText(getString(R.string.results_show_here));

        Toast.makeText(this, "Stopping all detectors!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFaceUp() {
        setResultTextView("Face UP", false);
    }

    @Override
    public void onFaceDown() {
        setResultTextView("Face Down", false);
    }

    @UiThread
    private void setResultTextView(final String text, final boolean realtime) {
        txtViewResult.setText(text);
        if (!realtime) {
            txtViewResult.setText(getString(R.string.results_show_here));
        }
    }

    @Override
    public void onDark() {
        setResultTextView("Dark", false);
    }

    @Override
    public void onLight() {
        setResultTextView("Not Dark", false);
    }

    @Override
    public void onTopSideUp() {
        setResultTextView("Top Side UP", false);
    }

    @Override
    public void onBottomSideUp() {
        setResultTextView("Bottom Side UP", false);
    }

    @Override
    public void onRightSideUp() {
        setResultTextView("Right Side UP", false);
    }

    @Override
    public void onLeftSideUp() {
        setResultTextView("Left Side UP", false);
    }

    @Override
    public void onNear() {
        setResultTextView("Near", false);
    }

    @Override
    public void onFar() {
        setResultTextView("Far", false);
    }

    @Override
    public void onShakeDetected() {
        setResultTextView("Shake Detected!", false);
    }

    @Override
    public void onShakeStopped() {
        setResultTextView("Shake Stopped!", false);
    }

    @Override
    public void onWave() {
        setResultTextView("Wave Detected!", false);
    }

    @Override
    public void onSoundDetected(float level) {

        setResultTextView(new DecimalFormat("##.##").format(level) + "dB", true);
    }

    @Override
    public void onMovement() {
        setResultTextView("Movement Detected!", false);
    }

    @Override
    public void onStationary() {
        setResultTextView("Device Stationary!", false);
    }

    @Override
    public void onChop() {
        setResultTextView("Chop Detected!", false);
    }

    @Override
    public void onWristTwist() {
        setResultTextView("Wrist Twist Detected!", false);
    }

    @Override
    public void onTiltInAxisX(int direction) {
        displayResultForTiltDirectionDetector(direction, "X");
    }

    @Override
    public void onTiltInAxisY(int direction) {
        displayResultForTiltDirectionDetector(direction, "Y");
    }

    @Override
    public void onTiltInAxisZ(int direction) {
        displayResultForTiltDirectionDetector(direction, "Z");
    }

    private void displayResultForTiltDirectionDetector(int direction, String axis) {
        String dir;
        if (direction == TiltDirectionDetector.DIRECTION_CLOCKWISE) {
            dir = "ClockWise";
        } else {
            dir = "AntiClockWise";
        }
        setResultTextView("Tilt in " + axis + " Axis: " + dir, false);
    }

    @Override
    public void onRotation(float angleInAxisX, float angleInAxisY, float angleInAxisZ) {
        setResultTextView("Rotation in Axis Detected(deg):\nX=" + angleInAxisX + ",\nY=" + angleInAxisY + ",\nZ=" + angleInAxisZ, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // *** IMPORTANT ***
        // Stop Sensey and release the context held by it
        Sensey.getInstance().stop();
    }
}
