import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Class Robot.
 */
public class Robot {

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        int mode = Integer.parseInt(args[0]);

        switch (mode) {

        case 0:
            String dFAFilename = args[1];
            File dFAFile = new File(dFAFilename);

            Scanner dFAScanner = null;
            try {
                dFAScanner = new Scanner(dFAFile);
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("FSA file does not exist");
            }

            String line = dFAScanner.nextLine();
            String num = "";
            int states = 0;
            int edges = 0;
            int transitions = 0;
            int count = 0;
            for (int i = 0; i < line.length(); i++) {
                if (!(line.charAt(i) + "").equals(" ")) {
                    num = num + line.charAt(i);
                } else if (count == 0) {
                    states = Integer.parseInt(num);
                    num = "";
                    count++;
                } else if (count == 1) {
                    edges = Integer.parseInt(num);
                    num = "";
                    count++;
                }
                if (i == line.length() - 1) {
                    transitions = Integer.parseInt(num);
                    num = "";
                    count++;
                }
            }
            String[][] dfa = new String[states][states];
            while (dFAScanner.hasNextLine()) {
                line = dFAScanner.nextLine();
                int startState = alphaToInt(line.substring(0, 1));
                int endState = alphaToInt(line.substring(4, 5));
                String edge = line.substring(2, 3);
                dfa[startState][endState] = edge;
            }
            for (int i = 0; i < states; i++) {
                for (int j = 0; j < states; j++) {
                    if (dfa[i][j] == null) {
                        dfa[i][j] = "  ";
                    }
                }
            }

            dFAScanner.close();
            String[][] dfaCopy = new String[states][states];
            for (int i = 0; i < states; i++) {
                for (int j = 0; j < states; j++) {
                    if (!(dfa[i][j].equals("  "))) {
                        dfaCopy[i][j] = " " + dfa[i][j];
                    } else {
                        dfaCopy[i][j] = dfa[i][j];
                    }
                }
            }

            for (int i = 0; i < states; i++) {
                for (int j = 0; j < states; j++) {
                    if (dfa[i][j].equals("  ") && i != j) {
                        dfaCopy[i][j] = Dijkstras.firstEdgeToSP(i, j, dfa);
                    }
                }
            }

            for (int i = 0; i < states; i++) {
                for (int j = 0; j < states; j++) {
                    if (i == states - 1 && j == states - 1) {
                        StdOut.print("");
                    } else if (j > 0) {
                        StdOut.print(" " + dfaCopy[i][j]);
                    } else {
                        StdOut.print(dfaCopy[i][j]);
                    }
                }
                if (i < states - 1) {
                    StdOut.println();
                }
            }

            break;

        case 1:
            String mazeFilename = args[1];
            File mazeFile = new File(mazeFilename);

            Scanner mazeScanner = null;
            try {
                mazeScanner = new Scanner(mazeFile);
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("DFA file does not exist");
            }

            line = mazeScanner.nextLine();
            int rows = 0;
            int columns = 0;

            String[] lineSplit = line.split(" ");
            rows = Integer.parseInt(lineSplit[0]);
            columns = Integer.parseInt(lineSplit[1]);
            int maveStates = rows * columns;

            String[][] mDFA = new String[maveStates][maveStates];
            // horizontal edges, doesnt work yet
            for (int i = 0; i < rows; i++) {
                line = mazeScanner.nextLine();
                line = mazeScanner.nextLine();
                //StdOut.println(line);
                for (int j = 0; j < columns; j++) {
                    StdOut.println(line.charAt((j+1)*4));
                    if (!(line.charAt((j+1)*4) + "").equals("|")) {
                        mDFA[i][j] = "b";
                        break;
                    }
                }
            }
            // vertical edges
            mazeScanner = null;
            try {
                mazeScanner = new Scanner(mazeFile);
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("maze file does not exist");
            }
            
            line = mazeScanner.nextLine();
            line = mazeScanner.nextLine();
            line = mazeScanner.nextLine();
            line = mazeScanner.nextLine();
            
            for (int i = 0; i < columns; i++) {
                
                for (int j = 0; j < rows; j++) {
                    
                    
                    
                }
            }
            
            
            
            
            
            

            for (int i = 0; i < maveStates; i++) {
                for (int j = 0; j < maveStates; j++) {
                    if (mDFA[i][j] == null) {
                        StdOut.print("  ");
                    } else {
                        StdOut.print(" " + mDFA[i][j]);
                    }
                }
                StdOut.println();
            }

            break;
        case 2:

            break;
        case 3:

            break;
        case 4:

            break;

        default:
            break;
        }
    }

    /**
     * Alpha to int.
     *
     * @param letter
     *            the letter
     * @return the int
     */
    public static int alphaToInt(String letter) {
        switch (letter) {
        case "A":
            return 0;
        case "B":
            return 1;
        case "C":
            return 2;
        case "D":
            return 3;
        case "E":
            return 4;
        case "F":
            return 5;
        case "G":
            return 6;
        case "H":
            return 7;
        case "I":
            return 8;
        case "J":
            return 9;
        case "K":
            return 10;
        case "L":
            return 11;
        case "M":
            return 12;
        case "N":
            return 13;
        case "O":
            return 14;
        case "P":
            return 15;
        case "Q":
            return 16;
        case "R":
            return 17;
        case "S":
            return 18;
        case "T":
            return 19;
        case "U":
            return 20;
        case "V":
            return 21;
        case "W":
            return 22;
        case "X":
            return 23;
        case "Y":
            return 24;
        case "Z":
            return 25;

        default:
            return -1;
        }
    }
}
