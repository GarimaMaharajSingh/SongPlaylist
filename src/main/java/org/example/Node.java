package org.example;

public class Node {
    private String title;
    private int duration;
    private Node prev;
    private Node next;

    public Node(String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.prev = null;
        this.next = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
