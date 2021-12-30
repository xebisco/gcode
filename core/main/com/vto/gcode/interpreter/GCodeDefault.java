package com.vto.gcode.interpreter;

import com.vto.gcode.runtime.GGlobal;
import com.vto.gcode.runtime.Variable;
import com.vto.gcode.runtime.VariableCreateType;
import com.vto.gcode.runtime.VariableType;

public class GCodeDefault {

    public GCodeDefault() {
        GGlobal.variables.add(new Variable("gcodeVersion", "dev1", VariableType.STRING, VariableCreateType.FINAL));
        GGlobal.actions.add(new Action() {
            @Override
            public String name() {
                return "begin";

            }

            @Override
            public void call(Variable[] args) {
                int l = Integer.parseInt(args[0].getValue().toString()) - 1;
                StringBuilder toCallContents = new StringBuilder();
                for (int i = l; i < GGlobal.mainFileLines.length; i++) {
                    toCallContents.append(GGlobal.mainFileLines[i]).append('\0');
                }
                GGlobal.interpreter.interpret(toCallContents.toString(), "");
            }
        });
        GGlobal.actions.add(new Action() {
            @Override
            public String name() {
                return "execute";

            }

            @Override
            public void call(Variable[] args) {
                int l = Integer.parseInt(args[0].getValue().toString()) - 1;
                GGlobal.interpreter.interpret(GGlobal.mainFileLines[l], "");
            }
        });
        GGlobal.actions.add(new Action() {
            @Override
            public String name() {
                return "wait";
            }

            @Override
            public void call(Variable[] args) {
                long l = Long.parseLong(args[0].getValue().toString()) - 1;
                try {
                    Thread.sleep(l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        new Math();
        new Console();
    }

}
