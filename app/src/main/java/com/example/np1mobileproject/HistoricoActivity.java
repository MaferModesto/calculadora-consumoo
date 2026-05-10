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

    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        container = findViewById(R.id.containerHistorico);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        Button btnLimpar = findViewById(R.id.btnLimpar);

        btnLimpar.setOnClickListener(v -> {

            SharedPreferences sharedPref =
                    getSharedPreferences("historico", MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPref.edit();

            editor.clear();
            editor.apply();

            container.removeAllViews();

            recreate();
        });
        btnVoltar.setOnClickListener(v -> finish());

        SharedPreferences sharedPref = getSharedPreferences("historico", MODE_PRIVATE);
        String dados = sharedPref.getString("dados", "");

        if (!dados.isEmpty()) {
            String[] registros = dados.split("\n");

            for (String r : registros) {

                View card = getLayoutInflater()
                        .inflate(R.layout.card_historico, container, false);

                TextView combustivel = card.findViewById(R.id.txtCombustivel);
                TextView consumo = card.findViewById(R.id.txtConsumo);
                TextView preco = card.findViewById(R.id.txtPreco);
                TextView custo = card.findViewById(R.id.txtCusto);

                String[] partes = r.split("\\|");

                combustivel.setText(partes[0]);
                consumo.setText(partes[1]);
                custo.setText(partes[2]);
                preco.setText(partes[3]);

                container.addView(card);
            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}