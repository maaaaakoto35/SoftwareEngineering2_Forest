package forest;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


//追加分
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;

//追加分
import java.io.File;


public class Example extends Object
{
    
	/**
	 * メインプログラム。
	 */
	public static void main(String[] arguments) throws IOException
	{
        
        //ここからファイル選択
        SetFile set = new SetFile();
        File file = null;
        
        set.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        set.setBounds(Const.WINDOW_WIDTH + 2, 10, 300, 200);
        set.setTitle("ファイル名を選択");
        set.setVisible(true);
        
        //この処理何とかしてほしい　流石にかっこ悪いかと とりあえず動くようにって感じ
        while(file == null){
            file = set.getFile();
            System.out.print(""); //これ書かないと動かん
        }
       
	   	BufferedImage anImage = ImageIO.read(new File("white.jpg"));
	   	
		ForestModel aModel = new ForestModel(file);
        ForestController aController = new ForestController();//ここ変えた　ゆーた
		ForestView aView = new ForestView(aModel,aController);
        
        JFrame aWindow = new JFrame("Forest");
        aWindow.getContentPane().add(aView);
        aWindow.setLayout(null);
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int titleBarHeight = aWindow.getInsets().top;
		aWindow.getContentPane().setBackground(new Color(255,255,255));
		Point aPoint = new Point(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
		aWindow.setMinimumSize(new Dimension(aPoint.x, aPoint.y + titleBarHeight));
		aWindow.setResizable(true);
		aWindow.setSize(aPoint.x, aPoint.y + titleBarHeight);
		aWindow.setLocation(0, 0);
		
		aModel.picture(anImage);
		aModel.picture(anImage);
        aController.setView(aView);//ここ変えた　ゆーた
		aWindow.setVisible(true);
		aWindow.toFront();
		
        
		HashMap<Integer,Node> nodes = aModel.getForest().getNodes();
        for(Node node : aModel.getForest().getRoot())
        {
            aModel.getForest().visit(node, new Point(0,Forest.underLine ));
        }
        
		return;
	}
}
