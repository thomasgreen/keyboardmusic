package music;

import java.util.ArrayList;
import java.util.List;

public class Tester
{
	public static void main(String[] args)
	{
		Note testNote = new Note(Synthesizer.PPQ, 2*Synthesizer.PPQ, new Pitch("D4"));
		Note testNote2 = new Note(2*Synthesizer.PPQ, 2*Synthesizer.PPQ, new Pitch("C4"));
		List<Note> notes = new ArrayList<Note>();
		notes.add(testNote);
		notes.add(testNote2);
		Music testMus = new Music(notes, 60);
		Synthesizer.toMidi(testMus);
	}
}
