package BST;

import java.util.ArrayList;
import java.util.Objects;

public class BSTree {
    public Node root;
    public boolean flag;
    public int height;
    public String owner;

    public BSTree(String input){
        owner = input;
        root = null;
    }

    public class Node {
        public String title;
        public String definition;
        public Node parent;
        public Node left;
        public Node right;

        Node(String item, String def){
            title = item;
            definition = def;
            parent = null;
            left = null;
            right = null;
        }

        public Node getSibling(){
            Node temp = this.parent;
            if (this == temp.right){
                return temp.left;
            } else return temp.right;
        }
    }

    public Boolean isEmpty(){
        return root == null;
    }

    public void insert(String item, String def){
        Node temp;
        temp = new Node(item, def);
        if (root == null) {
            height = 1;
            root = temp;
        }
        else{
            Node curr = root;
            while (curr != null) {
                temp.parent = curr;
                if (temp.title.compareToIgnoreCase(curr.title) > 0) curr = curr.right;
                else curr = curr.left;
                }
            if (temp.title.compareToIgnoreCase(temp.parent.title) > 0) temp.parent.right = temp;
            else temp.parent.left = temp;
        }
    }

    public void delete(String item){
        this.root = deletion(root,item);
    }

    Node deletion(Node root, String item){
        if (root == null){
            return null;
        }
        else if(item.compareToIgnoreCase(root.title) < 0)
            root.left = deletion(root.left,item);
        else if(item.compareToIgnoreCase(root.title) > 0)
            root.right = deletion(root.right,item);
        else{
            if (root.left == null && root.right == null){
                return null;
            }
            else if(root.left == null){
                Node parent = root.parent;
                root = root.right;
                root.parent = parent;
            }
            else if(root.right == null){
                Node parent = root.parent;
                root = root.left;
                root.parent = parent;
            }
            else {
                Node temp = minimum(root.right);
                root.title = temp.title;
                root.right = deletion(root.right,temp.title);
            }
        }
        return root;
    }

    Node minimum(Node root)
    {
        while (root.left != null)
        {
            root = root.left;
        }
        return root;
    }

    public Node search(String item){
        if (root == null){
            System.out.println("Tree is empty");
            return root;
        }
        Node curr = root;
        while (curr != null)
        {
            if (Objects.equals(curr.title, item)) {
                break;
            } else {
                if (item.compareToIgnoreCase(curr.title) > 0)
                    curr = curr.right;
                else curr = curr.left;
            }
        }
        return curr;
    }

    public ArrayList<String> inorder(Node n, ArrayList<String> temp){
        if (n!=null){
            inorder(n.left,temp);
            temp.add(n.title);
            inorder(n.right,temp);
        }
        return temp;
    }




}
