package aihw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

//Program tests travelling salesman solver
public class AntTester
{
    /**
     * @throws IOException ******************************************************************/

    //Main method
    public static void main(String[] args) throws IOException
    {
    	
    	  Scanner scan = new Scanner(System.in);  // allows user to choose the name of the output file
    	 
    	    System.out.println("Enter the name of input file: ");	
    	    String inputName = scan.nextLine();//read user input
      	    System.out.println("Enter the name of output file: ");
      		
      	    String outputName = scan.nextLine();//read user input
  	    
  	    
  	    
         //create adjacency matrix
         int size = 20;
         int seed = 54367;
         String seedString = Integer.toString(seed);
        
     	int cities =0;
    	int roads = 0;
    	
    
    	 File inputFile = new File(inputName);
         Scanner fileScanner = new Scanner(inputFile);
        cities =  fileScanner.nextInt();
       roads =  fileScanner.nextInt();
       int[][] matrix = new int[cities][cities];
       
       //assigns weights to each set of cities
       for (int i = 0; i < roads; i++) {
           int city1 = fileScanner.nextInt() - 1;
           int city2 = fileScanner.nextInt() - 1;
           int weight = fileScanner.nextInt();
           matrix[city1][city2] = matrix[city2][city1] = weight;
       }
       
         fileScanner.close();
         

         //display adjacency matrix
         displayMatrix(matrix, cities, outputName);

         //create travelling salesman solver
         Ant a = new Ant(matrix, cities);

         //set parameters
         int iterations = 10000;
         double chemicalExponent = .6;
         double distanceExponent = .9;
         double initialDeposit = .8;
         double depositAmount = .1;
         double decayRate = .3;
         a.setParameters(iterations, chemicalExponent, distanceExponent, 
         initialDeposit, depositAmount, decayRate, seed );

         //find optimal solution
       
         a.solve(outputName, seedString );
         
       
    }

    
   

    /**
     * @throws FileNotFoundException ********************************************************************/

    //method prints an adjacency matrix
    public static void displayMatrix(int[][] matrix, int size, String fileName) throws FileNotFoundException
    {
         for (int i = 0; i < size; i++)
         {
             for (int j = 0; j < size; j++)
                 System.out.print(matrix[i][j] + " ");
             System.out.println();
         }
         
    }
    

    /***********************************************************************/
}