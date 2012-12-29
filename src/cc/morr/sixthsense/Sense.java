package cc.morr.sixthsense;

public class Sense {
    Sensor sensor;
    Representation representation;

    public Sense(Sensor sensor, Representation representation) {
        this.sensor = sensor;
        this.representation = representation;
    }

    public void start() {
        representation.connect(sensor);
        representation.start();
    }
}
