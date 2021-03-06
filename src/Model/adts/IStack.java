package Model.adts;

public interface IStack<T> {
    void push(T elem);
    T pop();
    boolean isEmpty();
}
