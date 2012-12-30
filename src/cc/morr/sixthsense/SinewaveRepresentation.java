package cc.morr.sixthsense;

import android.content.Context;
import android.media.*;

public class SinewaveRepresentation extends Representation {
    Context context;
    AudioTrack audioTrack;

    int duration;
    int sampleRate;
    int numSamples;
    double sample[];
    double freqOfTone;
    byte generatedSnd[];

    public SinewaveRepresentation(Context context) {
        this.context = context;

        duration = 100; // milliseconds
        sampleRate = 8;
        numSamples = duration * sampleRate;
        sample = new double[numSamples];
        generatedSnd = new byte[2 * numSamples];

        audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
        1000*sampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
        AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
        AudioTrack.MODE_STREAM);
    }

    public void onStimulus(Stimulus s) {
        float a = s.getData().firstElement().firstElement();
        freqOfTone = 100+1000*a; // hz

        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (1000*sampleRate/freqOfTone));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }

        /*
        if (AudioTrack.STATE_INITIALIZED == audioTrack.getState()) {
            audioTrack.stop();
            audioTrack.flush();
        }
        */
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        //audioTrack.reloadStaticData();
        audioTrack.play();
    }

    public long millisToSleep() {
        return duration/2;
    }
}
