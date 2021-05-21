package forest;

import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import mvc.Model;

//追加分
import java.io.File;

/**
 * モデル。
 */
public class ForestModel extends Model
{
    /**
     * ForestクラスのインスタンスaForestを束縛する。
     */
    private Forest aForest;
    
	/**
	 * 木データaForestインスタンスからフォレストモデルを作るコンストラクタ。
     * @param textFileName 木データであるテキストのファイル名
	 */
	public ForestModel(File file)
	{
		super();
        this.aForest = new Forest();
        this.aForest.setModel(this);
        try
        {
            this.aForest.readText(file);
        }
        catch(IOException anException)
        {
            anException.printStackTrace();
        }
        aForest.setRoot();
	}
    
    /**
     * Forestのgetter
     * @return Forest型のインスタンス
     */
    public Forest getForest()
    {
        return this.aForest;
    }
    
	/**
	 * マウスクリックした位置を座標として受け取り、その位置にNodeがあれば出力
     * @param aPoint ピクチャ座標
     * @param aMouseEvent マウスのイベント
	 */
	public void mouseClicked(Point aPoint, MouseEvent aMouseEvent)
	{
		//ゴリ押し検出
        HashMap<Integer,Node> nodes = aForest.getNodes();
        for (Node node : nodes.values()) {
            if(node.getLocation().x <= aPoint.x && node.getLocation().x+node.getWidth() >= aPoint.x)
            {
                if(node.getLocation().y <= aPoint.y && node.getLocation().y+node.getHeight() >= aPoint.y)
                {
                    System.out.println("Clicked Node Name = ["+node.getName()+"]");
                }
            }
        }
		return;
	}
	
	/**
	 * マウスドラッグした位置をピクチャ座標として受け取り、それをただ出力する。
     * @param aPoint ピクチャ座標
     * @param aMouseEvent マウスのイベント
	 */
	public void mouseDragged(Point aPoint, MouseEvent aMouseEvent)
	{
		System.out.println(aPoint);
		return;
	}
    
}
