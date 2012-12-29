package cc.morr.sixthsense;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends Activity {
    Sense s;
    Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //s = new Sense(new MinuteSensor(this), new SinewaveRepresentation(this));
        s = new Sense(
                new WifiCountSensor(this),
                //new SinewaveRepresentation(this)
                new HeartbeatRepresentation(this)
                );
        //s = new Sense(new MinuteSensor(this), new HeartbeatRepresentation(this));
        s.start();

        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                updateDebug();
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    public void updateDebug() {
        TextView t = (TextView)findViewById(R.id.debug);
        float a = s.getRepresentation().getLastStimulus().getData().firstElement().firstElement();
        t.setText(""+a);
    }
}
