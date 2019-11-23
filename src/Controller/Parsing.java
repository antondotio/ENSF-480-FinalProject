package Controller;

public class Parsing {
    public static Integer parseInt(String s) {
        if (s.equals("NULL")) {
            return null;
        }
        return Integer.parseInt(s);
    }

    public static Boolean parseFurnished(String s) {
        if (s.equals("Yes")) {
            return true;
        }
        if (s.equals("No")) {
            return false;
        }
        return null;
    }

    public static Double parseDouble(String s) {
        if (s.equals("NULL")) {
            return null;
        }
        return Double.parseDouble(s);
    }
}
