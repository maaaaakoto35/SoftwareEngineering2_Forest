package forest;

import java.awt.Point;
import java.awt.Graphics;
import javax.swing.border.EtchedBorder;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

/*
 * 6/24 コーディング　担当：今江
 * 6/25 コーディング　担当：高橋
 * Forestに集約されるBranchクラス
 * 単体テスト良好(2013年6月21日)
 */
public class Branch extends Object
{

    private Node parentNode; //親ノード
    private Node childNode; //子ノード
    
    /**
     * コンストラクタ
     * 引数に親Node,子Nodeを指定し
     * フィールドに設定する
     * @param parentNode 親Node
     * @param childNode 子Node
     */
    Branch(Node parentNode, Node childNode)
    {
        this.parentNode = parentNode;
        this.childNode = childNode;
    }
    
    /**
     * 親Nodeを応答するメソッド
     * @return 親Node
     */
    public Node getParentNode()
    {
        return this.parentNode;
    }
    
    /**
     * 子Nodeを応答するメソッド
     * @return 子Node
     */
    public Node getChildNode()
    {
        return this.childNode;
    }
    
}