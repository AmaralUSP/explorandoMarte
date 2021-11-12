package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    private static Scanner sc;
    private static int DIFICIL = 3;
    private static int MEDIO = 4;
    private static int FACIL = 5;

    public static Sonda novaSonda(Malha m){
        int iniPosX, iniPosY;
        char direcIni;
        Sonda s = new Sonda();

        try{
            System.out.println("Informe a posicao inicial da sonda e a sua direcao:");
            
            System.out.println("Informe a coordenada x!");
            iniPosX = sc.nextInt();

            System.out.println("Informe a coordenada y!");
            iniPosY = sc.nextInt();
            
            System.out.println("Informe a direcao");
            direcIni = sc.next().charAt(0);  
        } catch (Exception e){
            throw new InputMismatchException("Informacoes invalida, missao abortada");
        }

        try{
            s = new Sonda(iniPosX, iniPosY, direcIni, m);
        } catch(Exception e){
            throw e;
        }

        return s;
    }
    public static void inserirObstaculo(Malha m){
        int x, y;
        int continuar, qtdObstaculos;
        int tamX = m.getTamX();
        int tamY = m.getTamY();

        try{
            System.out.println("Digite 1 para adicionar obstaculos e 0 para nao adicionar");
            continuar = sc.nextInt();
        } catch(Exception e){
            throw new InputMismatchException("Opcao invalida, missao abortada");
        }

        if(continuar == 0) return;
        
        if(continuar == 1){
            qtdObstaculos = (tamX*tamY)/FACIL;
        
            for(int qtd=0; qtd<qtdObstaculos; qtd++){
                x = (int)(Math.random() * tamX + 1);
                y = (int)(Math.random() * tamY + 1);

                try{
                    m.posicaoValida(x, y);
                } catch(Exception e){
                    continue;
                }

                Obstaculo o = new Obstaculo(x, y);
                m.addNovoObjt(o.getPosicao(), '*');
            }
        } else
            throw new InputMismatchException("Opcao invalida, missao abortada");
    }   
    public static void construirMarte(Malha m){
        int tamX, tamY;
        
        System.out.println("Informe o tamanho da malha");

        try{
            System.out.println("Informe o tamanho x");
            tamX = sc.nextInt();
            System.out.println("Informe o tamanho y");
            tamY = sc.nextInt();
        }catch (Exception e){
            throw new InputMismatchException("Valores invalidos, missao abortada");
        }
        
        if(tamX < 1 || tamY < 1)
            throw new IllegalArgumentException("O tamanho da malha deve ser no minimo 1x1!");
        
        m.setTamX(tamX);
        m.setTamY(tamY);
    }       
    public static void main( String[] args ) {
        Sonda s = new Sonda();
        Malha m = new Malha(0,0);
        int continuar = 1;
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
        } catch(InputMismatchException ME){
            System.out.println(ME);
            return;
        } catch(Exception e){
            System.out.println("Alguns asteroides colidiram");
        }
        
        while(true){           
            if(continuar == 0)
                break;

            System.out.println("Atualizando mapa de Marte ... ");
            System.out.println(m);

            try{
                s = novaSonda(m);
            } catch(Exception e){
                System.out.println(e);
                System.out.println("Abortar missao!");
                break;    
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
        System.out.println(m);
        sc.close();
    }
}