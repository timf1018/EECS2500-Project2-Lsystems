import java.util.NoSuchElementException;

public class Queue {

    private Rule[] queue;
    private int front;
    private int back;

    public Queue(int capacity){
        queue = new Rule[capacity];
    }
    public void add(Rule rule){
        if (back == queue.length){
            Rule[] newArray = new Rule[2 * queue.length];
            System.arraycopy(queue,0,newArray,0,queue.length);
            queue = newArray;
        }
        queue[back] = rule;
        back++;
    }
    public Rule remove(){
        if(size() == 0){
            throw new NoSuchElementException();
        }
        Rule rule = queue[front];
        queue[front] = null;
        front++;
        if(size() == 0){
            front = 0;
            back = 0;
        }

        return rule;
    }

    public Rule peek(){
        if(size() == 0){
            throw new NoSuchElementException();
        }

        return queue[front];
    }

    public int size(){
        return back - front;
    }
    public void printQueue(){
        for(int i = front; i < back; i++){
            System.out.println(queue[i]);
        }
    }

}
