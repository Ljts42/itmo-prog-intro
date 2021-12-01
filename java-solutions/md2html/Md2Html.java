package md2html;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.nio.charset.StandardCharsets;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Md2Html {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
                )
            );
            try {
                StringBuilder block = new StringBuilder();
                String line = "";
                while (line != null) {
                    line = in.readLine();
                    while (line != null && !line.isEmpty()) {
                        if (block.length() > 0) {
                            block.append('\n');
                        }
                        block.append(line);
                        line = in.readLine();
                    }

                    if (block.length() != 0) {
                        new MarkdownBlock(block).toHtml(result);
                        result.append('\n');
                        block = new StringBuilder();
                    }
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

        try {
            BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    StandardCharsets.UTF_8
                )
            );

            try {
                out.write(result.toString());

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
    }
}
