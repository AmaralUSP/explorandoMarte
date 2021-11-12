# Explorando Marte
## Controle uma sonda e explore o planeta Marte!

1. ### Introdução 
O planeta Marte é um ambiente ainda pouco explorado, o usuário deve dar um comando a nave para que ela possa percorrer e explorar o planeta. 

2. ### Especificações
O código foi desenvolvido na versão openjdk "11.0.11" no ambiente do VisualStudio Code.  
A aplicação aceita receber entradas via teclado ou através de arquivo de texto.
A execução via terminal não necessita de nenhum tipo de alteração.

No caso de arquivos de texto siga esses passos:
    
    1. Adicione o arquivo de texto no diretório inputFiles.
    2. No diretório .vscode, abra o arquivo launch.json e altere o campo “args” com o caminho e o nome do arquivo que será lido. 
    ** No arquivo ha um exemplo comentado!
    3. Salve as alterações e execute o programa.  

3. ### Definições
- Direções: As direções adotadas na aplicação são definidas conforme a rosa dos ventos em inglês, ou seja, Norte = N, Leste = W, Oeste = E e, por fim, Sul = S. Além disso, o canto inferior esquerdo da malha é a posição (0,0) e a superior a esquerda eh (x,y), onde x e y são os limites do campo definidos pelo usuário.

- Posição e direção: A sonda sempre está dentro do limite "observavel", ou seja, um valor não negativo e dentro dos limites da malha definidos pelo usuário, e ela estará apontando para uma direção.

- Comando e instruções: O comando eh a forma que o usuário se comunica com a sonda e ele será utilizado para percorrer a malha. Para cada sonda o usuário informará um comando, uma sequência de caracteres compostas por instruções que podem ser dos tipo: L = rotação de 90 graus no sentido anti horário, R = rotação de 90 graus no sentido horário, M = mover uma unidade na direção da sonda. 

4. ### Execução
A aplicação possui passos bem definidos, sendo eles:

    1. Inicialmente, o usuário deve definir o tamanho da malha, ou seja, o tamanho do campo a ser explorado. O tamanho é composto por dois valores inteiros x e y, e devem ser informados primeiro o x e depois o y. 
    2. Em seguida, deve-se definir se haverá, ou não, obstáculos na malha.
    3. A composição atual da malha será apresentada ao usuário.
    4. Definir as informações da sonda, nesta ordem posição inicial x e y, respectivamente, e a direção inicial.
    5. Definir o comando o qual a sonda deverá seguir.
    6. A composição final da malha será apresentada. 
    7. Informar se há mais sondas a serem lançadas.
    8. Caso ainda haja sondas a serem lançadas, a aplicação retornará ao passo 4.

Caso a sonda anterior tenha pousado em uma posição válida, esta posição estará ocupada e será tratada como posição indisponível para pouso.