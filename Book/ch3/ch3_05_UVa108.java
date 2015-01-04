import java.io.*;
import java.util.*;

// NOTE: algorithm varies from book
class ch3_05_UVa108 { /* Maximum Sum, 0.528s in UVa (C++ version runs in 0.008s) */
  public static void main(String[] args) throws Exception {
    File f = new File("input2.txt");
    Scanner sc = new Scanner(f);
    int i, j, l, r, row, n, maxSubRect, subRect;
    int[][] A = new int[101][101];

    // initialize 2D array from file but preprocess each row as we go along
    n = sc.nextInt();
    for (i = 0; i < n; i++) {
      for (j = 0; j < n; j++) {
        A[i][j] = sc.nextInt();
        if (j > 0) A[i][j] += A[i][j - 1]; // pre-processing
      }
    }
/*
0 -2 -9 -9 
9 11 5 7 
-4 -3 -7 -6 
-1 7 7 5
*/

    maxSubRect = -127 * 100 * 100;    // the lowest possible value for this problem
    for (l = 0; l < n; l++) {
      for (r = l; r < n; r++) {
        subRect = 0;

        for (row = 0; row < n; row++) {
          // Max 1D Range Sum on columns of this row
          if (l > 0) {
            subRect += A[row][r] - A[row][l - 1];
          } else { 
            subRect += A[row][r];
          }

          // Kadane's algorithm on rows
          if (subRect < 0) {
            subRect = 0;
          }
          System.out.println(subRect);
          maxSubRect = Math.max(maxSubRect, subRect);
        } 
      }System.out.println("$");
    }

    System.out.printf("%d\n", maxSubRect);
  } 
}
