package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static GradeLevel gradeLevel;

    private static StringBuilder fullText;
    private static int characters = 0;
    private static int words = 0;
    private static int sentences = 0;
    private static int syllables = 0;
    private static int polysyllables = 0;

    public static void main(String[] args) {
        fullText = new StringBuilder();
        gradeLevel = new readability.GradeLevel();
        loadFile(args[0]);

        System.out.println("The text is:");
        System.out.println(fullText);
        System.out.println();
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);

        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().toUpperCase();
        int totalYear = 0;
        double score;
        int y;
        ScoreType scoreType = ScoreType.valueOf(command);
        switch (scoreType) {
            case ALL:
            case ARI:
                score = ARI();
                y = gradeLevel.findAge((int) score);
                totalYear += y;
                System.out.printf("Automated Readability Index: %5.2f (about %s year olds)\n", score, y);
                if (scoreType != ScoreType.ALL) break;
            case FK:
                score = FK();
                y = gradeLevel.findAge((int) score);
                totalYear += y;
                System.out.printf("Flesch–Kincaid readability tests: %5.2f (about %s year olds)\n", score, y);
                if (scoreType != ScoreType.ALL) break;
            case SMOG:
                score = SMOG();
                y = gradeLevel.findAge((int) score);
                totalYear += y;
                System.out.printf("Simple Measure of Gobbledygook: %5.2f (about %s year olds)\n", score, y);
                if (scoreType != ScoreType.ALL) break;
            case CL:
                score = CL();
                y = gradeLevel.findAge((int) score);
                totalYear += y;
                System.out.printf("Coleman–Liau index: %5.2f (about %s year olds)\n", score, y);
                if (scoreType != ScoreType.ALL) break;
        }
        if (scoreType == ScoreType.ALL) {
            totalYear = totalYear / 4;
            System.out.printf("\nThis text should be understood in average by %s year olds.\n", totalYear);
        }
    }

    private static double ARI() {
        return 4.71 * ((double) characters / words) + 0.5 * ((double) words / sentences) - 21.43;
    }

    private static double FK() {
        return 0.39 * ((double) words / sentences) + 11.8 * ((double) syllables / words) - 15.59;
    }

    private static double SMOG() {
        return 1.043 * Math.sqrt(polysyllables * ((double) 30 / sentences)) + 3.1291;
    }

    private static double CL() {
        double S = (double) sentences / words * 100;
        double L = (double) characters / words * 100;
        return 0.0588 * L - 0.296 * S - 15.8;
    }

    private static void parseText(String text) {
        fullText.append(text);

        String[] s = text.split("[!?.]");
        sentences += s.length;

        String[] w = text.split("(\\u00a0|\\s)");
        words += w.length;

        for (int i = 0; i < w.length; i++) {
            characters += w[i].length();
            countSyllables(w[i]);
        }
    }

    private static void countSyllables(String word) {
        word = word.toLowerCase();
        Pattern pattern = Pattern.compile("[aiouy]+e*|e(?!d$|ly).|[td]ed|le$");
        Matcher matcher = pattern.matcher(word);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        syllables += count;
        if (count > 2) {
            polysyllables++;
        }
    }

    private static void loadFile(String filepath) {
        Scanner scanner = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(filepath));
            scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                parseText(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
