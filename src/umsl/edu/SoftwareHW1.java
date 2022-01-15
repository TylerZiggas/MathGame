/* 
Language to Use: JAVA
Author: Tyler Ziggas
Date: August 28, 2021
Class: Intro to Software Profession 4500
Expalnation: This program takes a number from 1 to 3 for whichever option they wish from the menu presented.  It then checks it accordingly to make sure you input
    something that is an option.  For option 1 it will ask for another input of a number higher than 1 to start the process, option 2 it will ask for a low number and a high number 
    (where it will get the low number that is higher than 1 and then ask for a number higher than the one previously stated), and option 3 will exit the program.
    Once it has the numbers and the user wants to continue, it will either divide it by 2 when it is even or multiply it by 3 and add 1 if it is odd; and will
    repeat the process until the number has reached 1 as it then becomes an endless loop.
Central Data Structures: The central data structures this program uses are based around arrays in a certain scenario that the user can pick.  When asking for a 
    range of numbers, the program stores those numbers into an array while also storing the number of steps each one took to get 1.  This is necessary for figuring out
    which numbers actually took the least or most amount of steps.
External Files: None
*/

package umsl.edu;

import java.util.Scanner;
import java.util.InputMismatchException;

public class SoftwareHW1 {
    
    /*
        main - Main acts as our input validation for our option while also sending that input to the
            correct place, it looks infinitely unless the user chooses the third option and exists 
            the program.
    */
    
    public static void main(String[] args) {
        menu();
        
        do {
            try {
                Scanner scan = new Scanner(System.in);
                int inputNumber = scan.nextInt();

                if (inputNumber == 1) { // Our case for one number being used
                    int firstInput = firstNumber();
                    equationGame(firstInput);
                    System.out.println("Press ENTER to continue...");
                    scan.nextLine();
                    scan.nextLine();
                    
                    menu();
                    
                } else if (inputNumber == 2) { // Our case for a range of numbers being used
                    int firstInput = firstNumber();
                    int secondInput = secondNumber(firstInput);
                    setupEquationGame(firstInput, secondInput);
                    System.out.println("Press ENTER to continue...");
                    scan.nextLine();
                    scan.nextLine();
                    
                    menu();
                    
                } else if (inputNumber == 3) { // Our case for exiting the program
                    System.out.print("Goodbye! \n\n");
                    scan.close();
                    break;
                    
                } else { // In case a different number was entered that is not an option 
                    System.out.print("That is not an option! \n");
                    
                }
            } catch (InputMismatchException ex) { // Our try catch block in case a different input is used
                System.out.print("Invalid input! \n"); 
                
            }
        } while (true);  
        
    }
    
    /*
        menu - Simple void function that prints out an explanation of the project and 
            prints the options that the user can choose between.
    */
    
    static void menu() {
        System.out.print("\nWe will be exploring the 3x + 1 problem, we will be taking a number input after your selection. \n");
        System.out.print("If the number is even it will be divided by 2, if it is odd, it will multiply it by 3 and then add 1, the process ends when the end result is 1. \n");
        System.out.print("Press the number for which option you would like to select: \n");
        System.out.print("1) Designate a number to start this process \n");
        System.out.print("2) Designate a range of numbers to use with the process \n");
        System.out.print("3) Quit the program \n");
    }
    
    /*
        firstNumber - Simple call to make sure the number is above 0, it does not matter
            otherwise as long as it is a number.
    */
    
    static int firstNumber() {
        boolean retry;
        int firstNumber = 0;
        
        do {
            try {
                System.out.print("Enter a number larger than 0: \n");
                retry = false;
                Scanner scan = new Scanner(System.in);
                firstNumber = scan.nextInt();

                if (firstNumber > 0) { // Need to make sure our number is larger than 0, as it needs to be a positive integer
                    
                } else {
                    System.out.print("Please pick a number larger than 0! \n");
                    retry = true;
                }
            } catch (InputMismatchException ex) {
                System.out.print("Invalid input! \n"); 
                retry = true;
            }
        } while (retry); 
        
        return firstNumber;
    }
    
