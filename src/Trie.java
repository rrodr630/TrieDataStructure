import java.util.*;

public class Trie
{
    private TrieNode dictionary;

    public Trie()
    {
        dictionary = new TrieNode('*',false);
    }

    /**
     * Searches for all words that contain the substring passed as parameter.
     * @param substring Prefix of the word
     */
    public void allWordsStartWith(String substring)
    {
        ArrayList<String> allWords = new ArrayList<>(); // to store all words found
        TrieNode iterator = dictionary;                 // iterator to traverse the tree
        StringBuilder str = new StringBuilder();        // build the path of the substring

        for (int i = 0; i < substring.length(); i++)
        {
            iterator = iterator.nextChars.get(substring.charAt(i)); // move to the new node that contains the char
            str.append(substring.charAt(i));                        // save the path
        }

        getWords(iterator, allWords, str); // get all words (look for every remaining path at every child node)
        printWords(allWords);
    }

    /**
     * Dynamic programming method that finds a word and stores it into the collection passed as parameter.
     * @param allWords To store all words found.
     * @param strBld To build the words as you traverse the tree.
     */
    private void getWords(TrieNode subTree, ArrayList<String> allWords, StringBuilder strBld)
    {
        if (subTree.isWord)
            allWords.add(strBld.toString());

        // the following loop must be performed always, even if the current letter marks a word (Ex: min -> mine)
        for (Character character : subTree.nextChars.keySet())
            getWords(subTree.nextChars.get(character), allWords, new StringBuilder(strBld).append(character));
    }

    // Prints all words found in previous method
    private void printWords(ArrayList<String> words)
    {
        for (String str: words)
            System.out.println(str);
    }

    /**
     * Adds a word to the dictionary list.
     * @param word Word to be added
     */
    public void addWord(String word)
    {
        addLetter(word.toCharArray());
    }

    // Finds the "right place" to add a word by traversing the path (letters of the word)
    private void addLetter(char[] path)
    {
        TrieNode iterator = dictionary;

        for (int i = 0; i < path.length; i++)
        {
            iterator.nextChars.putIfAbsent(path[i],new TrieNode(path[i],false));
            iterator = iterator.nextChars.get(path[i]);
        }
        // after you insert all letters, the path from root to the last letter "describes" a word
        iterator.isWord = true;
    }

    public boolean findWord(String wordToBeFound)
    {
        char[] word = wordToBeFound.toCharArray();
        TrieNode iterator = dictionary;

        for (char letter: word)
        {
            if (iterator.nextChars.get(letter) == null)
                return false;
            iterator = iterator.nextChars.get(letter);
        }

        return iterator.isWord;
    }

     class TrieNode
    {
        Hashtable<Character, TrieNode> nextChars;
        char letter;
        boolean isWord;

        TrieNode(char letter, boolean isWord)
        {
            nextChars = new Hashtable<>();
            this.letter = letter;
            this.isWord = isWord;
        }
    }
}
