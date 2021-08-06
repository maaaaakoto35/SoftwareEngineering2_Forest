package forest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import mvc.View;

/**
 * Forest View
 */
public class ForestView extends View
{
    /**
     * making mvc
     * setting view
	 */
	public ForestView(ForestModel aModel, ForestController aController)
	{
		super(aModel, aController);
        this.model = aModel;
        this.setSize(1500,1500);
	}

    /**
     * painting component
     */
    public void paintComponent(Graphics aGraphics)
    {
        int width = this.getWidth();
		int height = this.getHeight();
		aGraphics.setColor(Color.white);
        aGraphics.fillRect(0, 0, width, height);

        BufferedImage picture = (BufferedImage)this.createImage(width, height);
        Graphics aGraphicsBuffer = picture.getGraphics();
        aGraphicsBuffer.setColor(Color.white);
        aGraphicsBuffer.fillRect(0, 0, width, height);
        aGraphicsBuffer.setColor(Color.black);

        ForestModel aForestModel = (ForestModel)(this.model);
        ArrayList<Branch> branches = aForestModel.getForest().getBranches();
        for(Branch aBranch : branches)
        {
            Point parentPoint = aBranch.getParentNode().getLocation();
            Point childPoint = aBranch.getChildNode().getLocation();
            Dimension parentDimension = aBranch.getParentNode().getSize();
            Dimension childDimension = aBranch.getChildNode().getSize();

            aGraphicsBuffer.drawLine(parentPoint.x + parentDimension.width,
                                     parentPoint.y + parentDimension.height/2,
                                     childPoint.x,
                                     childPoint.y + childDimension.height/2);
		}

        HashMap<Integer,Node> nodes = aForestModel.getForest().getNodes();
        for (Node aNode : nodes.values())
        {
            Point aPoint = aNode.getLocation();
            Dimension aDimension = aNode.getSize();
            aGraphicsBuffer.setFont(aNode.getFont());
            aGraphicsBuffer.drawString(aNode.getName(), aPoint.x + Const.RECT_LINE_THICK, aPoint.y + aNode.getSize().height - Const.RECT_LINE_THICK * 2 - 1); //1pxがずれる理由がまったくわかりませんでした。
            aGraphicsBuffer.drawRect(aPoint.x, aPoint.y, aDimension.width, aDimension.height);
		}

        Point aPoint = this.scrollAmount();
        aGraphics.drawImage(picture, 0-aPoint.x, 0-aPoint.y, this);
		return;
	}
}
