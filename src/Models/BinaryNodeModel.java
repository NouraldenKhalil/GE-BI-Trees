package Models;

public class BinaryNodeModel {

    //Attributes:
    private char value;
    private BinaryNodeModel left;
    public BinaryNodeModel right;

    //Constructors:
    //Virtual Constructor:
    public BinaryNodeModel(){
    }

    //Constructor for value:
    public BinaryNodeModel(char value) {
        this.value = value;
    }

    //Constructor for value, left and right:
    public BinaryNodeModel(char value, BinaryNodeModel left, BinaryNodeModel right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    //Setters:
    public void setValue(char value) {
        this.value = value;
    }

    public void setLeft(BinaryNodeModel left) {
        this.left = left;
    }

    public void setRight(BinaryNodeModel right) {
        this.right = right;
    }

    //Getters:
    public char getValue() {
        return value;
    }

    public BinaryNodeModel getLeft() {
        return left;
    }

    public BinaryNodeModel getRight() {
        return right;
    }
}
