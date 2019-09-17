package se.lexicon.Konstantinos;


import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Hangman {

    boolean found;
    private String hiddenWord;
    private String[] poolOfWords = {"Jam", "Loic", "Hysteria", "Encapsulation"};
    private char[] correctGuesses = new char[0];
    private StringBuilder correctGuessesToStr = new StringBuilder();
    private StringBuilder allTypedLetters = new StringBuilder();
    private Scanner sc = new Scanner(System.in);
    private int guess = 1;
    private int lives = 8;
    private String passToStrOrChar = ""; // PASS TO STRINGBUILDER ONLY
    private String myGuess;
    private String toUpper;
    private String[] str; // DRAW LINES
    private int index = 0;


    public void setHiddenWord() {
        int position = ThreadLocalRandom.current().nextInt(0, poolOfWords.length);
        this.hiddenWord = poolOfWords[position];
    }

    public String getHiddenWord() {
        return hiddenWord;
    }


    public void play() {
        correctGuesses = new char[hiddenWord.length()];
        initializeWithSpaces();
        found = false;

        while (!found) {
            guessLetter();
            isValidGuess();
            matchesWord();


        }
    }

    public String[] initializeWithSpaces() {
        str = new String[getHiddenWord().length()];
        System.out.println("Hidden word = " + getHiddenWord());
        for (int i = 0; i < str.length; i++) {
            str[i] = "_ ";
            System.out.print(str[i]);
        }
        System.out.println();
        return str;
    }

    public String guessLetter() {

        System.out.print("Guess " + guess + ": ");
        guess++;
        myGuess = sc.nextLine();
        toUpper = myGuess.toUpperCase();
        return myGuess;
    }

    public boolean isValidGuess() {
        boolean isLetter = true;

        for (int i = 0; i < toUpper.length(); i++) {
            if (!Character.isLetter(myGuess.charAt(i))) {
                isLetter = false;
                break;
            }
        }

        if (passToStrOrChar.contains(toUpper)) {
            System.out.println("--> This letter or Sequence has already been typed!");
            System.out.print("--> All Typed Letters: " + allTypedLetters + "\n\n");
            System.out.println("--> Correct Guesses: " + correctGuessesToStr + "\n");
        } else if (!isLetter) {
            System.out.println("--> Please type a letter or a char sequence!");
            System.out.print("--> All Typed Letters: " + allTypedLetters + "\n\n");
            System.out.println("--> Correct Guesses: " + correctGuessesToStr + "\n");
        } else {
            passToStrOrChar += toUpper;
            allTypedLetters.append("[" + toUpper + "]");
            for (int i = 0; i < hiddenWord.length(); i++) {
                if (myGuess.toUpperCase().charAt(0) == hiddenWord.charAt(i) || myGuess.toLowerCase().charAt(0) == hiddenWord.charAt(i)) {
                    correctGuessesToStr.append("[" + myGuess.toUpperCase() + "]");
                }
            }

            System.out.print("--> All Typed Letters: " + allTypedLetters + "\n\n");
            System.out.println("--> Correct Guesses: " + correctGuessesToStr + "\n");
        }

        return true;
    }

    public boolean matchesWord() {

        boolean matchesLetter = false;

        if ((myGuess.equalsIgnoreCase(hiddenWord))) {
            won();
        }


        for (int i = 0; i < hiddenWord.length(); i++) {
            if (myGuess.toUpperCase().charAt(0) == hiddenWord.charAt(i) || (myGuess.toLowerCase().charAt(0) == hiddenWord.charAt(i))) {
                str[i] = myGuess.charAt(0) + " ";


//                correctGuesses[i] = myGuess.charAt(0);
//          if(correctGuesses.)
//                    won();

            }
        }
        System.out.println(Arrays.toString(str));
        System.out.println(Arrays.toString(correctGuesses));


        for (int j = 0; j < hiddenWord.length(); j++) {
            System.out.print(str[j]);
        }
        System.out.println("\n\n\n");


        System.out.println();
        return matchesLetter;
    }


    public boolean won() {
        found = true;
        System.out.println("----------------------------------------");
        System.out.println("Congrats you won!");
        System.out.println("The hidden word was: " + hiddenWord);
        System.out.println("----------------------------------------");
        System.exit(1);
        return found;
    }

    public boolean lost() {
        found = true;
        System.out.println("----------------------------------------");
        System.out.println("Sorry you lost!");
        System.out.println("The hidden word was: " + hiddenWord);
        System.out.println("----------------------------------------");
        //System.exit(1);
        return found;
    }


}
