package cc.morr.sixthsense;

import android.os.Vibrator;
import android.content.Context;

public class HeartbeatRepresentation extends Representation {
    Vibrator vibrator;
    Context context;
    Stimulus lastStimulus;

    public HeartbeatRepresentation(Context context) {
        this.context = context;
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void reactTo(Stimulus s) {
        lastStimulus = s;
        long[] pattern = new long[] {0, 200, 50, 200, millisToSleep()-450};
        vibrator.vibrate(pattern, -1);
    }

    public long millisToSleep() {
        float a = lastStimulus.getData().firstElement().firstElement();
        return 450+(60-(long)a)*10;
    }
}
