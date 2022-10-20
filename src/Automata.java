import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;
import org.jgrapht.*;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.connectivity.*;

public class Automata {
    private InputStream input = null;
    private String path = "";
    private Vector<String> text = new Vector<String>();
    private Vector<String> alphabet = new Vector<String>();
    public Vector<String> condition = new Vector<String>();
    public String zeroCondition = "";
    public Vector<String> finalConditions = new Vector<String>();
    public Vector<String> fStartConditions = new Vector<String>();
    private Vector<String> incomingWord = new Vector<String>();
    public Vector<String> fEndConditions = new Vector<String>();
    private Vector<String> unattainable = new Vector<String>();
    private Vector<String> deadEnd = new Vector<String>();
    private Graph<String, DefaultEdge> automata = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

    public void get_path() {
	System.out.println("Введіть шлях до вихідного файлу: ");
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader reader = new BufferedReader(isr);
	try {
	    path = reader.readLine();
	} catch (IOException e) {
	    path = "";
	}
    }
    // C:\Users\nsery\OneDrive\Документи\Study\Lab2Test.txt

    public void read() {
	try (BufferedReader br = new BufferedReader(new FileReader(path))) {
	    for (String line; (line = br.readLine()) != null;) {
		text.add(line);
	    }

	    StringTokenizer st = new StringTokenizer(text.elementAt(0), " ");
	    while (st.hasMoreTokens()) {
		alphabet.add(st.nextToken());
	    }

	    StringTokenizer st1 = new StringTokenizer(text.elementAt(1), " ");
	    while (st1.hasMoreTokens()) {
		condition.add(st1.nextToken());
	    }

	    zeroCondition = text.elementAt(2);

	    StringTokenizer st3 = new StringTokenizer(text.elementAt(3), " ");
	    while (st3.hasMoreTokens()) {
		finalConditions.add(st3.nextToken());
	    }

	    for (int i = 4; i < text.size(); i++) {
		StringTokenizer st4 = new StringTokenizer(text.elementAt(i), " ");
		while (st4.hasMoreTokens()) {
		    fStartConditions.add(st4.nextToken());
		    incomingWord.add(st4.nextToken());
		    fEndConditions.add(st4.nextToken());
		}

	    }

	} catch (IOException e) {
	    e.printStackTrace();
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

    public void getStream() throws Exception {
	try {
	    input = new FileInputStream(path);
	    InputStreamReader isr = new InputStreamReader(input);
	    BufferedReader reader = new BufferedReader(isr);

	    String line = null;
	    line = reader.readLine();
	    StringTokenizer st = new StringTokenizer(line, " ");
	    while (st.hasMoreTokens()) {
		alphabet.add(st.nextToken());
	    }

	} catch (Exception e) {
	    System.err.println("Error ocured.");
	} finally {
	    if (input != null) {
		input.close();
	    }
	}
    }

    public void vertexCreator() {
	for (int i = 0; i < condition.size(); i++) {
	    automata.addVertex(condition.elementAt(i));
	}
    }

    public void edgeCreator() {
	for (int i = 0; i < fStartConditions.size(); i++) {
	    automata.addEdge(fStartConditions.elementAt(i), fEndConditions.elementAt(i));
	}
    }

    public void graphCreator() {
	vertexCreator();
	edgeCreator();
    }

    public Boolean isUND(String s, String s0) {
	DijkstraShortestPath<String, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(automata);
	SingleSourcePaths<String, DefaultEdge> iPaths = dijkstraAlg.getPaths(s0);
	if (iPaths.getPath(s) == null) {
	    return true;
	} else {
	    return false;
	}
    }

    public void unatainableCheck() {
	for (int i = 0; i < condition.size(); i++) {

	    if (isUND(condition.elementAt(i), zeroCondition) == true) {

		if (!unattainable.contains(condition.elementAt(i))) {
		    unattainable.add(condition.elementAt(i));
		}
	    }
	}
    }

    public void deadendCheck() {
	for (int j = 0; j < condition.size(); j++) {
	    for (int i = 0; i < finalConditions.size(); i++) {
		if (isUND(finalConditions.elementAt(i), condition.elementAt(j)) == true
			&& !finalConditions.contains(condition.elementAt(j))) {
		    deadEnd.add(condition.elementAt(j));
		}
	    }
	}
    }

    public void show() {
	System.out.println("Недосяжні вершини: ");
	for (int i = 0; i < unattainable.size(); i++) {
	    System.out.println(unattainable.elementAt(i));
	}
	System.out.println("Тупикові вершини: ");
	for (int i = 0; i < deadEnd.size(); i++) {
	    System.out.println(deadEnd.elementAt(i));
	}
    }
}