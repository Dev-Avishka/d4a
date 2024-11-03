package com.dev;

import java.io.*;

public class Controllers {

    public static void playD4AFile(String d4aFilePath) {
        System.out.println(d4aFilePath);
        Player.play(d4aFilePath);
    }

    public static void convert_mp3(String mp3Loc,String outputD4AFile,String title,String artist){
        AudioFile audioFile = new AudioFile(mp3Loc, outputD4AFile, title, artist);
        try {
            audioFile.convertToD4A();
            System.out.println("Conversion complete! Saved to: " + outputD4AFile);
        } catch (IOException e) {
            System.err.println("An error occurred during conversion: " + e.getMessage());
        }
    }
}
