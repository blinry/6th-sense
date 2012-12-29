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

    public void start() {
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    Stimulus s = sensor.getStimulus();
                    float a = s.getData().firstElement().firstElement();
                    long pauseLength = (60-(long)a)*10;
                    long[] pattern = new long[] {0, 200, 50, 200, pauseLength};

                    vibrator.vibrate(pattern, -1);

                    long endTime = System.currentTimeMillis() + 450+pauseLength;
                    while (System.currentTimeMillis() < endTime) {
                        synchronized (this) {
                            try {
                                wait(endTime - System.currentTimeMillis());
                            } catch (Exception e) {
                            }
                        }
                    }

                }
            }
        }).start();
    }
}
