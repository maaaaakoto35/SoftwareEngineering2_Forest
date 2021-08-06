package forest;

import java.awt.event.MouseEvent;
import java.awt.Point;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import mvc.Model;

/**
 * Forest Model
 */
public class ForestModel extends Model
{
    /**
     * a Forest
     */
    private Forest aForest;

	/**
     * making forest model
     * @param textFileName file
	 */
	public ForestModel(File aFile)
	{
		super();
        this.aForest = new Forest();
        this.aForest.setModel(this);
        try
        {
            this.aForest.readText(aFile);
        }
        catch(IOException anException)
        {
            anException.printStackTrace();
        }
        aForest.setRoot();

        int y=0;
        HashMap<Integer,Node> nodes = this.getForest().getNodes();
        for (Node aNode : nodes.values())
        {
            aNode.setLocation(0,y);
            y += aNode.getSize().height+Const.VERTICAL_GAP;
        }
	}

    /**
     * getting forest
     * @return Forest
     */
    public Forest getForest()
    {
        return this.aForest;
    }

	/**
     * getting node for clicking mouse
     * @param aPoint point
     * @param aMouseEvent event
	 */
	public void mouseClicked(Point aPoint, MouseEvent aMouseEvent)
	{
        HashMap<Integer,Node> nodes = aForest.getNodes();
        for (Node node : nodes.values()) {
            if(node.getLocation().x <= aPoint.x && node.getLocation().x+node.getWidth() >= aPoint.x)
            {
                if(node.getLocation().y <= aPoint.y && node.getLocation().y+node.getHeight() >= aPoint.y)
                {
                    System.out.println(node.getName());
                }
            }
        }
		return;
	}

    public void changed() {
    }
}
