# Explorando Marte
## Controle uma sonda e explore o planeta Marte!

1. ### Introducao 
O planeta Marte eh um ambiente ainda pouco explorado, o usuario deve dar um comando a nave para que ela possa percorrer e explorar o planeta. 

2. ### Especificacoes
O codigo foi desenvolvido na versao openjdk "11.0.11" no ambiente do VisualStudio Code.  
A aplicacao aceita receber entradas via teclado ou atraves de arquivos de texto.
A execucao via terminal nao necessita de nenhum tipo de alteracao.
Para arquivos de texto:
    
    1. Adicione o arquivo de texto no diretorio inputFiles.
    2. No diretorio .vscode, abra o arquivo launch.json e altere o campo args com o caminho e o nome do arquivo que sera lido.
    3. Salve as alteracoes e execute o programa.  

3. ### Definicoes
- Direcoes: As direcoes adotadas na aplicacao sao definidas conforme a rosa dos ventos em ingles, ou seja, Norte = N, Leste = W, Oeste = E e, por fim, Sul = S. Alem disso, o canto inferior esquerdo da malha eh a posicao (0,0) e a superior a esquer eh (x,y), onde x e y sarao os limites do campo definidos pelo usuario.

- Posicao e direcao: A sonda sempre esta dentro do limite "observavel", ou seja, dentro dos limites da malha definidos pelo usuario, e uma diracao para a qual ela estara apontando. 

- Comando e instrucoes: Para cada sonda o usuario informara um comando, ou seja, uma sequencia de caracteres compostas por instrucoes que podem ser dos tipos: L = rotacao de 90 em sentido antihorario, R = rotacao de 90 em sentido horario, M = mover uma unidade na direcao para qual a sonda esta apontando. As instrucoes serao utilizadas para percorrer a malha. 

4. ### Execucao
A aplicacao possui alguns passos bem definidos, sendo eles:

    1. Inicialmente, o usuario deve definir o tamanho da malha, ou seja, o tamanho do campo a ser explorado. 
    2. Em seguida, deve-se definir se havera, ou nao, obstaculos na malha.
    3. Escolher a posicao inicial da sonda e a direcao a qual ela esta apontando.
    4. Definir o comando o qual a sonda devera seguir.
    5. A composicao atual da malha sera apresentada ao usuario e, ao fim das computacoes, a composicao final da malha tambem sera apresentada. 
    6. Informar se ha mais sondas a serem lancadas.

Caso ainda hajam sondas a serem lancadas, a aplicacao retornara ao passo 3, contudo, caso a sonda anterior tenha pousado em uma posicao valida, esta posicao ainda estara ocupada e sera tratada como posicao indisponivel para pouso.

