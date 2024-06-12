/*
 Ejemplo original

 https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html

 */

package poo.games;
import poo.games.circus_charlie.CircusCharlie;
import poo.games.pong.Pong;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;



public enum FXPlayer{
   START("Empieza.wav"),
   REVOTA("paleta.wav"),
   GANE("punto.wav"),
   SOUND_TRACK("SoundTrack.wav"),

   NivelSoundtrack1("1NivelSoundtrack.wav"),
   NivelSalto2("2NivelSalto.wav"),
   NivelSoundtrack2("2NivelSoundtrack.wav"),
   NivelSalto3("3NivelSalto.wav"),
   NivelSoundTrack3("3NivelSoundTrack.wav"),
   NivelSalto1("1NivelSalto.wav"),
   WinCircus("street-fighter_you_win.wav"),
   WinCircus2("AplausosFinales.wav");

   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }

   public static Volume volume = Volume.LOW;


   private Clip clip;


   FXPlayer(String wav) {
      try {
         URL url = this.getClass().getClassLoader().getResource("sonidos/"+wav);
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         clip = AudioSystem.getClip();
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }


   public void play() {
      if (volume != Volume.MUTE) {
         if (!clip.isRunning()){
            clip.setFramePosition(0);
            clip.start();
         }
      }
   }

   public void stop(){
      if (volume != Volume.MUTE) {
         if (clip.isRunning()){
            clip.setFramePosition(0);
            clip.stop();
         }
      }
   }
   public void loop(){
      if (volume != Volume.MUTE) {
         if (!clip.isRunning()){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
         }
      }
   }
   public void SwitchingLoop() {
      if(volume != Volume.MUTE) {
         if (!clip.isRunning()){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
         }else if (clip.isRunning()){
            clip.setFramePosition(0);
            clip.stop();
         }
      }else SwitchingMUTE();
   }
   public void SwitchingMUTE() {
      if (volume != Volume.MUTE) {
         volume = Volume.MUTE;
      }  else {
         volume = Volume.LOW;
      }
   }

   static void init() {
      values();
   }
}