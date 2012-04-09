import java.io.*;
import java.util.*;

/**
 * GeOlymp 2010 TSU training contest 1 - STL contest; Problem C (Friends)
 *
 * @author Alexander Dolidze <alexander.dolidze@gmail.com>
 */
public class Main {
    private static int inputData[][];
    private static int matrix[][];
    private static int lengths[];
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedInputStream(System.in));

        int N = in.nextInt();
        int M = in.nextInt();
        int a = 0;
        int b = 0;

        inputData = new int[M][2];
        matrix = new int[N][];
        lengths = new int[N];

        if (M!=0)
            for (int i=0; i<M; i++) {
                a = in.nextInt();
                b = in.nextInt();

                lengths[a-1]++;
                lengths[b-1]++;

                inputData[i][0] = a;
                inputData[i][1] = b;
            }

        in.close();

        for (int i=0; i<N; i++) {
            matrix[i]=new int[lengths[i]];
        }

        lengths = new int[N];

        for (int i=0; i<M; i++) {
            matrix[inputData[i][0]-1][lengths[inputData[i][0]-1]++] = inputData[i][1]-1;
            matrix[inputData[i][1]-1][lengths[inputData[i][1]-1]++] = inputData[i][0]-1;
        }

        lengths = null;
        inputData = null;

//        System.out.println();

        for (int i=0; i<N; i++) {
            Arrays.sort(matrix[i]);
            System.out.print((i+1)+":");

            for (int j=0; j<matrix[i].length; j++)
            {
                System.out.print(" "+(matrix[i][j]+1));
            }
            System.out.println();
        }

    }
}

//Sample input:
//4 5
//1 2
//2 3
//4 1
//3 4
//1 3

//Expected output:
//1: 2 3 4
//2: 1 3
//3: 1 2 4
//4: 1 3