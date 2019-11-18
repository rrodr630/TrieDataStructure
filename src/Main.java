public class Main {
    public static void main(String[] args)
    {
        Trie myDictionary = new Trie();

        myDictionary.addWord("min");
        myDictionary.addWord("minus");
        myDictionary.addWord("mino");
        myDictionary.addWord("mine");
        myDictionary.addWord("mines");

        myDictionary.allWordsStartWith("m");

        System.out.println(myDictionary.findWord("min"));
        System.out.println(myDictionary.findWord("minos"));
    }
}
