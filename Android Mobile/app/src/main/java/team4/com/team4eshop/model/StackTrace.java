package team4.com.team4eshop.model;

/**
 * Created by E0046636 on 12/21/2016.
 * All copy from Workshop11
 */
import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTrace {
    public static String trace(Exception ex) {
        StringWriter outStream = new StringWriter();
        ex.printStackTrace(new PrintWriter(outStream));
        return outStream.toString();
    }
}