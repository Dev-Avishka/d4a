package com.dev;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;

public class Player {
    public static void play(String d4aFilePath) {
        try (FileInputStream fis = new FileInputStream(d4aFilePath);
             DataInputStream dis = new DataInputStream(fis)) {
            dis.skipBytes(20);
            byte[] mp3Data = new byte[(int) (new File(d4aFilePath).length() - 20)];
            dis.readFully(mp3Data);


            AdvancedPlayer player = new AdvancedPlayer(new ByteArrayInputStream(mp3Data));
            player.play();
        } catch (IOException e) {
            System.err.println("An error occurred during playback: " + e.getMessage());
        } catch (JavaLayerException e) {
            System.err.println("An error occurred with the audio playback: " + e.getMessage());
        }
    }

}
