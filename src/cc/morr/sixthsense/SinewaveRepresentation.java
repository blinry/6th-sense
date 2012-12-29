package cc.morr.sixthsense;

import android.content.Context;
import android.media.*;

public class SinewaveRepresentation extends Representation {
    Context context;

    public SinewaveRepresentation(Context context) {
        this.context = context;
    }

    public void onStimulus(Stimulus s) {
        float a = s.getData().firstElement().firstElement();

        final int duration = 1; // seconds
        final int sampleRate = 8000;
        final int numSamples = duration * sampleRate;
        final double sample[] = new double[numSamples];
        final double freqOfTone = 20*a; // hz
        final byte generatedSnd[] = new byte[2 * numSamples];

        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
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

        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
        sampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
        AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
        AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();
    }

    public long millisToSleep() {
        return 1000;
    }
}
