package forest;

import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

public class Node extends JLabel
{
    /**
     * node num
     */
	private int nodeNumber;

    /**
     * node name
     */
    private String nodeName;

    /**
     * node parent
     */
    private Node parent;

    /**
     * node chidlen
     */
    private ArrayList<Node> children;

    /**
     * visit
     */
    private boolean visit;

    /**
	 * make instance
     * @param number node num
     * @param name node namve
     */
    Node(int number, String name)
    {
        this.nodeNumber = number;
        this.nodeName = name;
        this.parent = null;
        this.children = new ArrayList<Node>();
        this.visit = false;

		this.setFont(new Font("Arial", Font.PLAIN, 12));
        this.setText(this.nodeName);
		this.setBorder(new LineBorder(Color.black, 1));
        this.setSize(this.getPreferredSize());
    }

    /**
     * getting node num
     * @return node num
     */
    public int getNumber()
    {
        return this.nodeNumber;
    }

    /**
     * getting node name
     * @return node name
     */
    public String getName()
    {
        return this.nodeName;
    }

    /**
     * getting node parent
     * @return node parent
     */
    public Node getParent()
    {
        return this.parent;
    }

    /**
     * getting children
     * @return node children
     */
    public ArrayList<Node> getChildren()
    {
        return this.children;
    }

    /**
     * setting node parent
     * @param aNode node parent
     */
    public void setParent(Node aNode)
    {
        this.parent = aNode;
        return;
    }

    /**
     * setting node children
     * @param aNode node children
     */
    @SuppressWarnings("unchecked")
    public void setChildren(Node aNode)
    {
        this.children.add(aNode);
        Collections.sort(this.children, new NodeComparator()); //辞書順にソート
        return;
    }

    /**
     * setting visit
     */
    public void setVisit(boolean flag)
    {
        this.visit = flag;
        return;
    }

    /**
     * getting visit
     */
    public boolean getVisit()
    {
        return this.visit;
    }
}

/**
 * for sort
 */
@SuppressWarnings("rawtypes")
class NodeComparator implements java.util.Comparator {
    /**
     * compare
     * @param node1 node
     * @param node2 node
     * @return result
     */
	public int compare(Object node1, Object node2) {
		//               + (x > y)
		// compare node1 node2 = 0 (x = y)
		//               - (x < y)
        return ((Node) node1).getName().compareTo(((Node) node2).getName());
	}
}