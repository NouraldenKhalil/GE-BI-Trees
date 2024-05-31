

import Controllers.ColorController;
import Controllers.Controller;
import Models.BinaryOvalModel;
import Models.BinaryTreeModel;
import Models.GenericTreeModel;
import Views.BI2GE_TreeView;
import Views.GE2BI_TreeView;
import Views.MainMenuView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MainMenuView mainMenuView=new MainMenuView();
        GE2BI_TreeView ge2BI_treeView=new GE2BI_TreeView();
        BI2GE_TreeView bi2GE_treeView=new BI2GE_TreeView();
        GenericTreeModel genericTreeModel=new GenericTreeModel();
        BinaryTreeModel binaryTreeModel=new BinaryTreeModel();
        Controller controller=new Controller(mainMenuView, ge2BI_treeView, bi2GE_treeView,genericTreeModel, binaryTreeModel );
    }
}