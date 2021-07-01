import java.util.ArrayList;

public class Word {
    private String str;

    public Word(String str) {
        this.str = str;
    }

    public boolean isValidWord(WordTreeNode root) {
        ArrayList<Character> charList = makeCharList();
        WordTreeNode currentNode = root;

        while (!charList.isEmpty()) {
            if (currentNode.isLastNode()) {
                return false;
            }
            else {
                if (currentNode.hasChild(charList.get(0))) {
                    currentNode = currentNode.getChildNode(charList.get(0));
                    charList.remove(0);
                }
                else {
                    return false;
                }
            }
        }

        return currentNode.getIsEndOfWord();
    }

    public boolean isExtendable(WordTreeNode root) {
        ArrayList<Character> charList = makeCharList();
        WordTreeNode currentNode = root;

        while (!charList.isEmpty()) {
            if (currentNode.isLastNode()) {
                return false;
            }
            else {
                if (currentNode.hasChild(charList.get(0))) {
                    currentNode = currentNode.getChildNode(charList.get(0));
                    charList.remove(0);
                }
                else {
                    return false;
                }
            }
        }

        return !currentNode.isLastNode();
    }

    private ArrayList<Character> makeCharList() {
        ArrayList<Character> charList = new ArrayList<>();
        for (char c: this.str.toCharArray()) {
            charList.add(c);
        }

        return charList;
    }
}
