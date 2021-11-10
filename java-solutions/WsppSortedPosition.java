import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import java.io.FileNotFoundException;
import java.io.IOException;


class WordChecker implements CharChecker {
    public boolean check(char c) {
        return ((Character.getType(c) == Character.DASH_PUNCTUATION) ||
                (Character.isLetter(c)) || (c == '\''));
    }
}

class Pair {
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.valueOf(x) + ":" + String.valueOf(y);
    }
}

public class WsppSortedPosition {
    public static void main(String[] args) {
        WordChecker wordChecker = new WordChecker();

        Map<String, ArrayList<Pair>> words = new TreeMap<>();
        int n = 1, m = 1;

        try {
            MyScanner in = new MyScanner(
                new InputStreamReader(new FileInputStream(args[0]), "utf-8"),
                wordChecker
            );

            try {
                while (in.hasNext()) {
                    String word = in.nextWord().toLowerCase();

                    if (in.getLineNumber() != n) {
                        n = in.getLineNumber();
                        m = 1;
                    }

                    if (words.get(word) == null) {
                        ArrayList<Pair> positions = new ArrayList<>();
                        positions.add(new Pair(n, m++));
                        words.put(word, positions);
                    } else {
                        ArrayList<Pair> positions = words.get(word);
                        positions.add(new Pair(n, m++));
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
                        for (Map.Entry<String, ArrayList<Pair>> word: words.entrySet()) {
                            out.write(word.getKey() + " ");
                            out.write(String.valueOf(word.getValue().size()));
                            for (int entrance = 0; entrance < word.getValue().size(); entrance++) {
                                out.write(" ");
                                out.write(word.getValue().get(entrance).toString());
                            }
                            out.write(System.lineSeparator());
                        }
                    } catch (IOException e) {
                        System.out.println("Output file error: " + e.getMessage());
                    } finally {
                        out.close();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Output file error: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Output file error: " + e.getMessage());
                }
            } catch (IOException e) {
                System.out.println("Input file error: " + e.getMessage());
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input file error: " + e.getMessage());
        }
    }
}