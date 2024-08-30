package com.example.material_design3_maana;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText name;
    TextInputEditText phone;
    TextInputEditText email;
    TextInputEditText date;
    TextInputEditText password;
    Button botonRegistrar;
    Button botonMain;

    TextInputLayout nameLayout;
    TextInputLayout phoneLayout;
    TextInputLayout emailLayout;
    TextInputLayout dateLayout;
    TextInputLayout passwordLayout;

    MaterialDatePicker myDate;
    ArrayList<Validacion> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  );

        configurarComponentes();

        date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    myDate.show(getSupportFragmentManager(), "DATE_PICKER");
                    return true;
                }

                return false;
            }
        });

        botonRegistrar.setOnClickListener(v -> {
            boolean todoOK = validarCampos();
            if(todoOK) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });

        botonMain.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });









    }

    private void configurarComponentes() {

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        date = findViewById(R.id.date);
        password = findViewById(R.id.password);
        botonRegistrar = findViewById(R.id.botonRegistrar);
        botonMain = findViewById(R.id.botonMain);

        nameLayout = findViewById(R.id.nameLayout);
        phoneLayout = findViewById(R.id.phoneLayout);
        emailLayout = findViewById(R.id.emailLayout);
        dateLayout = findViewById(R.id.dateLayout);
        passwordLayout = findViewById(R.id.passwordLayout);

        myDate = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccione una fecha")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String hoy = dateFormat.format(new Date( calendar.getTimeInMillis() ));
        date.setText(hoy);

        lista = new ArrayList<>();

        //Expresiones regulares que se utilizan para verificar que los datos contengan solo los caracteres permitidos
        String exNombres = "^[a-zA-ZÀ-ÿ\\s]+$"; //dieresis
        String exCorreos = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        //Llenamos la lista con la configuración de cada dato solicitado en el formulario
        lista.add(new Validacion(name,          exNombres,  3, 50,   nameLayout,     true));
        lista.add(new Validacion(phone,         "",10, 10,  phoneLayout,    false));
        lista.add(new Validacion(email,         exCorreos,  0, 50,   emailLayout,    true));
        lista.add(new Validacion(password,      "",6, 10,   passwordLayout, true));

    }

    private boolean validarCampos() {

        boolean valido = true;
        boolean error = false;

        for(Validacion elemento: lista) {

            error = false;
            String texto = elemento.campo.getText().toString();

            //Si es requerido validamos
            if(elemento.requerido){

                //Si el campo está vacío mostramos un mensaje de error
                if(texto.isEmpty()){
                    elemento.mostrarMensajeError("Campo requerido");
                    valido = false;
                    error = true;
                }

            }
            //Si el campo tiene texto validamos las restricciones
            if(!error && texto.length() > 0) {

                if(elemento.expresion != "" && !texto.matches(elemento.expresion)){
                    elemento.mostrarMensajeError("Formato incorrecto");
                    valido = false;
                    error = true;
                }

                //Validamos el tamaño del texto
                if(!error && ((elemento.min != 0 && texto.length() < elemento.min) || (elemento.max != 0 && texto.length() > elemento.max))) {
                    if(elemento.min == elemento.max)
                        elemento.mostrarMensajeError("El campo debe tener " + elemento.max + " caracteres");
                    else
                        elemento.mostrarMensajeError("El campo debe tener entre " + elemento.min + " y " + elemento.max + " caracteres");

                    valido = false;
                    error = true;
                }

            }
            //Si el elemento no tienen ningún error se borra el mensaje de error
            if(!error) elemento.ocultaError();

        }

        //Retorna true si no se encontró ningún error
        return valido;
    }

    //Clase personalizada para validar los campos
    public class Validacion {

        public TextInputEditText    campo;
        public TextInputLayout      campoError;
        public String   expresion;
        public int      min;
        public int      max;
        public boolean  requerido;

        public Validacion(TextInputEditText campo, String expresion, int min, int max, TextInputLayout campoError, boolean requerido){
            this.campo      = campo;
            this.expresion  = expresion;
            this.min        = min;
            this.max        = max;
            this.campoError = campoError;
            this.requerido  = requerido;

            campoError.setCounterEnabled(true);
            campoError.setCounterMaxLength(max);
        }

        public void mostrarMensajeError(String mensaje){
            campoError.setError(mensaje);
        }

        public void ocultaError(){
            campoError.setError(null);

            int iconMode = campoError.getEndIconMode();
            if(iconMode != TextInputLayout.END_ICON_PASSWORD_TOGGLE)
                campoError.setEndIconMode( TextInputLayout.END_ICON_CUSTOM );
        }
    }
}