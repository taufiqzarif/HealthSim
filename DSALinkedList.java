import java.util.*;
// Doubly linked list

public class DSALinkedList implements Iterable{ 
    DSAListNode head;
    DSAListNode tail;

    public DSALinkedList() {
        this.head = null;
        this.tail = null;
    }

    public Iterator iterator() {
        return new DSALinkedListIterator(this);
    }

    public class DSALinkedListIterator implements Iterator {
        private DSAListNode iterNext;
        public DSALinkedListIterator(DSALinkedList theList) {
            iterNext = theList.head;
        }
        

        public boolean hasNext() {
            return iterNext!=null;
        }


        public Object next() {
            Object value;
            if(!hasNext()) {
                value = null;
            }
            else {
                value = iterNext.getValue(); //Get value in the node
                iterNext = iterNext.getNext(); //Ready for subsequent calls to next()
            }
            return value;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    public Object insertFirst(Object newValue) {
        DSAListNode newNode = new DSAListNode(newValue);
        if(isEmpty()) {
            head = newNode; // Point to newNode | head -> newNode[current head]
            tail = newNode;
        }
        else { // If nodes already exist in LinkedList
            newNode.setNext(head); 
      
            head.setPrev(newNode);
            head = newNode;
    
        }
        return newValue;
    }

    public Object insertLast(Object newValue) {
        DSAListNode newNode = new DSAListNode(newValue);
        if(isEmpty()) {
            head = newNode;
            tail = newNode;
        }
        else {
            DSAListNode currNode = head;
            while(currNode.getNext() != null) {
                currNode = currNode.getNext();
            }
            currNode.setNext(newNode);
        }
        tail = newNode;
        return newValue;
    }

    public Object removeFirst() {
        Object nodeValue = null;
        if(isEmpty()) {
            throw new IllegalArgumentException("LIST IS EMPTY!");
        }
        else {
            nodeValue = head.getValue();
            head = head.getNext();
        }
        return nodeValue;
    }

    public Object removeLast() {
        Object nodeValue = null;
        if(isEmpty()) {
            throw new IllegalArgumentException("List is empty!");
        }
        else if(head.getNext() == null) {
            nodeValue = head.getValue();
            head = null;
        }
        else {
            DSAListNode prevNode = null;
            DSAListNode currNode = head;
            while(currNode.getNext() != null) {
                prevNode = currNode;
                currNode = currNode.getNext();
            }
            tail = prevNode;
            tail.setNext(null);
            nodeValue = currNode.getValue();
        }
        return nodeValue;
    }

    public Object remove(Object value) {
        Object nodeValue = null;
        if(isEmpty()) {
           
        }
        else if(value == head.getValue()){
            nodeValue = head.getValue();
            removeFirst();
        }
        else if(head.getNext() == null) {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else if(value == tail.getValue()) {
            nodeValue = tail.getValue();
            removeLast();
        }
        else {
            DSAListNode prevNode = null;
            DSAListNode currNode = head;
            while(currNode.getNext()!=null) {
                if(value == currNode.getValue()) {
                    nodeValue = currNode.getValue();
                    prevNode.setNext(currNode.getNext());
                }
                prevNode = currNode;
                currNode = currNode.getNext();
            }
            if(currNode.getValue() == null) {
                nodeValue = null;
                System.out.println(value + " does not exist!");
            }
            if(value == currNode.getValue()) {
                nodeValue = currNode.getValue();
                prevNode.setNext(currNode);
            }
        }
        return nodeValue;
    }

    public Object peekFirst() {
        Object nodeValue;
        if(isEmpty()) {
            throw new IllegalArgumentException("LIST IS EMPTY!");
        }
        else {
            nodeValue = head.getValue();
        }
        return nodeValue;
    }

    public Object peekLast() {
        Object nodeValue;
        if(isEmpty()) {
            throw new IllegalArgumentException("List is empty!");
        }
        else {
            DSAListNode currNode = head;
            while(currNode.getNext() != null) {
                currNode = currNode.getNext();
            }
            nodeValue = currNode.getValue();
        }
        return nodeValue;
    }

    public boolean isEmpty() {
        boolean empty = false;
        if(head == null) {
            empty = true;
        }
        return empty;
    }

    public void printList() {
        DSAListNode currentNode = head;
        if(isEmpty()) {
            throw new IllegalArgumentException("LIST IS EMPTY!");
        }
        else {
            while(currentNode.getNext()!=null) {
                System.out.println(currentNode.getValue());
                currentNode = currentNode.getNext();
            }
            System.out.println(currentNode.getValue());
        }
    }

    public String toString() {
        DSAListNode currentNode = head;
        String output = new String("");
        if(isEmpty()) {
            output = "NULL / No edge";
        }
        else {
            while(currentNode.getNext()!=null) {
                output = output + currentNode.getValue() + " -> ";
                currentNode = currentNode.getNext();
            }
            output = output + currentNode.getValue() + " ";
        }
        return output;
    }


}