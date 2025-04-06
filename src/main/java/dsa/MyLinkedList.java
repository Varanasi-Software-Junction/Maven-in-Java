/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa;

public class MyLinkedList {

    Node next = null;

    public static void reverseList(MyLinkedList hd) {
        if (hd.next == null) {
            return;
        }
        Node p = hd.next;
        if (p.next == null) {
            return;
        }
        Node q = p.next;
        if (q.next == null) {
            q.next = p;
            p.next = null;
            hd.next = q;
            return;
        }
        Node r = q.next;
        while (r.next != null) {
            q.next = p;
            p = q;
            q = r;
            r = r.next;
        }
        q.next = p;
        r.next = q;
        hd.next.next = null;
        hd.next = r;

    }

    public static void addLast(MyLinkedList hd, int i) {
        Node n = new Node(i);
        if (hd.next == null) {
            hd.next = n;
            return;
        }
        Node p = hd.next; //address store
        while (p.next != null) {
            p = p.next;
        }
        p.next = n;
    }

    public static void traverse(MyLinkedList hd) {
        System.out.println("\n\n");
        Node p = hd.next; //address store
        while (p != null) {
            System.out.print(p);
            p = p.next;
        }
        System.out.println("\n\n");
    }

    public static void addFirst(MyLinkedList hd, int i) {
        Node n = new Node(i);
        if (hd.next == null) {
            hd.next = n;
            return;
        }
    }

    public static void main(String[] args) {

         
        MyLinkedList hd = new MyLinkedList();

        addLast(hd, 9);
        addLast(hd, 10);
        addLast(hd, 11);
        addLast(hd, 12);
        addLast(hd, 13);
        addLast(hd, 14);
        traverse(hd);
        reverseList(hd);
        traverse(hd);
    }
}
