package com.vto.gcode.interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public static String read(String path) {
        StringBuilder readed = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            boolean onQuotes = false, addSpace;
            while ((line = reader.readLine()) != null) {
                StringBuilder line2 = new StringBuilder();
                if (!line.equals("")) {
                    while (line.charAt(0) == ' ') {
                        line = line.substring(1);
                    }
                    for (char c : line.toCharArray()) {
                        addSpace = false;
                        if (c == '\'')
                            onQuotes = !onQuotes;
                        if (onQuotes && c == ' ') {
                            addSpace = true;
                        }
                        if (addSpace)
                            line2.append("\\s");
                        else
                            line2.append(c);
                    }
                    line2 = new StringBuilder(line2.toString().replace("'", ""));
                }

                readed.append(line2).append('\0');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(readed);

        return readed.toString();
    }

}
