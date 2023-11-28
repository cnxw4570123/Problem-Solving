import java.util.*;
import java.io.*;

class Solution {
    static List<Node> nodes = new ArrayList<>();
    static List<Integer> pre = new ArrayList<>(), post = new ArrayList<>();
    static Node root;
    public int[][] solution(int[][] nodeinfo) {
        for(int i = 0; i < nodeinfo.length; i++){
            int[] current = nodeinfo[i];
            nodes.add(new Node(i + 1, current[1], current[0]));
        }
        nodes.sort((node1, node2) -> {
            if(node1.y == node2.y){
                return node1.x - node2.x;
            }
            return node2.y - node1.y;
        });
        
        root = nodes.get(0);
        for(Node next : nodes){
            setNodes(root, next);
        }
        preorder(root);
        postorder(root);
        int[] prearr = new int[nodes.size()];
        int[] postarr = new int[nodes.size()];
        int idx =0;
        while(!pre.isEmpty()){
            prearr[idx++] = pre.remove(0);
        }
        idx =0;
        while(!post.isEmpty()){
            postarr[idx++] = post.remove(0);
        }
        
        return new int[][]{prearr, postarr};
    }
    
    static void setNodes(Node current, Node next){
        if(current.y <= next.y) return;
        if(current.x < next.x){
            if(current.right == null){
                current.right = next;
                return;
            }
            setNodes(current.right, next);
        } else{
            if(current.left == null){
                current.left = next;
                return;
            }
            setNodes(current.left, next);
        }
    }
    
    static void preorder(Node current){
        pre.add(current.num);
        if(current.left != null){
            preorder(current.left);
        }
        if(current.right != null){
            preorder(current.right);
        }
    }
    
    static void postorder(Node current){
        if(current.left != null){
            postorder(current.left);
        }
        if(current.right != null){
            postorder(current.right);
        }
        post.add(current.num);
        
    }
}

class Node{
    int num;
    int y;
    int x;
    Node right;
    Node left;
    
    public Node(int num, int y, int x){
        this.num = num;
        this.y = y;
        this.x = x;
    }
    
    public void setLeft(Node left){
        this.left = left;
    }
    
    public void setRight(Node right){
        this.right = right;
    }
    
    @Override
    public String toString(){
        return "Node = {" + num + ", "
            + y + ", " + x + "}";
    }
}