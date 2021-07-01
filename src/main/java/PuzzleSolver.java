import java.util.ArrayList;
import java.util.HashSet;

public class PuzzleSolver {
    private final char[] chars1;
    private final char[] chars2;
    private final char[] chars3;
    private final char[] chars4;
    private final ArrayList<char[]> list = new ArrayList<>();

    private final int maxWords;
    private final WordTreeNode root;

    private final ArrayList<String>[] words1 = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList()};
    private final ArrayList<String>[] words2 = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList()};
    private final ArrayList<String>[] words3 = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList()};
    private final ArrayList<String>[] words4 = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList()};
    private final ArrayList<ArrayList<String>[]> wordsList = new ArrayList<>();

    private HashSet<Character> allChar = new HashSet<>();
    private HashSet<String> usedWord = new HashSet<>();

    public PuzzleSolver(char[] chars1, char[] chars2, char[] chars3, char[] chars4, WordTreeNode root, int maxWords) {
        this.chars1 = chars1;
        this.chars2 = chars2;
        this.chars3 = chars3;
        this.chars4 = chars4;

        this.maxWords = maxWords;
        this.root = root;

        this.list.add(chars1);
        this.list.add(chars2);
        this.list.add(chars3);
        this.list.add(chars4);

        this.wordsList.add(words1);
        this.wordsList.add(words2);
        this.wordsList.add(words3);
        this.wordsList.add(words4);

        for (int i = 0; i < 4; i ++) {
            for (char c: this.list.get(i)) {
                allChar.add(c);
            }
        }
    }

    public ArrayList<ArrayList<String>> solve() {
        generateWords();
        ArrayList<ArrayList<String>> solutions = new ArrayList<>();
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 3; j ++) {
                solveBacktrack(solutions, new ArrayList<>(), this.list.get(i)[j], new HashSet<>(this.allChar));
            }
        }

        return solutions;
    }

    public void solveBacktrack(ArrayList<ArrayList<String>> solutions, ArrayList<String> currentSolution, char lastChar, HashSet<Character> notUsedCharacter) {
        if (currentSolution.size() > this.maxWords) {
            return;
        }

        if (notUsedCharacter.isEmpty()) {
            solutions.add(new ArrayList<>(currentSolution));
            System.out.println(currentSolution);
            return;
        }

        // can prune
        int[] charsIndex = getCharsIndex(lastChar);
        for (String word: wordsList.get(charsIndex[0])[charsIndex[1]]) {
            if (usedWord.contains(word)) {
                continue;
            }
            ArrayList<String> updateSolution = new ArrayList<>(currentSolution);
            updateSolution.add(word);
            usedWord.add(word);
            solveBacktrack(solutions, updateSolution, word.charAt(word.length() - 1), getLeftCharacter(notUsedCharacter, word));
            usedWord.remove(word);
        }
    }

    public void generateWords() {
        for (int i = 0; i < 3; i ++) {
            makeWordBacktrack(words1[i], Character.toString(chars1[i]), 1);
        }
        for (int i = 0; i < 3; i ++) {
            makeWordBacktrack(words2[i], Character.toString(chars2[i]), 2);
        }
        for (int i = 0; i < 3; i ++) {
            makeWordBacktrack(words3[i], Character.toString(chars3[i]), 3);
        }
        for (int i = 0; i < 3; i ++) {
            makeWordBacktrack(words4[i], Character.toString(chars4[i]), 4);
        }
    }

    public void makeWordBacktrack(ArrayList<String> allWords, String currentWord, int currentChars) {
        Word word = new Word(currentWord);
        if (currentWord.length() >= 3 && word.isValidWord(this.root)) {
            allWords.add(currentWord);
        }

        if (word.isExtendable(this.root)) {
            for (int i = 0; i < 4; i ++) {
                if (i + 1 == currentChars) {
                    continue;
                }

                char[] tempChars = this.list.get(i);
                for (int j = 0; j < 3; j ++) {
                    makeWordBacktrack(allWords, currentWord + tempChars[j], i + 1);
                }
            }
        }

    }

    public HashSet<Character> getLeftCharacter(HashSet<Character> nonUsed, String word) {
        HashSet<Character> newNonUsed = new HashSet<>(nonUsed);
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            newNonUsed.remove(c);
        }

        return newNonUsed;
    }

    public int[] getCharsIndex(char c) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (list.get(i)[j] == c) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }
}
