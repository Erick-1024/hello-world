package com.cana.vbam.common.utils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TreeNode <T> implements Serializable {

    private static final long serialVersionUID = 1121587793899373233L;

    private String name;
    private T data;
    private List<TreeNode<T>> children;
    private TreeNode<T> parent;

    public TreeNode() {
        super();
    }

    public TreeNode(String name) {
        this.name = name;
    }

    public TreeNode(String name, T data) {
        this(name);
        this.data = data;
    }

    public TreeNode(String name, TreeNode<T> parent) {
        this(name);
        this.parent = parent;
    }

    public TreeNode(String name, TreeNode<T> parent, T data) {
        this(name, parent);
        this.data = data;
    }

    public void addChild(TreeNode<T> child) {
        if (this.children == null) {
            this.children = new LinkedList<>();
        }
        this.children.add(child);
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

}
