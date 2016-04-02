package music.keyboard;

public class Rhythm
{
	public static final int BASE = 4; //1/4 of a beat = 16th note
	public static final int PPQ = Synthesizer.PPQ;
	public static final int TICK_PER_BASE = PPQ/BASE;
	public static double bpmToBeatLength(int bpm)
	{
		return 60.0/bpm;
	}
	public static int convert(double duration, double beatLength)
	{
		//Input: duration and beat length in seconds.
		//Returns duration in ticks.
		return (((int) Math.round(duration*PPQ/beatLength))/TICK_PER_BASE) * TICK_PER_BASE;
	}
}
