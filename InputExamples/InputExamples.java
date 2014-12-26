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

  public static void main(String args[]) { // entry point from OS
    InputExamples ex = new InputExamples();  // create a dynamic instance
    //ex.numCasesInFirstLine();
    //ex.stopAtAllZeroes();
    //ex.endOfFile();
    //ex.displayCaseNum();
    ex.variableLineInput();
  }

  // Usage: java InputExamples < eof.txt
  static void endOfFile() {
    String input;
    StringTokenizer iData;
    int a, b;

    while ((input = InputExamples.ReadLn(255)) != null) {
      iData = new StringTokenizer(input);
      a = Integer.parseInt(iData.nextToken());
      b = Integer.parseInt(iData.nextToken());
      System.out.println(a + b);
    }
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

  // Usage: java InputExamples < stopAtAllZeroes.txt
  static void stopAtAllZeroes() {
    String input = InputExamples.ReadLn(255).trim(); // must trim the input as ReadLn also reads in the "\n"
    StringTokenizer iData = new StringTokenizer(input);
    int a = Integer.parseInt(iData.nextToken()),
        b = Integer.parseInt(iData.nextToken());

    while ((a != 0) && (b != 0)) {  // Java doesn't allow: while (a || b) { .... }
      System.out.println(a + b);

      input = InputExamples.ReadLn(255);
      iData = new StringTokenizer(input);
      a = Integer.parseInt(iData.nextToken());
      b = Integer.parseInt(iData.nextToken());
    }
  }

  // java InputExamples < eof.txt
  static void displayCaseNum() {
    String input;
    StringTokenizer iData;
    int a, b, caseNum = 1;

    while ((input = InputExamples.ReadLn(255)) != null) {
      iData = new StringTokenizer(input);
      a = Integer.parseInt(iData.nextToken());
      b = Integer.parseInt(iData.nextToken());
      if (caseNum != 1) { // so there isn't an extra space after the last test case
        System.out.println();
      }
      System.out.println("Case " + caseNum++ + ": " + (a + b));
    }    
  }

  // Usage: java InputExamples < variableLineInput.txt
  static void variableLineInput() {
    String input;
    StringTokenizer iData;

    while ((input = InputExamples.ReadLn(255)) != null) {
      iData = new StringTokenizer(input);

      int sum = 0;
      while (iData.hasMoreTokens()) {
        sum += Integer.parseInt(iData.nextToken());
      }
      System.out.println(sum);
    }
  }  
}