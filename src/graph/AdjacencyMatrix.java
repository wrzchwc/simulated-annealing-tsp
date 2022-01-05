package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdjacencyMatrix {
    private int[][] matrix;
    private int size;

    public AdjacencyMatrix(String filepath) {
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            this.size = Integer.parseInt(scanner.nextLine());
            this.matrix = new int[this.size][this.size];
            List<String[]> lines = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                lines.add(scanner.nextLine().trim().split(" +"));
            }

            scanner.close();
            initializeMatrix(lines);
        } catch (FileNotFoundException exception) {
            System.out.println("File named " + filepath + " not found!");
            this.size = 0;
            this.matrix = new int[0][0];
        }
    }

    private void initializeMatrix(List<String[]> lines) {
        int row = 0;
        for (String[] line : lines) {
            for (int i = 0; i < line.length; i++) {
                matrix[row][i] = Integer.parseInt(line[i]);
            }
            row++;
        }
    }

    public int getSize() {
        return size;
    }

    public int getData(int row, int column) {
        return matrix[row][column];
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
