package cc.morr.sixthsense;

import android.text.format.Time;
import java.util.Vector;
import java.util.List;
import android.net.wifi.*;
import android.content.Context;

public class WifiCountSensor extends Sensor {
    public WifiCountSensor(Context context) {
        super(context);
    }

    public Stimulus getStimulus() {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        List<ScanResult> confs = wifiManager.getScanResults();
        wifiManager.startScan();

        Vector<Float> tuple = new Vector<Float>();
        tuple.add((float)(confs.size()/10.0)); // estimate of wifi count around you

        Stimulus s = new Stimulus();
        s.add(tuple);
        return s;
    }
}
