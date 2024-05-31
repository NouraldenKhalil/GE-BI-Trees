package Models;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class BinaryTreeModel {
    //Attributes:
    private static BinaryNodeModel root;

    //Constructors:
    //Virtual Constructor:
    public BinaryTreeModel() {
    }

    ;

    //Constructor for root:
    public BinaryTreeModel(BinaryNodeModel root) {
        this.root = root;
    }

    //Setter:
    public void setRoot(BinaryNodeModel root) {
        this.root = root;
    }

    //Getter:
    public BinaryNodeModel getRoot() {
        return root;
    }

    //hasChildren method:
    //this method check if the node has children or not.
    //if it has children return the line that contain its children.
    //else if it doesn't have children return null.
    private String hasChildren(BinaryNodeModel node, String filePath) throws IOException {
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

    //importFromFileToBinaryTree method:
    //This method reads the text from the file and convert it to Binary Tree.

    public BinaryTreeModel importFromFileToBinaryTree(String filePath) throws IOException {
        BinaryTreeModel binaryTree=new BinaryTreeModel();
        LinkedList<BinaryNodeModel> queue=new LinkedList<>();//for travel by level order
        FileReader fileReader=new FileReader(filePath+".txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
            //find the root: it's the first Letter in the first Line.
            String firstLine=bufferedReader.readLine();
            for (char c:firstLine.toCharArray()){
                if (Character.isLetter(c)){  //is found -> set root then add into the queue.
                    BinaryNodeModel root=new BinaryNodeModel(c);
                    binaryTree.setRoot(root);
                    queue.add(root);
                    break;
                }
            }
            //find the children of each node:
            while (!queue.isEmpty()){
                BinaryNodeModel currentNode=queue.remove();
                String line= hasChildren(currentNode,filePath);//Check if it has children and get the Line of the children.
                if (line!=null){
                    int i=line.length();
                    while (--i>=0 && line.toCharArray()[i]!=currentNode.getValue()){
                        if(Character.isLetter(line.toCharArray()[i])){
                            if(currentNode.getRight()==null){
                                BinaryNodeModel right=new BinaryNodeModel(line.toCharArray()[i]);
                                currentNode.setRight(right);
                                queue.add(right);
                            }
                            else if(currentNode.getLeft()==null){
                                BinaryNodeModel left=new BinaryNodeModel(line.toCharArray()[i]);
                                currentNode.setLeft(left);
                                queue.add(left);
                            }
                            else
                                break;
                        }
                    }
                }
            }
        return binaryTree;
    }

    //exportFromBinaryTreeToFile method:
    //this method converts the binary tree to text which write it in file.
    public void exportFromBinaryTreeToFile() throws IOException {
        exportFromBinaryTreeToFile(getRoot());
    }
    private void exportFromBinaryTreeToFile(BinaryNodeModel root) throws IOException {
        if(root==null)
            return;
            File file=new File("output File.txt");
            FileWriter fileWriter=new FileWriter(file);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            LinkedList<BinaryNodeModel> queue=new LinkedList<>();//for travel by level order
            queue.add(root);
            while (!queue.isEmpty()){
                BinaryNodeModel currentNode=queue.remove();
                if(currentNode.getLeft()==null && currentNode.getRight()==null)
                    //the node doesn't have any child.
                    continue;
                //bufferedWriter.write(currentNode.getValue()+" -> ");
                else if(currentNode.getLeft()!=null && currentNode.getRight()!=null){
                    //the node has two children.
                    bufferedWriter.write(currentNode.getValue()+" -> "+currentNode.getLeft().getValue()+", "+currentNode.getRight().getValue());
                    queue.add(currentNode.getLeft());
                    queue.add(currentNode.getRight());
                } else if (currentNode.getLeft()!=null && currentNode.getRight()==null){
                    //the node has only left child.
                    bufferedWriter.write(currentNode.getValue()+" -> "+currentNode.getLeft().getValue());
                    queue.add(currentNode.getLeft());
                }else {
                    //the node has only right child.
                    bufferedWriter.write(currentNode.getValue()+" -> "+currentNode.getRight().getValue());
                    queue.add(currentNode.getRight());
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
    }

    //convertFromBinaryToGeneric method:
    //this function converts the binary tree to generic tree.
    public GenericTreeModel convertFromBinaryToGeneric(){
        if(getRoot()==null)
            return null;
        // the rootBinaryTree is the same rootGenericTree.
        GenericNodeModel rootGenericTree=new GenericNodeModel(getRoot().getValue());
        GenericTreeModel genericTree=new GenericTreeModel(rootGenericTree);
        return convertFromBinaryToGeneric(getRoot().getRight(),rootGenericTree,genericTree);
    }
    private GenericTreeModel convertFromBinaryToGeneric(BinaryNodeModel binaryRoot, GenericNodeModel genericRoot, GenericTreeModel genericTree){
        if(root==null)
            return null;
        if (binaryRoot.getLeft()!=null)
            convertFromBinaryToGeneric(binaryRoot.getLeft(),genericRoot,genericTree);
        GenericNodeModel child=new GenericNodeModel(binaryRoot.getValue());
        genericRoot.getChildren().add(child);
        if (binaryRoot.getRight()!=null)
            convertFromBinaryToGeneric(binaryRoot.getRight(),child,genericTree);
        return genericTree;
    }

    public ArrayList<BinaryOvalModel> getBinaryOvals(){
        return getBinaryOvals(getRoot());
    }

    ArrayList<BinaryOvalModel> ovals=new ArrayList<>();

    private ArrayList<BinaryOvalModel> getBinaryOvals(BinaryNodeModel root) {
        if (root==null)
            return null;
        LinkedList<BinaryOvalModel> queue = new LinkedList<>();
        queue.add(new BinaryOvalModel(root, 0, 0, 700, 350));
        while (!queue.isEmpty()) {
            BinaryOvalModel currentNode = queue.remove();
            if (currentNode.getLeft() != null) {
                int x1 = currentNode.getX1();
                int x2 = currentNode.getX1() + (currentNode.getX2() - currentNode.getX1()) / 2;
                queue.add(new BinaryOvalModel(currentNode.getLeft(), currentNode.getLevel() + 1, x1, x2, (currentNode.getX1() + currentNode.getX2()) / 2));
            }
            if (currentNode.getRight() != null) {
                int x1 = currentNode.getX1() + (currentNode.getX2() - currentNode.getX1()) / 2;
                int x2 = currentNode.getX1() + 2 * (currentNode.getX2() - currentNode.getX1()) / 2;
                queue.add(new BinaryOvalModel(currentNode.getRight(), currentNode.getLevel() + 1, x1, x2, (currentNode.getX1() + currentNode.getX2()) / 2));
            }
            ovals.add(currentNode);
        }
        return ovals;
    }
}