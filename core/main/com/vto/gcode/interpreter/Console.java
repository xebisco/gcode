package com.vto.gcode.interpreter;

import com.vto.gcode.runtime.GGlobal;
import com.vto.gcode.runtime.Variable;

public class Console {

    public Console() {
        GGlobal.actions.add(new Action() {
            @Override
            public String name() {
                return "print";
            }

            @Override
            public void call(Variable[] args) {
                for (Variable variable : args) {
                    System.out.print(variable.getValue());
                }
            }
        });
        GGlobal.actions.add(new Action() {
            @Override
            public String name() {
                return "println";
            }

            @Override
            public void call(Variable[] args) {
                if(args.length == 0) {
                    System.out.println();
                }
                for (Variable variable : args) {
                    System.out.println(variable.getValue());
                }
            }
        });
    }

}
