package com.github.jkarw7n.clilyricsviewer.lyrics;

import com.github.jkarw7n.clilyricsviewer.exception.LyricsFileNotFoundException;
import com.github.jkarw7n.clilyricsviewer.model.Song;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class LyricsChecker {
    private List<String> lines, timestamps, checkedLyrics;
    private Song song;

    public LyricsChecker(Song song) throws IOException, LyricsFileNotFoundException {
        this.song = song;
        timestamps = new ArrayList<>();
        checkedLyrics = new ArrayList<>();
        checkLyrics();
    }

    private void checkLyrics() throws IOException, LyricsFileNotFoundException {
        if (!Files.exists(song.getLyricsFilePath()))
            throw new LyricsFileNotFoundException("Lyrics file " + song.getLyricsFilePath().getFileName().toString() + " could not be found.");

        lines = Files.readAllLines(song.getLyricsFilePath());
        lines.removeIf(line -> line.equals("WEBVTT") || line.isBlank() || line.matches("\\d+"));
        
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("-->")) {
                timestamps.add(lines.get(i));
                lines.remove(i);
            }
        }

        for (int i = 0; i < timestamps.size() - 1; i++) {
            String[] split = timestamps.get(i).split(" --> "),
                     nextSplit = timestamps.get(i + 1).split(" --> ");

            if (split[1].equals(nextSplit[0]))
                continue;

            timestamps.add(i + 1, split[1] + " --> " + nextSplit[0]);
            lines.add(i + 1, "");
        }

        if (!timestamps.get(0).startsWith("00:00:00.000")) {
            String[] split = timestamps.get(0).split(" --> ");

            timestamps.add(0, "00:00:00.000 --> " + split[0]);
            lines.add(0, "");
        }

        timestamps.add("00:00:00.000 --> 00:00:00.000");
        lines.add("");

        for (int i = 0; i < timestamps.size(); i++) {
            checkedLyrics.add(timestamps.get(i));
            checkedLyrics.add(lines.get(i));
        }
    }

    public List<String> getCheckedLyrics() { return checkedLyrics; }
}
