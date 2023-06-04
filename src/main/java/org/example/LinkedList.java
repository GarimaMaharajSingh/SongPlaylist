package org.example;

import java.util.Scanner;

public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addSong(String t, String dur) {

        if (findDuplicate(t) != -1) {
            System.out.println("The song '" + t + "' already exists in the playlist.");
            return;
        }

        // Parse the duration into minutes and seconds
        String[] durationParts = dur.split(":");
        if (durationParts.length != 2) {
            System.out.println("Invalid duration format. Please try again.");
            return;
        }

        int minutes, seconds;
        try {
            minutes = Integer.parseInt(durationParts[0]);
            seconds = Integer.parseInt(durationParts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid duration format. Please try again.");
            return;
        }

        int durationInSeconds = minutes * 60 + seconds;

        // Create the new song and add it to the playlist
        Node node = new Node(t, durationInSeconds);

        //Check if the playlist is empty
        if (isEmpty()) {
            //Set the new song as both head and tail
            head = node;
            tail = node;
        } else {
            // Add the new song at the end of the playlist
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }

        size++;
        System.out.println("Added the song '" + t + "' to the playlist.");
    }

    public int findDuplicate(String t) {
        Node current = head;
        int position = 1;

        while (current != null) {
            if (current.getTitle().equalsIgnoreCase(t)) {
                return position;
            }
            current = current.getNext();
            position++;
        }
        return -1;
    }

    public void deleteAtPos(int pos) {
        int mid = size/2;

        if (isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        if (pos < 1 || pos > size) {
            System.out.println("Invalid position.");
            return;
        }

        if (pos == 1) {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            }
        } else if (pos == size) {
            tail = tail.getPrev();
            if (tail != null) {
                tail.setNext(null);
            }
        }
        else{
            if(pos> mid){
                Node current = tail;
                int count = size;

                while (count > pos) {
                    current = current.getPrev();
                    count--;
                }

                current.getNext().setPrev(current.getPrev());
                current.getPrev().setNext(current.getNext());
                current = null;
            }
            else{
                Node current = head;
                int count = 1;

                while (count < pos) {
                    current = current.getNext();
                    count++;
                }

                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                current = null;
            }
            }

        size--;
        System.out.println("Song deleted from the playlist.");
    }

    public int findSong(String t) {
        if (isEmpty()) {
            System.out.println("Playlist is empty.");
            return -1;
        }

        Node current = head;
        int position = 1;

        while (current != null) {
            if (current.getTitle().equalsIgnoreCase(t)) {
                return position;
            }

            current = current.getNext();
            position++;
        }

        System.out.println("Song not found in the playlist.");
        return -1;
    }

public void playTrack(String start1) {
    int start;
    try {
        start = Integer.parseInt(start1);
    } catch (NumberFormatException e) {
        System.out.println("Invalid track to start from. Please try again.");
        return;
    }

    if (isEmpty()) {
        System.out.println("Playlist is empty.");
        return;
    }

    if (start < 1 || start > size) {
        System.out.println("Invalid starting position.");
        return;
    }

    Node current = head;
    int position = 1;

    // Traverse to the specified starting position
    while (position < start) {
        current = current.getNext();
        position++;
    }

    System.out.println("Current Song is " + position + ". " + current.getTitle());

    Scanner scanner = new Scanner(System.in);
    String input;
    do {
        System.out.print("Enter 'n' for Next Track, 'p' for Previous Track, 'e' to exit: ");
        input = scanner.nextLine();

        if (input.equalsIgnoreCase("n")) {
            if (current.getNext() != null) {
                current = current.getNext();
                position++;
            } else {
                current = current.getNext();
                position++;
                System.out.println("End of the playlist reached.");
                break;
            }
        } else if (input.equalsIgnoreCase("p")) {
            if (current.getPrev() != null) {
                current = current.getPrev();
                position--;

            } else {
                current = current.getPrev();
                position--;
                System.out.println("Beginning of the playlist reached.");
                break;
            }
        } else if (!input.equalsIgnoreCase("e")) {
            System.out.println("Invalid input.");
            continue;
        }

        System.out.println("Current Song is " + position + ". " + current.getTitle());
    } while (!input.equalsIgnoreCase("e"));
}


    public void sortList() {
        if (isEmpty() || head == tail) {
            // No need to sort an empty list or a list with only one song
            return;
        }

        head = mergeSort(head);
        // Update the tail reference
        tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }

        System.out.println("Playlist sorted by song title.");
    }

    private Node mergeSort(Node start) {
        if (start == null || start.getNext() == null) {
            // Base case: return the start if it's null or there's only one song
            return start;
        }

        // Split the list into two halves
        Node middle = getMiddle(start);
        Node nextOfMiddle = middle.getNext();
        middle.setNext(null);

        // Recursively sort the two halves
        Node left = mergeSort(start);
        Node right = mergeSort(nextOfMiddle);

        // Merge the sorted halves
        return merge(left, right);
    }

    private Node merge(Node left, Node right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        Node result;
        if (left.getTitle().compareToIgnoreCase(right.getTitle()) <= 0) {
            result = left;
            result.setNext(merge(left.getNext(), right));
            result.getNext().setPrev(result);
        } else {
            result = right;
            result.setNext(merge(left, right.getNext()));
            result.getNext().setPrev(result);
        }

        return result;
    }

    private Node getMiddle(Node start) {
        if (start == null) {
            return start;
        }

        Node slow = start;
        Node fast = start.getNext();

        while (fast != null) {
            fast = fast.getNext();
            if (fast != null) {
                slow = slow.getNext();
                fast = fast.getNext();
            }
        }

        return slow;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        System.out.println("************ Playlist ************");
        Node current = head;
        int position = 1;

        while (current != null) {
            int minutes = current.getDuration() / 60;
            int seconds = current.getDuration() % 60;

            System.out.println(position + ". " + current.getTitle() + " <" + minutes + ":" + String.format("%02d", seconds) + ">");

            current = current.getNext();
            position++;
        }
        System.out.println("Total number of songs in the playlist: "+(position-1));
        System.out.println("**********************************");
    }

}
