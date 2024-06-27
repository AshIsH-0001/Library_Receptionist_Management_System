import Includes.*;

public class PendingRequests {
    private int length = 0;
    private Node<RequestData> front;
    private Node<RequestData> back;

    public boolean insert(Node<RequestData> insnode) {
        if(back != null){
            back.next = insnode;
            insnode.previous = back;
            back = insnode;
        } else {
            front = back = insnode;
        }
        this.length++;
        return true;
    }

    public boolean delete(Node<RequestData> delnode) {
        // I am assuming that this delnode is already having the previous and next defined
        //Will it be fine to do so? (To be confirmed by the TA);
        if(delnode.previous==null && delnode.next!=null){
            front = delnode.next;
            //delnode.previous.next = delnode.next;
            front.previous = null;
            //delnode.previous = null;
            delnode.next = null;
        } else if(delnode.next==null && delnode.previous!=null){
            back = delnode.previous;
            back.next = null;
            //delnode.next.previous = delnode.previous;
            delnode.previous = null;
            //delnode.next = null;
        } else if(delnode.next!=null && delnode.previous!=null){
            // Node<RequestData> prev_delnode = delnode.previous;
            // Node<RequestData> next_delnode = delnode.next;
            delnode.previous.next = delnode.next;
            delnode.next.previous = delnode.previous;
            delnode.previous = null;
            delnode.next = null;
        } else if(front==null){
            return false;
        } else{
            front = back = null;
        }
        this.length--;
        //To be confirmed by the TA!!
        return true;
    }

    public Node<RequestData> find(int ISBN) {
        //Ye toh easy hee hai bruh!
        Node<RequestData> helper = front;
        while(helper != null && helper.data.ISBN != ISBN){
            helper = helper.next;
        }
        return helper;
    }

    public String toString(){
        Node<RequestData> temp = front;
        String s = "Length of Pending Requests: " + length + "\n";
        while(temp != null){
            s+=temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
}
