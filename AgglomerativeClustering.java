//Agglomerative Clustering (Method 1)

import java.util.Scanner;
public class AgglomerativeClustering {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        int N = kbd.nextInt();
        double[][] data = new double[N][2];
        String[] names = new String[N];  
        for (int i = 0; i < N; i++) {
            names[i] = "p" + (i + 1);
            for (int j = 0; j < 2; j++) data[i][j] = kbd.nextDouble();
        }
        System.out.println("1.Single 2.Complete 3.Average");
        int choice = kbd.nextInt();
        double[][] d = new double[N][N];
        double INF = Double.POSITIVE_INFINITY;
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) 
                d[i][j] = (i == j) ? INF : dist(data[i], data[j]);
        for (int s = 0; s < N - 1; s++) {
            int i1 = -1, i2 = -1;
            double minDist = INF;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (d[i][j] < minDist) {
                        minDist = d[i][j];
                        i1 = i; i2 = j;
                    }
                }
            }
            if (i1 != -1 && i2 != -1) {
                System.out.println("Cluster " + names[i1] + " and " + names[i2]);
                for (int j = 0; j < N; j++) 
                    d[i1][j] = d[j][i1] = (choice == 1) ? Math.min(d[i1][j], d[i2][j]) :
                                         (choice == 2) ? Math.max(d[i1][j], d[i2][j]) : 
                                         (d[i1][j] + d[i2][j]) / 2;
                for (int j = 0; j < N; j++) 
                    d[i2][j] = d[j][i2] = INF;
            }
        }
        kbd.close();
    }
    static double dist(double[] a, double[] b) {
        return Math.hypot(b[0] - a[0], b[1] - a[1]);
    }
}
