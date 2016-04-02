package music;

import java.util.Dictionary;
import java.util.Hashtable;

public class Pitch
{
	public static final Pitch NONE = null;
	public static final Hashtable<String, String> NAME_DICT = makeDict();
	public static final Hashtable<String, Integer> CODE_DICT = makeDict2();
	public int midiCode;
	public Pitch(String key, boolean isKey)
	{
		midiCode = nameToCode(keyToString(key));
	}
	public Pitch(String name)
	{
		midiCode = nameToCode(name);
	}
	public static int nameToCode(String name)
	{
		int x = CODE_DICT.get(name.substring(0, 1));
		int octave = Integer.parseInt(name.substring(1, 2));
		int dO = octave - 4;
		return x + (dO * 12);
	}
	public static String keyToString(String s)
	{
		return NAME_DICT.get(s);
	}
	public static Hashtable<String, String> makeDict()
	{
		Hashtable<String, String> dict = new Hashtable<String, String>();
		String[] key = {"a", "s", "d", "f", "g", "h", "j", "k", "l", ";"};
		String[] pitch = {"C4", "D4", "E4", "F4", "G4", "A4", "B4", "C5", "D5", "E5"};
		for(int i = 0; i < key.length; i++)
		{
			dict.put(key[i], pitch[i]);
		}
		return dict;
	}
	public static Hashtable<String, Integer> makeDict2()
	{
		Hashtable<String, Integer> dict = new Hashtable<String, Integer>();
		String[] name = {"C", "D", "E", "F", "G", "A", "B"};
		Integer[] code = {60, 62, 63, 64, 66, 68, 70};
		for(int i = 0; i < name.length; i++)
		{
			dict.put(name[i], code[i]);
		}
		return dict;
	}
}
