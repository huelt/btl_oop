import java.util.Scanner;

public class DictionaryManagement {
    public Dictionary dictionary = new Dictionary();

    public void insertFromCommandline(){
        Word word = new Word();
        Scanner scanner = new Scanner(System.in);

        String english  = scanner.nextLine();
        String vietnamese  = scanner.nextLine();

        word.word_target = english;
        word.word_explain = vietnamese;

        this.dictionary.add(word);
    }
}
