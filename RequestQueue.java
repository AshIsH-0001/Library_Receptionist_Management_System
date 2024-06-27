import Includes.*;

public class RequestQueue {
    private Node<RequestData> front;
    private Node<RequestData> back;
    private int length = 0;


    public RequestData getFront() {
        return this.front.data;
    }

    public int getLength() {
        Node<RequestData> x = new Node<>();
        x = front;
        // length = 0;
        while(x!=null){
            x = x.next;
            length++;
        }
        return length;
    }

    public void push(int ISBN, int UserID) {
        RequestData ruladiya = new RequestData();
        ruladiya.ISBN = ISBN;
        ruladiya.UserID = UserID;
        ruladiya.RequestDate = new MyDate();
        
        Node<RequestData> nayahai = new Node<>();
        nayahai.data = ruladiya;

        if(front==null){
            front = back = nayahai;
        } else {
            back.next = nayahai;
            nayahai.previous = back;
            back = nayahai;
        }
        //length = getLength();
        return;
    }

    public void pop() {      
        
        if(front!=null && front.next!=null){
            front = front.next;
            front.previous = null;
            if(front==null){
                back = null;
            }
        }
        else{
            front = back = null;
        }
        this.length--;
        return;
    }

    //ab toh sab sorted hai!! Let's gooo!

    public String toString(){
        Node<RequestData> temp = front;
        String s = "Length of Request Queue: " + getLength() + "\n";
        while(temp != null){
            s+=temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
}
