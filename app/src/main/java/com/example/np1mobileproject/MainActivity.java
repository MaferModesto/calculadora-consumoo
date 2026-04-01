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

    //Declarando as variáveis, colocando nome nos componentes
    EditText km, litros;
    Button calcular;
    TextView resultado;

    //Código que já vem e a gnt apaga aql parte de erro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    //Fazendo a conexão entre os componentes do XML e o JAVA, por meio do id
        km = findViewById(R.id.editKm);
        litros = findViewById(R.id.editLitros);
        calcular = findViewById(R.id.btnCalcular);
        resultado = findViewById(R.id.txtResultado);

        //método quando o usuário clica no botão
        calcular.setOnClickListener( v -> {

            //recebe os valores digitados e converte para String
            String KmRodados = km.getText().toString();
            String litrosAbastecidos = litros.getText().toString();

            //Se KmRodados estiver vazio ou litrosAbastecidos estiver vazio, retorna a msg de erro
            if (KmRodados.isEmpty() || litrosAbastecidos.isEmpty()) {
                resultado.setText("Preencha todos os campos!");
                return;
            }

            //convertendo String para números (double)
            double KmNumero = Double.parseDouble(KmRodados);
            double litrosNumero = Double.parseDouble(litrosAbastecidos);

            //Se o EditText (litros) estiver com 0 ou número negativo, retorna a msg de erro
            if (litrosNumero <= 0) {
                resultado.setText("Erro: Litros não pode ser igual a 0!");
                return;
            }

            //fazendo a conta
            double consumo = KmNumero / litrosNumero;

            //exibindo o resultado
            resultado.setText("Consumo: " + consumo + " km/L");

        });
    }
}