package Models;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class GenericTreeModel {
    //Attributes:
    private GenericNodeModel root;

    //Constructors:
    //Virtual Constructor:
    public GenericTreeModel(){};

    //Constructor for root:
    public GenericTreeModel(GenericNodeModel root) {
        this.root = root;
    }

    //Setter:
    public void setRoot(GenericNodeModel root) {
        this.root = root;
    }

    //Getter:
    public GenericNodeModel getRoot() {
        return root;
    }

    //hasChildren method:
    //this method check if the node has children or not.
    //if it has children return the line that contain its children.
    //else if it doesn't have children return null.
    private String hasChildren(GenericNodeModel node, String filePath) throws IOException {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath+".txt"));
            String line;
            //enough to compare value of node and the first line in all line.
            while ((line=bufferedReader.readLine())!=null){
                char firstCharInLine = 0;
                for (char c:line.toCharArray()){
                    if(Character.isLetter(c)){
                        firstCharInLine=c;
                        break;
                    }
                }
                if(firstCharInLine==node.getValue()){
                    return line;
                }
            }
        return null;
    }

    //importFromFileToGenericTree method:
    //This method reads the text from the file and convert it to Generic Tree.
    public GenericTreeModel importFromFileToGenericTree(String filePath) throws IOException {
        GenericTreeModel genericTree=new GenericTreeModel();
        LinkedList<GenericNodeModel> queue=new LinkedList<>();//for travel by level order
            FileReader fileReader=new FileReader(filePath+".txt");
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            //find the root: it's the first Letter in the first Line.
            String firstLine=bufferedReader.readLine();
            for (char c:firstLine.toCharArray()){
                if (Character.isLetter(c)){  //is found -> set root then add into the queue.
                    GenericNodeModel root=new GenericNodeModel(c);
                    genericTree.setRoot(root);
                    queue.add(root);
                    break;
                }
            }
            //find the children of each node:
            while (!queue.isEmpty()){
                GenericNodeModel currentNode=queue.remove();
                String line= hasChildren(currentNode,filePath);//Check if it has children and get the Line of the children.
                if (line!=null){
                    ArrayList<GenericNodeModel> children=new ArrayList<>();
                    for(char c:line.toCharArray()){
                        if(Character.isLetter(c) && c!=currentNode.getValue()){
                            GenericNodeModel node=new GenericNodeModel(c);
                            children.add(node);
                            queue.add(node);
                        }
                    }
                    currentNode.setChildren(children);
                }
            }

        return genericTree;
    }

    //exportFromGenericTreeToFile method:
    //this method converts the generic tree to text which write it in file.
    public void exportFromGenericTreeToFile() throws IOException {
        exportFromGenericTreeToFile(getRoot());
    }

    private void exportFromGenericTreeToFile(GenericNodeModel root) throws IOException {
        if(root==null)
            return;
            FileWriter fileWriter=new FileWriter("output File.txt");
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            LinkedList<GenericNodeModel> queue=new LinkedList<>();//for travel by level order
            queue.add(root);
            while (!queue.isEmpty()){//pass on each node and writes the values of its children in the file then adds them into the queue.
                GenericNodeModel currentNode=queue.remove();
                if(!currentNode.getChildren().isEmpty()){
                    bufferedWriter.write(currentNode.getValue()+" - > ");

                    for (int i=0;i<currentNode.getChildren().size()-1;i++){
                        bufferedWriter.write(currentNode.getChildren().get(i).getValue()+", ");
                        queue.add(currentNode.getChildren().get(i));
                    }
                    bufferedWriter.write(currentNode.getChildren().get(currentNode.getChildren().size()-1).getValue());
                    bufferedWriter.newLine();
                    queue.add(currentNode.getChildren().get(currentNode.getChildren().size()-1));
                }

            }
            bufferedWriter.close();
    }


    //convertFromGenericToBinary method:
    //this function converts the generic tree to binary tree.
    public BinaryTreeModel convertFromGenericToBinary(){
        return convertFromGenericToBinary(getRoot());
    }

    private BinaryTreeModel convertFromGenericToBinary(GenericNodeModel rootGenericTree) {
        if (rootGenericTree == null)
            return null;
        //the rootBinaryTree is the same rootGenericTree:
        BinaryNodeModel rootBinaryTree = new BinaryNodeModel(rootGenericTree.getValue());
        BinaryTreeModel binaryTree=new BinaryTreeModel(rootBinaryTree);
        //Stack for generic nodes:
        Stack<GenericNodeModel> genericNodeStack=new Stack<>();
        //Stack for binary nodes:
        Stack<BinaryNodeModel> binaryNodeStack=new Stack<>();
        genericNodeStack.add(rootGenericTree);
        binaryNodeStack.add(rootBinaryTree);
        while (!genericNodeStack.isEmpty()){
            GenericNodeModel currentGenericNode=genericNodeStack.pop();
            BinaryNodeModel currentBinaryNode=binaryNodeStack.pop();
            ArrayList<GenericNodeModel> childrenOfCurrentGenericNode=currentGenericNode.getChildren();
            if (!childrenOfCurrentGenericNode.isEmpty()){
                //the right child in the binary tree is the right child in the generic tree:
                GenericNodeModel rightGenericNode=childrenOfCurrentGenericNode.get(childrenOfCurrentGenericNode.size()-1);
                BinaryNodeModel rightBinaryNode=new BinaryNodeModel(rightGenericNode.getValue());
                currentBinaryNode.setRight(rightBinaryNode);
                genericNodeStack.add(rightGenericNode);
                binaryNodeStack.add(rightBinaryNode);
            }
            //the remained children are left then left then left then .......
            for (int i=childrenOfCurrentGenericNode.size()-2;i>=0;i--){
                GenericNodeModel genericNode=new GenericNodeModel();
                genericNode=childrenOfCurrentGenericNode.get(i);
                BinaryNodeModel binaryNode=new BinaryNodeModel(genericNode.getValue());
                binaryNodeStack.peek().setLeft(binaryNode);
                genericNodeStack.add(genericNode);
                binaryNodeStack.add(binaryNode);
            }
        }
        return binaryTree;
    }

    public ArrayList<OvalModel> getOvals(){
        return getOvals(getRoot());
    }
    ArrayList <OvalModel> ovals=new ArrayList<>();
    private ArrayList<OvalModel> getOvals(GenericNodeModel root) {
        if (root==null)
            return null;
        LinkedList<OvalModel> queue = new LinkedList<>();
        queue.add(new OvalModel(root, 0, 0, 700, 350));
        while (!queue.isEmpty()) {
            OvalModel currentNode = queue.remove();
            ArrayList<GenericNodeModel> children = currentNode.getChildren();
            for (int i = 0; i < children.size(); i++) {
                int x1 = currentNode.getX1() + i * (currentNode.getX2() - currentNode.getX1()) / children.size();
                int x2 = currentNode.getX1() + (i + 1) * (currentNode.getX2() - currentNode.getX1()) / children.size();
                queue.add(new OvalModel(children.get(i), currentNode.getLevel() + 1, x1, x2, (currentNode.getX1() + currentNode.getX2()) / 2));
            }
            ovals.add(currentNode);
        }
        return ovals;
    }
}