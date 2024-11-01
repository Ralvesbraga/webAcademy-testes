import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Pilha<T> {
    List<T> pilha = new ArrayList<>();
    int tamanho = 0;


    public int size(){
        return tamanho;
    }

    public void push(T item){
        pilha.add(item);
        tamanho++;
    }
    
    public boolean isEmpty(){
        return (tamanho == 0);
    }

    public T pop(){
        if (!this.isEmpty()){
            T item = pilha.remove(this.tamanho-1);
            tamanho--;
            return item;
        }
        throw new EmptyStackException();
    }
}
