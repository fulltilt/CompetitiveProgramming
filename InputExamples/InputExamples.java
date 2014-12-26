import java.util.*;
import java.io.*;

class InputExamples {
  static String ReadLn(int maxLg) { // utility function to read from stdin
    byte lin[] = new byte[maxLg];
    int lg = 0, car = -1;
    String line = "";

    try {
      while (lg < maxLg) {
        car = System.in.read();
        if ((car < 0) || (car == '\n')) {
          break;
        }
        lin[lg++] += car;
      }
    } catch (IOException e) {
      return (null);
    }

    if ((car < 0) && (lg == 0)) { // eof
      return (null);
    }

    return (new String (lin, 0, lg));
  }

  public static void main (String args[]) { // entry point from OS
    InputExamples ex = new InputExamples();  // create a dynamic instance
    ex.numCasesInFirstLine();
  }


  // Usage: java InputExamples < numTestCasesInFirstLine.txt
  static void numCasesInFirstLine() {
    String input = InputExamples.ReadLn(255).trim(); // must trim the input as ReadLn also reads in the "\n"
    StringTokenizer iData;
    int numTestCases = Integer.parseInt(input),
        a, b;

    for (int i = 0; i < numTestCases; i++) {
      input = InputExamples.ReadLn(255);
      iData = new StringTokenizer(input);
      a = Integer.parseInt(iData.nextToken());
      b = Integer.parseInt(iData.nextToken());
      System.out.println(a + b);
    }
  }

  
}