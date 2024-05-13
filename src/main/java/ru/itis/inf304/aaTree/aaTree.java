package ru.itis.inf304.aaTree;

public interface aaTree{
    void clear(); //очистить дерево

    boolean isEmpty(); //проверка на пустоту

    Node skew(Node t); //наклон(устранение левого горизонтального ребра) - балансировка

    Node split(Node t); //разделение(устранение двух последовательных правых горизонтальных ребер) - балансировка

    void insert(Integer value);//вставить

    boolean search(Integer value); //найти

    void delete(Integer value);//удалить
}
