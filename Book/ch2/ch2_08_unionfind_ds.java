import java.util.Vector;

// Union-Find Disjoint Sets Library written in OOP manner, using both path compression and union by rank heuristics
class UnionFind {                                              // OOP style
  private Vector<Integer> p, rank, setSize;
  private int numSets;

  public UnionFind(int N) {
    p = new Vector<Integer>(N);
    rank = new Vector<Integer>(N);
    setSize = new Vector<Integer>(N);
    numSets = N;

    // initialize each item as a disjoint set by itself with rank 0 and the parent of each item is initially set to itself
    for (int i = 0; i < N; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1);
    }
  }


  public int findSet(int i) { 
    if (p.get(i) == i) {
      return i;
    } else {  // this clause is used to vastly speed up findSet() by using Path Compression
      int ret = findSet(p.get(i)); 
      p.set(i, ret);
      return ret; 
    } 
  }

  public Boolean isSameSet(int i, int j) { 
    return findSet(i) == findSet(j); 
  }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { 
      numSets--; 
      int x = findSet(i), 
          y = findSet(j);
    
      // rank is used to keep the tree short
      if (rank.get(x) > rank.get(y)) { 
        p.set(y, x); 
        setSize.set(x, setSize.get(x) + setSize.get(y)); 
      } else { 
        p.set(x, y); 
        setSize.set(y, setSize.get(y) + setSize.get(x));
                                     
        if (rank.get(x) == rank.get(y)) {
          rank.set(y, rank.get(y) + 1); 
        }
      } 
    } 
  }

  public int numDisjointSets() { return numSets; }
  public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}

class ch2_08_unionfind_ds {
  public static void main(String[] args) {
    System.out.printf("Assume that there are 5 disjoint sets initially\n");
    UnionFind UF = new UnionFind(5); // create 5 disjoint sets
    System.out.printf("%d\n", UF.numDisjointSets()); // 5
    UF.unionSet(0, 1);
    System.out.printf("%d\n", UF.numDisjointSets()); // 4
    UF.unionSet(2, 3);
    System.out.printf("%d\n", UF.numDisjointSets()); // 3
    UF.unionSet(4, 3);
    System.out.printf("%d\n", UF.numDisjointSets()); // 2
    System.out.printf("isSameSet(0, 3) = %b\n", UF.isSameSet(0, 3)); // will return false
    System.out.printf("isSameSet(4, 3) = %b\n", UF.isSameSet(4, 3)); // will return true
    for (int i = 0; i < 5; i++) // findSet will return 1 for {0, 1} and 3 for {2, 3, 4}
      System.out.printf("findSet(%d) = %d, sizeOfSet(%d) = %d\n", i, UF.findSet(i), i, UF.sizeOfSet(i));
    UF.unionSet(0, 3);
    System.out.printf("%d\n", UF.numDisjointSets()); // 1
    for (int i = 0; i < 5; i++) // findSet will return 3 for {0, 1, 2, 3, 4}
      System.out.printf("findSet(%d) = %d, sizeOfSet(%d) = %d\n", i, UF.findSet(i), i, UF.sizeOfSet(i));
  }
}

/* NOTES

Union-Find Disjoint Sets (UFDS)
-M operations of this UFDS data structure with 'path compression' and 'union by rank' heuristics run in O(N * alpha(n)). However since the inverse Ackermann function 
 (alpha(n)) grows very slowly, we can treat it as a constant
-the main innovation fo UFDS is in choosing a representative 'parent' item to represent a set. If we can ensure that each set is represented by only one unique item, then 
 determining if items belong to the same set becomes far simpler: The representative 'parent' item can be used as a sort of identifier for the set. To achieve this, the UFDS
 creates a tree structure where the disjoint sets form a forest of trees. Each tree corresponds to a disjoint set. The root of the tree is determined to be the representative
 item for a set. Thus, a representative set identifier for an item can be obtained simply by following the chain of parents to the root of the tree and since a tree can only
 have one root, this representative item can be used as a unique identifier for the set.

 To do this efficiently, we store the index of the parent item and (the upper bound of) the height of the tree of each set ('p' and 'rank' in our implementation). p[i] stores
 the immediate parent of item i. If item i is the representative item of a certain disjoint set, then p[i] = i; i.e. a self-loop. rank[i] yields (the upper bound of) the 
 height of the tree rooted at item i
-'union by rank' heuristic: for efficiency, we can use the information in 'rank' to set the representative item of the disjoint set with higher rank to be the new parent of the
 disjoint set with lower rank, thereby minimizing the rank of the resulting tree. If both ranks are the same, we arbitrarily choose one of them as the new parent and increase
 the resultant root's rank.
-Path Compression (see findSet(i)): whenever we find the representative (root) item of a disjoint set by following the chain of 'parent' links from a given item, we can set the
 parent of all items traversed to point directly to the root. Any subsequent calls to findSet(i) on the affected items will then result in only one link being traversed. This
 changes the structure of the tree (to make findSet(i) more efficient) but yet preserves the actual constitution of the disjoint set. Since this will occur any time findSet(i)
 is called, the combined effect is to render the runtime of the findSet(i) operation to run in an extremely efficient amortized O(M * alpha(n)) time
 -NOTE: ignore setSize for the time being as it seems like it doesn't necessarily affect the algorithm
*/