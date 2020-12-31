/* *****************************************************************************
 *  Name:              Purab Patel
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    final private double[] results;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            double nn = n * n;
            double numOpen = perc.numberOfOpenSites();
            results[i] = numOpen / nn;
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - 2 * stddev();
    }

    public double confidenceHi() {
        return mean() + 2 * stddev();
    }

    public static void main(String[] args) {
        PercolationStats percstats = new PercolationStats(Integer.parseInt(args[0]),
                                                          Integer.parseInt(args[1]));
        System.out.println("mean    " + percstats.mean());
        System.out.println("stdev    " + percstats.stddev());
        System.out.println("95% confidence interval    " + "[" + percstats.confidenceLo() + " , " +
                                   percstats.confidenceHi() + "]");
    }
}
