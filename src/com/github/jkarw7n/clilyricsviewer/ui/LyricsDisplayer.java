package com.github.jkarw7n.clilyricsviewer.ui;

import com.github.jkarw7n.clilyricsviewer.model.Song;
import com.github.jkarw7n.clilyricsviewer.music.MusicPlayer;
import com.github.jkarw7n.clilyricsviewer.util.AnsiColor;
import com.github.jkarw7n.clilyricsviewer.util.PrintUtils;

import java.util.List;

public class LyricsDisplayer {
    private AnsiColor color;
    private int width;
    private List<String> checkedLyrics;
    private Song song;

    public LyricsDisplayer(Song song, List<String> checkedLyrics, AnsiColor color, int width) {
        this.song = song;
        this.checkedLyrics = checkedLyrics;
        this.color = color;
        this.width = width;
    }

    public void startDisplay() throws InterruptedException {
        PrintUtils pu = new PrintUtils(width, color);
        pu.printStart();
        pu.printAnsi(song.getTitle(), true);
        pu.printAnsi("by " + song.getArtist(), false);
        MusicPlayer player = new MusicPlayer(song.getMusicFilePath());
        player.start();
        int previousLines = 0;

        for (int i = 0; i < checkedLyrics.size(); i++) {
            if (i % 2 == 0)
                continue;

            for (int j = 0; j < previousLines; j++) {
                System.out.print("\u001B[1A\u001B[2K");
            }
            
            pu.printCentered(checkedLyrics.get(i));
            pu.printEnd();
            previousLines = (checkedLyrics.get(i).length() / (width - 4)) + 2;
            Thread.sleep(durationOf(checkedLyrics.get(i - 1)));
        }
    }

    private long durationOf(String string) {
        String[] split = string.split(" --> ");
        String start = split[0], end = split[1];
        String[] startSplit = start.split(":"), endSplit = end.split(":");
        startSplit[2] = startSplit[2].replace(".", "");
        endSplit[2] = endSplit[2].replace(".", "");
        return ((Long.valueOf(endSplit[1]) * 60000) + Long.valueOf(endSplit[2])) - ((Long.valueOf(startSplit[1]) * 60000) + Long.valueOf(startSplit[2]));
    }
}
