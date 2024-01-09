package com.shilaeva.inputOutput;

import java.io.*;
import java.nio.charset.Charset;

public class ReplacerWindowsToUnix {
    public static void main(String[] args) throws IOException {
        int read = System.in.read();
        int prev = read;

        while (read > 0) {
            prev = read;
            read = System.in.read();

            if (read != 10 || prev != 13) {
                System.out.write(prev);
            }
        }

        System.out.flush();
    }

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        Reader reader = new InputStreamReader(inputStream, charset);

        StringBuilder result = new StringBuilder();
        int read;
        while ((read = reader.read()) > 0) {
            result.append((char) read);
        }

        return result.toString();
    }
}
