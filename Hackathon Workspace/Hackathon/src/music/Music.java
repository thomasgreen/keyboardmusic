package music;

import java.util.ArrayList;
import java.util.List;

public class Music
{
	public static boolean legato = false;
	List<Note> notes;
	public int tempo;
	public Music(List<Note> notes, int tempo)
	{
		if(legato)
			toLegato(notes);
		this.notes = notes;
		this.tempo = tempo;
	}
	public List<Note> getNotes()
	{
		return notes;
	}
	public static List<Note> convert(List<KeyPress> keyPresses, int bpm)
	{
		List<Note> notes = new ArrayList<Note>();
		double beatLength = Rhythm.bpmToBeatLength(bpm);
		for(KeyPress k: keyPresses)
		{
			int startTime = Rhythm.convert(k.startTime, beatLength);
			int duration = Rhythm.convert(k.duration, beatLength);
			Pitch pitch = new Pitch(k.key, true);
			notes.add(new Note(startTime, duration, pitch));
		}
		return notes;
	}
	public static void toLegato(List<Note> notes)
	{
		for(Note n: notes)
		{
			int end = n.getEnd();
			int legatoEnd = -1;
			for(Note p: notes)
			{
				int pEnd = p.getStart();
				if(legatoEnd == -1 && pEnd > end)
					legatoEnd = pEnd;
				else if(end < pEnd && pEnd < legatoEnd)
					legatoEnd = pEnd;
			}
			n.setEnd(Math.max(legatoEnd, end));
		}
	}
}
class KeyPress
{
	public double startTime;
	public double duration;
	public String key;
	public KeyPress(double startTime, String key, double duration)
	{
		this.startTime = startTime;
		this.duration = duration;
		this.key = key;
	}
}