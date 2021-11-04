package src;
import java.util.Scanner;

public class main{
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        int xSize, ySize, begXPos, begYPos;
        String instructions;
        char initialDirection;
        
        // Ler tamanho da malha x e y
        xSize = sc.nextInt();
        ySize = sc.nextInt();
        
        malha m = new malha(xSize, ySize);
        obstaculo o_1 = new obstaculo(1, 1);
        obstaculo o_2 = new obstaculo(1, 2);
        obstaculo o_3 = new obstaculo(1, 5);
        obstaculo o_4 = new obstaculo(1, 4);
        m.addNewObject(o_1.getposition());
        m.addNewObject(o_2.getposition());
        m.addNewObject(o_3.getposition());
        m.addNewObject(o_4.getposition());

        begXPos = sc.nextInt();
        begYPos = sc.nextInt();
        initialDirection = sc.next().charAt(0);   

        sonda s = new sonda(begXPos, begYPos, initialDirection);

        sc.nextLine();
        // ler instrucoes de movimentos
        instructions = sc.nextLine();

        posicao finalPos = s.pousar(instructions);
        if(!m.addNewObject(finalPos)){
            System.out.println("A sonda colidiu!\n");
        }

        System.out.println("x: " + finalPos.getxPosition() + " y: " + finalPos.getyPosition() + ' ' + s.intToDirection(s.getcurrDirection()));
        sc.close();
    }
}