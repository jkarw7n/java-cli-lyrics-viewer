package com.github.jkarw7n.clilyricsviewer.exception;

public class LyricsFileNotFoundException extends Exception {
    public LyricsFileNotFoundException(String message) {
        super(message);
    }

    public LyricsFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}