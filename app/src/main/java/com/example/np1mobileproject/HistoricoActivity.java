package com.example.np1mobileproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HistoricoActivity extends AppCompatActivity {

    //Container dos cards
    LinearLayout container;
    Button btnVoltar;
    Button btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        container = findViewById(R.id.containerHistorico);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnLimpar = findViewById(R.id.btnLimpar);

        //Botão que apaga todos os dados do histórico
        //Método clear() remove todas as informações armazenadas

        btnLimpar.setOnClickListener(v -> {

            SharedPreferences sharedPref = getSharedPreferences("historico", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.clear();
            editor.apply();

            container.removeAllViews();

            //Recarrega a Activity para atualizar a tela depois de limpar
            recreate();
        });

        btnVoltar.setOnClickListener(v -> finish());

        // Abre os dados salvos utilizando SharedPreferences.
        SharedPreferences sharedPref = getSharedPreferences("historico", MODE_PRIVATE);
        //Pega o histórico armazenado em formato de texto
        String dados = sharedPref.getString("dados", "");

        //Verifica se tem histórico salvo, se sim
        if (!dados.isEmpty()) {
            //Divide as respostas salvas por linha
            String[] respostas = dados.split("\n");

            //Percorre todas as respostas
            for (String r : respostas) {

                //Criação de cards baseada em exemplos de LayoutInflater
                //Referência: https://developer.android.com/reference/android/view/LayoutInflater
                //Cria um card baseado no XML

                View card = getLayoutInflater()
                        .inflate(R.layout.card_historico, container, false);

                TextView combustivel = card.findViewById(R.id.txtCombustivel);
                TextView consumo = card.findViewById(R.id.txtConsumo);
                TextView preco = card.findViewById(R.id.txtPreco);
                TextView custo = card.findViewById(R.id.txtCusto);

                //Divide os dados da resposta
                String[] partes = r.split("\\|");

                //Mostra os dados na tela
                combustivel.setText(partes[0]);
                consumo.setText(partes[1]);
                custo.setText(partes[2]);
                preco.setText(partes[3]);

                //Insere o card dentro do container(LinearLayout)
                container.addView(card);
            }
        }
    }
}