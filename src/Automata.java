import java.io.*;
import java.util.Scanner;
import java.util.Vector;
public class Automata {
    private InputStream input = null;
    private String path = "";
    //
    private Vector<String> alphabet = new Vector<String>();
    public Vector<String> condition = new Vector<String>();
    private String zeroCondition = "0";
    private Vector<String> finalConditions = new Vector<String>();    
    public Vector<String> fStartConditions = new Vector<String>();
    private Vector<String> incomingWord = new Vector<String>();
    public Vector<String> fEndConditions = new Vector<String>();
    private Vector<String> unattainable = new Vector<String>();
    private Vector<String> deadEnd = new Vector<String>();
    //
    private Vector<Integer> temp = new Vector<Integer>();
    private Vector<Integer> iTemp = new Vector<Integer>();
    //
    int sum = 0;
    //
    
    /*public void get_path() {
	System.out.println("Enter your input file path: ");
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader reader = new BufferedReader(isr);
	try {
	    path = reader.readLine();
	} catch (IOException e) {
	    path = "";
	}
    }

    public Boolean isReadable() {
	File file = new File(path);
	if (file.canRead() == true) {
	    return true;
	} else {
	    return false;
	}
    }

    public String getStream() throws Exception {
	try {
	    input = new FileInputStream(path);
	    InputStreamReader isr = new InputStreamReader(System.in);
	    BufferedReader reader = new BufferedReader(isr);

	} catch (Exception e) {
	    System.err.println("Error ocured.");
	} finally {
	    if (input != null) {
		input.close();
	    }
	}
	return text;
    }*/
    
    public Vector<String> getUnattainable()
    {
	for(int i=0; i<condition.size(); i++)
	{
	    if(this.isUnattainable(condition.elementAt(i)))
	    {
		unattainable.add(condition.elementAt(i));
	    }
	}
	return unattainable;
    }
    
    public Boolean isUnattainable(String s)
    {
	    for(int i=0; i<fEndConditions.size(); i++)
	    {
		if(s == fEndConditions.elementAt(i))
		{
		   temp.add(1);
		}
		else {
		    temp.add(0);
		}
	    }
	    
	    for(int i=0; i<temp.size(); i++) {
		if(temp.elementAt(i) == 1) {
		    s = fStartConditions.elementAt(i);
		    temp.clear();
		    isUnattainable(s);
		}
	    }
	    
	    return true;
    }
   
}
