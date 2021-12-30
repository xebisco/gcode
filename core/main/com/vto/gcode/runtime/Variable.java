package com.vto.gcode.runtime;

public class Variable {

    public String name;
    private Object value;
    public VariableCreateType createType;
    public VariableType type;

    public Variable() {
    }

    public Variable(String name, Object value, VariableType type, VariableCreateType createType) {
        this.type = type;
        this.name = name;
        setValue(value);
        this.createType = createType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        if (type == VariableType.INT) {
            this.value = Integer.parseInt(value.toString());
        } else if (type == VariableType.FLOAT) {
            this.value = Float.parseFloat(value.toString());
        } else if (type == VariableType.DOUBLE) {
            this.value = Double.parseDouble(value.toString());
        } else if (type == VariableType.BOOL) {
            this.value = Boolean.parseBoolean(value.toString());
        } else if (type == VariableType.LONG) {
            this.value = Long.parseLong(value.toString());
        } else {
            this.value = value;
        }
    }
}
