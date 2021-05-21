package forest;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mvc.View;

import java.awt.Container;

/**
 * 6/27 コーディング　担当：高橋
 * ForestViewクラス。
 */
public class ForestView extends View
{

    
    private Point offset;//追加
    
    private ForestModel model; //スーパークラスのmodelはModelなので動かんみたい
    private ForestController controller;
    
    /**
	 * 指定されたモデルとコントローラと自分（ビュー）とでMVCを構築するコンストラクタ。
	 */
	public ForestView(ForestModel aModel, ForestController aController)
	{
		super(aModel, aController);
        this.model = aModel;
        this.offset = new Point(0, 0);//追加
   		this.setLocation(offset.x,offset.y);

		this.controller = aController;
        this.setLayout(null);
        this.setSize(1200,1200);
		//this.setSize(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

        int y=0;
        HashMap<Integer,Node> nodes = this.model.getForest().getNodes();

        for(int index = 0; index <= nodes.size(); index++)
        {
            Node aNode = nodes.get(index);
            if(aNode != null) {
                aNode.setLocation(0,y);
                y += aNode.getSize().height+Const.VERTICAL_GAP;
                this.add(aNode);
            }
        }
	}

	
	/**
	 * 自分のモデルを応答する。
	 */
	public ForestModel getModel()
	{
		return (ForestModel)(this.model);
	}
	
    /**
	 * 線（Branch）を描く。
     */
    public void paintComponent(Graphics aGraphics)
    {
        super.paintComponent(aGraphics);
        int width = this.getWidth();
		int height = this.getHeight();
		//aGraphics.setColor(Color.white);
		//aGraphics.fillRect(offset.x, offset.y, width, height);
        aGraphics.setColor(Color.black);
        
        ArrayList<Branch> branches = this.model.getForest().getBranches();
        
		this.setLocation(offset.x,offset.y);
		for(Branch aBranch : branches)
        {
            Point parentPoint = aBranch.getParentNode().getLocation();
            Point childPoint = aBranch.getChildNode().getLocation();
            Dimension parentDimension = aBranch.getParentNode().getSize();
            Dimension childDimension = aBranch.getChildNode().getSize();
            
            aGraphics.drawLine(parentPoint.x + parentDimension.width,
					parentPoint.y + parentDimension.height/2,
					childPoint.x,
					childPoint.y + childDimension.height/2);
		
		}
		return;
	}

	public Point getOffset(){
		return offset;
	}

	public Point scrollAmount()
	{
		int x = 0 - this.offset.x;
		int y = 0 - this.offset.y;
		return (new Point(x, y));
	}

	/**
	 * スクロール量を指定された座標分だけ相対スクロールする。
	 * 良好（2010年7月25日）
	 */
	public void scrollBy(Point aPoint)
	{
		int x = this.offset.x + aPoint.x;
		int y = this.offset.y + aPoint.y;
		this.scrollTo(new Point(x, y));
		return;
	}

	/**
	 * スクロール量を指定された座標に設定（絶対スクロール）する。
	 * 良好（2010年7月25日）
	 */
	public void scrollTo(Point aPoint)
	{
		this.offset = aPoint;
		return;
	}




}
