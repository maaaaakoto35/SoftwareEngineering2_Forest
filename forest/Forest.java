package forest;

import java.awt.Point;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import utility.StringUtility;

/**
 * Forest
 */
public class Forest extends Object
{
    /**
     * model
     */
    private ForestModel model;

    /**
     * root nodes
     */
    public static ArrayList<Node> rootNodes;

    /**
     * nodes
     */
    private HashMap<Integer,Node> nodes;

    /**
     * branches
     */
    public static ArrayList<Branch> branches;

    /**
     * under line
     */
    public static int underLine;

	/**
	 * ini
	 */
	public Forest()
    {
        this.model = null;
        this.rootNodes = new ArrayList<Node>();
        this.nodes = new HashMap<Integer,Node>();
        this.branches = new ArrayList<Branch>();
        this.underLine = 0;
	}

    /**
     * reading text file
     * @param aFile file
     */
    public void readText(File aFile) throws IOException
    {
        ArrayList<String> textList = StringUtility.readTextFromFile(aFile);
        System.out.println(textList.size());

        int type = 0; //0="trees:" 1="nodes:" 2="branches:"
        for (String tmp : textList)
        {
            tmp = tmp.replaceAll(" ", "");
            System.out.println("= " + tmp);
            if ( tmp.equals("nodes:") ) {type = 1;}
            if ( tmp.equals("branches:") ) {type = 2;}
            this.setTypeData(type, tmp);
        }
        return;
    }

    /**
     * setting type data
     */
    private void setTypeData(int type, String text)
    {
        if ( text.equals("nodes:") ) {return;}
        if ( text.equals("branches:") ) {return;}

        if (type == 0)
        {
        }
        else if (type == 1)
        {
            String[] tmp = text.split(",");
            int nodeNumber = Integer.parseInt(tmp[0]);
            String nodeName = tmp[1];
            Node aNode = new Node(nodeNumber, nodeName);
            this.nodes.put(nodeNumber, aNode);
        }
        else if (type == 2)
        {
            String[] tmp = text.split(",");
            Node parentNode = this.nodes.get( Integer.parseInt(tmp[0]) );
            Node childNode = this.nodes.get( Integer.parseInt(tmp[1]) );

            parentNode.setChildren(childNode);
            childNode.setParent(parentNode);
            this.nodes.put(Integer.parseInt(tmp[0]), parentNode);
            this.nodes.put(Integer.parseInt(tmp[1]), childNode);

            Branch aBranch = new Branch(parentNode, childNode);
            this.branches.add(aBranch);
        }
        else
        {
            System.err.println("===========?????????????????????============");
        }

        return;
    }

    /**
     * setting model
     * @param aModel model
     */
    public void setModel(ForestModel aModel)
    {
        this.model = aModel;
        return;
    }

    /**
     * setting root
     */
    public void setRoot()
    {
        for (Node node : this.nodes.values())
        {
            if(node.getParent() == null){ rootNodes.add(node); }
        }
        return;
    }

    /**
     * getting nodes
     * @return ArrayList<Node> nodes
     */
    public HashMap<Integer,Node> getNodes()
    {
        return this.nodes;
    }

    /**
     * getting root
     * @return ArrayList<Node> root nodes
     */
    public ArrayList<Node> getRoot()
    {
        return this.rootNodes;
    }

    /**
     * getting branches
     * @return ArrayList<Branch> branches
     */
    public ArrayList<Branch> getBranches()
    {
        return this.branches;
    }

    /**
     * visit and view
     * @param root root node
     * @param aPoint point
	 */
    public void visit(Node root, Point aPoint)
    {
        if(root.getVisit() == false){ root.setLocation(aPoint); }
        try
        {
            Thread.sleep(Const.SLEEP_TIME);
        }
        catch (InterruptedException anException)
        {
            System.err.println(anException);
            throw new RuntimeException(anException.toString());
        }
        this.model.changed();

        int childY = aPoint.y;
        int count=0;
        for(Node childNode : root.getChildren())
        {
            while(childY <= this.underLine) {childY += 18;}
            this.visit(childNode,new Point(aPoint.x+root.getWidth()+Const.HORIZONTAL_GAP, childY));
            childNode.setVisit(true);
            count++;
            if(root.getChildren().size() > count){
                childY += Const.VERTICAL_GAP + root.getHeight();
            }
        }

        if(this.underLine < childY) {this.underLine = childY;}
        if(root.getVisit() == false){root.setLocation( aPoint.x, (childY+aPoint.y+root.getHeight()) / 2 - (root.getHeight()/2) );}
        if(root.getParent()==null && root.getChildren().size()==1){ root.setLocation( aPoint.x, root.getChildren().get(0).getLocation().y);}

        return;
    }
}
