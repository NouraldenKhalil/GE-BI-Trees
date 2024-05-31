package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.jar.Attributes;

public class OvalModel extends GenericNodeModel {
    //Attributes:
    private int level;
    private int x1;
    private int x2;
    private int xLine;

    //Constructors:
    //Virtual Constructor:
    public OvalModel() {
    }

    //Constructor for all attributes:
    public OvalModel(GenericNodeModel node, int level, int x1, int x2, int xLine) {
        super(node.getValue(), node.getChildren());
        this.level = level;
        this.x1 = x1;
        this.x2 = x2;
        this.xLine = xLine;
    }

    //Getters:
    public int getLevel() {
        return level;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getXLine() {
        return xLine;
    }

}