package myStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

public class MyStack<T> {
    private List<T> elements;

    public MyStack() {
        elements = new ArrayList<>();
    }

    public void add(T element) {
        elements.add(element);
    }

    public T pop() {
        if (size() == 0) {
            throw new EmptyStackException();
        }
        T element = elements.getLast();
        elements.removeLast();
        return element;
    }

    public int size() {return elements.size();}

    public void addAll(Collection<T> elements) {
        this.elements.addAll(elements);
    }
}