package com.example.administrador.semana3enclase;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText txtNom, txtCor, txtTel;
    TextInputLayout inputNom, inputCor, inputTel;
    Button btnValidar;

    //La instancia se llama bd
    BD_Sqlite bd=new BD_Sqlite(this,"bd1",null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNom=(EditText)findViewById(R.id.txtNom);
        txtCor=(EditText)findViewById(R.id.txtCor);
        txtTel=(EditText)findViewById(R.id.txtTel);

        inputNom=(TextInputLayout)findViewById(R.id.inputNom);
        inputCor=(TextInputLayout)findViewById(R.id.inputMail);
        inputTel=(TextInputLayout)findViewById(R.id.inputTel);

        btnValidar=(Button)findViewById(R.id.btnValidar);

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar(); //Para que se realicen todas las funciones y se validen

            }
        });

    }

    boolean ValidarNom(String nom){
        Pattern patron=Pattern.compile("^[a-zA-Z]+$"); //Patrón que indica que solo se pueden usar letras minúsculas y mayúsculas
        if(!patron.matcher(nom).matches()||nom.length()>15){
            inputNom.setError("Nombre Inválido"); //setError es un texto que se muestra abajo
            return false;
        }else{
            inputNom.setError(null);
        }
        return true;
    }


    boolean ValidarTel(String tel) {

        if (!Patterns.PHONE.matcher(tel).matches()) {
            inputTel.setError("Teléfono Inválido");
            return false;
        } else {
            inputTel.setError(null);
        }
        return true;
    }

    boolean ValidarCorreo(String cor) {

        if (!Patterns.EMAIL_ADDRESS.matcher(cor).matches()) {
            inputTel.setError("Correo Inválido");
            return false;
        } else {
            inputTel.setError(null);
        }
        return true;

    }

    //Necesitamos que se den las tres condiciones previas en simultáneo:
    void validar(){
        boolean a=ValidarNom(txtNom.getText().toString());
        boolean b=ValidarCorreo(txtCor.getText().toString());
        boolean c=ValidarTel(txtTel.getText().toString());

        if(a==true && b==true && c==true){
            //Toast.makeText(getApplicationContext(),"Datos Validados Correctamente", Toast.LENGTH_LONG).show();
            bd.abrir();
            bd.insertaUsu(txtNom.getText().toString(),txtTel.getText().toString(),txtCor.getText().toString());
            bd.close();

            txtCor.setText(null);
            txtTel.setText(null);
            txtNom.setText(null);

            Toast.makeText(getApplicationContext(),"Usuario grabado",Toast.LENGTH_LONG).show();
        }
    }


}
