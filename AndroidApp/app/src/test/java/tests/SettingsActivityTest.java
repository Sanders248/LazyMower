package tests;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.alex.lazymowing.BuildConfig;
import com.example.alex.lazymowing.MainActivity;
import com.example.alex.lazymowing.R;
import com.example.alex.lazymowing.SettingsActivity;
import com.example.alex.lazymowing.Tools.ConnectionApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowToast;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Alex on 05/12/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=22)

public class SettingsActivityTest {
    private SettingsActivity settingsActivity;

    @Before
    public void setup()  {
        settingsActivity = Robolectric.setupActivity(SettingsActivity.class);
    }

    @Test
    public void clickingSettingsMenu_shouldStartSettingActivity() {
        settingsActivity.findViewById(R.id.menu_main).performClick();

        Intent expectedIntent = new Intent(settingsActivity, MainActivity.class);

        ShadowActivity shadowActivity = Shadows.shadowOf(settingsActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(settingsActivity);
    }

    @Test
    public void testTryConnectionClick() throws Exception {
        Button view = (Button) settingsActivity.findViewById(R.id.buttonTryCo);
        assertNotNull(view);

        EditText editTextPort = ((EditText)settingsActivity.findViewById(R.id.editTextPort));
        assertNotNull(editTextPort);

        EditText editTextURL = ((EditText)settingsActivity.findViewById(R.id.editTextURL));
        assertNotNull(editTextURL);

        view.performClick();
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Waiting for server response"));
    }

    @Test
    public void testChangeSettingsClick() throws Exception {
        Button view = (Button) settingsActivity.findViewById(R.id.buttonChangeSet);
        assertNotNull(view);

        EditText editTextPort = ((EditText)settingsActivity.findViewById(R.id.editTextPort));
        assertNotNull(editTextPort);
        editTextPort.setText("12345");

        EditText editTextURL = ((EditText)settingsActivity.findViewById(R.id.editTextURL));
        assertNotNull(editTextURL);
        editTextURL.setText("myUrl");

        view.performClick();
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Settings saved"));

        assertThat(ConnectionApi.getPort(), equalTo(Integer.valueOf(editTextPort.getText().toString())));
        assertThat(ConnectionApi.getURL(), equalTo(editTextURL.getText().toString()));
    }
}
