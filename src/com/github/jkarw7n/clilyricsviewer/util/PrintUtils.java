package com.github.jkarw7n.clilyricsviewer.util;

public class PrintUtils {
    private AnsiColor color;
    private int width;

    public PrintUtils(int width, AnsiColor color) {
        this.width = width;
        this.color = color;
    }

    public void printCentered(String string) {
        if (string.length() <= (width - 4)) {
            int firstHalf = (width - string.length() - 4) / 2, secondHalf = width - string.length() - firstHalf - 4;
            System.out.println("| " + " ".repeat(firstHalf) + string + " ".repeat(secondHalf) + " |");
        } else if (string.length() > (width - 4)) {
            String split[] = string.split(" "), firstLine = "", secondLine = "";

            for (int i = 0; i < split.length; i++) {
                if (firstLine.length() + split[i].length() <= (width - 4))
                    firstLine += split[i] + " ";
                else {
                    firstLine = firstLine.substring(0, firstLine.length() - 1);

                    for (int j = i; j < split.length; j++) {
                        secondLine += split[j] + " ";
                    }

                    secondLine = secondLine.substring(0, secondLine.length() - 1);
                    printCentered(firstLine);
                    printCentered(secondLine);
                    break;
                }
            }
        }
    }

    public void printAnsi(String string, boolean bold) {
        int firstHalf = (width - string.length() - 6) / 2, secondHalf = width - string.length() - firstHalf - 6;
        System.out.println("| " + " ".repeat(firstHalf) + color.getString() + (bold ? AnsiColor.BOLD.getString() : "") + " " + string + " " + AnsiColor.RESET.getString() + " ".repeat(secondHalf) + " |");
    }

    public void printStart() {
        System.out.println(" " + "_".repeat(width - 2) + " ");
        System.out.println("|" + " ".repeat(width - 2) + "|");
    }

    public void printEnd() {
        System.out.println("|" + "_".repeat(width - 2) + "|");
    }

    public void printLine() {
        System.out.println("|" + " ".repeat(width - 2) + "|");
    }
}