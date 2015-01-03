import java.io.*;
import java.util.*;

class ch3_02_UVa11450_td { /* UVa 11450 - Wedding Shopping - Top Down */
  private static int M; // budget 
  private static int C; // # of garments
  private static int K; // # of models for a garment

  private static int[][] price = new int[25][25]; // price[g (<= 20)][model (<= 20)]
  private static int[][] memo = new int[210][25]; // dp table memo[money (<= 200)][g (<= 20)]

  private static int shop(int money, int g) {
    if (money < 0)            return -1000000000;     // fail, return a large negative number (1B)
    if (g == C)               return M - money;       // we have bought last garment, done
    if (memo[money][g] != -1) return memo[money][g];  // this state has been visited before (if this line was commented, top-down DP will become backtracking)

/* price[][] (note: the first column represents K)
3 6 4  8 0 
2 5 10 0 0 
4 1 5  3 5 
*/
    int ans = -1000000000;
    for (int model = 1; model <= price[g][0]; model++) {  // try all possible models
      ans = Math.max(ans, shop(money - price[g][model], g + 1));
    }

    return memo[money][g] = ans; // assign ans to dp table + return it!
  }

  public static void main(String[] args) throws Exception { // easy to code if you are already familiar with it
    File f = new File("input.txt");
    Scanner sc = new Scanner(f);
    int i, j, TC, // don't know what 'TC' is. In the input file, I just set this to '1' to ensure it entered the loop just once
        score;

    TC = sc.nextInt();
    while (TC-- > 0) {
      M = sc.nextInt();   // budget
      C = sc.nextInt();   // total # of garments

      // initialize price table
      for (i = 0; i < C; i++) {
        K = sc.nextInt(); // # of models of current garment
        price[i][0] = K; // to simplify coding, we store K in price[i][0]
        for (j = 1; j <= K; j++)
          price[i][j] = sc.nextInt();
      }
/* price[][]
3 6 4  8 0 
2 5 10 0 0 
4 1 5  3 5 
*/
      // initialize DP memo table to all -1's
      for (i = 0; i < 210; i++)
        for (j = 0; j < 25; j++)
          memo[i][j] = -1;

      score = shop(M, 0); // start the top-down DP (passing in starting budget and first garment)
      if (score < 0) System.out.printf("no solution\n");
      else           System.out.printf("%d\n", score);
    }
  }
}
