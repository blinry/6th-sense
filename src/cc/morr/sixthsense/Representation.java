package cc.morr.sixthsense;

import java.util.Vector;

public abstract class Representation {
    Stimulus lastStimulus;
    Sensor sensor;

        public Vector<Float> min;
        public Vector<Float> max;

    public void connect(Sensor sensor) {
        this.sensor = sensor;
        min = new Vector<Float>();
        max = new Vector<Float>();
    }

    public void start() {
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    Stimulus s = sensor.getStimulus();
                    for (Vector<Float> tuple : s.getData()) {
                        for (int i=0; i<tuple.size(); i++) {
                            while (i >= min.size()) {
                                min.add(0f);
                            }
                            while (i >= max.size()) {
                                max.add(1f);
                            }

                            if (max.get(i) < tuple.get(i))
                                max.set(i, tuple.get(i));
                            if (min.get(i) > tuple.get(i))
                                min.set(i, tuple.get(i));
                        }
                    }

                    for (int i=0; i<min.size(); i++) {
                        float d = max.get(i)-min.get(i);

                        for (int j=0; j<s.getData().size(); j++) {
                            s.getData().get(j).set(i, 
                                    ((s.getData().get(j).get(i))-min.get(i))/
                                    (max.get(i)-min.get(i)));
                        }

                        max.set(i, max.get(i)-0.1f*d);
                        min.set(i, min.get(i)+0.1f*d);
                    }

                    readStimulus(s);

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

    public void readStimulus(Stimulus s) {
        lastStimulus = s;
        onStimulus(s);
    }

    public Stimulus getLastStimulus() {
        return lastStimulus;
    }

    abstract public void onStimulus(Stimulus s);
    abstract public long millisToSleep();
}
