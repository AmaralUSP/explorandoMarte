package src;
import java.util.Scanner;

public class main{
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        int xSize, ySize, begXPos, begYPos, opc=-1;
        String instructions;
        char initialDirection;
        boolean nextSpaceship = true, newObstacle = true;
        // ************** mandar as operacoes para funcoes auxliares para "limpar" a main
        
        // Ler tamanho da malha x e y
        System.out.println("********* Pouse a sonda da NASA *********\n\n");
        System.out.println("Informe o tamanho da malha (x, y):\n");
        
        xSize = sc.nextInt();
        ySize = sc.nextInt();
        
        malha m = new malha(xSize, ySize);
        
        // verificar se eh nao negativo
        // Verificar se eh posicao valida
        // loop para adicionar novas malhas, obstaculos, etc
        
        System.out.println("Digite 1 para inserir novo obstaculo e 0 para continuar!\n");
        opc = sc.nextInt();

        while(newObstacle && opc != 0){
            while(opc != 1 && opc != 0){
                System.out.println("Digite 1 para inserir novo obstaculo e 0 para continuar!");
                opc = sc.nextInt();
            }

            if(opc == 0)
                newObstacle = false;
            else{
                System.out.println("Informe a posicao do obstaculo!");
                begXPos = sc.nextInt();
                begYPos = sc.nextInt();

                obstaculo o = new obstaculo(begXPos, begYPos);
                m.addNewObject(o.getposition());
            }
            opc = -1;
        }
    
        while(nextSpaceship){
            System.out.println("Informe a posicao inicial da sonda e a sua direcao:");

            begXPos = sc.nextInt();
            begYPos = sc.nextInt();
            initialDirection = sc.next().charAt(0);  

            sonda s = new sonda(begXPos, begYPos, initialDirection, m);

            System.out.println("Informe o comando:");
            sc.nextLine();
            // ler instrucoes de movimentos
            instructions = sc.nextLine();

            posicao finalPos = s.pousar(instructions);
            if(!m.addNewObject(finalPos)){
                System.out.println("A sonda colidiu!");
            }
            else
                System.out.println("x: " + finalPos.getxPosition() + " y: " + finalPos.getyPosition() + ' ' + s.intToDirection(s.getcurrDirection()));
            
            while(opc != 1 && opc != 0){
                System.out.println("Digite 1 para lancar nova sonda ou 0 para finalizar!");
                opc = sc.nextInt();
            }
            if(opc == 0)
                nextSpaceship = false;
        }
        sc.close();
    }
}