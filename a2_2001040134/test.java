package a2_2001040134;
import static utils.TextIO.*;
import java.util.Vector;
public class test {
    private Set<PC> objs;

  /**
   * @effects
   *          initialise this to have an empty set of PCs
   */
  public test() {
    objs = new Set<>();
  }
    public Vector<PC> getObjects() {
        Vector<PC> getter = this.objs.getElements();
        return getter;
      }
    public void createObjects() {    
        Set<String> a = new Set<>();
        a.insert("a");
        a.insert("b");
        a.insert("c");
        Set<String> b = new Set<>();
        b.insert("a");
        b.insert("c");
        b.insert("b");
        String m1 = "a";
        String mn1 = "abc";
        int y1 = 2000;

        PC pc2 = PCFactory.createPC("a", 2000, "abc", a);
        PC pc1 = PCFactory.createPC(m1,y1,mn1,b);

        if (pc2.repOK()) {
          if (objs.size() > 0) {
            Vector<PC> pcs = this.getObjects();
            for (PC e : pcs) {
              if (e.equals(pc2)) {
                System.out.println("Duplicate PC");
              } else {
                objs.insert(pc2);
              }
            }
          } else {
            objs.insert(pc2);
            System.out.println("PC2 recoreded");
          }
        } else System.out.println("Invalid PC");

        if (pc1.repOK()) {
          if (objs.size() > 0) {
            Vector<PC> pcs = this.getObjects();
            for (PC e : pcs) {
              if (e.equals(pc1)) {
                System.out.println("Duplicate PC");
              } else {
                objs.insert(pc1);
                System.out.println("PC recoreded");
              }
            }
          } else {
            objs.insert(pc1);
            System.out.println("PC1 recoreded");
          }
        } else System.out.println("Invalid PC");
        System.out.println(objs.toString());
        


      // enter data
    System.out.println("Enter theses required data values: ");
    System.out.print("Model: ");
    String m = getln();
    System.out.println("recorded model: <" + m +">");

    System.out.print("Year: ");
    int y = getlnInt();
    System.out.println("recorded year: <" + y +">");

    System.out.print("Manufacturer: ");
    String mn = getln();
    System.out.println("recorded manName: <" + mn +">");

    Set<String> cs = new Set<>();
    System.out.println("Components: ");
    boolean test = true;
    while (test) {
      String c = getln();
      if (!c.isEmpty()) {
        if (!cs.isIn(c)) {
          cs.insert(c);
        } else {
          System.out.println("Duplicate component");
        }
      } else {
        test = false;
      }
    }

    // create a local PC object
    PC newPC = PCFactory.createPC(m, y, mn, cs);
    // check valid PC and duplication
    if (newPC.repOK()) {
      if (objs.size() > 0) {
        Vector<PC> pcs = this.getObjects();
        for (PC e : pcs) {
          if (e.equals(newPC)) {
            System.out.println("Duplicate PC");
          } else {
            objs.insert(newPC);
            System.out.println("new pc recoreded");
          }
        }
      } else {
        objs.insert(newPC);
        System.out.println("new pc recoreded");
      }
    } else System.out.println("Invalid PC");


  }
    public static void main(String[] args) {
        test test = new test();
        test.createObjects();
    }
}
