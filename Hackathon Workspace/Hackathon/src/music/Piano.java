package music;

import javax.sound.midi.*;

public class Piano {
	public static void playNote(String key) throws Exception
	{
		int code = Pitch.nameToCode(Pitch.keyToString(key));
		Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        Sequence sequence = new Sequence(Sequence.PPQ,4);
        Track track = sequence.createTrack();

        MidiEvent event = null;

        ShortMessage first = new ShortMessage();
        first.setMessage(192,1,0,0);
        MidiEvent changeInstrument = new MidiEvent(first, 1);
        track.add(changeInstrument);

        ShortMessage a = new ShortMessage();
        a.setMessage(144,1,code,100);
        MidiEvent noteOn = new MidiEvent(a, 1);
        track.add(noteOn);

        ShortMessage b = new ShortMessage();
        b.setMessage(128,1,code,100);
        MidiEvent noteOff = new MidiEvent(b, 16);
        track.add(noteOff);

        sequencer.setSequence(sequence);
        sequencer.start();
	}
}