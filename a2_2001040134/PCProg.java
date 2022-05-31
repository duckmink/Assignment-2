package a2_2001040134;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

import static utils.TextIO.*;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * @overview PCProg is a program that captures data about PC objects
 *           and displays a report about them on the console.
 * 
 * @attributes
 *             objs Set<PC>
 * 
 * @object
 *         A typical PCProg is {c1,...,cn} where c1,...,cn are pcs
 * 
 * @abstract_properties
 *                      mutable(objs)=true /\ optional(objs)=false
 *
 */
public class PCProg {
  @DomainConstraint(mutable = true, optional = false)
  private Set<PC> objs;

  /**
   * @effects
   *          initialise this to have an empty set of PCs
   */
  public PCProg() {
    objs = new Set<>();
  }

  /**
   * @effects Use PCFactory to create a new PC object from user's input
   *          and record it in objs
   *          User's input in order: model, year, manufacturer, components
   *          components are recorded in a Set
   *          the user can stop adding components by a blank line
   */
  public void createObjects() {
    // enter data
    System.out.println("Enter theses required data values: ");
    // model
    System.out.print("Model: ");
    String m = getln();
    // year
    System.out.print("Year: ");
    int y = getlnInt();
    // manufacturer
    System.out.print("Manufacturer: ");
    String mn = getln();
    // Components
    Set<String> cs = new Set<>();
    System.out.println("Components: ");
    boolean test = true;
    while (test) { //check duplicate components
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
          }
        }
      } else {
        objs.insert(newPC);
      }
    } else
      System.out.println("Invalid PC");

    // asking if the user want to create a new PC or not
    System.out.println("Continue creating PC? [Y/N]");
    char toCreate = getlnChar();
    switch (toCreate) {
      case 'Y':
        this.createObjects();
        break;
      case 'N':
        break;
      default:
        System.out.println("Invalid answer, must be either Y or N");
    }

  }

  /**
   * @effects
   *          if objs is not empty
   *          display to the standard console a text-based tabular report on objs
   *          return this report
   *          else
   *          display nothing and return null
   */
  public String displayReport() {
    if (objs.size() > 0) {
      Vector<PC> pcs = objs.getElements();
      PCReport reportObj = new PCReport();
      return reportObj.displayReport(pcs.toArray(new PC[pcs.size()]));
    } else {
      return null;
    }
  }

  @DOpt(type = OptType.Observer)
  @AttrRef("objs")
  public Vector<PC> getObjects() {
    Vector<PC> getter = this.objs.getElements();
    return getter;
  }

  /**
   * @effects
   *          save report to a file pcs.txt in the same directory
   *          as the program's
   */
  public void saveReport(String report) {
    String fileName = "pcs.txt";

    File f = new File(fileName);
    try {
      f.createNewFile();
    } catch (IOException e) {
      System.out.println("IOException");
    }

    writeFile(fileName);
    putln(report);
    writeStandardOutput();
  }

  /**
   * The run method
   * 
   * @effects
   *          initialise an instance of PCProg
   *          create objects from data entered by the user
   *          display a report on the objects
   *          prompt user to save report to file
   *          if user answers "Y"
   *          save report
   *          else
   *          end
   */
  public static void main(String[] args) {
    //
    PCProg prog = new PCProg();

    // create objects
    try {
      prog.createObjects();

      // display report
      String report = prog.displayReport();

      if (report != null) {
        // prompt user to save report
        putln("Save report to file? [Y/N]");
        String toSave = getln();
        if (toSave.equals("Y")) {
          prog.saveReport(report);
          putln("report saved");
        }
      }
    } catch (NotPossibleException e) {
      System.err.printf("%s: %s%n", e, e.getMessage());
    }

    putln("~END~");
  }
}
