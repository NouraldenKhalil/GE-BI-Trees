package Views;

import Controllers.ColorController;
import Models.BinaryOvalModel;
import Models.OvalModel;
import button.Button;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BI2GE_TreeView extends JFrame {
    TreePanel genericTreePanel=new TreePanel("Generic Tree",950);
    TreePanel binaryTreePanel=new TreePanel("Binary Tree",50);
    public BI2GE_TreeView(){
        setTitle("GE2BI Tree");
        setSize(1800,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorController.MainMenuColor);
        genericTreePanel.drawTreePanel.showGuideLine();

        add(genericTreePanel);
        add(binaryTreePanel);
    }
    public void setGenericOvals(ArrayList<OvalModel> ovals){
        genericTreePanel.drawTreePanel.setGenericOvals(ovals);
    }
    public void setBinaryOval(ArrayList<BinaryOvalModel> ovals){
        binaryTreePanel.drawTreePanel.setBinaryOvals(ovals);
    }
    public Button getConvertButton(){
        return binaryTreePanel.convertButton;
    }
    public void hideGuideLine(){
        genericTreePanel.drawTreePanel.hideGuideLine();
    }

    public void showPathOfOutput(){
        JLabel pathLabel=new JLabel("Open 'C:\\Users\\Dell\\IdeaProjects\\GE-BI-Trees-Project\\output File.txt' to see the text file.");
        pathLabel.setBounds(60, 775, 800, 100);
        pathLabel.setFont(new Font("Serif",Font.BOLD,18));
        pathLabel.setForeground(ColorController.instructionsLabelColor);
        genericTreePanel.add(pathLabel);

    }
}
