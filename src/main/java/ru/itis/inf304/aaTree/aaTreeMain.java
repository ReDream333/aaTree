package ru.itis.inf304.aaTree;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class aaTreeMain {

    public static final int ARRAY_SIZE = 10_000;
    public static final int SEARCH_TEST_SIZE = 100;
    public static final int DELETE_SIZE = 1000;


    public static void main(String[] args) throws FileNotFoundException {
        Tree tree = new Tree();

//        int[] a = new int[]{9, 19, 3, 13, 27, 6, 1, 17, 30, 24, 10, 18, 15, 2};
//        for (int i = 0; i < a.length; i++) {
//            tree.insert(a[i]);
//        }
//        Tree.setOpIn(0);
//        tree.print();

        int[] a = randomArray();
        insertionTest(tree, a);
        Arrays.sort(a);
        searchTest(tree, a);
        deleteTest(tree, a);

    }
    public static void insertionTest(Tree tree, int[] a) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("insert.txt");
        int totalIters = 0;
        long startTimeTotal = System.nanoTime();
        for (int j = 0; j < a.length; j++) {
            int i = a[j];
            long startTime = System.nanoTime();
            tree.insert(i);
            float duration = ((System.nanoTime() - startTime) /1000f/ 1000f);
            int iters = Tree.getOpIn();
            totalIters+=iters;
            Tree.setOpIn(0);
            out.println(j+" "+iters+" "+duration);
        }
        int avgIters = totalIters/a.length;
        float avgTime = (System.nanoTime() - startTimeTotal)/1000f/1000f/a.length;
        out.println(avgIters+" "+ avgTime);
        out.close();
    }

    public static void searchTest(Tree tree, int[] a) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("search.txt");
        int totalIters = 0;
        long startTimeTotal = System.nanoTime();
        for (int j = 0; j < SEARCH_TEST_SIZE; j++) {
            int i = a[(int)(Math.random()*a.length)];
            long startTime = System.nanoTime();
            tree.search(i);
            float duration = ((System.nanoTime() - startTime) /1000f/ 1000f);
            int iters = Tree.getOpSr();
            totalIters+=iters;
            Tree.setOpSr(0);
            out.println(j+" "+iters+" "+duration);
        }
        int avgIters = totalIters/SEARCH_TEST_SIZE;
        float avgTime = (System.nanoTime() - startTimeTotal)/1000f/1000f/SEARCH_TEST_SIZE;
        out.println(avgIters+" "+ avgTime);
        out.close();
    }

    public static void deleteTest(Tree tree, int[] a) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("delete.txt");
        int totalIters = 0;
        long startTimeTotal = System.nanoTime();
        for (int j = 0; j < DELETE_SIZE; j++) {
            int i = a[(int)(Math.random()*a.length)];
            long startTime = System.nanoTime();
            tree.delete(i);
            float duration = ((System.nanoTime() - startTime)/1000f/1000f);
            int iters = Tree.getOpDl();
            totalIters += iters;
            Tree.setOpDl(0);
            out.println(j+" "+iters+" "+duration);
        }
        int avgIters = totalIters/DELETE_SIZE;
        float avgTime = (System.nanoTime() - startTimeTotal)/1000f/1000f/DELETE_SIZE;
        out.println(avgIters+" "+ avgTime);
        out.close();
    }

    public static int[] randomArray(){
        int[] a = new int[ARRAY_SIZE];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int)(Math.random()*100_000_000);
        }

        return a;
    }
}
