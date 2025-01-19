package org.example.musicfx;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.musicfx.ButtonsAndSliders.addMusicButton;

public class MusicList {
    static String fileMSC = "music_list/";
    static List<File> file = new ArrayList<>(Arrays.asList(
            new File(fileMSC + "Ether – Silent Partner (No Copyright Music).wav")
    ));

    public static void addMusicWav(File newFile) {
        if (newFile != null && newFile.getName().endsWith(".wav")) {
            file.add(newFile);
            addMusicButton.setStyle("-fx-background-color:rgb(29, 247, 9);");
            addMusicButton.setText("Added!");
        } else {
            addMusicButton.setStyle("-fx-background-color:rgb(253, 9, 9);");
            addMusicButton.setText("Selected file not .wav");
        }
    }
    
    
    public static void  clearMusicList (AtomicInteger atomicInteger) {
        File lastFile = new File(file.get(atomicInteger.get()).getPath());
        file.clear();
        file.add(lastFile);
    }
}