    /*
        secondNumber - Getting the second number for our range of numbers, it is important to check
            what is being input as it has to be larger than the first number.
    */
    
    static int secondNumber(int firstNumber) {
        boolean retry;
        int secondNumber = 0;
        System.out.print("\n");
        
        do {
            try {
                System.out.print("Enter a number larger than " + firstNumber + ": \n");
                retry = false;
                Scanner scan = new Scanner(System.in);
                secondNumber = scan.nextInt();

                if (secondNumber > firstNumber) { // Need to make sure our second number is larger than our first
                    
                } else {
                    System.out.print("Please pick a number larger than the first one! \n");
                    retry = true;
                }
            } catch (InputMismatchException ex) {
                System.out.print("Invalid input! \n"); 
                retry = true;
            }
        } while (retry); 
        
        return secondNumber;
    }
    
    /*
        setupEquationGame - Creating our arrays and range of numbers this equation game will go on for.
            The arrays will be used for the statistics while the the for loop sets up our arrays as they happen.
    */
    
    static void setupEquationGame(int lowNumber, int highNumber) {
        int arraySizeCounter = 0;
        int arraySize = highNumber - lowNumber + 1; // Creation of our array size by simply subtracting the low number from the high number and adding 1
        int[] arrayNumbers = new int[arraySize]; // Declare our arrays
        int[] arraySteps = new int[arraySize];
        
        for(int counter = lowNumber; counter <= highNumber; counter++) { // For loop, will fill our arrays while doing the calls for the equations to start
            arrayNumbers[arraySizeCounter] = counter;
            arraySteps[arraySizeCounter] = equationGame(counter);
            arraySizeCounter++;
            
        }
        
        findMinMaxAverage(arrayNumbers, arraySteps, arraySize); // Finish up by finding our statistics
    }
    
    /*
        equationGame - Uses the math equations provided depending on whether or not the number received was
            even or odd; and repeating that process with what comes after that until 1
    */
    
    static int equationGame(int number) {
        int counter = 0;
        System.out.print("\n");
        
        do {
            if (number % 2 == 0) {
                System.out.print(number + " is even!  Dividing by 2...\n");
                number /= 2;
                counter++;
            } else {
                System.out.print(number + " is odd!  Multiplying by 3 and then adding 1...\n");
                number = (number * 3) + 1;
                counter++;
            }
        } while (number != 1);
        
        System.out.print("We have reached 1, it took " + counter + " steps! \n");
        return counter;
    }
    
    /*
        findMinMaxAverage - Calculates our minimum, maximum, and average (our statistics) 
            for the range of numbers that was entered for option 2
    */
    
    static void findMinMaxAverage(int[] arrayNumbers, int[] arraySteps, int arraySize) {
        int minimumSteps = arraySteps[0];
        int minimumNumber = arrayNumbers[0];
        int maximumSteps = arraySteps[0];
        int maximumNumber = arrayNumbers[0];
        double averageSteps = 0;
        
        
        for (int counter = 0; counter < arraySteps.length; counter++) { // Use a single for loop for checking maximum, minimum, and averaging
            if (arraySteps[counter] < minimumSteps) {
                minimumSteps = arraySteps[counter];
                minimumNumber = arrayNumbers[counter];
            }
            
            if (arraySteps[counter] > maximumSteps) {
                maximumSteps = arraySteps[counter];
                maximumNumber = arrayNumbers[counter];
            }
            
            averageSteps = averageSteps + arraySteps[counter];
        }
        
        averageSteps /= arraySize; // Need to divide the average once everything is completed
        
        System.out.println("\nOur statistics for this range of numbers: ");
        System.out.println("Number Used: " + minimumNumber + "\tMinimum Steps: " + minimumSteps);
        System.out.println("Number Used: " + maximumNumber + "\tMaximum Steps: " + maximumSteps);
        System.out.println("Average: " + averageSteps);
    }
}
