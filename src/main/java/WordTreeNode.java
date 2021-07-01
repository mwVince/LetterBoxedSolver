public class WordTreeNode {
    private boolean isEndOfWord = false;
    private WordTreeNode[] children = new WordTreeNode[26];

    public WordTreeNode() {

    }

    public void setEndOfWord() {
        this.isEndOfWord = true;
    }

    public boolean getIsEndOfWord() {
        return this.isEndOfWord;
    }

    public boolean isLastNode() {
        for (int i = 0; i < 26; i ++) {
            if (children[i] != null) {
                return false;
            }
        }

        return true;
    }

    public boolean hasChild(char c) {
        return this.children[Character.valueOf(c) - 97] != null;
    }

    public WordTreeNode getChildNode(char c) {
        return this.children[Character.valueOf(c) - 97];
    }

    public void addChild(char c) {
        if (!hasChild(c)) {
            this.children[Character.valueOf(c) - 97] = new WordTreeNode();
        }
    }
}
