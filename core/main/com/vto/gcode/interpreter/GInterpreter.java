package com.vto.gcode.interpreter;

import com.vto.gcode.runtime.*;
import com.vto.gcode.runtime.exceptions.GCodeSyntaxException;

import java.io.File;
import java.util.Locale;
import java.util.Objects;

public class GInterpreter {

    public GInterpreter(final String path) {
        Locale.setDefault(Locale.US);
        GGlobal.interpreter = this;
        new GCodeDefault();
        final String contents = Reader.read(path);
        GGlobal.mainFileLines = contents.split("\0");
        GGlobal.mainFile = new File(path);
        interpret(contents, "");

    }


    public void interpret(final String contents, final String functionName) {
        GGlobal.actFunction = functionName;
        String[] lines = contents.split("\0");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            GGlobal.actLine = i + 1;
            if (!Objects.equals(line, "")) {
                String aline = line.substring(1);

                String[] c = aline.split(" ");
                aline = aline.replace("\\s", " ");
                for (int i2 = 0; i2 < c.length; i2++)
                    c[i2] = c[i2].replace("\\s", " ");
                switch (line.charAt(0)) {
                    case '+':
                        VariableCreateType type1 = VariableCreateType.NONE;
                        VariableType type = VariableType.STRING;
                        try {
                            type = VariableType.valueOf(c[2].toUpperCase());
                        } catch (ArrayIndexOutOfBoundsException ignore) {
                        } catch (IllegalArgumentException e) {
                            throw new GCodeSyntaxException(e.getMessage());
                        }

                        try {
                            type1 = VariableCreateType.valueOf(c[3].toUpperCase());
                        } catch (ArrayIndexOutOfBoundsException ignore) {
                        } catch (IllegalArgumentException e) {
                            throw new GCodeSyntaxException(e.getMessage());
                        }
                        addVar(c[0], c[1], type, type1);
                        break;
                    case '@':
                        String[] cz = new String[c.length - 1];
                        System.arraycopy(c, 1, cz, 0, c.length - 1);
                        executeAction(c[0], cz);
                        break;
                    case '-':
                        removeVar(c[0]);
                        break;
                    case '=':
                        setVar(c[0], c[1]);
                        break;
                    case '{':
                        while (aline.charAt(0) == ' ') {
                            aline = aline.substring(1);
                        }
                        StringBuilder functionContents = new StringBuilder();
                        int l = 1;
                        for (int i2 = i + 1; i2 < lines.length; i2++) {
                            if (lines[i2].equals("}")) {
                                lines[i2] = "";
                                l--;
                                if (l == 0)
                                    break;
                            }
                            try {
                                if (lines[i2].charAt(0) == '{')
                                    l++;
                            } catch (StringIndexOutOfBoundsException ignore) {
                            }

                            functionContents.append(lines[i2]).append("\0");
                            lines[i2] = "";
                        }
                        addFunction(aline, functionContents.toString());
                        break;
                    case 'r':
                        if (!Objects.equals(functionName, "")) {
                            VariableType type2 = VariableType.STRING;
                            try {
                                type2 = VariableType.valueOf(c[2].toUpperCase());
                            } catch (ArrayIndexOutOfBoundsException ignore) {
                            } catch (IllegalArgumentException e) {
                                throw new GCodeSyntaxException(e.getMessage());
                            }
                            addVar(functionName, c[1], type2, VariableCreateType.FINAL);
                        } else {
                            throw new GCodeSyntaxException("Cannot use \"r\" outside on the global scope!");
                        }

                        break;
                    case '.':
                        interpret(Reader.read(GGlobal.mainFile.getParent() + "\\" + c[0].replace(".", "/") + ".gcode"), "");
                        break;
                    case '*':
                        break;
                    default:
                        throw new GCodeSyntaxException("\"" + line.charAt(0) + "\" is not a gcode starter.");
                }
            }
        }
    }

    public void addFunction(final String name, final String contents) {
        Function function = new Function();
        function.name = name;
        function.contents = contents;
        GGlobal.functions.add(function);
    }

    public void executeAction(final String actionName, final String[] variableNames) {
        Action a1 = null;
        Function f1 = null;
        final Variable[] v2 = new Variable[variableNames.length];
        int _index = 0;
        if (variableNames.length > 0) {
            for (Variable v : GGlobal.variables) {
                try {
                    if (v.name.hashCode() == variableNames[_index].hashCode()) {
                        if (v.name.equals(variableNames[_index])) {
                            v2[_index] = v;
                            _index++;
                        }
                    }

                } catch (ArrayIndexOutOfBoundsException ignore) {
                }

            }
            _index = 0;
            for (Variable variable : v2) {
                if (variable == null && !Objects.equals(variableNames[_index], "")) {
                    variable = new Variable();
                    variable.name = "action var";
                    variable.setValue(variableNames[_index]);
                    variable.createType = VariableCreateType.FINAL;
                    variable.type = VariableType.STRING;
                    v2[_index] = variable;
                }
                _index++;
            }
        }

        for (Function f : GGlobal.functions) {
            if (f.name.hashCode() == actionName.hashCode()) {
                if (f.name.equals(actionName)) {
                    f1 = f;
                    break;
                }
            }
        }
        if (f1 == null) {
            for (Action a : GGlobal.actions) {
                if (a.name().hashCode() == actionName.hashCode()) {
                    if (a.name().equals(actionName)) {
                        a1 = a;
                        break;
                    }
                }
            }
        }
        if (a1 == null && f1 == null)
            throw new GCodeSyntaxException("Cannot find \"" + actionName + "\"!");
        if (a1 != null) {
            a1.call(v2);
        } else {
            interpret(f1.contents, f1.name);
        }

        _index = 0;
        for (Variable variable : v2) {
            if (variable.createType == VariableCreateType.ONE) {
                removeVar(variable.name);
            }
            _index++;
        }

    }

    public void removeVar(final String name) {
        boolean removed = false;
        for (Variable v : GGlobal.variables) {
            if (v.name.hashCode() == name.hashCode() && v.name.equals(name)) {
                GGlobal.variables.remove(v);
                removed = true;
                break;
            }
        }
        if (!removed)
            throw new GCodeSyntaxException("Cannot find \"" + name + "\" variable.");
    }

    public void addVar(final String name, final String value, final VariableType type, final VariableCreateType createType) {
        Variable v1 = new Variable(name, value, type, createType);
        GGlobal.variables.add(v1);
    }

    public void setVar(final String name, final String value) {
        Variable toSet = null;
        for (Variable v : GGlobal.variables) {
            if (v.name.hashCode() == name.hashCode()) {
                if (v.name.equals(name)) {
                    toSet = v;
                    break;
                }
            }
        }
        try {
            assert toSet != null;
            if (toSet.createType == VariableCreateType.FINAL)
                throw new GCodeSyntaxException("Cannot set the value of \"" + name + "\" variable, because it is a final variable.");
            if (toSet.createType == VariableCreateType.ONE)
                throw new GCodeSyntaxException("Cannot set the value of \"" + name + "\" variable, because it is a one use variable.");
            toSet.setValue(value);
        } catch (NullPointerException e) {
            throw new GCodeSyntaxException("Cannot find \"" + name + "\" variable.");
        }
    }
}


