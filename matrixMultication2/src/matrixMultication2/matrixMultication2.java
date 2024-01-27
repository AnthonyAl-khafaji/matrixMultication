package matrixMultication2;

import java.io.*;
import java.util.Scanner;

public class matrixMultication2 {

    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);

        // Check if command line arguments are provided
        if (args.length == 2) {
            // Case 1: Read matrices from provided files
            String file1 = args[0];
            String file2 = args[1];
            int[][] matrix1 = readMatrixFromFile(file1);
            int[][] matrix2 = readMatrixFromFile(file2);

            // Check if matrix multiplication is possible
            if (matrix1[0].length != matrix2.length) {
                System.out.println("Matrix multiplication is not possible with the given matrices.");
                return;
            }

            // Perform matrix multiplication
            int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);

            // Save the result to a file
            writeMatrixToFile(resultMatrix, "matrix3.txt");
        } else if (args.length == 1) {
            // Case 2: Generate random matrices
            int size = Integer.parseInt(args[0]);
            int[][] matrix1 = generateRandomMatrix(size);
            int[][] matrix2 = generateRandomMatrix(size);

            // Perform matrix multiplication
            int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);

            // Save the matrices to files
            writeMatrixToFile(matrix1, "matrix1.txt");
            writeMatrixToFile(matrix2, "matrix2.txt");

            // Save the result to a file
            writeMatrixToFile(resultMatrix, "matrix3.txt");
        } else {
            System.out.println("Invalid number of arguments. Please provide two file names or an integer.");
        }
    }

    // Function to read a matrix from a file
    private static int[][] readMatrixFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            int[][] matrix = new int[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }

            return matrix;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return null;
        }
    }

    // Function to write a matrix to a file
    private static void writeMatrixToFile(int[][] matrix, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(matrix.length + " " + matrix[0].length);
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    writer.print(matrix[i][j] + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }

    // Function to generate a random square matrix
    private static int[][] generateRandomMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int) (Math.random() * 10); // Adjust the range as needed
            }
        }
        return matrix;
    }

    // Function to perform matrix multiplication
    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int columns2 = matrix2[0].length;

        int[][] resultMatrix = new int[rows1][columns2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                for (int k = 0; k < columns1; k++) {
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return resultMatrix;
    }
}
