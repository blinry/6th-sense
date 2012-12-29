package cc.morr.sixthsense;

import android.text.format.Time;
import java.util.Vector;

public class MinuteSensor extends Sensor {
    Stimulus getStimulus() {
        Time now = new Time();
        now.setToNow();

        Vector<Float> tuple = new Vector<Float>();
        tuple.add((float)now.second);

        Stimulus s = new Stimulus();
        s.add(tuple);
        return s;
    }
}
