import java.io.BufferedReader;
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
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    "utf-8"
                )
            );
            
            String line;
            while ((line = in.readLine()) != null) {
                line = line.toLowerCase();
                int left = -1;
                for (int right = 0; right <= line.length(); right++) {
                    if (right != line.length() && check(line.charAt(right))) {
                        if (left == -1)
                            left = right;
                    } else if (left != -1) {
                        String word = line.substring(left, right);
                        words.put(word, words.getOrDefault(word, 0) + 1);
                        left = -1;
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

    private static boolean check(char c) {
        return Character.isLetter(c) || c == '\'' ||
                Character.getType(c) == Character.DASH_PUNCTUATION;
    }
}
