package com.example.np1mobileproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText km, litros;
    Button calcular;
    TextView resultado;
    Spinner spinner;

    Button historico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        km = findViewById(R.id.editKm);
        litros = findViewById(R.id.editLitros);
        calcular = findViewById(R.id.btnCalcular);
        resultado = findViewById(R.id.txtResultado);
        Spinner spinner = findViewById(R.id.id_do_spinner);

        historico.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoricoActivity.class);
            startActivity(intent);
        });

        String[] combustiveis = {
          "Gasolina",
          "Diesel",
          "Etanol",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                combustiveis
        );

        spinner.setAdapter(adapter);


        calcular.setOnClickListener( v -> {

            String KmRodados = km.getText().toString();
            String litrosAbastecidos = litros.getText().toString();

            if (KmRodados.isEmpty() || litrosAbastecidos.isEmpty()) {
                resultado.setText("Preencha todos os campos!");
                return;
            }

            double KmNumero = Double.parseDouble(KmRodados);
            double litrosNumero = Double.parseDouble(litrosAbastecidos);

            if (KmNumero <= 0 || litrosNumero <= 0) {
                resultado.setText("Erro: os valores devem ser maiores que 0!");
                return;
            }

            double consumo = KmNumero / litrosNumero;

            String tipoDeCombustivel = spinner.getSelectedItem().toString();
            double preco = 0;

            if (tipoDeCombustivel.equals("Gasolina")) {preco = 6.66;}
            if (tipoDeCombustivel.equals("Diesel")) {preco = 7.10;}
            if (tipoDeCombustivel.equals("Etanol")) {preco = 4.20;}

            double custoPorKm = preco / consumo;

            resultado.setText(
                    "Combustível: " + tipoDeCombustivel +
                            "\nConsumo: " + consumo + " km/L" +
                            "\nPreço: R$ " + preco +
                            "\nCusto por km: R$ " + custoPorKm
            );
            SharedPreferences sharedPref = getSharedPreferences("historico", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            String registro =
                    "Combustível: " + tipoDeCombustivel +
                            " | Consumo: " + consumo + " km/L" +
                            " | Custo/km: R$ " + custoPorKm + "\n";

            String historicoAntigo = sharedPref.getString("dados", "");
            editor.putString("dados", historicoAntigo + registro);
            editor.apply();

        });
    }
}