//Agglomerative Clustering (Method 2)

import java.util.Scanner;

public class AggloClustering {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        System.out.print("Enter number of points: ");
        int N = kbd.nextInt(), M = 2;
        double[][] data = new double[N][M];
        String[] names = new String[N];

        // Read points and store coordinates
        for (int i = 0; i < N; i++) {
            names[i] = "p" + (i + 1);
            System.out.print("Enter x and y for " + names[i] + ": ");
            data[i][0] = kbd.nextDouble(); // X coordinate
            data[i][1] = kbd.nextDouble(); // Y coordinate
            System.out.println(names[i] + ": X = " + data[i][0] + " Y = " + data[i][1]);
        }

        System.out.println("Enter Choice : 1.Single 2.Complete 3.Average");
        int choice = kbd.nextInt();
        double[][] d = new double[N][N];
        double INF = Double.POSITIVE_INFINITY;

        // Initialize distance matrix
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) d[i][j] = i == j ? INF : dist(data[i], data[j]);

        // Clustering process
        for (int s = 0; s < N - 1; s++) {
            int i1 = 0, i2 = 1; // Start with the first two clusters

            // Find the closest pair of clusters
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (d[i][j] < d[i1][i2]) {
                        i1 = i;
                        i2 = j;
                    }
                }
            }

            // Print the clusters being merged
            System.out.println("Cluster " + names[i1] + " and " + names[i2]);

            // Update distances based on the chosen method
            if (choice == 1) { // Single Linkage
                for (int j = 0; j < N; j++) {
                    d[i1][j] = Math.min(d[i1][j], d[i2][j]);
                    d[j][i1] = d[i1][j]; // Symmetric
                }
            } else if (choice == 2) { // Complete Linkage
                for (int j = 0; j < N; j++) {
                    d[i1][j] = Math.max(d[i1][j], d[i2][j]);
                    d[j][i1] = d[i1][j]; // Symmetric
                }
            } else if (choice == 3) { // Average Linkage
                for (int j = 0; j < N; j++) {
                    d[i1][j] = (d[i1][j] + d[i2][j]) / 2;
                    d[j][i1] = d[i1][j]; // Symmetric
                }
            }

            // Mark the merged cluster as INF
            for (int j = 0; j < N; j++) {
                d[i2][j] = INF;
                d[j][i2] = INF;
            }
        }
        kbd.close();
    }

    static double dist(double[] a, double[] b) {
        return Math.sqrt(Math.pow(b[0] - a[0], 2) + Math.pow(b[1] - a[1], 2));
    }
}
