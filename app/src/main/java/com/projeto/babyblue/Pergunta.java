package com.projeto.babyblue;

public class Pergunta {

    //lista de perguntas de 1 a 10
    private String[] listaPerguntas = {
            "Eu tenho sido capaz de rir e achar graça das coisas",
            "Eu sinto prazer quando penso no que está por acontecer em meu dia-a-dia",
            "Eu tenho me culpado sem necessidade quando as coisas saem erradas",
            "Eu tenho me sentido ansiosa ou preocupada sem uma boa razão",
            "Eu tenho me sentido assustada ou em pânico sem um bom motivo",
            "Eu tenho me sentido esmagada pelas tarefas e acontecimentos do meu dia-a-dia",
            "Eu tenho me sentido tão infeliz que eu tenho tido dificuldade de dormir",
            "Eu tenho me sentido triste ou arrasada",
            "Eu tenho me sentido tão infeliz que eu tenho chorado",
            "A idéia de fazer mal a mim mesma passou por minha cabeça"
    };

    //lista de respostas para cada pergunta
    //cada pergunta possui 4 respostas
    private String[][] listaOpcoesRespostas = {
            {"Como eu sempre fiz", "Não tanto quanto antes", "Sem dúvida, menos que antes", "De jeito nenhum"},
            {"Como sempre senti", "Talvez, menos que antes", "Com certeza menos", "De jeito nenhum"},
            {"Sim, na maioria das vezes", "Sim, algumas vezes", "Não muitas vezes", "Não, nenhuma vez"},
            {"Não, de maneira alguma", "Pouquíssimas vezes", "Sim, algumas vezes", "Sim, muitas vezes"},
            {"Sim, muitas vezes", "Sim, algumas vezes", "Não muitas vezes", "Não, nenhuma vez"},
            {"Sim. Na maioria das vezes eu não consigo lidar bem com eles",
                    "Sim. Algumas vezes não consigo lidar bem como antes",
                    "Não. Na maioria das vezes consigo lidar bem com eles",
                    "Não. Eu consigo lidar com eles tão bem quanto antes"},
            {"Sim, na maioria das vezes", "Sim, algumas vezes", "Não muitas vezes", "Não, nenhuma vez"},
            {"Sim, na maioria das vezes", "Sim, muitas vezes", "Não muitas vezes", "Não, de jeito nenhum"},
            {"Sim, quase todo o tempo", "Sim, muitas vezes", "De vez em quando", "Não, nenhuma vez"},
            {"Sim, muitas vezes, ultimamente", "Algumas vezes nos últimos dias", "Pouquíssimas vezes, ultimamente ", "Nenhuma vez"}
    };

    //vetor para armazenar respostas do usuario
    private int[] respostas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    //peso para cada alternativa de cada pergunta especifica
    //cada pergunta possui 4 respostas que possuem seus respectivos pesos
    private int[][] peso = {
            {0, 1, 2, 3},
            {0, 1, 2, 3},
            {3, 2, 1, 0},
            {0, 1, 2, 3},
            {3, 2, 1, 0},
            {3, 2, 1, 0},
            {3, 2, 1, 0},
            {3, 2, 1, 0},
            {3, 2, 1, 0},
            {3, 2, 1, 0}
    };

    //busca uma pergunta especifica na lista de perguntas de acordo com o valor enviado
    public String getPerguntas(int p) {
        String pergunta = listaPerguntas[p];
        return pergunta;
    }

    //busca as opcoes de respostas para determinada pergunta
    public String getOpcoesRespostas(int p, int or) {
        String respostas = listaOpcoesRespostas[p][or];
        return respostas;
    }

    //armazena as respostas do usuario no vetor, levando em conta a pergunta e a opcao escolhida
    public void setRespostas(int p, int or) {
        respostas[p] = or;
    }

    //retorna o valor da resposta do usuario (1, 2, 3 ou 4)
    public int getRespostas(int p) {
        return respostas[p];
    }

    //retorna os pesos para calculos do resultado
    public int getPeso(int p, int r) {
        return peso[p][r];
    }

    //funcao que realiza o calculo do funcionario
    public int resultado(){

        //declaramos uma variavel para acumular a soma da pontuacao
        int calculo = 0;
        //percorre todas as perguntas
        for(int p = 0; p < 10; p++){
            //realiza o somatorio
            //pega o peso especifico da resposta de determinada pergunta
            //envia o indice da pergunta para o peso
            //envia o indice da pergunta para respostas
            calculo += getPeso(p, getRespostas(p)-1);
        }
        //por fim retorna o calculo do resultado
        return calculo;
    }

}