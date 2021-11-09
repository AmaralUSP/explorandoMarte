package src;
import java.nio.file.WatchService;
import java.util.Scanner;

import javax.swing.plaf.TextUI;

public class Main{
    private static Scanner sc = new Scanner(System.in);
    public static Sonda novaSonda(Malha m){
        int iniPosX, iniPosY;
        char iniDirec;
        Sonda s = new Sonda();

        System.out.println("Informe a posicao inicial da sonda e a sua direcao:");

        iniPosX = sc.nextInt();
        iniPosY = sc.nextInt();
        iniDirec = sc.next().charAt(0);  

        try{
            s = new Sonda(iniPosX, iniPosY, iniDirec, m);
        } catch(Exception e){
            System.out.println(e);
            return new Sonda();
        }

        return s;
    }
    public static void inserirObstaculo(Malha m){
        int x, y;
        int continuar = 1;

        while(true){
            System.out.println("Digite 1 para adicionar um obstaculo e 0 para nao adicionar");
            continuar = sc.nextInt();

            if(continuar == 0) break;
            if(continuar == 1){
                System.out.println("Informe a posicao do obstaculo!");
                x = sc.nextInt();
                y = sc.nextInt();

                try{
                    m.posicaoValida(x, y);
                } catch(Exception e){
                    System.out.println(e);
                }

                Obstaculo o = new Obstaculo(x, y);
                m.addNovoObjt(o.getPosicao());
            }
        }
    }
    
    public static void construirMarte(Malha m){
        int tamX, tamY;
        
        System.out.println("Informe o tamanho da malha (x, y):");
        
        // Ler tamanho da malha x e y
        tamX = sc.nextInt();
        tamY = sc.nextInt();

        if(tamX < 1 || tamY < 1)
            throw new IllegalArgumentException("O tamanho da malha deve ser no minimo 1x1!");
        
        m.setTamX(tamX);
        m.setTamY(tamY);
    }
        
    public static void main( String[] args ) {
        Sonda s = new Sonda();
        Malha m = new Malha(0,0);
        int continuar =1;
        String instrucoes;
        boolean valoresInvalidos = true;

        System.out.println("********* Pouse a sonda da NASA *********\n\n");
        
        while (valoresInvalidos) {
            valoresInvalidos = false;
            try{
                construirMarte(m);
            } catch(Exception e){
                valoresInvalidos = true;
                System.out.println(e);
            }
        }

        try{
            inserirObstaculo(m);
        }catch(Exception e){
            System.out.println(e);
        }

        while(continuar != 0){
            try{
                s = novaSonda(m);
            } catch(Exception e){
                System.out.println(e);
                break;
            }
            
            System.out.println("Informe o comando:");
            sc.nextLine();
            // ler instrucoes de movimentos
            instrucoes = sc.nextLine();

            Posicao posicaoFinal = new Posicao(0, 0);
            try{
                posicaoFinal = s.pousar(instrucoes, m);
                m.addNovoObjt(posicaoFinal);
            } catch(Exception e){
                System.out.println(e);
            }

            System.out.println("Posicao final x: " + posicaoFinal.getPosicaoX() + " y: " + posicaoFinal.getPosicaoY() + ' ' + s.intParaDirecao(s.getDirecaoAtual()));
            System.out.println("Digite 1 para adicionar uma nova sonda e 0 para finalizar");
            continuar = sc.nextInt();
        }
        sc.close();
    }
}