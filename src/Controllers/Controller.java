package Controllers;

import Models.BinaryOvalModel;
import Models.BinaryTreeModel;
import Models.GenericTreeModel;
import Models.OvalModel;
import Views.BI2GE_TreeView;
import Views.GE2BI_TreeView;
import Views.MainMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private MainMenuView mainMenuView;
    private GE2BI_TreeView ge2BI_treeView;
    private BI2GE_TreeView bi2GE_treeView;

    GenericTreeModel genericTreeModel;
    BinaryTreeModel binaryTreeModel;

    public Controller(MainMenuView mainMenuView, GE2BI_TreeView ge2BI_treeView, BI2GE_TreeView bi2GE_treeView,
                      GenericTreeModel genericTreeModel, BinaryTreeModel binaryTreeModel) {
        this.mainMenuView = mainMenuView;
        this.ge2BI_treeView = ge2BI_treeView;
        this.bi2GE_treeView = bi2GE_treeView;
        this.genericTreeModel = genericTreeModel;
        this.binaryTreeModel = binaryTreeModel;

        this.mainMenuView.getSelectGenericTreeButton().addActionListener(new SelectGenericTreeButtonListener());
        this.mainMenuView.getSelectBinaryTreeButton().addActionListener(new SelectBinaryTreeButtonListener());
        this.ge2BI_treeView.getConvertButton().addActionListener(new ConvertFromGenericToBinaryButtonListener());
        this.bi2GE_treeView.getConvertButton().addActionListener(new ConvertFromBinaryToGenericButtonListener());
    }
    class SelectGenericTreeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String filePath=mainMenuView.getGenericTreePath();
            try {
                genericTreeModel=genericTreeModel.importFromFileToGenericTree(filePath);
                ArrayList<OvalModel> ovals=new ArrayList<>();
                ovals=genericTreeModel.getOvals();
                ge2BI_treeView.setGenericOvals(ovals);
                mainMenuView.dispose();
                ge2BI_treeView.setVisible(true);
            }catch (FileNotFoundException ex){
                mainMenuView.addFileNotFoundMessageToG();
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class SelectBinaryTreeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String filePath=mainMenuView.getBinaryTreePath();
            try {
                binaryTreeModel=binaryTreeModel.importFromFileToBinaryTree(filePath);
                ArrayList<BinaryOvalModel> ovals=new ArrayList<>();
                ovals=binaryTreeModel.getBinaryOvals();
                bi2GE_treeView.setBinaryOval(ovals);
                mainMenuView.dispose();
                bi2GE_treeView.setVisible(true);
            }catch (FileNotFoundException ex){
                mainMenuView.addFileNotFoundMessageToB();
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class ConvertFromGenericToBinaryButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            binaryTreeModel=genericTreeModel.convertFromGenericToBinary();
            ArrayList<BinaryOvalModel> ovals=new ArrayList<>();
            ovals=binaryTreeModel.getBinaryOvals();
            ge2BI_treeView.setBinaryOval(ovals);
            ge2BI_treeView.hideGuideLine();
            try {
                binaryTreeModel.exportFromBinaryTreeToFile();
                ge2BI_treeView.showPathOfOutput();
                ge2BI_treeView.repaint();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class ConvertFromBinaryToGenericButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            genericTreeModel=binaryTreeModel.convertFromBinaryToGeneric();
            ArrayList<OvalModel> ovals=new ArrayList<>();
            ovals=genericTreeModel.getOvals();
            bi2GE_treeView.setGenericOvals(ovals);
            bi2GE_treeView.hideGuideLine();
            try {
                genericTreeModel.exportFromGenericTreeToFile();
                bi2GE_treeView.showPathOfOutput();
                bi2GE_treeView.repaint();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
