package com.github.jkarw7n.clilyricsviewer.util;

public enum AnsiColor {
    ROSEWATER("\u001B[38;5;16;48;2;220;138;120m"),
    FLAMINGO("\u001B[38;5;16;48;2;221;120;120m"),
    PINK("\u001B[38;5;16;48;2;234;118;203m"),
    RED("\u001B[38;5;231;48;2;210;15;57m"),
    PEACH("\u001B[38;5;16;48;2;254;100;11m"),
    YELLOW("\u001B[38;5;16;48;2;223;142;29m"),
    GREEN("\u001B[38;5;231;48;2;64;160;43m"),
    TEAL("\u001B[38;5;231;48;2;23;146;153m"),
    SKY("\u001B[38;5;16;48;2;4;165;229m"),
    SAPPHIRE("\u001B[38;5;16;48;2;32;159;181m"),
    BLUE("\u001B[38;5;231;48;2;30;102;245m"),
    LAVENDER("\u001B[38;5;16;48;2;114;135;253m"),
    MAUVE("\u001B[38;5;231;48;2;136;57;239m"),
    BOLD("\u001B[1m"),
    RESET("\u001B[0m");

    private String ansiColorString;

    private AnsiColor(String ansiColorString) {
        this.ansiColorString = ansiColorString;
    }

    public String getString() { return ansiColorString; }
}
