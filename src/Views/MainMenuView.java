package Views;

import Controllers.ColorController;
import PanelRound.PanelRound;
import button.Button;
import textfield.TextField;

import javax.swing.*;
import java.awt.*;

class OptionPanel extends PanelRound {
    JLabel optionLabel=new JLabel();
    TextField path=new TextField();
    Button select=new Button();

    public OptionPanel(String optionName){

        setSize(500,650);
        optionLabel.setBounds(110, 150, 500, 100);
        path.setBounds(75, 350, 350, 50);
        select.setBounds(300, 500, 150, 50);

        setRoundBottomLeft(50);
        setRoundBottomRight(50);
        setRoundTopLeft(50);
        setRoundTopRight(50);
        select.setRound(50);

        select.setFocusable(false);
        setLayout(null);

        path.setText("Paste path of the file");
        select.setText("Select");
        optionLabel.setText(optionName);

        optionLabel.setForeground(ColorController.OrderLabelColor);
        path.setForeground(ColorController.instructionsLabelColor);
        select.setForeground(ColorController.ChoosePanelColor);

        setBackground(ColorController.ChoosePanelColor);
        path.setBackground(ColorController.MainMenuColor);
        select.setBackground(ColorController.selectButtonColor);

        optionLabel.setFont(new Font("Serif", Font.BOLD, 50));
        path.setFont(new Font("Serif", Font.BOLD, 20));
        select.setFont(new Font("Serif", Font.BOLD, 20));

        add(optionLabel);
        add(path);
        add(select);

    }
}
public class MainMenuView extends JFrame {
    public OptionPanel binaryTreeOption=new OptionPanel("Binary Tree");
    public OptionPanel genericTreeOption =new OptionPanel("Generic Tree");

    public MainMenuView(){
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1800,1000);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorController.MainMenuColor);

        binaryTreeOption.setBounds(250,150,binaryTreeOption.getWidth(),binaryTreeOption.getHeight());
        genericTreeOption.setBounds(1050,150, genericTreeOption.getWidth(), genericTreeOption.getHeight());

        add(genericTreeOption);
        add(binaryTreeOption);
        setVisible(true);
    }
    public String getGenericTreePath(){
        return genericTreeOption.path.getText();
    }
    public String getBinaryTreePath(){
        return binaryTreeOption.path.getText();
    }

    public void addFileNotFoundMessageToG(){
        genericTreeOption.path.setText("File not found!");
    }
    public void addFileNotFoundMessageToB(){
        binaryTreeOption.path.setText("File not found!");
    }
    public Button getSelectGenericTreeButton(){
        return genericTreeOption.select;
    }
    public Button getSelectBinaryTreeButton(){
        return binaryTreeOption.select;
    }


}
