package tests;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.alex.lazymowing.BuildConfig;
import com.example.alex.lazymowing.MainActivity;
import com.example.alex.lazymowing.R;
import com.example.alex.lazymowing.SettingsActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Alex on 03/12/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=22)

public class MainActivityTest {
    private com.example.alex.lazymowing.MainActivity mainActivity;

    @Before
    public void setup()  {
        mainActivity = Robolectric.setupActivity(com.example.alex.lazymowing.MainActivity.class);
    }

    @Test
    public void clickingSettingsMenu_shouldStartSettingActivity() {
        mainActivity.findViewById(R.id.menu_settings).performClick();

        Intent expectedIntent = new Intent(mainActivity, SettingsActivity.class);

        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(mainActivity);
    }

    @Test
    public void testButtonsPresent() throws Exception {
        ImageButton moveForward = (ImageButton) mainActivity.findViewById(R.id.imMoveForward);
        assertNotNull(moveForward);

        ImageButton moveBack = (ImageButton) mainActivity.findViewById(R.id.imMoveBack);
        assertNotNull(moveBack);

        ImageButton turnLeft = (ImageButton) mainActivity.findViewById(R.id.imTurnLeft);
        assertNotNull(turnLeft);

        ImageButton turnRight = (ImageButton) mainActivity.findViewById(R.id.imTurnRight);
        assertNotNull(turnRight);

        ImageButton centerImg = (ImageButton) mainActivity.findViewById(R.id.imCenter);
        assertNotNull(centerImg);
        Assert.assertFalse(centerImg.isClickable());
    }

}
