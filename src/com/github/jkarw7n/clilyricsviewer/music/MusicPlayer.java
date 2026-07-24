package com.github.jkarw7n.clilyricsviewer.music;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {
    private Path musicFilePath;

    public MusicPlayer(Path musicFilePath) {
        this.musicFilePath = musicFilePath;
    }

    public void start() {
        if (musicFilePath != null) {
            Thread audioThread = new Thread(() -> {
                try {
                    FileInputStream stream = new FileInputStream(musicFilePath.toString());
                    Player player = new Player(stream);
                    player.play();
                } catch (FileNotFoundException | JavaLayerException e) {
                    System.out.println(e.getMessage());
                }
            });
            audioThread.start();
        }
    }
}
