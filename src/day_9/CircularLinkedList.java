package day_9;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 13/12/2018.
 */
public class CircularLinkedList<T> {

    private Node head;
    private Node tail;
    private int listSize;


    public CircularLinkedList() {
        head = new Node(null);
        listSize = 0;
    }

    public Node addFirst(T data) {

        Node node = new Node(data);

        if (listSize == 0) {
            head = node; //one-element list: head and tail are the same node
            tail = node;
            tail.next = head;
            head.next = tail;
            head.previous = tail;
            tail.previous = head;
        } else  {
            Node temp = head;
            node.next = temp; //the new node points to the head as its next node
            temp.previous = node;
            head = node; //the new node becomes the head
            tail.next = head; //also the tail's reference is updated
            head.previous = tail;
        }
        listSize++;
        return head;
    }

    public Node addLast(T data) {

        if (listSize == 0) {
            addFirst(data);
        } else {
            Node node = new Node(data);
            tail.next = node; //the tail points to the new node as next
            node.previous = tail;
            tail = node; //the new node becomes the tail
            tail.next = head; //its reference points to the head
            head.previous = tail;
            listSize++;
        }

        return tail;
    }

    public void addAt(T data, int index) {

        if (listSize == 0) {
            addFirst(data);
        } else if (index == listSize - 1) {
            addLast(data);
        } else {
            Node node = new Node(data);
            Node currentNode = head;
            for (int i = 0; i < index - 1; i++) { //it loops through the nodes until it finds the node before the node to be added
                if (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                }
            }
            node.next = currentNode.getNext();
            node.previous = currentNode;
            currentNode.getNext().previous = node;
            currentNode.next = node;
            listSize++;
        }

    }

    //keep track of node in main class but pass it as argument
    //here
    public Node addAfterCurrent(Node nodeOld, T data) {

        Node node = new Node(data);

        nodeOld = nodeOld.next;
        nodeOld.next.previous = node;
        node.next = nodeOld.next;
        nodeOld.next = node;
        node.previous = nodeOld;
        listSize++;

        return nodeOld.next;
    }

    public void clear() {
        Node node = head;
        while (node != null) {
            node.next = null;
            node = node.next;
        }
    }

    public T get(int index) {

        T data;
        Node currentNode = head;
        if (index == 0) {
            data = head.data;
        } else if (index == listSize - 1) {
            data = tail.data;
        } else {

            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            data = currentNode.data;
        }

        return data;
    }

    public int size() {
        return listSize;
    }

    public void removeFirst() {

        if (listSize != 0) {
            head = head.next;
            head.previous = tail;
            tail.next = head;
            listSize--;
        }
    }

    public void removeLast() {

        if (listSize != 0) {
            Node currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
                if (currentNode.getNext() == tail)
                    break;
            }
            tail = currentNode;
            tail.next = head;
            head.previous = tail;
            listSize--;
        }
    }

    public void removeAt(int index) {

        if (index == 0 || index == listSize) {
            removeFirst();
        } else if (index == listSize - 1){
            removeLast();
        } else
        {
            if (listSize != 0) {
                Node currentNode = head;
                for (int i = 0; i < index - 1; i++) { //it loops through the nodes until it finds the node before the node to be removed
                    if (currentNode.getNext() != null) {
                        currentNode = currentNode.getNext();
                    }
                }
                Node tempNode = currentNode.getNext(); //temporary node gets the value of the node to be removed
                currentNode.setNext(tempNode.getNext()); //the node before the one to be removed changes reference
                listSize--;
            }
        }
    }

    public Object[] removeAfterCurrent(Node nodeOld, int index) {
        Object[] data = new Object[2];

        if (index == 0) {
            removeFirst();
        } else if (index == listSize - 1) {
            removeLast();
        } else {
            Node currentNode = nodeOld;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.previous;
            }
            data[0] = currentNode.data;
            currentNode.previous.next = currentNode.next;
            currentNode.next.previous = currentNode.previous;
            data[1] = currentNode.next;
            listSize--;
            return data;
        }
        return null;
    }

    public void print() {
        Node currentNode = head;
        for (int i = 0; i < listSize; i++) {
            System.out.println(currentNode.data);
            currentNode = currentNode.getNext();
        }
    }
    public class Node {
        Node next;
        Node previous;
        T data;

        public Node(T myData) {
            next = null;
            previous = null;
            data = myData;
        }


        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}

