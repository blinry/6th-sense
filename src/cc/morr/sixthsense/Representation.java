package cc.morr.sixthsense;

public abstract class Representation {
    Sensor sensor;
    Stimulus lastStimulus;

    public void connect(Sensor sensor) {
        this.sensor = sensor;
    }

    public void start() {
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    reactTo(sensor.getStimulus());

                    long endTime = System.currentTimeMillis() + millisToSleep();
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

    public void reactTo(Stimulus s) {
        lastStimulus = s;
        onStimulus(s);
    }

    public Stimulus getLastStimulus() {
        return lastStimulus;
    }

    abstract public void onStimulus(Stimulus s);
    abstract public long millisToSleep();
}
