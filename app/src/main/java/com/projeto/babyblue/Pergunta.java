package com.projeto.babyblue;

public class Pergunta {

    //lista de perguntas de 1 a 10
    private final int[] listaPerguntas = {
            R.string.p1, R.string.p2, R.string.p3, R.string.p4, R.string.p5,
            R.string.p6, R.string.p7, R.string.p8, R.string.p9, R.string.p10
    };

    //lista de respostas para cada pergunta
    //cada pergunta possui 4 respostas
    private final int[][] listaOpcoesRespostas = {
            {R.string.r1, R.string.r2, R.string.r3, R.string.r4},
            {R.string.r5, R.string.r6, R.string.r7, R.string.r4},
            {R.string.r8, R.string.r9, R.string.r10, R.string.r11},
            {R.string.r12, R.string.r13, R.string.r9, R.string.r14},
            {R.string.r14, R.string.r9, R.string.r10, R.string.r11},
            {R.string.r15, R.string.r16, R.string.r17, R.string.r18},
            {R.string.r8, R.string.r9, R.string.r10, R.string.r11},
            {R.string.r8, R.string.r14, R.string.r10, R.string.r19},
            {R.string.r20, R.string.r14, R.string.r21, R.string.r11},
            {R.string.r22, R.string.r23, R.string.r24, R.string.r25}
    };

    //vetor para armazenar respostas do usuario
    private int[] respostas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    //peso para cada alternativa de cada pergunta especifica
    //cada pergunta possui 4 respostas que possuem seus respectivos pesos
    private final int[][] peso = {
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
    public int getPerguntas(int p) {
        return listaPerguntas[p];
    }

    //busca as opcoes de respostas para determinada pergunta
    public int getOpcoesRespostas(int p, int or) {
        return listaOpcoesRespostas[p][or];
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