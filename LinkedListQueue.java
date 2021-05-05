// Ben Fristad

public class LinkedListQueue
{
    protected BSTNode head; // points to the first Node in the Linked List
    protected BSTNode tail; // points to the last Node in the Linked List
    protected int size; // number of items in the queue

    public LinkedListQueue() // this constructor initailzes the queue
    {
        head = null;
        tail = null;
        size = 0;

    }// end DVC

    public int size() // returns the number of items in the queue
    {
        return this.size;

    }// end size

    public BSTNode front() // returns the first item in the queue
    {
        if(size == 0)
            throw new IllegalArgumentException("queue is empty");
        else
            return head;

    }// end front

    public void enqueue(BSTNode newNode) // adds the passed in item to the tail of the queue
    {
        if(newNode == null)
            throw new IllegalArgumentException("newNode is null");

        if(size == 0)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.right = newNode;
            tail = newNode;
        }
        size++;

    }// end enqueue

    public BSTNode dequeue() // removes the first item in the queue and returns its contents
    {
        if(size == 0)
            throw new IllegalArgumentException("queue is empty");
        else
        {
            BSTNode ret = head;

            if(size == 1)
            {
                head = null;
                tail = null;
            }

            else
            {
                BSTNode oldHead = head;
                head = head.right;
            }

            size--;
            return ret;

        }// end else

    }// end dequeue

}// end class