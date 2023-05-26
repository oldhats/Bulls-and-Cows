package bullscows;
import java.util.*;



public class Main {
    public static void main(String[] args) {

        try {
            game();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: it's not possible to generate a code with " +
                    "a length of "  + variables.lengthOfCode + " with " + variables.lengthOfSymbols
                    + " unique symbols. ");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        } catch (InputMismatchException e) {
            System.out.println("Error: \"" + e.getMessage() + "\" is not a valid number.");
        }



    }

    static void game() {
        Scanner scanner = new Scanner(System.in);
        //initializing variables
        String secretCode = symbols();

        int bulls = 0, cows = 0, turn = 1;
        String userGuess;

        //turning strings into char arrays
        char [] secretCodeChar = secretCode.toCharArray();


        while (bulls != secretCode.length()) {
            cows = 0;
            bulls = 0;

            System.out.println("Turn " + turn + ":");
            userGuess = scanner.next();
            char [] userGuessChar = userGuess.toCharArray();

            //looping through user guess array to check if any of their numbers match
            //the secret code

            for (int i = 0; i <= secretCode.length() - 1; i++) {
                if ((new String(secretCodeChar).contains(String.valueOf(userGuessChar[i])))) {
                    cows++;
                }

                if (userGuessChar[i] == secretCodeChar[i]) {
                    bulls++;
                }
            }

            //subtract cows from bulls to get total amount of cows
            if (cows != 0) {
                cows = cows - bulls;
            } else {
                cows = 0;
            }

            if (bulls != secretCodeChar.length) {
                System.out.println("Grade: " + bulls + " bull(s) " + cows + " cow(s).");
            }
            turn++;


        }
        System.out.println("Grade: " + bulls + " bulls");
        System.out.println("Congratulations! You guessed the secret code.");


    }



    public class variables {
        protected static int lengthOfCode = 0;
        protected static int lengthOfSymbols = 0;
    }

    static String symbols(){
        //initializing variables
        Scanner scanner = new Scanner(System.in);

         variables.lengthOfCode = 0;
         variables.lengthOfSymbols = 0;

         System.out.println("Input the length of the secret code:");
        variables.lengthOfCode = scanner.nextInt();

            System.out.println("Input the number of possible symbols in the code:");
        variables.lengthOfSymbols = scanner.nextInt();

        if (variables.lengthOfCode == 0){
            throw new IllegalArgumentException("Error: length of code cannot be 0");
        }

        // Now create string of all possible symbols
        String set = "0123456789abcdefghijklmnopqrstuvwxyz";

        // Now convert string into array of string
        String[] strSplit = set.split("");
        String[] strSplit2 = set.split("");

        ArrayList<String> strList2 = new ArrayList<String>(
                Arrays.asList(strSplit));

        // Now convert string into ArrayList
        ArrayList<String> strList = new ArrayList<String>(
                Arrays.asList(strSplit));
        strList.subList(variables.lengthOfSymbols, strList.size()).clear();
        String[] secretCode = new String[variables.lengthOfCode];
        List<String> secretCodeList = new ArrayList<>();

        // Now shuffle the ArrayList
        for (int i = 0; i < variables.lengthOfCode; i++){
            Collections.shuffle(strList,new Random());
            secretCode[i] = strList.get(0);
            strList.remove(0);
        }

        // Now print the shuffled ArrayList
        if (variables.lengthOfSymbols < 10) {
            System.out.println("The secret is prepared: " + "*".repeat(variables.lengthOfCode) + " (0-" +
                    strList2.get(variables.lengthOfSymbols - 1) + ")");
            System.out.println("Okay, let's start a game!");
        } else {
            System.out.println("The secret is prepared: " + "*".repeat(variables.lengthOfCode) + " (0-9, a-" +
                    strList2.get(variables.lengthOfSymbols - 1) + ")");
            System.out.println("Okay, let's start a game!");
        }

        //converting array to string
        StringBuilder builder = new StringBuilder();
        for (String value : secretCode) {
            builder.append(value);
        }

        return builder.toString();



    }


}





