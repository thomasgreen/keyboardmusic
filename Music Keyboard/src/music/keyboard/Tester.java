package music.keyboard;

import java.util.ArrayList;
import java.util.List;

public class Tester
{
	public static void main(String[] args)
	{
		/*Note testNote = new Note(Synthesizer.PPQ, Synthesizer.PPQ, new Pitch("D4"));
		Note testNote2 = new Note(3*Synthesizer.PPQ, Synthesizer.PPQ, new Pitch("C4"));
		Note testNote3 = new Note(5*Synthesizer.PPQ, Synthesizer.PPQ, new Pitch("C4"));

		List<Note> notes = new ArrayList<Note>();
		notes.add(testNote);
		notes.add(testNote2);
		notes.add(testNote3);
		Music testMus = new Music(notes, 60);
		Synthesizer.toMidi(testMus);*/
            Metronome x = new Metronome(60);
            x.start();
            try {
                Thread.sleep(10000);
            }
            catch (Exception e) {
                System.out.println(e);
               
            }
	}
}