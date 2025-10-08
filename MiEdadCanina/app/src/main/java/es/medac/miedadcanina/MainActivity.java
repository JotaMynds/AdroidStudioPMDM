package es.medac.miedadcanina;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 1. Declarar las vistas como variables de la clase para acceder a ellas desde cualquier método
    private EditText urEdad;
    private TextView resultText;
    private Button botonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // Puedes mantener o quitar esta línea si no la usas
        setContentView(R.layout.activity_main);

        // 2. Encontrar las vistas y asignarlas a las variables
        urEdad = findViewById(R.id.urEdad);
        resultText = findViewById(R.id.result_text);
        botonCalcular = findViewById(R.id.boton); // El botón "Calcular" de abajo
        //Para limitar que el teclado sea numerico en la entrada se hace asi:
        //urEdad.setInputType(InputType.TYPE_CLASS_NUMBER);

        // 3. Configurar los listeners para todos los botones
        ConfigurarBtn();
        ConfigActionBtn();

        // 4. Se reutiliza el listener original para el botón "Calcular"
        botonCalcular.setOnClickListener(view -> calculateAge());
    }

    /**
     * Configura los listeners para los botones numéricos (0-9).
     * Al pulsar, añade el número al EditText si la longitud es menor a 3.
     */
    private void ConfigurarBtn() {
        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            String currentText = urEdad.getText().toString();
            // Limita la entrada a 3 dígitos
            if (currentText.length() < 3) {
                urEdad.append(b.getText().toString());
            }
        };

        findViewById(R.id.btn0).setOnClickListener(listener);
        findViewById(R.id.btn1).setOnClickListener(listener);
        findViewById(R.id.btn2).setOnClickListener(listener);
        findViewById(R.id.btn3).setOnClickListener(listener);
        findViewById(R.id.btn4).setOnClickListener(listener);
        findViewById(R.id.btn5).setOnClickListener(listener);
        findViewById(R.id.btn6).setOnClickListener(listener);
        findViewById(R.id.btn7).setOnClickListener(listener);
        findViewById(R.id.btn8).setOnClickListener(listener);
        findViewById(R.id.btn9).setOnClickListener(listener);
    }

    /**
     * Configura los listeners para los botones de acción (Limpiar y Borrar).
     */
    private void ConfigActionBtn() {
        // Botón Limpiar (C)
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            urEdad.setText("");
            resultText.setText("");
        });

        // Botón Borrar (DEL)
        findViewById(R.id.btnDel).setOnClickListener(v -> {
            String currentText = urEdad.getText().toString();
            if (!currentText.isEmpty()) {
                urEdad.setText(currentText.substring(0, currentText.length() - 1));
            }
        });
    }

    /**
     * Lógica principal para calcular la edad canina.
     */
    private void calculateAge() {
        String edad = urEdad.getText().toString();

        // Se reutiliza tu lógica de validación
        if (!edad.isEmpty()) {
            int edadHumana = Integer.parseInt(edad);
            if (edadHumana > 0 && edadHumana <= 100) {
                int edadCanina = edadHumana * 7;
                resultText.setText(getString(R.string.EdadPerro, edadCanina));
            } else {
                // Mensaje emergente si el número está fuera de rango
                Toast.makeText(this, "Por favor, introduce una edad realista (1-100)", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Mensaje si el campo está vacío al pulsar "Calcular"
            Toast.makeText(this, "Por favor, introduce una edad", Toast.LENGTH_SHORT).show();
        }
    }
}
