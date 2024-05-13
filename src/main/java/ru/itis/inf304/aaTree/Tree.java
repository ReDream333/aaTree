package ru.itis.inf304.aaTree;


public class Tree implements aaTree{
    private Node root;
    private Node deletedNode;
    private Node lastNode;
    private static int opIn = 0;
    private static int opSr = 0;
    private static int opDl = 0;

    public boolean isEmpty(){
        return this.root == null;
    }
    public Tree(){
        this.root = null;
    }

    public void print(){
        if(this.root != null)
            print(this.root);
    }

    private void print(Node t){
        if(t == null)
            return;
        if(t.left != null)
            print(t.left);
        System.out.print(t.value + " ");
        if(t.right != null)
            print(t.right);
    }

    @Override
    public void clear(){
        root = null;
    }

    @Override
    public Node skew(Node t){
        if(t == null)
            return null;
        else if(t.left == null)
            return t;
        else if(t.left.level == t.level){
            Node left = t.left;
            t.left = left.right;
            left.right = t;
            opIn++;
            return left;
        } else
            return t;
    }

    @Override
    public Node split(Node t){

        if(t == null)
            return null;
        else if(t.right == null || t.right.right == null)
            return t;
        else if(t.level == t.right.right.level){
            Node right = t.right;
            t.right = right.left;
            right.left = t;
            right.level = right.level + 1;
            opIn++;
            return right;
        } else
            return t;
    }

    @Override
    public void insert(Integer value){
        this.root = insert(value, this.root);
    }

    private Node insert(Integer value, Node parent){
        opIn++;
        if(parent == null)
            parent = new Node((java.lang.Integer) value, null, null);
        else if(value.compareTo(parent.value) < 0)
            parent.left = insert(value, parent.left);
        else if(value.compareTo(parent.value) > 0)
            parent.right = insert(value, parent.right);
        else
            return parent;
        parent = skew(parent);
        parent = split(parent);
        return parent;
    }

    @Override
    public void delete(Integer value){
        deletedNode = null;
        this.root = this.delete(value, this.root);
    }

    private Node delete(Integer value, Node t){
        opDl++;
        if(t != null && t.left != null && t.right != null){
            lastNode = t;
            if(value.compareTo(t.value) < 0)
                t.left = delete(value, t.left);
            else{
                deletedNode = t;
                t.right = delete(value, t.right);
            }
            if(t == lastNode){
                if(deletedNode == null)
                    return t;
                deletedNode.value = t.value;
                t = t.right;
            } else if(t.left.level < t.level - 1 || t.right.level < t.level - 1){
                if(t.right.level > --t.level)
                    t.right.level = t.level;
                t = skew(t);
                t.right = skew(t.right);
                t.right.right = skew(t.right.right);
                t = split(t);
                t.right = split(t.right);
            }
        }
        return t;
    }

    @Override
    public boolean search(Integer value){
        return search(this.root, value);
    }

    private boolean search(Node t, Integer value){
        while((t != null)){
            opSr++;
            Integer tValue = t.value;
            if(value.compareTo(tValue) < 0)
                t = t.left;
            else if(value.compareTo(tValue) > 0)
                t = t.right;
            else{
                return true;
            }
        }
        return false;
    }


    public static int getOpDl() {
        return opDl;
    }

    public static void setOpDl(int opDl) {
        Tree.opDl = opDl;
    }

    public static int getOpSr() {
        return opSr;
    }

    public static void setOpSr(int opSr) {
        Tree.opSr = opSr;
    }

    public static int getOpIn() {
        return opIn;
    }

    public static void setOpIn(int op) {
        Tree.opIn = op;
    }
}

