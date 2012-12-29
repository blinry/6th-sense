package cc.morr.sixthsense;

public abstract class Representation {
    Sensor sensor;

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

    abstract public void reactTo(Stimulus s);
    abstract public long millisToSleep();
}
