package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class Main{
    private static Scanner sc;
    private static int DIFICIL = 3;
    private static int MEDIO = 4;
    private static int FACIL = 5;

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
        int continuar, qtdObstaculos;
        int xSize = m.getTamX();
        int ySize = m.getTamY();

        System.out.println("Digite 1 para adicionar um obstaculo e 0 para nao adicionar");
        continuar = sc.nextInt();
        
        if(continuar == 0) return;
        
        if(continuar == 1){
            qtdObstaculos = (xSize*ySize)/FACIL;
        
            for(int qtd=0; qtd<qtdObstaculos; qtd++){
                x = (int)(Math.random() * xSize + 1);
                y = (int)(Math.random() * ySize + 1);

                try{
                    m.posicaoValida(x, y);
                } catch(Exception e){
                    continue;
                }

                Obstaculo o = new Obstaculo(x, y);
                m.addNovoObjt(o.getPosicao(), '*');
            }
        } else
            System.out.println("Opcao invalida, os obstaculos nao foram adicionados");
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
        File file;
        
        if(args.length > 0){
            try{
                file = new File(args[0]);
                sc = new Scanner(file);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else 
            sc = new Scanner(System.in);

        System.out.println("********* Pouse a sonda da NASA *********\n\n");
        
        try{
            construirMarte(m);
        } catch(Exception e){
            System.out.println(e);
            return;
        }

        try{
            inserirObstaculo(m);
        } catch(Exception e){
            System.out.println("Alguns asteroides colidiram");
        }
        
        while(true){           
            if(continuar == 0)
                break;

            System.out.println("Atualizando mapa de Marte ... ");
            System.out.println(m.imprimirMalha());

            try{
                s = novaSonda(m);
            } catch(Exception e){
                System.out.println(e);
                return;
            }
            
            System.out.println("Informe o comando:");
            sc.nextLine();
            instrucoes = sc.nextLine();
            
            Posicao posicaoFinal = new Posicao(0, 0);
            char direcao = s.intParaDirecao(s.getDirecaoAtual());

            try{
                posicaoFinal = s.pousar(instrucoes, m);
                direcao = s.intParaDirecao(s.getDirecaoAtual());
                m.addNovoObjt(posicaoFinal, direcao);
            } catch(Exception e){
                System.out.println(e);
            }
            
            System.out.println("Posicao final x: " + posicaoFinal.getPosicaoX() + " y: " + posicaoFinal.getPosicaoY() + ' ' + direcao);
            System.out.println("Digite 1 para continuar ou 0 para finalizar");
            continuar = sc.nextInt();
        }
        System.out.println("\n************Escaneamento finalizado!************\n");
        System.out.println(m.imprimirMalha());
        sc.close();
    }
}