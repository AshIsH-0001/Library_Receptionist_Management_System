import java.util.*;

import Includes.*;

public class LibraryStorage {
    // HashMap
    // process return
    private HashMap<Integer, BookData> storage;
    private RequestQueue rqQueue;
    private PendingRequests prLinkedList;

    public LibraryStorage() {
        storage = new HashMap<Integer, BookData>();
        for (int i=100001; i<100021; i++) {
            BookData book = new BookData();
            MyDate dateor = new MyDate();
            dateor.month = 0;
            dateor.year = 0;
            book.BorrowedStatus = false;
            book.BorrowedByUserID = -1;
            book.ISBN = i;
            book.Publisher = "publisher";
            book.Author = "author";
            book.DateOfReturn = dateor;
            storage.put(i, book);
        }

        rqQueue = new RequestQueue();
        prLinkedList = new PendingRequests();
    }

    public void push(int ISBN, int UserID) {
        rqQueue.push(ISBN, UserID);
        return;
    }

    //@SuppressWarnings("unlikely-arg-type")
    public boolean processQueue() {
        //RequestData helper = rqQueue.getFront();
       // try{
        if(!storage.get(rqQueue.getFront().ISBN).BorrowedStatus){
            
            //Set the variables

            storage.get(rqQueue.getFront().ISBN).BorrowedStatus = true;
            storage.get(rqQueue.getFront().ISBN).BorrowedByUserID = rqQueue.getFront().UserID;
            /*Ye kaise likhna hai ?!
            storage.get(rqQueue.getFront().ISBN).DateOfReturn = 
            */
            storage.get(rqQueue.getFront().ISBN).DateOfReturn.month = 6;
            storage.get(rqQueue.getFront().ISBN).DateOfReturn.year = 2024;
            rqQueue.pop();
            return true;
        } else {
            Node<RequestData> helper = new Node<>();
            rqQueue.getFront().RequestDate.month = 5;
            rqQueue.getFront().RequestDate.year = 2024;
            helper.data = rqQueue.getFront();
            // helper.ISBN = rqQueue.getFront().ISBN;
            // helper.UserId = rqQue
            prLinkedList.insert(helper);
            rqQueue.pop();
            return false;
        }
        //} catch(Exception e){
        //    System.err.println("Incorrect Data type for rqQueue");
        //}
    }

    public boolean processReturn(BookData book) {          
        // I have assumed this takes BookData object as argument, can also work with ISBN perhaps
        /*
         * Your code here.
         */
        Node<RequestData> yeMila = prLinkedList.find(book.ISBN);
        if(yeMila != null){
            storage.get(book.ISBN).BorrowedByUserID = yeMila.data.UserID;
            storage.get(book.ISBN).DateOfReturn.month = 10;
            storage.get(book.ISBN).DateOfReturn.year = 2024;
            prLinkedList.delete(yeMila);
            return true;
        } else{
            storage.get(book.ISBN).BorrowedByUserID = -1;
            storage.get(book.ISBN).BorrowedStatus = false;
            storage.get(book.ISBN).DateOfReturn.month = 4;
            storage.get(book.ISBN).DateOfReturn.month = 2024;
        }
        return false;
    }

    public String rqQueueToString(){
        return rqQueue.toString();
    }

    public String prLinkedListToString(){
        return prLinkedList.toString();
    }
    
}
