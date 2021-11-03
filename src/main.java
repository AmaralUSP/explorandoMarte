package src;
import java.util.Scanner;

public class main{

    // The main method
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        int xSize, ySize, begXPos, begYPos;
        String instructions;
        char initialDirection;
        
        // Ler tamanho da malha x e y
        xSize = sc.nextInt();
        ySize = sc.nextInt();
        
        malha m = new malha(xSize, ySize,2);

        begXPos = sc.nextInt();
        begYPos = sc.nextInt();
        initialDirection = sc.next().charAt(0);   

        sonda s = new sonda(begXPos, begYPos, initialDirection);

        sc.nextLine();
        // ler instrucoes de movimentos
        instructions = sc.nextLine();

        s.pousar(instructions);
        posicao finalPos = s.getfinalPos();

        System.out.println("x: " + finalPos.getxPosition() + " y: " + finalPos.getyPosition() + ' ' + s.intToDirection(s.getcurrDirection()));
        sc.close();
    }
}