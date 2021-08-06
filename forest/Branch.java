package forest;

/*
 * Branch
 */
public class Branch extends Object
{
    /**
     * parent node
     */
    private Node parentNode;

    /**
     * child node
     */
    private Node childNode;

    /**
     * getting branch
     * @param parentNode parent node
     * @param childNode child node
     */
    Branch(Node parentNode, Node childNode)
    {
        this.parentNode = parentNode;
        this.childNode = childNode;
    }

    /**
     * getting parent node
     * @return parent node
     */
    public Node getParentNode()
    {
        return this.parentNode;
    }

    /**
     * getting child node
     * @return childe node
     */
    public Node getChildNode()
    {
        return this.childNode;
    }
}