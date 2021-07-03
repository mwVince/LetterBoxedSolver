import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Driver {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        WordTreeNode root = new WordTreeNode();
        WordTreeMaker treeMaker = new WordTreeMaker(root);

        while (true) {
            int wordListNumber;
            System.out.print("Which word list to used (1. Full Dictionary | 2. Most Frequent 10,000 Used Words): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input, please enter 1 or 2");
                scanner.next();
            }
            else {
                wordListNumber = scanner.nextInt();
                if (wordListNumber != 1 && wordListNumber != 2) {
                    System.out.println("Invalid input, please enter 1 or 2");
                }
                else {
                    treeMaker.makeTree(wordListNumber);
                    break;
                }
            }
        }

        char[][] chars = makePuzzle();

        int maxWords;
        while (true) {
            int max;
            System.out.print("\nMax number of words: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input, please enter a positive integer");
                scanner.next();
            }
            else {
                max = scanner.nextInt();
                if (max <= 0) {
                    System.out.println("Invalid input, please enter a positive integer");
                }
                else {
                    maxWords = max;
                    break;
                }
            }
        }

        PuzzleSolver puzzleSolver = new PuzzleSolver(chars[0], chars[1], chars[2], chars[3], root, maxWords);
        ArrayList<ArrayList<String>> solutions = puzzleSolver.solve();
        System.out.println("\nFinished " + solutions.size() + " solutions found");
    }

    static char[][] makePuzzle() {
        HashSet<Character> puzzleChars = new HashSet<>();

        System.out.println("\nEnter puzzle in form of xyz");

        String chars;
        do {
            System.out.print("First chars: ");
            chars = scanner.nextLine();
        } while (!isInputCharsValid(puzzleChars, chars));
        char[] chars1 = makeChars(chars);

        chars = "";
        do {
            System.out.print("Second chars: ");
            chars = scanner.nextLine();
        } while (!isInputCharsValid(puzzleChars, chars));
        char[] chars2 = makeChars(chars);

        chars = "";
        do {
            System.out.print("Third chars: ");
            chars = scanner.nextLine();
        } while (!isInputCharsValid(puzzleChars, chars));
        char[] chars3 = makeChars(chars);

        chars = "";
        do {
            System.out.print("Forth chars: ");
            chars = scanner.nextLine();
        } while (!isInputCharsValid(puzzleChars, chars));
        char[] chars4 = makeChars(chars);

        return new char[][]{chars1, chars2, chars3, chars4};
    }

    static boolean isInputCharsValid(HashSet<Character> puzzleChars, String inputChars) {
        HashSet<Character> temp = new HashSet<>();

        if (inputChars.length() != 3) {
            System.out.println("Please enter 3 characters");
            return false;
        }

        for (char c: inputChars.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                System.out.println(c + " is not an alphabet, please enter only alphabetic characters");
                return false;
            }
            else if (puzzleChars.contains(c) || temp.contains(c)) {
                System.out.println(c + " already in the puzzle, please enter non repeating characters");
                return false;
            }
            else {
                temp.add(c);
            }
        }

        for (char c: temp) {
            puzzleChars.add(c);
        }
        return true;
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
