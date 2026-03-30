package com.example.np1mobileproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText km, litros;
    Button calcular;
    TextView resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        km = findViewById(R.id.editKm);
        litros = findViewById(R.id.editLitros);
        calcular = findViewById(R.id.btnCalcular);
        resultado = findViewById(R.id.txtResultado);

        calcular.setOnClickListener( v -> {

            String KmRodados = km.getText().toString();
            String litrosAbastecidos = litros.getText().toString();

            if (KmRodados.isEmpty() || litrosAbastecidos.isEmpty()) {
                resultado.setText("Preencha todos os campos!");
                return;
            }

            double KmNumero = Double.parseDouble(KmRodados);
            double litrosNumero = Double.parseDouble(litrosAbastecidos);

            /*if (litrosNumero == 0) {
                resultado.setText("Erro: Litros não pode ser igual a 0!");
                return;
            }*/

            double consumo = KmNumero / litrosNumero;


            resultado.setText("Consumo: " + consumo + " km/L");

        });
    }
}