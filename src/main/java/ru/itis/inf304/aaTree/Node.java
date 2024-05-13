package ru.itis.inf304.aaTree;

import java.util.Objects;

public class Node{
    public int level; //уровень
    public Node left; //левый сын
    public Node right; //правый сын
    public Integer value; // значение

    public Node(Integer value, Node left, Node right){
        this.level = 1; //у листов уровень всегда 1
        this.left = left;
        this.right = right;
        this.value = value;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Node))
            return false;
        Node node = (Node)o;
        return level == node.level && Objects.equals(left, node.left) && Objects.equals(right, node.right) && Objects.equals(value, node.value);
    }
    public Node(Integer value){
        this(value, null, null);
    }

    public Node(){
        this.level = 0;
        this.value = null;
        this.left = this.right = this;
    }
}