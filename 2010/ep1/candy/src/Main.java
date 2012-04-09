import java.io.*;
import java.util.ArrayList;

/**
 * GeOlymp 2010 Eposode 1 Problem C solution
 *
 * @author Alexander Dolidze <alexander.dolidze@gmail.com>
 */
public class Main {
    public static PrintWriter printWriter;
    public static int[] solution;
    public static int solutionIndex = 0;

    public static void main(String... args) throws IOException {
        printWriter = new PrintWriter(new File("candy.out"));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("candy.in")));

        String[] tokens = bufferedReader.readLine().split("[ ]+");
        int nCandies, nPairs;
        nCandies = Integer.parseInt(tokens[0]);
        nPairs = Integer.parseInt(tokens[1]);

        CandyFactory.init(nCandies);
        solution = new int[nCandies];

        for (int i = 0; i < nPairs; i++) {
            tokens = bufferedReader.readLine().split("[ ]+");
            CandyFactory.getCandy(Integer.parseInt(tokens[1]) - 1).getCoveredBy().add(CandyFactory.getCandy(Integer.parseInt(tokens[0]) - 1));
            CandyFactory.getCandy(Integer.parseInt(tokens[0]) - 1).getCovers().add(CandyFactory.getCandy(Integer.parseInt(tokens[1]) - 1));
        }
        bufferedReader.close();

        for (Candy c : CandyFactory.getCandies()) {
            c.setCoveredUnprocessed(c.getCoveredBy().size());
            if (c.getCoveredUnprocessed() == 0) solution[solutionIndex++] = c.getIndex();
        }

        for (int i = 0; i < solution.length; i++) {
            int current = solution[i];
            printWriter.print((current + 1));
            for (Candy d : CandyFactory.getCandy(current).getCovers()) {
                d.setCoveredUnprocessed(d.getCoveredUnprocessed() - 1);
                if (d.getCoveredUnprocessed() == 0) solution[solutionIndex++] = d.getIndex();
            }
            if (i == solution.length - 1) printWriter.println();
            else printWriter.print(" ");
        }

        printWriter.flush();
        printWriter.close();
    }

}

class CandyFactory {
    private static Candy[] candies;

    public static void init(int nCandies) {
        candies = new Candy[nCandies];
        for (int i = 0; i < candies.length; i++) candies[i] = new Candy(i);
    }

    public static Candy getCandy(int index) {
        //if (candies[index] == null) candies[index] = new Candy(index);
        return candies[index];
    }

    public static Candy[] getCandies() {
        return candies;
    }
}

class Candy {
    private int index;
    private ArrayList<Candy> coveredBy = new ArrayList<Candy>();
    private int coveredUnprocessed;
    private ArrayList<Candy> covers = new ArrayList<Candy>();

    public Candy(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public ArrayList<Candy> getCoveredBy() {
        return coveredBy;
    }

    public ArrayList<Candy> getCovers() {
        return covers;
    }

    public int getCoveredUnprocessed() {
        return coveredUnprocessed;
    }

    public void setCoveredUnprocessed(int coveredUnprocessed) {
        this.coveredUnprocessed = coveredUnprocessed;
    }
}
