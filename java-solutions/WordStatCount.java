import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WordStatCount {
    public static void main(String[] args) {
        String[] words = new String[10];
        int n = 0;
        try {
            MyScanner in = new MyScanner(
                new InputStreamReader(new FileInputStream(args[0]), "utf-8"),
                new WordChecker()
            );

            while (in.hasNext()) {
                words[n++] = in.nextWord().toLowerCase();

                if (n >= words.length) {
                    String[] newWords = new String[words.length * 2];
                    System.arraycopy(words, 0, newWords, 0, words.length);
                    words = newWords;
                }
            }
            
            int[][] indexes = new int[n][2];
            for (int i = 0; i < n; i++) {
                indexes[i][0] = i;
                indexes[i][1] = 0;
            }
            
            for (int j = n - 1; j >= 1; j--) {
                for (int i = 0; i < j; i++) {
                    if ((words[i].compareTo(words[i+1]) > 0) && (indexes[i][0] < indexes[i+1][0])) {
                        String tmps = words[i];
                        int tmpi = indexes[i][0];
                        words[i] = words[i+1]; words[i+1] = tmps;
                        indexes[i][0] = indexes[i+1][0]; indexes[i+1][0] = tmpi;
                    }
                }
            }

            int cnt = 1;
            for (int i = 1; i < n; i++) {
                if (words[i].compareTo(words[i - cnt]) == 0) {
                    cnt++;
                } else {
                    indexes[i - cnt][1] = cnt;
                    cnt = 1;
                }
            }
            indexes[n - cnt][1] = cnt;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (indexes[j][1] > indexes[j + 1][1]) {
                        String tmps = words[j];
                        int tmpi0 = indexes[j][0], tmpi1 = indexes[j][1];
                        words[j] = words[j + 1]; words[j + 1] = tmps;
                        indexes[j][0] = indexes[j + 1][0]; indexes[j + 1][0] = tmpi0;
                        indexes[j][1] = indexes[j + 1][1]; indexes[j + 1][1] = tmpi1;
                    } 
                    if (indexes[j][1] == indexes[j + 1][1]) {
                        if (indexes[j][0] > indexes[j + 1][0]) {
                            String tmps = words[j];
                            int tmpi0 = indexes[j][0], tmpi1 = indexes[j][1];
                            words[j] = words[j + 1]; words[j + 1] = tmps;
                            indexes[j][0] = indexes[j + 1][0]; indexes[j + 1][0] = tmpi0;
                            indexes[j][1] = indexes[j + 1][1]; indexes[j + 1][1] = tmpi1;
                        }
                    }
                }
            }

            try {
                BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        "utf-8"
                    )
                );

                try {
                    for (int i = 0; i < n; i++) {
                        if (indexes[i][1] == 0) {
                            continue;
                        }
                        out.write(words[i] + " " + indexes[i][1]);
                        out.newLine();
                    }
                } finally {
                    out.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Output file not found: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Output file error: " + e.getMessage());
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input file error: " + e.getMessage());
        }
    }
}