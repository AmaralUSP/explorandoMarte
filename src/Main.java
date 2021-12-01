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
        int posicaoInicialX, posicaoInicialY;
        char direcaoInicial;
        Sonda s = new Sonda();

        try{
            System.out.println("Informe a posicao inicial da sonda e a sua direcao:");
            
            System.out.println("Informe a coordenada x!");
            posicaoInicialX = sc.nextInt();

            System.out.println("Informe a coordenada y!");
            posicaoInicialY = sc.nextInt();
            
            System.out.println("Informe a direcao");
            direcaoInicial = sc.next().charAt(0);  
        } catch (Exception e){
            throw new InputMismatchException("Informacoes invalida, missao abortada");
        }

        try{
            Posicao posicaoInicial = new Posicao(posicaoInicialX, posicaoInicialY);
            m.verificarPosicaoValida(posicaoInicial);
        }
        catch(Exception e){
            throw new IllegalArgumentException("A posicao inicial selecionada ja esta ocupada."); 
        }
        try{
            s = new Sonda(posicaoInicialX, posicaoInicialY, direcaoInicial, m);
        } catch(Exception e){
            throw e;
        }

        return s;
    }
    public static void inserirObstaculo(Malha m){
        int x, y;
        int continuar, quantidadeDeObstaculos;
        int tamanhoX = m.getTamX();
        int tamanhoY = m.getTamY();

        try{
            System.out.println("Digite 1 para adicionar obstaculos e 0 para nao adicionar");
            continuar = sc.nextInt();
        } catch(Exception e){
            throw new InputMismatchException("Opcao invalida, missao abortada");
        }

        if(continuar == 0) return;
        
        if(continuar == 1){
            quantidadeDeObstaculos = (tamanhoX*tamanhoY)/FACIL;
        
            for(int qtd=0; qtd<quantidadeDeObstaculos; qtd++){
                x = (int)(Math.random() * tamanhoX + 1);
                y = (int)(Math.random() * tamanhoY + 1);

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
        int tamanhoX, tamanhoY;
        
        System.out.println("Informe o tamanho da malha");

        try{
            System.out.println("Informe o tamanho x");
            tamanhoX = sc.nextInt();
            System.out.println("Informe o tamanho y");
            tamanhoY = sc.nextInt();
        }catch (Exception e){
            throw new InputMismatchException("Valores invalidos, missao abortada");
        }
        
        if(tamanhoX < 1 || tamanhoY < 1)
            throw new IllegalArgumentException("O tamanho da malha deve ser no minimo 1x1!");
        
        m.setTamX(tamanhoX);
        m.setTamY(tamanhoY);
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
        
        while(continuar != 0){    
            System.out.println("Atualizando mapa de Marte ... ");
            System.out.println(m);

            try{
                s = novaSonda(m);
            } catch(Exception e){
                System.out.println(e);
                System.out.println("Insira as informacoes novamente");
                continue;    
            }
            
            System.out.println("Informe o comando:");
            sc.nextLine();
            instrucoes = sc.nextLine();
            
            Posicao posicaoFinal = new Posicao(0, 0);
            char direcao = s.intParaDirecao(s.getDirecaoAtual());

            s.pousar(instrucoes, m);
            direcao = s.intParaDirecao(s.getDirecaoAtual());
            posicaoFinal = s.getPosFinal();

            m.addNovoObjt(posicaoFinal, direcao);            
            
            System.out.println("Posicao final x: " + posicaoFinal.getPosicaoX() + " y: " + posicaoFinal.getPosicaoY() + ' ' + direcao);
            System.out.println("Digite 1 para continuar ou 0 para finalizar");
            continuar = sc.nextInt();
        }
        System.out.println("\n************Escaneamento finalizado!************\n");
        System.out.println(m);
        sc.close();
    }
}