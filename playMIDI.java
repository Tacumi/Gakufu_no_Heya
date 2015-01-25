package GakufunoHeya;

public class playMIDI {
	public static void main(String args[]) {
		status sts = new status();
		measureList mList = new measureList();
		int measureLength;
		String fileNameP;
		String fileNameD;

		measureLength = sts.pianoMusic.length();

		for (int i = 0; i <= measureLength; i++) {
			fileNameP = mList.pianoList(sts.pianoMusic.substring(i, 1));
			fileNameD = mList.drumList(sts.drumMusic.substring(i, 1));
			music_play.music_play(fileNameP);
			music_play.music_play(fileNameD);
		}
	}
}
