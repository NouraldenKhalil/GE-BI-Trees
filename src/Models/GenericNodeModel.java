package Models;

import java.util.ArrayList;

public class GenericNodeModel {
    //Attributes:
    private char value;
    private ArrayList<GenericNodeModel> children=new ArrayList<>();

    //Constructors:
    //Virtual Constructor:
    public GenericNodeModel() {
    }

    //Constructor for value:
    public GenericNodeModel(char value) {
        this.value = value;
    }

    //Constructor for value and children:
    public GenericNodeModel(char value, ArrayList<GenericNodeModel> children) {
        this.value = value;
        this.children = children;
    }

    //Setters:
    public void setValue(char value) {
        this.value = value;
    }

    public void setChildren(ArrayList<GenericNodeModel> children) {
        this.children = children;
    }

    //Getters:
    public char getValue() {
        return value;
    }

    public ArrayList<GenericNodeModel> getChildren() {
        return children;
    }

}
