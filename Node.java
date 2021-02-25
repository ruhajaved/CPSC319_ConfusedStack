/**
 * Node class used to construct the stack.
 */
public class Node
{
    private int data; // stack will only use natural numbers ideally
    private Node next; // singly LinkedList

    /**
     * Constructor for Node.
     * @param data value to set to.
     * @param next value to set to.
     */
    Node(int data, Node next)
    {
        this.data = data;
        this.next = next;
    }

    /**
     * Setter for data.
     * @param data value to set to.
     */
    public void setData(int data)
    {
        this.data = data;
    }

    /**
     * Getter for data.
     * @return returns data value.
     */
    public int getData()
    {
        return this.data;
    }

    /**
     * Setter for next.
     * @param next next node to point to.
     */
    public void setNext(Node next)
    {
        this.next = next;
    }

    /**
     * Getter for next.
     * @return returns next node current node points to.
     */
    public Node getNext()
    {
        return this.next;
    }
}
