package src;

public class Sonda extends Objeto{
    private Posicao posFinal;
    private int direcaoAtual;
    private static int[][] movimentos = {{ 0, 1},
                                        { 1, 0},
                                        { 0,-1},
                                        {-1, 0}};

    public Sonda(){
        super(0, 0);
        this.direcaoAtual = 0;
    }

    public Sonda(int x, int y, char direcaoInicial, Malha m){
        super(x, y);
        try{
            m.posicaoValida(x, y);
            this.diracaoInvalida(direcaoInicial);
        } catch (Exception e){
            throw e;
        }

        this.posFinal = new Posicao(x, y);
        this.direcaoAtual = diracaoParaInt(direcaoInicial);
    }
    public Posicao getPosFinal(){
        return this.posFinal;
    }
    public int getDirecaoAtual(){
        return this.direcaoAtual;
    }
    private int diracaoParaInt(char direcao){
        switch (direcao){
            case 'N':
               return 0;
            case 'E':
                return 1;
            case 'S':
                return 2;
            case 'W':
                return 3;
            case '*':
                return 4;
            default:
                throw new IllegalArgumentException("Direcao invalida!");
        }
    }
    public char intParaDirecao(int direcao){
        switch (direcao){
            case 0:
               return 'N';
            case 1:
                return 'E';
            case 2:
                return 'S';
            case 3:
                return 'W';
            case 4:
                return '*';
            default:
                throw new IllegalArgumentException("Direcao invalida!");
        }
    }
    public void diracaoInvalida(char direcao){
        if(direcao != 'N' && direcao != 'W' && direcao != 'E' && direcao != 'S' && direcao != '*')
            throw new IllegalArgumentException("Direcao invalida!");
    
    }
    public Posicao pousar(String instrucoes, Malha m){
        if(instrucoes.length() == 0){
            return this.posFinal;
        }
        int numInstrucoes = instrucoes.length();

        for(int index=0; index<numInstrucoes; index++){
            char currInstruction = instrucoes.charAt(index);
            try{
                m.verificarPosicaoValida(this.posFinal);
            } catch(Exception e){
                // System.out.println(e);
                return this.posFinal;
            }
            switch (currInstruction){
                case 'L':
                    if(this.direcaoAtual-1 < 0){
                        this.direcaoAtual = 3;
                    } else{
                        this.direcaoAtual = this.direcaoAtual-1;
                    }
                break;
                case 'R':
                    if(this.direcaoAtual+1 > 3){
                        this.direcaoAtual = 0;
                    } else{
                        this.direcaoAtual = this.direcaoAtual+1;
                    }
                break;
                case 'M':
                    this.posFinal.setPosicaoX(this.posFinal.getPosicaoX() + movimentos[this.direcaoAtual][0]);
                    this.posFinal.setPosicaoY(this.posFinal.getPosicaoY() + movimentos[this.direcaoAtual][1]);
                    if(this.posFinal.getPosicaoX() < 0 || this.posFinal.getPosicaoX() > m.getTamX()
                    || this.posFinal.getPosicaoY() < 0 || this.posFinal.getPosicaoY() > m.getTamY()){
                        System.out.println("A sonda saiu do espaco observavel!");
                        return this.posFinal;
                    }

                break;
                default:
                    System.out.println("Comando invalido, seguindo para proxima instrucao");
            }
        }
        return this.posFinal; 
    }
}
