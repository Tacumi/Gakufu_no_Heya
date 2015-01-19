import javax.sound.midi.spi.*;
import javax.sound.midi.*;
public class HelloFile {
  public static void main(String[] args){
    try {
      //一秒あたり24フレーム、一フレーム24tick
      Sequence sequence = new Sequence(Sequence.PPQ, 24);
      Track track = sequence.createTrack();
      byte[] data = new byte[1];
      int channel = 0;
      int pitch = 48;
      int velocity = 127;
      int instrument = 6;
      //set tempo
      MetaMessage mmessage = new MetaMessage();
      int tempo = 20;
      int l = 60*1000000/tempo;
      mmessage.setMessage(0x51, 
                          new byte[]{(byte)(l/65536), (byte)(l%65536/256), (byte)(l%256)}, 
                          3);
      track.add(new MidiEvent(mmessage, 0));
      //set instrument
      ShortMessage message = new ShortMessage();
      message.setMessage(ShortMessage.PROGRAM_CHANGE, channel, instrument, 0);
      track.add(new MidiEvent(message, 0));
      // Note on
      message = new ShortMessage();
      message.setMessage(ShortMessage.NOTE_ON, channel, pitch, velocity);
      track.add(new MidiEvent(message, 0));
      // Note off after quater (24tick)
      message = new ShortMessage();
      message.setMessage(ShortMessage.NOTE_OFF, channel, pitch, velocity);
      track.add(new MidiEvent(message, 24));
      //write to file
      MidiSystem.write(sequence, 0,new java.io.File("hello.mid"));
     } catch(Exception e){
      e.printStackTrace();
    }
  }
}