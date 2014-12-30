import java.util.Vector;
import java.util.Collections;

/* Dynamically-resizeable array (example below shows that if we try to add to index that is out of bounds for static array, we will get an error. 
   However with add, vector will resize itself)
   -misc helpful Vector fxns: insertElementAt(E obj, int index), toArray(), removeElementAt(int index)
*/
class ch2_01_array_vector {
  public static void main(String[] args) {
    // initial value {7,7,7,0,0} and thus initial size (5)
    int[] arr = new int[] {7,7,7,0,0};
    // initial size (5) and initial value {5,5,5,5,5}
    Vector<Integer> v = new Vector<Integer>(Collections.nCopies(5, 5)); // nCopies(# of elements in the list, element to appear repeatedly in returned list)

    // 7 and 5, for Java Vector, we must use 'get'
    System.out.println("arr[2] = " + arr[2] + " and v[2] = " + v.get(2));

    for (int i = 0; i <= 4; i++) {
      arr[i] = i;
      v.set(i, i);                    // for Java Vector, we must use 'set'
    }

    // 2 and 2
    System.out.println("arr[2] = " + arr[2] + " and v[2] = " + v.get(2));

    // arr[5] = 5;   // static array will generate index out of bound error
    // uncomment the line above to see the error

    v.add(5);  // vector will resize itself (use method add in Java Vector)
    System.out.println("v[5] = " + v.get(5));                          // 5
  }
}
