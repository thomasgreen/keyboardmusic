package music.keyboard;

import java.util.ArrayList;
import java.util.List;
import music.keyboard.MusicFrame.KeyNode;

public class Music
{       public static boolean legato = true;
	List<Note> notes;
	public int tempo;
	public Music(List<Note> notes, int tempo)
	{
		if(legato) {
                    toLegato(notes);
                }
                this.notes = notes;
		this.tempo = tempo;
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
	public List<Note> getNotes()
	{
		return notes;
	}
	public static double timeAvg(double[] times)
	{
		double sum = 0;
		for(double t: times)
		{
			sum += t;
		}
		return sum/times.length;
	}
	public static int bpm(double timeAvg)
	{
		return (int) Math.round(60/timeAvg);
	}
	public static List<Note> convert(List<KeyNode> keyPresses, int bpm)
	{
		List<Note> notes = new ArrayList<Note>();
		double beatLength = Rhythm.bpmToBeatLength(bpm);
		for(KeyNode k: keyPresses)
		{
			int startTime = Rhythm.convert(k.seconds, beatLength);
			int duration = Rhythm.convert(k.duration, beatLength);
			Pitch pitch = new Pitch(k.note, true);
			notes.add(new Note(startTime, duration, pitch));
		}
		return notes;
	}
}
/*
class KeyPress
{
	public double startTime;
	public double duration;
	public String key;
	public KeyPress(String key, double duration, double startTime)
	{
		this.startTime = startTime;
		this.duration = duration;
		this.key = key;
	}

}*/