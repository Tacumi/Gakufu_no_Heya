package GakufunoHeya;

public class playMIDI {
	public static void main(String args[]) {
		status sts = new status();
		int measureLength;
		String fileNameP;
		String fileNameB;
		String fileNameD;

		measureLength = sts.pianoMusic.length();

		for (int i = 0; i <= measureLength; i++) {
			fileNameP = measureList.pianoList(sts.pianoMusic.substring(i, 1));
			fileNameB = measureList.baseList(sts.baseMusic.substring(i, 1));
			fileNameD = measureList.drumList(sts.drumMusic.substring(i, 1));
			music_play.music_play(fileNameP);
			music_play.music_play(fileNameB);
			music_play.music_play(fileNameD);
			sleep(60 * 1000 / measureList.bpm);
		}
	}

	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}
}
