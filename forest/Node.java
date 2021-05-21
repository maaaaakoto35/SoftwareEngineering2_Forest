package forest;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Container;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Node extends JLabel
{
    private String nodeName;
	private int nodeNumber;
    private Node parent;
    private ArrayList<Node> children = new ArrayList<Node>();
    private boolean visit = false;

    
    Node(int number, String name)
    {
        this.nodeNumber = number;
        this.nodeName = name;
        
		this.setFont(new Font("Arial", Font.PLAIN, 12));
        this.setText(this.nodeName);
		this.setBorder(new LineBorder(Color.black, 1));
        
        this.setLayout(null);
        this.setSize(this.getPreferredSize());//こういう風に書くと文字によって最適化
    }
    
       
    /**
     * Nodeの名前を応答するメソッド
     * @return name Nodeの名前
     */
    public String getName()
    {
        return this.nodeName;
    }
    
    
    /**
     * ノードの番号を応答するメソッド
     * @return number Nodeの番号
     */
    public int getNumber()
    {
        return this.nodeNumber;
    }
    
    
    /**
     *  Nodeの親を応答するメソッド
     */
    public Node getParent()
    {
        return this.parent;
    }
    
    /**
     *  Nodeの子を応答するメソッド
     */
    public ArrayList<Node> getChildren()
    {
        return this.children;
    }
    
    /**
     *  Nodeの親を登録するメソッド
     */
    public void setParent(Node aNode)
    {
        this.parent = aNode;
        return;
    }
    
    /**
     *  Nodeの子を登録するメソッド
     */
    public void setChildren(Node aNode)
    {
        this.children.add(aNode);
        Collections.sort(this.children, new NodeComparator()); //辞書順にソート
        return;
    }
    
    public void setVisit(boolean flag)
    {
        this.visit = flag;
    }
    
    public boolean getVisit()
    {
        return this.visit;
    }
}

/**
 * Nodeを辞書順にソートするためのコンパレータ
 */
class NodeComparator implements java.util.Comparator {
	public int compare(Object node1, Object node2) {
		//               + (x > y)
		// compare node1 node2 = 0 (x = y)
		//               - (x < y)
        return ((Node) node1).getName().compareTo(((Node) node2).getName());
	}
}