import java.util.*;
import java.io.*;

class Main {
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
    Main myWork = new Main();  // create a dynamic instance
    myWork.Begin();            // the true entry point
  }

  void Begin() {
    String line;
    int bit = 1;

    while ((line = Main.ReadLn(255)) != null) {
      
    }
  }
}