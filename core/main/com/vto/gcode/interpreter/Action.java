package com.vto.gcode.interpreter;

import com.vto.gcode.runtime.Variable;

public abstract class Action {

    public abstract String name();
    public abstract void call(Variable[] args);

}
