package cc.morr.sixthsense;

import java.util.Vector;

public class Stimulus {
    Vector<Vector<Float>> data;

    public Stimulus() {
        data = new Vector<Vector<Float>>();
    }

    public void add(Vector<Float> tuple) {
        data.add(tuple);
    }

    public Vector<Vector<Float>> getData() {
        return data;
    }
}
