package GakufunoHeya;

import javax.sound.midi.*;

public class editMIDI {
	public static void main(String args[]){
		createMD();
	}
	public static void createMD() {
		try {
			// 一秒あたり24フレーム、一フレーム24tick
			Sequence sequence = new Sequence(Sequence.PPQ, 24);
			Track track = sequence.createTrack();
			/* チャンネル0,音の高さ48音の強さ127音色番号6 */
			byte[] data = new byte[1];
			int channel = 0;
			int pitch = 48;
			int velocity = 127;
			int instrument = 6;
			// set tempo
			// 四分音符の長さをμsecで指定、3バイトに分解
			MetaMessage mmessage = new MetaMessage();
			int tempo = 20;
			int l = 60 * 1000 * 1000 / tempo;
			mmessage.setMessage(0x51, new byte[] { (byte) (l / 65536),
					(byte) (l % 65536 / 256), (byte) (l % 256) }, 3);
			track.add(new MidiEvent(mmessage, 0));
			// set instrument
			// 音色指定、番号6
			ShortMessage message = new ShortMessage();
			message.setMessage(ShortMessage.PROGRAM_CHANGE, channel,
					instrument, 0);
			track.add(new MidiEvent(message, 0));
			// Note on 音を出す
			message = new ShortMessage();
			message.setMessage(ShortMessage.NOTE_ON, channel, pitch, velocity);
			track.add(new MidiEvent(message, 0));
			// Note off after quater (24tick)
			message = new ShortMessage();
			message.setMessage(ShortMessage.NOTE_OFF, channel, pitch, velocity);
			track.add(new MidiEvent(message, 24));
			// write to file　ファイルに書き出す
			MidiSystem.write(sequence, 0, new java.io.File("hello.mid"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}