package forest;

import java.awt.event.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import mvc.Controller;

//追加分
import java.awt.Component;
import java.awt.Cursor;
/**
 * 7/13 コーディング　担当：今江
 * コントローラ。
 */
public class ForestController extends Controller
{


	/**
	 * 上位コンストラクタを継承するただけのコンストラクタ。
	 */
	public ForestController()
	{
		super();
	}
    
	/**
	 * 自分のビューを応答する。
	 */
	public ForestView getView()
	{
		return (ForestView)(this.view);
	}
	
	/**
	 * マウスクリックした位置の木の要素（文字列）を出力する。
	 */
	public void mouseClicked(MouseEvent aMouseEvent)
	{
        
        Point aPoint = aMouseEvent.getPoint();
        //System.out.println("Controller Clicked !!");
		ForestView aView = this.getView();
		aView.getModel().mouseClicked(aPoint, aMouseEvent);
		return;
	}
    
/*	public void mouseDragged(MouseEvent aMouseEvent)
	{
		System.out.println("ドラッグ");
		Cursor aCursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
		Component aComponent = (Component)aMouseEvent.getSource();	
		aComponent.setCursor(aCursor);
	
		current = aMouseEvent.getPoint();
		int x = current.x - previous.x;
		int y = current.y - previous.y;
		Point point = new Point(x, y);
        getView().scrollBy(point);
		getView().repaint();
		previous = current;
		return;    
	
	}
  */  
    /*public void setView(ForestView aView){
        aView.
    }
	 */
}
