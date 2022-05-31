package a2_2001040134;

import java.util.Vector;

public class test {
    public static void main(String[] args) {
        Vector<String> a = new Vector<>();
        a.add("a");
        a.add("b");
        a.add("c");
        Vector<String> b = new Vector<>();
        b.add("a");
        b.add("c");
        b.add("b");
        System.out.println(a.hashCode() == b.hashCode());
    }
}
