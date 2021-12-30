package com.vto.gcode.runtime;

import com.vto.gcode.interpreter.Action;
import com.vto.gcode.interpreter.GInterpreter;

import java.io.File;
import java.util.HashSet;

public class GGlobal {

    public static GInterpreter interpreter;
    public static String[] mainFileLines;
    public static HashSet<Action> actions = new HashSet<>();
    public static HashSet<Variable> variables = new HashSet<>();
    public static HashSet<Function> functions = new HashSet<>();
    public static int actLine;
    public static String actFunction;
    public static File mainFile;

    public static void dispose() {
        GGlobal.variables.clear();
        GGlobal.actions.clear();
        GGlobal.interpreter = null;
        GGlobal.mainFileLines = null;
        GGlobal.mainFile = null;
        GGlobal.actLine = 0;
        GGlobal.functions.clear();
        GGlobal.actFunction = null;
    }

}
