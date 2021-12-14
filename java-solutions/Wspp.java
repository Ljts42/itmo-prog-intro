import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Wspp {
    public static void main(String[] args) {

        Map<String, ArrayList<Integer>> words = new LinkedHashMap<>();
        int n = 1;

        try {
            MyScanner in = new MyScanner(
                new InputStreamReader(new FileInputStream(args[0]), "utf-8"),
                new WordChecker()
            );

            try {
                while (in.hasNext()) {
                    String word = in.nextWord().toLowerCase();

                    if (words.get(word) == null) {
                        ArrayList<Integer> positions = new ArrayList<>();
                        positions.add(n++);
                        words.put(word, positions);
                    } else {
                        ArrayList<Integer> positions = words.get(word);
                        positions.add(n++);
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
                        for (Map.Entry<String, ArrayList<Integer>> word: words.entrySet()) {
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