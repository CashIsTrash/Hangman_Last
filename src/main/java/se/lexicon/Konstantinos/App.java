package se.lexicon.Konstantinos;

public class App 
{
    public static void main( String[] args )
    {
        Hangman game = new Hangman();
        game.setHiddenWord();
        game.play();
    }
}
