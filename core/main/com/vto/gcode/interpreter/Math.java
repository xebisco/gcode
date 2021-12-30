package com.vto.gcode.interpreter;

import com.vto.gcode.runtime.GGlobal;
import com.vto.gcode.runtime.Variable;
import com.vto.gcode.runtime.VariableCreateType;
import com.vto.gcode.runtime.VariableType;

import java.util.Locale;

public class Math {

    public Math() {
        GGlobal.actions.add(new Action() {
            @Override
            public String name() {
                return "sum";
            }

            @Override
            public void call(Variable[] args) {
                String type = args[0].getValue().toString();
                if (type.equalsIgnoreCase("int")) {
                    int value = 0;
                    for (int i = 1; i < args.length; i++) {
                        value += Integer.parseInt(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.INT, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("float")) {
                    float value = 0;
                    for (int i = 1; i < args.length; i++) {
                        value += Float.parseFloat(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.FLOAT, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("long")) {
                    long value = 0;
                    for (int i = 1; i < args.length; i++) {
                        value += Long.parseLong(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.LONG, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("double")) {
                    double value = 0;
                    for (int i = 1; i < args.length; i++) {
                        value += Double.parseDouble(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.DOUBLE, VariableCreateType.FINAL));
                }
            }
        });
        GGlobal.actions.add(new Action() {
            @Override
            public String name() {
                return "minus";
            }

            @Override
            public void call(Variable[] args) {
                String type = args[0].getValue().toString();
                if (type.equalsIgnoreCase("int")) {
                    int value = Integer.parseInt(args[1].getValue().toString()) * 2;
                    for (int i = 1; i < args.length; i++) {
                        value -= Integer.parseInt(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.INT, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("float")) {
                    float value = Float.parseFloat(args[1].getValue().toString()) * 2;
                    for (int i = 1; i < args.length; i++) {
                        value -= Float.parseFloat(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.FLOAT, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("long")) {
                    long value = Long.parseLong(args[1].getValue().toString()) * 2;
                    for (int i = 1; i < args.length; i++) {
                        value -= Long.parseLong(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.LONG, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("double")) {
                    double value = Double.parseDouble(args[1].getValue().toString()) * 2;
                    for (int i = 1; i < args.length; i++) {
                        value -= Double.parseDouble(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.DOUBLE, VariableCreateType.FINAL));
                }
            }
        });
        GGlobal.actions.add(new Action() {
            @Override
            public String name() {
                return "multiply";
            }

            @Override
            public void call(Variable[] args) {
                String type = args[0].getValue().toString();
                if (type.equalsIgnoreCase("int")) {
                    int value = 1;
                    for (int i = 1; i < args.length; i++) {
                        value *= Integer.parseInt(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.INT, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("float")) {
                    float value = 1;
                    for (int i = 1; i < args.length; i++) {
                        value *= Float.parseFloat(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.FLOAT, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("long")) {
                    long value = 1;
                    for (int i = 1; i < args.length; i++) {
                        value *= Long.parseLong(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.LONG, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("double")) {
                    double value = 1;
                    for (int i = 1; i < args.length; i++) {
                        value *= Double.parseDouble(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.DOUBLE, VariableCreateType.FINAL));
                }
            }
        });
        GGlobal.actions.add(new Action() {
            @Override
            public String name() {
                return "divide";
            }

            @Override
            public void call(Variable[] args) {
                String type = args[0].getValue().toString();
                if (type.equalsIgnoreCase("int")) {
                    int value = Integer.parseInt(args[1].getValue().toString()) * Integer.parseInt(args[1].getValue().toString());
                    for (int i = 1; i < args.length; i++) {
                        value /= Integer.parseInt(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.INT, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("float")) {
                    float value = Float.parseFloat(args[1].getValue().toString()) * Float.parseFloat(args[1].getValue().toString());
                    for (int i = 1; i < args.length; i++) {
                        value /= Float.parseFloat(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.FLOAT, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("long")) {
                    long value = Long.parseLong(args[1].getValue().toString()) * Long.parseLong(args[1].getValue().toString());
                    for (int i = 1; i < args.length; i++) {
                        value /= Long.parseLong(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.LONG, VariableCreateType.FINAL));
                } else if (type.equalsIgnoreCase("double")) {
                    double value = Double.parseDouble(args[1].getValue().toString()) * Double.parseDouble(args[1].getValue().toString());
                    for (int i = 1; i < args.length; i++) {
                        value /= Double.parseDouble(args[i].getValue().toString());
                    }
                    GGlobal.variables.add(new Variable(name(), value, VariableType.DOUBLE, VariableCreateType.FINAL));
                }
            }
        });
    }

}
