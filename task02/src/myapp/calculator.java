package myapp;
import java.io.Console;
public class calculator {
    public static void main(String[] args) {
        
        System.out.println("Welcome.");
        float last = 0;
        String[] input = {" "};
        float result = 0;

        do{
            Console cons = System.console();
            String userInput = cons.readLine("> ");
            input = userInput.split(" ");
            float x = 0;
            float y = 0;
            String sign;
            boolean forX = false;
            boolean forY = false;
            if (input[0].equals("exit")){
                break;
            }

            if (input[0].equals("$last")){
                x = last;
                forX = true;
            }
            
            if (input[2].equals("$last")){
                y = last;
                forY = true;
            }

            if (forX != true) {
                x = Float.parseFloat(input[0]);
            }

            if (forY != true) {
                y = Float.parseFloat(input[2]);
            }

            sign = input[1];

            switch(sign){
                case "+":
                    result = x + y;
                    last = result;
                    System.out.println(result);
                    break;
                case "-":
                    result = x-y;
                    last = result;
                    System.out.println(result);
                    break;
                case "/":
                    result = x/y;
                    last = result;
                    System.out.println(result);
                    break;    
                case "*":
                    result = x*y;
                    last = result;
                    System.out.println(result);
                    break;
                default:
                    System.out.println("Invalid sign");
            }
        }while(!input[0].equals("exit"));
        System.out.println("Bye bye");
    }
}