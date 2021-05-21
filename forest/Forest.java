package forest;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import utility.StringUtility;
import java.util.ArrayDeque;

import java.util.Iterator;

//追加分
import java.io.File;

/**
 * 7/14 コーディング　担当：高橋 & 今江ぃ
 * 木を辿って行くForestクラス
 */
public class Forest extends Object
{
    /**
     * 情報を握っているModelのインスタンスを束縛する。
     */
    private ForestModel model;
    
    /**
     * このForest内でのルートノードのリスト
     */
    public static ArrayList<Node> rootNodes;
    
    
    /**
     * NodeのデータをすべてまとめたHashMap
     */
    private HashMap<Integer,Node> nodes;
    
    /**
     * BranchのデータをすべてまとめたArrayList
     */
    public static ArrayList<Branch> branches;
    
    /**
     * 現在のNode位置の下限
     */
    public static int underLine = 0;
    
	/**
	 * フォレストのコンストラクタ。初期化を行う。とりま今はこれで
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
     * テキストファイルの読み込み用
     * @param textFileName テキストファイル名
     */
    public void readText(File file) throws IOException
    {
        ArrayList<String> textList = StringUtility.readTextFromFile(file);
        System.out.println(textList.size());
        
        int type = 0; //0="trees:" 1="nodes:" 2="branches:"
        
        for (String tmp : textList)
        {
            tmp = tmp.replaceAll(" ", "");//空白の置換
            System.out.println("= " + tmp);
            if ( tmp.equals("nodes:") ) {type = 1;}
            if ( tmp.equals("branches:") ) {type = 2;}
            this.setTypeData(type, tmp);
        }
        return;
    }
    
    /**
     * それぞれの要素格納用のメソッド
     */
    private void setTypeData(int type, String text)
    {
        //エラー回避
        if ( text.equals("nodes:") ) {return;}
        if ( text.equals("branches:") ) {return;}
        
        if (type == 0)
        {
        }
        else if (type == 1)
        {
            //Nodeを作成していく
            String[] tmp = text.split(",");
            int nodeNumber = Integer.parseInt(tmp[0]);
            String nodeName = tmp[1];
            Node aNode = new Node(nodeNumber, nodeName);
            this.nodes.put(nodeNumber, aNode);
        }
        else if (type == 2)
        {
            //テキストファイルからの取り出し
            String[] tmp = text.split(",");
            Node parentNode = this.nodes.get( Integer.parseInt(tmp[0]) );
            Node childNode = this.nodes.get( Integer.parseInt(tmp[1]) );
            
            //Nodeへの親子の登録
            parentNode.setChildren(childNode);
            childNode.setParent(parentNode);
            this.nodes.put(Integer.parseInt(tmp[0]), parentNode);
            this.nodes.put(Integer.parseInt(tmp[1]), childNode);
            
            //Branchへの登録
            Branch aBranch = new Branch(parentNode, childNode);
            this.branches.add(aBranch);
        }
        else
        {
            System.err.println("===========不正な値です。============");
        }
        return;
    }
    
    /**
     * 指定されたモデルをインスタンス変数modelに設定する。
     * @param aModel Modelのインスタンス
     */
    public void setModel(ForestModel aModel)
    {
        this.model = aModel;
    }
    
    /**
     * Nodeのリストを応答
     * @return ArrayList<Node> ノードのリスト
     */
    public HashMap<Integer,Node> getNodes()
    {
        return this.nodes;
    }
    
    /**
     * Branchのリストを応答
     * @return ArrayList<Branch> ブランチのリスト
     */
    public ArrayList<Branch> getBranches()
    {
        return this.branches;
    }
    
    /**
     * ルートNodeを束縛するメソッド
     */
    public void setRoot()
    {
        // 列挙する
        for (Node node : this.nodes.values())
        {
            if(node.getParent() == null) { rootNodes.add(node); System.out.println("node = " + node.getName()); }
        }
        
    }
    
    /**
     * ルートノードを応答するメソッド
     */
    public ArrayList<Node> getRoot()
    {
        return this.rootNodes;
    }
    
    /**
     * 現在の下限を更新するメソッド
     */
    private void changeUnderLine (int y)
    {
        if(this.underLine < y) {this.underLine = y;}
        return;
    }
    
    /**
     * 描画を停止させるメソッド
     */
    private void sleep(int msec)
    {
        try
        {
            Thread.sleep(msec);
        }
        catch (InterruptedException anException)
        {
            System.err.println(anException);
            throw new RuntimeException(anException.toString());
        }
    }
    
    /**
	 * 木を探索する
     * @param root 枝
     * @param aPoint 座標
	 */
    
    public void visit(Node root, Point aPoint)
    {
        
        if(root.getVisit() == false){root.setLocation( aPoint );}
        this.sleep(100);
        
        this.model.changed();
        
        int childY = aPoint.y;
        
        int count=0;
        for(Node childNode : root.getChildren())
        {
            while(childY <= this.underLine) {childY += 18;} //現在一番下のNodeより下に描くために位置を調整
            this.visit(childNode,new Point(aPoint.x+root.getWidth()+Const.HORIZONTAL_GAP, childY)); //子Nodeを探索
            childNode.setVisit(true); //一度探索したら、座標を変更出来ないようにする
            count++;
            if(root.getChildren().size() > count){
            childY += Const.VERTICAL_GAP + root.getHeight(); //次の子を現在の位置より、2pixel開けた位置に描画
            }
        }
        
        this.changeUnderLine(childY); //現在のNodeの下限を更新
        System.out.println("name="+root.getName()+" y="+aPoint.y+ " childY="+childY);
        if(root.getVisit() == false){root.setLocation( aPoint.x, (childY+aPoint.y+root.getHeight()) / 2 - (root.getHeight()/2) );}
        if(root.getParent()==null && root.getChildren().size()==1){ root.setLocation( aPoint.x, root.getChildren().get(0).getLocation().y);}
        
        
        return;
    }
}
