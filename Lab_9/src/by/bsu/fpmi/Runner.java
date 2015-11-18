package by.bsu.fpmi;
import javafx.scene.transform.NonInvertibleTransformException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileReader;

public class Runner {
    public static double[] methodOfGauss(int n, double [][]A, double [] b) {
        double[] d;
        d = b.clone();
        double[][] C = new double[n][];
        for (int i = 0; i < n; i++) {
            C[i] = A[i].clone();
        }

        for (int i = 0; i < n; i++) {
            if (C[i][i] == 0) {
                int j = 0;
                int k = 0;
                while (j < n && k < n) {
                    if (C[j][i] != 0 && C[i][k] != 0) {
                        break;
                    }
                    j++;
                    k++;
                }
                if (k < n && k > i) {
                        for (int m = 0; m < n; m++) {
                            double buffer = C[m][i];
                            C[m][i] = C[m][k];
                            C[m][k] = buffer;
                    }

                }
                else
                {
                    if (j < n && j > i) {
                        for (int m = 0; m < n; m++) {
                            double buffer = C[i][m];
                            C[i][m] = C[j][m];
                            C[j][m] = buffer;
                        }
                    }
                    else{
                        throw new UnsupportedOperationException();
                    }
                }
            }

            for (int k = i + 1; k < n; k++) {
                for (int j = i + 1; j < n; j++) {
                    C[k][j] = C[k][j] - (C[i][j] / C[i][i]) * C[k][i];
                }
                d[k] = d[k] - (d[i] / C[i][i]) * C[k][i];
            }
        }

        double[] X = d;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                X[i] -= C[i][j] * X[j];
            }
            X[i] /= C[i][i];
        }
        return X;
    }

    public static double[][] inverseMatrix(int n, double[][] A) {
        double[][] B = new double[n][];
        for (int i = 0; i < n; i++) {
            B[i] = new double[n];
        }

        double[][] C = new double[n][];
        for (int i = 0; i < n; i++) {
            C[i] = new double[n];
        }

        double[] e = new double[n];
        for (int i = 0; i < n; i++) {
            e[i] = 0;
        }
       for (int i = 0; i < n; i++) {
            e[i] = 1;
            B[i] = methodOfGauss(n, A, e);

            for (int j = 0; j < n; j++) {
                C[j][i] = B[i][j];
            }
            e[i] = 0;
        }
        return C;
    }

    public static void main(String[] args) {
        try {
            String fileName = "Data1.txt";
            FileReader file = new FileReader(fileName);
            Scanner scan = new Scanner(file);
            int n = scan.nextInt();

            double [][] A = new double[n][];
            for(int i = 0; i < n; i++) {
                A[i] = new double[n];
                for(int j = 0; j < n; j++) {
                    A[i][j] = scan.nextDouble();
                }
            }
            double [][] C = inverseMatrix(n, A);

            System.out.println("Inversed matrix: ");
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(C[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
        catch(InputMismatchException e) {
            System.out.println("InputMismatchException caught!");
        }
        catch(NoSuchElementException e) {
            System.out.println("NoSuchElementException caught!");
        }
        catch(IOException e) {
            System.out.println("IOException caught!");
        }
    }
}
