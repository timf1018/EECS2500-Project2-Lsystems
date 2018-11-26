import java.util.NoSuchElementException;

public class Queue {

    private String[] textC;
    private int front;
    private int back;

    public Queue(int capacity){
        textC = new String[capacity];
    }
    public void add(String text){
        if (back == textC.length){
            String[] newArray = new String[2 * textC.length];
            System.arraycopy(textC,0,newArray,0,textC.length);
            textC = newArray;
        }
        textC[back] = text;
        back++;
    }

    public String remove(){
        if(size() == 0){
            throw new NoSuchElementException();
        }
        String textR = textC[front];
        textC[front] = null;
        front++;
        if(size() == 0){
            front = 0;
            back = 0;
        }

        return textR;
    }

    public String peek(){
        if(size() == 0){
            throw new NoSuchElementException();
        }

        return textC[front];
    }

    public int size(){
        return back - front;
    }
    public void printQueue(){
        for(int i = front; i < back; i++){
            System.out.println(textC[i]);
        }
    }

}
