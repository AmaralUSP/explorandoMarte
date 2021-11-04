package src;

public class sonda extends objeto{
    private posicao finalPos;
    private int currDirection;
    private static int[][] moviments = {{ 0, 1},
                                        { 1, 0},
                                        { 0,-1},
                                        {-1, 0}};

    public sonda(int x, int y, char initialDirection){
        super(x, y);
        this.finalPos = new posicao(x, y);
        this.currDirection = 0;
        this.currDirection = directionToInt(initialDirection);
    }
    public posicao getfinalPos(){
        return this.finalPos;
    }
    public int getcurrDirection(){
        return this.currDirection;
    }
    private int directionToInt(char direction){
        switch (direction){
            case 'N':
               return 0;
            case 'E':
                return 1;
            case 'S':
                return 2;
            case 'W':
                return 3;
            default:
                System.out.println("Direcao invalida");
                return 0;
        }
    }
    public char intToDirection(int direction){
        switch (direction){
            case 0:
               return 'N';
            case 1:
                return 'E';
            case 2:
                return 'S';
            case 3:
                return 'W';
            default:
                System.out.println("Direcao invalida");
                return '0';
        }
    }
    public posicao pousar(String instructions){
        if(instructions.length() == 0){
            return this.finalPos;
        }
        int numInstructions = instructions.length();

        for(int index=0; index<numInstructions; index++){
            char currInstruction = instructions.charAt(index);
            
            switch (currInstruction){
                case 'L':
                    if(this.currDirection-1 < 0){
                        this.currDirection = 3;
                    } else{
                        this.currDirection = this.currDirection-1;
                    }
                break;
                case 'R':
                    if(this.currDirection+1 > 3){
                        this.currDirection = 0;
                    } else{
                        this.currDirection = this.currDirection+1;
                    }
                break;
                case 'M':
                    this.finalPos.setxPosition(this.finalPos.getxPosition() + moviments[this.currDirection][0]);
                    this.finalPos.setyPosition(this.finalPos.getyPosition() + moviments[this.currDirection][1]);
                break;
                default:
                    System.out.println("Comando invalido");
            }
        }
        return this.finalPos; 
    }
}
