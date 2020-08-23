package GinkgoStack.P10_Digital.knapsackProblem;


import java.io.*;
import java.util.*;

public class JD_BuyCandy {
    static class SeqSort implements Comparator<Candy> {
        @Override
        public int compare(Candy b1, Candy b2) {
            return b1.num - b2.num;
        }
    }

    static class Candy implements Comparable<Candy> {
        int t, p, num;

        Candy() {
        }

        Candy(int t, int p, int num) {
            this.p = p;
            this.t = t;
            this.num = num;
        }

        @Override
        public int compareTo(Candy b) {
            if (b.p == this.p) {
                int r = this.t - b.t;
                if (r == 0)
                    return this.num - b.num;
                else
                    return r;
            } else
                return b.p - this.p;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        while (line != null && line.trim().length() > 0) {
            String[] input = line.split(" ");
            int n = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            Candy[] candies = new Candy[n];
            List<Candy> a1 = new ArrayList<Candy>();
            List<Candy> a2 = new ArrayList<Candy>();
            List<Candy> best = new ArrayList<Candy>();

            int maxCapacity = 0;

            for (int i = 0; i < n; i++) {
                input = in.readLine().split(" ");
                candies[i] = new Candy(Integer.parseInt(input[0]),
                        Integer.parseInt(input[1]), i + 1);
            }

            //
            Arrays.sort(candies);

            int j = 0;
            while (j < candies.length) {
                if (candies[j].t == 1) {
                    a1.add(candies[j]);
                } else {
                    a2.add(candies[j]);
                }
                j++;
            }
            int a1count = 0, a2Count = 0;
            while (v > 1) {
                if (a1count < a1.size()
                        && (a2Count >= a2.size() || a1.get(a1count).p > a2
                        .get(a2Count).p)) {
                    best.add(a1.get(a1count));
                    maxCapacity += a1.get(a1count).p;
                    a1count++;
                    v--;
                } else if (a1.size() > a1count + 1
                        && (a2Count >= a2.size() || (a1.get(a1count).p + a1
                        .get(a1count + 1).p) > a2.get(a2Count).p)) {
                    best.add(a1.get(a1count));
                    maxCapacity += a1.get(a1count).p;
                    a1count++;
                    v--;
                } else {
                    if (a2Count >= a2.size()) {
                        break;
                    }
                    best.add(a2.get(a2Count));
                    maxCapacity += a2.get(a2Count).p;
                    a2Count++;
                    v -= 2;
                }
            }

            if (v == 1 && a1count < a1.size()) {
                best.add(a1.get(a1count));
                maxCapacity += a1.get(a1count).p;
                a1count++;
                v--;
            }

            System.out.println(maxCapacity);
            if (maxCapacity > 0) {
                Collections.sort(best, new SeqSort());
                for (int i = 0; i < best.size() - 1; i++) {
                    System.out.print(best.get(i).num + " ");
                }
                System.out.print(best.get(best.size() - 1).num);
                System.out.println();
            } else {
                System.out.println("No");
            }

            line = in.readLine();
        }// while
    }// main
}

