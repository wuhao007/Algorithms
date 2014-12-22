public class Percolation {
  private boolean[][] sites;
  private int grid;
  private int beginNode;
  private int endNode;

  private WeightedQuickUnionUF QU;
  private WeightedQuickUnionUF Backwash;
  //private QuickFindUF QU;
  public Percolation(int N) {
    grid = N;
    N++;
    QU = new WeightedQuickUnionUF(N * N + 1); //+2 for the top and bottom node
    Backwash = new WeightedQuickUnionUF(N * N + 2);
    //QU = new QuickFindUF(N * N + 2);
    sites = new boolean[N][N]; // create N-by-N grid, with all sites blocked
    beginNode = 0;
    endNode = N * N + 1;
  }

  public void open(int i, int j) {
    // open site (row i, column j) if it is not already
    // check if out of bound:
    checkException(i, j); 
    if (!sites[i][j]) {
      sites[i][j] = true;
    }

    int index = indexCalc(i, j);
    //Connect the site with surrounding nodes

    //left:
    if (j != 1) {
      if (sites[i][j - 1]) {
        QU.union(index, index - 1);
        Backwash.union(index, index - 1);
      }
    }

    //right:
    if (j != grid) {
      if (sites[i][j + 1]) {
        QU.union(index, index + 1);
        Backwash.union(index, index + 1);
      }
    }

    //top:
    if (i == 1) {
      QU.union(beginNode, index); //connect with top node which we will just have as N^2
      Backwash.union(beginNode, index);
    } else {
      if (sites[i - 1][j]) {
        QU.union(index, grid * (i - 1) + j);
        Backwash.union(index, grid * (i - 1) + j);
      }
    }

    //bottom:
    if (i == grid) {
      Backwash.union(index, endNode);
    } else {
      if (sites[i + 1][j]) {
        QU.union(index, grid * (i + 1) + j);
        Backwash.union(index, grid * (i + 1) + j);
      }
    }
  }
  
  public boolean isOpen(int i, int j) {
    checkException(i, j);
    // is site (row i, column j) open?
    return sites[i][j];
  }
  
  public boolean isFull(int i, int j) {
    checkException(i, j);
    // is site (row i, column j) full?
    return QU.connected(indexCalc(j, i), beginNode);
  }
  
  public boolean percolates() {
    return Backwash.connected(beginNode, endNode);
  }
  
  private int indexCalc(int i, int j) {
    return i * grid + j;
  }

  private void checkException(int i, int j) { 
    if (i <= 0 || i > grid || j <= 0 || j > grid) {
      throw new IndexOutOfBoundsException("row index i out of bounds");
    }
  }
    
  public static void main(String[] args) {
    int grid_size = 3;
    Percolation perc = new Percolation(grid_size);
    //initialize the grid to something
    /*
    for(int i = 0; i < grid_size; i++){
      for (int j=0; j < grid_size; j++){
        StdOut.println(perc.isOpen(i,j));
      }
    }
    */
    //  let's test if the openning of a site works. 
    perc.open(2,3);
    perc.open(3,3);
    perc.open(3,1);
    perc.open(2,1);
    perc.open(1,1);
    System.out.println(perc.percolates());
    System.out.println(perc.isFull(1,1));
  }
}
