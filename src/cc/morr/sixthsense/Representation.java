package cc.morr.sixthsense;

public abstract class Representation {
    Sensor sensor;

    public void connect(Sensor sensor) {
        this.sensor = sensor;
    }

    public abstract void start();
}
