//Javier Ramirez
//10-14-2025
//CPSC-39 - Scrabble game with binary search and OOD
//Added points and a while loop to continue gameplay


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class ScrabbleGame {


    //Data structure to hold the dictionary
    private static List<Word> dictionary = new ArrayList<Word>();


    public static void main(String[] args) {


        //Read text file of words and defs into the ArrayList
        //  (Try catch ensures our program does not implode
        //  if the file is not found)
        Scanner input = null;
        try {
            input = new Scanner(new File("ScrabbleWordList.txt"));
            while(input.hasNextLine()) {
                String word = input.next();
                //Trim removes all whitespace
                String definition = input.nextLine().trim();
                dictionary.add(new Word(word, definition));
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("error");
            e.printStackTrace();
        }


        //Tracks if the program should keep running
        boolean play = true;
        Scanner scnr = new Scanner(System.in);
        int totalPoints = 0;


        //The program runs so long as play is true
        while (play) {
            System.out.println("Your four letters:");


            //Use Random to generate a random letter, using ASCII codes (A is 65, Z is 90)
            Random random = new Random();
            for (int i = 0; i < 4; i++) {
                int randomInt = random.nextInt(90 - 65 + 1) + 65;
                char randomChar = (char) randomInt;
                System.out.print(randomChar + " ");
            }


            System.out.println();
            System.out.println("Enter a word using the four letters:");


            //Get the user's word
            String userWord = scnr.next();
            //Grab following whitespace and discard it
            scnr.nextLine();
            System.out.println("Your word: " + userWord);


            //Create new word with the user's word and no definition
            Word word1 = new Word(userWord, "N/A");


            //Call binary search with the word list and the user's word
            boolean found = BinarySearch(dictionary, word1);


            //If the word is found, print valid word statement and their points
            //Also increment total points
            if (found) {
                System.out.println("Valid word - congrats!");
                int points = word1.getPoints(userWord);


                if (points > 1) {
                    System.out.println("You earned " + points + " points!");
                }
                else {
                    System.out.println("You earned " + points + " point!");
                }
                totalPoints += points;
            }
            //If the word is not found, print invalid word statement
            else {
                System.out.println("Invalid word - sorry!");
            }


            //Ask user if they want to keep playing, and read a string response
            System.out.println("Would you like to keep playing? Enter y or n.");
            String userReply = scnr.nextLine();
           
            //If user enters y, continue playing
            if (userReply.equals("y")) {
                play = true;
            }
            //If user enters n, while loop ends
            else if (userReply.equals("n")) {
                play = false;
            }


            //scnr.close();


        }//end while


        //Print out the user's total points
        System.out.println("You earned " + totalPoints + " total points!");


        scnr.close();


    }//end main


    //Binary search method - returns true if the user word is found in the
    //dictionary, false if not
    public static boolean BinarySearch(List<Word> dictionary, Word key) {


        int low = 0;
        int high = dictionary.size() - 1;


        while (high >= low) {
            int mid = (low + high) / 2;


            //Current middle word


            Word midWord = dictionary.get(mid);


            //Compare user word to current middle word
            int wordCompare = key.compareTo(midWord);


            //If user word is alphabetically smaller than the
            //middle word, then search lower half of array
            if(wordCompare < 0) {
                high = mid - 1;
            }
            //If user word is alphabetically larger than the
            //middle word, then search upper half of array
            else if(wordCompare > 0) {
                low = mid + 1;
            }
            //If user word matches the current middle word in
            //the dictionary, then the word is found
            else if(wordCompare == 0) {
                return true;
            }
        }


        //Word not found
        return false;
    }


}
