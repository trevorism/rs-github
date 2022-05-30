package com.trevorism

import groovy.transform.Canonical

/*
     A
   B       C
D    E   F    G
 */

@Canonical
class Node {
    String name;
    Node left;
    Node right;

    static Node createGraph() {
        Node a = new Node(name: "A")
        Node b = new Node(name: "B")
        Node c = new Node(name: "C")
        Node d = new Node(name: "D")
        Node e = new Node(name: "E")
        Node f = new Node(name: "F")
        Node g = new Node(name: "G")

        c.left = f
        c.right = g
        b.left = d
        b.right = f
        a.left = b
        a.right = c
        return a
    }

    static void traverseDFS(Node node) {
        println node.name
        if (node.left) {
            traverseDFS(node.left)
        }
        if (node.right) {
            traverseDFS(node.right)
        }
    }

    static List linkedList = new LinkedList();
    static void traverseBFS(Node node) {
        linkedList.push(node)
        while(!linkedList.isEmpty()){
            node = linkedList.pop()
            println node.name
            if(node.left){
                linkedList.add(node.left);
            }
            if(node.right){
                linkedList.add(node.right);
            }


        }
    }

    static void main(String [] args){
        traverseDFS(createGraph())
    }


}
