/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa;

public class Node {

    int id;
    Node next;

    public Node(int id) {
        this.id = id;
        this.next = null;
    }

    @Override
    public String toString() {
        return "," + id;
    }

    public static void main(String[] args) {
    }
}
