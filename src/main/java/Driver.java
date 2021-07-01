import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        WordTreeNode root = new WordTreeNode();
        WordTreeMaker treeMaker = new WordTreeMaker(root);
        System.out.print("Which word list to used (1. Full Dictionary | 2. Most Frequent 10,000 Used Words): ");
        treeMaker.makeTree(Integer.parseInt(scanner.nextLine()));

        char[][] chars = makePuzzle();
        System.out.print("Max number of words: ");
        int maxWords = scanner.nextInt();
        PuzzleSolver puzzleSolver = new PuzzleSolver(chars[0], chars[1], chars[2], chars[3], root, maxWords);

        ArrayList<ArrayList<String>> solutions = puzzleSolver.solve();

        System.out.println("Finished " + solutions.size() + " solutions found");
    }

    static char[][] makePuzzle() {
        System.out.println("Enter puzzle in form of xyz");
        System.out.print("First chars: ");
        char[] chars1 = makeChars(scanner.nextLine());
        System.out.print("Second chars: ");
        char[] chars2 = makeChars(scanner.nextLine());;
        System.out.print("Third chars: ");
        char[] chars3 = makeChars(scanner.nextLine());
        System.out.print("Forth chars: ");
        char[] chars4 = makeChars(scanner.nextLine());

        return new char[][]{chars1, chars2, chars3, chars4};
    }

    static char[] makeChars(String str) {
        char[] res = new char[3];
        for (int i = 0; i < 3; i ++) {
            res[i] = str.charAt(i);
        }
        return res;
    }

    static void printSolutions(ArrayList<ArrayList<String>> solutions) {
        for (ArrayList<String> solution: solutions) {
            System.out.println(solution);
        }
    }
}
