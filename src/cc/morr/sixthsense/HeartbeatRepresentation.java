package cc.morr.sixthsense;

import android.os.Vibrator;
import android.content.Context;

public class HeartbeatRepresentation extends Representation {
    Vibrator vibrator;
    Context context;

    public HeartbeatRepresentation(Context context) {
        this.context = context;
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void onStimulus(Stimulus s) {
        float a = lastStimulus.getData().firstElement().firstElement();
        long[] pattern = new long[] {0, 200, 50, 200, 200+1000*(1l-(long)a)};
        vibrator.vibrate(pattern, -1);
    }

    public long millisToSleep() {
        float a = lastStimulus.getData().firstElement().firstElement();
        return 450+200+500*(1l-(long)a);
    }
}
