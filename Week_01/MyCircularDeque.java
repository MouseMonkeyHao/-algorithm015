package com.run.weekend_01;

/**
 * Created by qihao on 2020/8/29.
 */
public class MyCircularDeque {

    private int count;

    private int capacity;

    private Node left;

    private Node right;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.count = 0;
        this.capacity = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (this.count == this.capacity){
            return false;
        }
        Node node = new Node(value);
        if (count == 0){
            this.left = this.right = node;
        }else {
            node.next = this.left;
            this.left.prev = node;
            this.left = this.left.prev;
        }
        this.count++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (this.count == this.capacity){
            return false;
        }
        Node node = new Node(value);
        if (count == 0){
            this.left = this.right = node;
        }else {
            this.right.next = node;
            node.prev = this.right;
            this.right = this.right.next;
        }
        this.count++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (count == 0){
            return false;
        }
        if (this.count == 1){
            this.left = this.right = null;
        }else {
            Node next = this.left.next;
            this.left.next = null;
            next.prev = null;
            this.left = next;
        }
        this.count--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (count == 0){
            return false;
        }
        if (this.count == 1){
            this.left = this.right = null;
        }else {
            Node pre = this.right.prev;
            this.right.prev = null;
            pre.next = null;
            this.right = pre;
        }
        this.count--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return count == 0 ? -1 : this.left.val;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return count == 0 ? -1 : this.right.val;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return count == 0 ? true : false;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return count == capacity ? true : false;
    }

    class Node {
        int val;
        Node prev, next;

        Node(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

}
