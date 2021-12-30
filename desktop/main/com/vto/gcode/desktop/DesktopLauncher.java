package com.vto.gcode.desktop;

import com.vto.gcode.interpreter.GInterpreter;
import com.vto.gcode.runtime.GGlobal;

import java.util.Scanner;

public class DesktopLauncher {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Path to .gcode file: ");
        final String readed = scanner.nextLine();
        scanner.close();
        final long start = System.currentTimeMillis();
        new GInterpreter(readed);
        GGlobal.dispose();
        final long stop = System.currentTimeMillis();
        System.out.println("\nFinished in " + (stop - start) + "ms.");
    }

}
