import java.util.Iterator;

public class DictionaryCommandline extends DictionaryManagement {

    public void showAllWords(){
        Iterator itr = this.dictionary.words.iterator();//getting the Iterator
        while(itr.hasNext()){//check if iterator has the elements
            Word word = (Word) itr.next();//printing the element and move to next
            System.out.println(word.word_target);
            System.out.println(word.word_explain);
        }
    }

    public void dictionaryBasic(){
        this.insertFromCommandline();
        this.showAllWords();
    }
}
