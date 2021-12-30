package com.vto.gcode.runtime.exceptions;

import com.vto.gcode.runtime.GGlobal;

import java.util.Objects;

public class GCodeSyntaxException extends GCodeRuntimeException {

    public GCodeSyntaxException(String msg) {
        super(GGlobal.actFunction + ": " + "In line " + GGlobal.actLine + ": " + msg);
    }

}
