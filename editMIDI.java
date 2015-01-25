package GakufunoHeya;

public class editMIDI {
	public void editMIDI() {
		status sts = new status();
		measureList mList = new measureList();

		String RamdomP = Integer
				.toHexString((int) (Math.random() * mList.amountPiano));
		String RamdomD = Integer
				.toHexString((int) (Math.random() * mList.amountDrum));

		StringBuffer buffer = new StringBuffer();
		buffer.append(sts.pianoMusic);
		buffer.append(RamdomP);
		sts.pianoMusic = buffer.toString();

		buffer.setLength(0);
		buffer.append(sts.drumMusic);
		buffer.append(RamdomD);
		sts.drumMusic = buffer.toString();
	}

}