package com.projeto.babyblue;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.babyblue.model.Pergunta;

public class TesteActivity extends AppCompatActivity {

    //declarando componentes
    private TextView tvPerguntas;
    private TextView tvIntro;
    private Button btnFinalizar;
    private RadioGroup rgOpcoes;
    private RadioButton rbOp1, rbOp2, rbOp3, rbOp4;
    private Button btnProx, btnVoltar;

    //declarando e instanciando objeto pergunta
    //será apenas um objeto trabalho que possui todas as perguntas e opcoes de resposta
    private Pergunta pergunta = new Pergunta();

    //contador que serve para identificar em qual pergunta está no momento
    private int perguntaAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        //inicia componentes e coloca primeira pergunta para o usuario
        iniciarComponentes();
        gerarPergunta();

        //opcoes de resposta para o usuario escolher
        rgOpcoes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //de acordo com sua escolha, sera enviado a identificacao da pergunta atual e a resposta selecionada
                switch (checkedId){
                    case R.id.rbOp1:
                        pergunta.setRespostas(perguntaAtual, 1);
                        break;
                    case R.id.rbOp2:
                        pergunta.setRespostas(perguntaAtual, 2);
                        break;
                    case R.id.rbOp3:
                        pergunta.setRespostas(perguntaAtual, 3);
                        break;
                    case R.id.rbOp4:
                        pergunta.setRespostas(perguntaAtual, 4);
                        break;
                    default:
                        //como padrao, a escolha vai com valor 0 para futuros ajustes no marcador
                        pergunta.setRespostas(perguntaAtual, 0);
                        break;
                }
            }
        });

        //botao de acao para voltar
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //definindo o começo das questoes de 1 ate 10, porem como estamos trabalhando com indice, começa pelo valor 0 ate 9
                if(perguntaAtual > 0){

                    //decrementa para a pergunta anterior
                    perguntaAtual--;
                    rotina();

                    //debug para saber se está recebendo os valores corretos das repostas (1, 2, 3 ou 4)
                    Log.i("Pergunta " + perguntaAtual, String.valueOf(pergunta.getRespostas(perguntaAtual)));
                }
            }
        });

        //botao de acao para avancar
        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //debug para saber se está recebendo os valores corretos das repostas (1, 2, 3 ou 4)
                Log.i("Pergunta " + perguntaAtual, String.valueOf(pergunta.getRespostas(perguntaAtual)));

                //definindo o limite de questoes ate 10, porem como estamos trabalhando com indice, vai ate 9
                if(perguntaAtual < 10){

                    //verifica se o usuario respondeu a questao anterior antes de passar para a proxima
                    if(pergunta.getRespostas(perguntaAtual) <= 0){
                        Toast.makeText(TesteActivity.this, R.string.responderQuestaoAnterior, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //incrementa para a proxima pergunta
                    perguntaAtual++;
                    rotina();
                }
            }
        });

        //botao para finalizar questionario e realizar calculo do resultado
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //funcao para finalizar questionario e realizar calculos do resultado
                finalizar();

                Intent i = new Intent(TesteActivity.this, MapaActivity.class);
                startActivity(i);

            }
        });
    }

    //funcao que instancia componentes graficos do sistema
    private void iniciarComponentes() {
        tvPerguntas = findViewById(R.id.tvPerguntas);
        tvIntro = findViewById(R.id.tvIntro);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnProx = findViewById(R.id.btnProx);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        rgOpcoes = findViewById(R.id.rgOpcoes);
        rbOp1 = findViewById(R.id.rbOp1);
        rbOp2 = findViewById(R.id.rbOp2);
        rbOp3 = findViewById(R.id.rbOp3);
        rbOp4 = findViewById(R.id.rbOp4);
    }

    private void rotina(){
        //funcao para alterações na tela
        mostrarIntro();
        //gera uma nova pergunta ou pode gerar a pergunta anterior)
        gerarPergunta();
        //armazena no 'or' a resposta escolhida pelo usuario e envia para o registro
        int or = pergunta.getRespostas(perguntaAtual);
        registrarResposta(or);
    }

    //funcao que mostra ou esconde a intro e muda visibilidade dos botoes
    private void mostrarIntro() {
        if (perguntaAtual > 0)
            tvIntro.setVisibility(View.GONE);
        else
            tvIntro.setVisibility(View.VISIBLE);

        if(perguntaAtual < 9){
            btnProx.setVisibility(View.VISIBLE);
            btnFinalizar.setVisibility(View.GONE);
        }
        else{
            btnProx.setVisibility(View.GONE);
            btnFinalizar.setVisibility(View.VISIBLE);
        }
    }

    //funcao de configuracao, serve para colocar os textos das perguntas e opcoes de resposta para o usuario
    private void gerarPergunta(){
        //busca a pergunta especifica baseada no contador (perguntaAtual)
        tvPerguntas.setText(pergunta.getPerguntas(perguntaAtual));

        //busca as opcoes de resposta da pergunta especifica
        //é enviado o contador especificando a pergunta e os indices das opcoes de resposta
        rbOp1.setText(pergunta.getOpcoesRespostas(perguntaAtual, 0));
        rbOp2.setText(pergunta.getOpcoesRespostas(perguntaAtual, 1));
        rbOp3.setText(pergunta.getOpcoesRespostas(perguntaAtual, 2));
        rbOp4.setText(pergunta.getOpcoesRespostas(perguntaAtual, 3));
    }

    //funcao que serve para marcar e desmarcar as opcoes de resposta quando o usuario clicar no botao de voltar ou proximo
    //essa funcao se encarregua de: as respostas que o usuario ja marcou serao marcadas novamente, assim funcionando como um registro temporario
    public void registrarResposta(int or){
        //de acordo com a opcao de resposta selecionada pelo usuario, é realizado a marcação da opcao desejada
        switch (or){
            case 1:
                rgOpcoes.check(R.id.rbOp1);
                break;
            case 2:
                rgOpcoes.check(R.id.rbOp2);
                break;
            case 3:
                rgOpcoes.check(R.id.rbOp3);
                break;
            case 4:
                rgOpcoes.check(R.id.rbOp4);
                break;
            default:
                //caso não tenha marcado nenhuma opcao, todas os radio buttons serao desmarcados
                rgOpcoes.clearCheck();
                break;
        }
    }

    //funcao que realiza os calculos do resultado
    private void finalizar() {
        //se chegar na ultima pergunta, retorna a pontuação
        //realizar armazenamento do historico aqui

        int selecionado = rgOpcoes.getCheckedRadioButtonId();

        if(perguntaAtual >= 9 && selecionado >= 0){
            int resultado = pergunta.resultado();
            Log.i("Resultado", String.valueOf(resultado));
            Toast.makeText(TesteActivity.this, "Resultado: " + String.valueOf(resultado), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(TesteActivity.this, R.string.responderQuestaoAnterior, Toast.LENGTH_SHORT).show();
        }
    }
}