public class DSAListNode {
    public Object value;
    public DSAListNode next;
    public DSAListNode prev;

    public DSAListNode(Object inValue) {
        this.value = inValue;
        this.next = null;
        this.prev = null;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object inValue) {
        this.value = inValue;
    }

    public DSAListNode getNext() {
        return this.next;
    }

    public DSAListNode getPrev() {
        return this.prev;
    }

    public void setNext(DSAListNode newNext) {
        this.next = newNext;
    }

    public void setPrev(DSAListNode newPrev) {
        this.prev = newPrev;
    }
}