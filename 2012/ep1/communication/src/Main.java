import java.io.*;
import java.util.ArrayList;

/**
 * GeOlymp 2012 Eposode 1 Problem C (Communication)
 *
 * @author Alexander Dolidze <alexander.dolidze@gmail.com>
 */
public class Main {

    public static int subnet = 1;

    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("communication.in"));
        PrintWriter printWriter = new PrintWriter(new File("communication.out"));

        String[] tokens;
        tokens = bufferedReader.readLine().split("[ ]+");

        int N = Integer.parseInt(tokens[0]);
        double D = Double.parseDouble(tokens[1]);

        BaseFactory.init(N);

        for (int i = 0; i < N; i++) {
            tokens = bufferedReader.readLine().split("[ ]+");
            BaseFactory.getBase(i).setCoords(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        }

        for (Base b : BaseFactory.getBases()) {
            for (Base c : BaseFactory.getBases()) {
                if (b != c) {
                    if (b.canReach(c, D)) {
                        b.getReachable().add(c);
                    }
                }
            }
        }

        for (Base b : BaseFactory.getBases()) {
            if (b.getSubnet() == 0) dfs(b, subnet++);
        }

        int unreachable_counter = 0;
        for (Base b : BaseFactory.getBases()) {
            for (Base c : BaseFactory.getBases()) {
                if (b.getSubnet() != c.getSubnet()) unreachable_counter++;
            }
        }

        if (unreachable_counter != 0)
            printWriter.println(unreachable_counter / 2);
        else printWriter.println(0 + "");
        printWriter.flush();
        printWriter.close();
    }

    private static void dfs(Base b, int subnet) {
        b.setSubnet(subnet);
        for (Base r : b.getReachable()) {
            if (r.getSubnet() == 0) dfs(r, subnet);
        }
    }
}

class Base {
    public int getIndex() {
        return index;
    }

    private int index;
    private int x;
    private int y;
    private ArrayList<Base> reachable = new ArrayList<Base>();
    private int subnet;

    Base(int index) {
        this.index = index;
    }

    public boolean canReach(Base base, double D) {
        double dX = base.x - x * 1.0;
        double dY = base.y - y * 1.0;
        if (D * D >= (dX * dX + dY * dY)) return true;
        return false;
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getSubnet() {
        return subnet;
    }

    public void setSubnet(int subnet) {
        this.subnet = subnet;
    }

    public ArrayList<Base> getReachable() {
        return reachable;
    }
}

class BaseFactory {
    private static Base[] bases;

    public static void init(int nBases) {
        bases = new Base[nBases];
        for (int i = 0; i < nBases; i++) bases[i] = new Base(i);
    }

    public static Base getBase(int i) {
        return bases[i];
    }

    public static Base[] getBases() {
        return bases;
    }
}