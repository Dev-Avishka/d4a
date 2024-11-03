package com.dev;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class AudioFile {
    private String mp3FilePath;
    private String d4aFilePath;
    private String title;
    private String artist;

    public AudioFile(String mp3FilePath, String d4aFilePath, String title, String artist) {
        this.mp3FilePath = mp3FilePath;
        this.d4aFilePath = d4aFilePath;
        this.title = title;
        this.artist = artist;
    }

    public void convertToD4A() throws IOException {

        File mp3File = new File(mp3FilePath);
        if (!mp3File.exists()) {
            throw new FileNotFoundException("MP3 file not found: " + mp3FilePath);
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(d4aFilePath))) {

            writeHeader(dos);


            byte[] mp3Data = FileUtils.readFileToByteArray(mp3File);
            dos.write(mp3Data);
        }
    }

    private void writeHeader(DataOutputStream dos) throws IOException {

        writeUTF16String(dos, "MYFMT");

        dos.writeInt(1);


        writeUTF16String(dos, title);


        writeUTF16String(dos, artist);


        dos.writeInt(0);
        dos.writeInt(0);
    }

    private void writeUTF16String(DataOutputStream dos, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-16LE");
        dos.writeInt(bytes.length);
        dos.write(bytes);
    }
}