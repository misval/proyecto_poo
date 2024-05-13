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
   GANE("punto.wav");


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


   static void init() {
      values();
   }
}