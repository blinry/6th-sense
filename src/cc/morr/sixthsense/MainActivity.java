package cc.morr.sixthsense;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Sense s = new Sense(new MinuteSensor(), new HeartbeatRepresentation(this));
        s.start();
    }
}
