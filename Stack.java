/**
 * Stack implementation.
 */
public class Stack 
{
    private Node head;  // head/top of stack
    private int length; // length of stack - how much data is currently stored

    /**
     * Class constructor; make empty by default.
     */
    public Stack()
    {
        head = null;
        length = 0;
    }

    /**
     * Setter for head.
     * @param head new head of list.
     */
    public void setHead(Node head)
    {
        this.head = head;
    }

    /**
     * Getter for head.
     * @return returns head.
     */
    public Node getHead()
    {
        return this.head;
    }

    /**
     * Setter for length.
     * @param length new length of list.
     */
    public void setLength(int length)
    {
        this.length = length;
    }

    /**
     * Getter for length.
     * @return returns current list length.
     */
    public int getLength()
    {
        return this.length;
    }
    
    /**
     * Used to check if the list is empty.
     * @return returns true if the list is empty, otherwise false.
     */
    public boolean isEmpty()
    {
        return ((this.getLength() == 0) ? true : false);
    }

    /**
     * Gets data stored in head; used for top.                                       
     * @return returns data in current head.
     */
    public int getHeadData()
    {
        return this.head.getData();
    }

    /**
     * Stack push method; assumes that data is a valid natural number.
     * @param data data to add to the BEGINNING of the list.
     */
    public void push(int data)
    {
        Node newHead = new Node(data, head);
        head = newHead;                         // set the head as the new data
        int newLength = this.getLength() + 1;   // increment the length
        this.setLength(newLength);               // set the new length
    }

    /**
     * Stack top function; prints what's at the top of the stack.
     * @return returns value at top of stack.
     */
    public int top()
    {
        if(this.isEmpty())                           // if stack is empty, return error key -4
        {
            return -4;
        }
        return this.head.getData();
    }

    public int pop()
    {
        if(this.isEmpty())                           // if stack is empty, return error key -5
        {
            return -5;
        }
        int headData = this.getHeadData();      // get data in head to output later on
        this.setHead(head.getNext());           // "pop" head, set new head to what current head's next is
        int newLength = this.getLength() - 1;   // decrement length by 1
        this.setLength(newLength);              // set the new length
        return headData;                        // return previous head's data

    }

    /**
     * Utility function; used to print the stack.
     */
    public void printStack()
    {
        if(this.isEmpty()) // if an empty stack
        {
            System.out.println("Stack is empty.");
        }

        Node current = head;

        for(int i = 0; i < this.length; i++)    // print out each element in the stack
        {
            System.out.println("index: " + i + " stack value: " + current.getData());
            current = current.getNext();
        }
    }
}
