import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.util.LinkedHashMap;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WordStatInput {
    public static void main(String[] args) {
        Map<String, Integer> words = new LinkedHashMap<>();

        try {
            MyScanner in = new MyScanner(
                new InputStreamReader(new FileInputStream(args[0]), "utf-8"),
                new WordChecker()
            );
            
            while (in.hasNext()) {
                String word = in.nextWord().toLowerCase();
                words.put(word, words.getOrDefault(word, 0) + 1);
            }

            try {
                BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        "utf-8"
                    )
                );

                try {
                    for (Map.Entry<String, Integer> word: words.entrySet()) {
                        out.write(word.getKey() + " " + word.getValue() + System.lineSeparator());
                    }
                } catch (IOException e) {
                    System.out.println("Output file error: " + e.getMessage());
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
