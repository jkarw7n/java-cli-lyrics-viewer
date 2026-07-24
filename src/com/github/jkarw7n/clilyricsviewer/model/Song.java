package com.github.jkarw7n.clilyricsviewer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.github.jkarw7n.clilyricsviewer.exception.SongNotFoundException;

public class Song {
    private String title, artist;
    private int seconds;
    private Path musicFilePath, lyricsFilePath;

    public Song(String title, String artist, Path musicFilePath, Path lyricsFilePath) {
        this.title = title;
        this.artist = artist;
        this.musicFilePath = musicFilePath;
        this.lyricsFilePath = lyricsFilePath;
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getSeconds() { return seconds; }
    public Path getMusicFilePath() { return musicFilePath; }
    public Path getLyricsFilePath() { return lyricsFilePath; }

    public static Song getSong(String songTitle) throws IOException, SongNotFoundException {
        List<String> lines = Files.readAllLines(Paths.get("assets/songbook.txt"));
        lines.removeIf(line -> line.startsWith("//") || line.isBlank());
        String title, artist;
        Path musicFilePath, lyricsFilePath;

        for (int i = 0; i < lines.size(); i++) {
            if (i % 4 != 0)
                continue;

            if (lines.get(i).trim().substring(7).equalsIgnoreCase(songTitle)) {
                title = lines.get(i).trim().substring(7);
                artist = lines.get(i + 1).trim().substring(8);
                musicFilePath = Paths.get("assets/music/" + lines.get(i + 2).trim().substring(7));
                lyricsFilePath = Paths.get("assets/lyrics/" + lines.get(i + 3).trim().substring(8));
                return new Song(title, artist, musicFilePath, lyricsFilePath);
            }
        }

        throw new SongNotFoundException("Song \"" + songTitle + "\" could not be found in songbook.txt.");
    }
}
