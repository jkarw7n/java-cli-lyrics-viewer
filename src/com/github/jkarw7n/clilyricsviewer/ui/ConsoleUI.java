package com.github.jkarw7n.clilyricsviewer.ui;

import com.github.jkarw7n.clilyricsviewer.exception.LyricsFileNotFoundException;
import com.github.jkarw7n.clilyricsviewer.exception.SongNotFoundException;
import com.github.jkarw7n.clilyricsviewer.lyrics.LyricsChecker;
import com.github.jkarw7n.clilyricsviewer.model.Song;
import com.github.jkarw7n.clilyricsviewer.util.AnsiColor;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    public void start() {
        int width = 80;
        List<String> checkedLyrics = null;
        Scanner scanner = new Scanner(System.in);
        Song song = null;

        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine();
        System.out.print("Enter color name: ");
        String colorInput = scanner.nextLine();
        
        try {
            song = Song.getSong(songTitle);
            LyricsChecker checker = new LyricsChecker(song);
            checkedLyrics = checker.getCheckedLyrics();
        } catch (IOException | LyricsFileNotFoundException | SongNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        AnsiColor color = switch (colorInput.trim().toLowerCase()) {
            case "rosewater", "rose" -> AnsiColor.ROSEWATER;
            case "flamingo" -> AnsiColor.FLAMINGO;
            case "pink" -> AnsiColor.PINK;
            case "red" -> AnsiColor.RED;
            case "peach", "orange" -> AnsiColor.PEACH;
            case "yellow" -> AnsiColor.YELLOW;
            case "green" -> AnsiColor.GREEN;
            case "teal" -> AnsiColor.TEAL;
            case "sky" -> AnsiColor.SKY;
            case "sapphire" -> AnsiColor.SAPPHIRE;
            case "blue" -> AnsiColor.BLUE;
            case "lavender", "purple" -> AnsiColor.LAVENDER;
            case "mauve", "violet" -> AnsiColor.MAUVE;
            default -> null;
        };

        LyricsDisplayer displayer = new LyricsDisplayer(song, checkedLyrics, color, width);
        
        try {
            displayer.startDisplay();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}