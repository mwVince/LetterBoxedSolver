import java.io.*;

public class WordTreeMaker {
    private WordTreeNode root;

    public WordTreeMaker(WordTreeNode root) {
        this.root = root;
    }

    public void makeTree(int choice) {
        try {
            InputStream inputStream = null;
            if (choice == 1) {
                inputStream = this.getClass().getResourceAsStream("/words_alpha.txt");
            }
            else {
                this.getClass().getResourceAsStream("/google-10000-english.txt");
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while (bufferedReader.ready()) {
                String currentWord = bufferedReader.readLine();
                addWord(currentWord);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWord(String word) {
        WordTreeNode currentNode = this.root;
        for (int i = 0; i < word.length(); i ++) {
            currentNode.addChild(word.charAt(i));
            currentNode = currentNode.getChildNode(word.charAt(i));
        }

        currentNode.setEndOfWord();
    }
}
