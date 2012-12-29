package cc.morr.sixthsense;

import android.content.Context;

public abstract class Sensor {
    Context context;

    public Sensor(Context context) {
        this.context = context;
    }

    abstract Stimulus getStimulus();
}
