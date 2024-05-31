package Views;

import Controllers.ColorController;
import Models.BinaryOvalModel;
import Models.OvalModel;
import PanelRound.PanelRound;
import button.Button;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


class DrawTreePanel extends PanelRound{
    ArrayList<OvalModel> genericOvals=null;
    ArrayList<BinaryOvalModel> binaryOvals=null;
    JLabel guideLine=new JLabel("Click on 'Convert' for drawing the binary tree!");
    DrawTreePanel(){
        setBounds(50,125,700,670);
        setRoundBottomLeft(50);
        setRoundBottomRight(50);
        setRoundTopLeft(50);
        setRoundTopRight(50);
        setLayout(null);
        setBackground(ColorController.selectButtonForgroundColor);
    }

    public void setGenericOvals(ArrayList<OvalModel> genericOvals) {
        this.genericOvals = genericOvals;
    }

    public void setBinaryOvals(ArrayList<BinaryOvalModel> binaryOvals) {
        this.binaryOvals = binaryOvals;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(genericOvals!=null){
            paintTree(g,genericOvals);
        }
        if (binaryOvals!=null){
            paintTree(binaryOvals, g);
        }
    }
    protected void paintTree(Graphics g,ArrayList<OvalModel> ovals){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        for (OvalModel oval:ovals){
            g2d.setColor(ColorController.selectButtonColor);
            int ovalX=(oval.getX1() + oval.getX2()) / 2 - 25;
            int ovalY= 100 * oval.getLevel();
            int ovalWidth=50;
            int ovalHeight=50;
            g2d.fillOval(ovalX,ovalY ,ovalWidth ,ovalHeight );
            g2d.drawLine(oval.getXLine(), ovalY - 50, (oval.getX1() + oval.getX2()) / 2, 25+ oval.getLevel() * 100);
            g2d.setColor(ColorController.selectButtonForgroundColor);

            String text = String.valueOf(oval.getValue());
            Font font = new Font("Serif", Font.BOLD, 20);
            g2d.setFont(font);
            FontMetrics metrics = g2d.getFontMetrics(font);
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();

            int x = ovalX + (ovalWidth - textWidth) / 2;
            int y = ovalY + ((ovalHeight - textHeight) / 2) + metrics.getAscent();

            g2d.drawString(text, x, y);
        }
    }
    protected void paintTree(ArrayList<BinaryOvalModel> ovals,Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (BinaryOvalModel oval : ovals) {
            g2d.setColor(ColorController.selectButtonColor);
            int ovalX = (oval.getX1() + oval.getX2()) / 2 - 25;
            int ovalY =  100 * oval.getLevel();
            int ovalWidth = 50;
            int ovalHeight = 50;
            g2d.fillOval(ovalX, ovalY, ovalWidth, ovalHeight);
            g2d.drawLine(oval.getXLine(), ovalY - 50, (oval.getX1() + oval.getX2()) / 2, 25 + oval.getLevel() * 100);
            g2d.setColor(ColorController.selectButtonForgroundColor);

            String text = String.valueOf(oval.getValue());
            Font font = new Font("Serif", Font.BOLD, 20);
            g2d.setFont(font);
            FontMetrics metrics = g2d.getFontMetrics(font);
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();

            int x = ovalX + (ovalWidth - textWidth) / 2;
            int y = ovalY + ((ovalHeight - textHeight) / 2) + metrics.getAscent();

            g2d.drawString(text, x, y);
        }
    }

    public void showGuideLine(){
        setBackground(ColorController.ChoosePanelColor);
        guideLine.setForeground(ColorController.instructionsLabelColor);
        guideLine.setFont(new Font("Serif", Font.BOLD, 20));
        guideLine.setBounds(150,275,700,100);
        add(guideLine);
    }
    public void hideGuideLine(){
        setBackground(ColorController.selectButtonForgroundColor);
        guideLine.setVisible(false);
    }
}
class TreePanel extends PanelRound{
    JLabel titleLabel=new JLabel();
    public DrawTreePanel drawTreePanel=new DrawTreePanel();
    public Button convertButton=new Button();
    public TreePanel(String title,int x){
        setLayout(null);
        setRoundBottomLeft(50);
        setRoundBottomRight(50);
        setRoundTopLeft(50);
        setRoundTopRight(50);
        convertButton.setRound(50);

        convertButton.setFocusable(false);

        titleLabel.setText(title);
        convertButton.setText("Convert");

        setBounds(x,25,800,900);
        titleLabel.setBounds(250, 25, 300, 100);
        convertButton.setBounds( 600, 825, 150, 50);

        setBackground(ColorController.ChoosePanelColor);
        convertButton.setBackground(ColorController.selectButtonColor);

        convertButton.setForeground(ColorController.selectButtonForgroundColor);
        titleLabel.setForeground(ColorController.OrderLabelColor);

        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        convertButton.setFont(new Font("Serif", Font.BOLD, 20));

        add(titleLabel);
        add(drawTreePanel);
        if(x==50)
            add(convertButton);
    }
}


public class GE2BI_TreeView extends JFrame {
    public TreePanel genericTreePanel=new TreePanel("Generic Tree",50);
    public TreePanel binaryTreePanel=new TreePanel("Binary Tree",950);
    public GE2BI_TreeView(){
        setTitle("GE2BI Tree");
        setSize(1800,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorController.MainMenuColor);

//        ArrayList<OvalModel> ovals=new ArrayList<>();
//        ovals.add(new OvalModel(new GenericNodeModel('A'),0,0,700,350));
//        ovals.add(new OvalModel(new GenericNodeModel('A'),1,0,233,350));
//        ovals.add(new OvalModel(new GenericNodeModel('A'),1,233,466,350));
//        ovals.add(new OvalModel(new GenericNodeModel('A'),1,466,699,350));
//
//        genericTreePanel.drawTreePanel.setGenericOval(ovals);
        binaryTreePanel.drawTreePanel.showGuideLine();

        add(genericTreePanel);
        add(binaryTreePanel);
    }
    public void setGenericOvals(ArrayList<OvalModel> ovals){
        genericTreePanel.drawTreePanel.setGenericOvals(ovals);
    }
    public void setBinaryOval(ArrayList<BinaryOvalModel> ovals){
        binaryTreePanel.drawTreePanel.setBinaryOvals(ovals);
    }
    public  Button getConvertButton(){
        return genericTreePanel.convertButton;
    }
    public void hideGuideLine(){
        binaryTreePanel.drawTreePanel.hideGuideLine();
    }
    public void showPathOfOutput(){
        JLabel pathLabel=new JLabel("Open 'C:\\Users\\Dell\\IdeaProjects\\GE-BI-Trees-Project\\output FIle.txt' to see the text file.");
        pathLabel.setBounds(60, 775, 800, 100);
        pathLabel.setFont(new Font("Serif",Font.BOLD,18));
        pathLabel.setForeground(ColorController.instructionsLabelColor);
        binaryTreePanel.add(pathLabel);

    }
}
