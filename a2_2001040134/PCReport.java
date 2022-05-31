package a2_2001040134;

import java.util.Vector;

/**
 * @overview PCReport is a class with contains a single operation 
 *          displaying PCs in a formated list
 * @author Minh
 * 
 */
public class PCReport {

    /**
     * @effects displaying an array of PCs into a formated output
     */
    public String displayReport(PC[] objs) {
        String s = "";
        // make hyphens line
        StringBuffer line = new StringBuffer(99);
        for (int i = 1; i <= 99; i++) {
            line.append("-");
        }

        // make header
        StringBuffer header = new StringBuffer(99);
        for (int i = 1; i <= 43; i++) {
            header.append(" ");
        }
        header.append("PCPROG REPORT");
        for (int i = 1; i <= 43; i++) {
            header.append(" ");
        }

        // header
        s += line + "\r\n" + header + "\r\n" + line + "\r\n";

        // listing
        for (int i = 0; i < objs.length; ++i) {

            // number
            StringBuffer num = new StringBuffer(3);
            String index = String.valueOf(i + 1);
            for (int j = 1; j <= (3 - index.length()); j++) {
                num.append(" ");
            }
            num.append(index);

            // Model name
            String model = objs[i].getModel();
            StringBuffer m = new StringBuffer(20);
            for (int j = 1; j <= (20 - model.length()); j++) {
                m.append(" ");
            }
            m.append(model);

            // Year
            String year = String.valueOf(objs[i].getYear());
            StringBuffer y = new StringBuffer(6);
            for (int j = 1; j <= (6 - year.length()); j++) {
                y.append(" ");
            }
            y.append(year);

            // Manufaturer Name
            String manName = objs[i].getManName();
            StringBuffer mn = new StringBuffer(15);
            for (int j = 1; j <= (15 - manName.length()); j++) {
                mn.append(" ");
            }
            mn.append(manName);

            // Components
            StringBuffer comps = new StringBuffer(52);
            comps.append("[");
            Set<String> c = objs[i].getComps();
            Vector<String> e = c.getElements();
            for (int j = 0; j < (e.size() - 1); j++) {
                comps.append(e.elementAt(j).toString());
                comps.append(", ");
            }
            comps.append(e.elementAt(e.size() - 1).toString());
            comps.append("]");

            // Output line
            StringBuffer pc = new StringBuffer(99);
            pc.append(num);
            pc.append(" ");
            pc.append(m);
            pc.append(" ");
            pc.append(y);
            pc.append(" ");
            pc.append(mn);
            pc.append(" ");
            pc.append(comps);
            s += pc + "\r\n";
        }
        return s;
    }
}