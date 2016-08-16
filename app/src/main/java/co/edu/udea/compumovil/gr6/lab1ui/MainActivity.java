package co.edu.udea.compumovil.gr6.lab1ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AutoCompleteTextView paises;
    private Spinner hobbies;
    private Button btnMostrar;
    private RadioGroup sexo;
    private EditText nombre;
    private EditText apellido;
    private EditText pais;
    private EditText telefono;
    private EditText direccion;
    private EditText email;
    private DatePicker fecha;
    private CheckBox favorito;
    private TextView datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asignacion de variables globales
        paises = (AutoCompleteTextView) findViewById(R.id.pais);
        hobbies = (Spinner) findViewById(R.id.hobbies);
        btnMostrar = (Button) findViewById(R.id.mostrar);
        sexo = (RadioGroup) findViewById(R.id.sexo);
        nombre = (EditText) findViewById(R.id.Nombre);
        apellido = (EditText) findViewById(R.id.Apellido);
        pais = (EditText) findViewById(R.id.pais);
        telefono  = (EditText) findViewById(R.id.telefono);
        direccion = (EditText) findViewById(R.id.direccion);
        email = (EditText) findViewById(R.id.email);
        fecha = (DatePicker)  findViewById(R.id.fecha);
        favorito = (CheckBox) findViewById(R.id.favorito);
        datos = (TextView) findViewById(R.id.datos);

        //*********Autocompletar paises***********
        String[] paisesArray = getResources().getStringArray(R.array.paises);
        ArrayAdapter<String> adapterPaises = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,paisesArray);
        paises.setAdapter(adapterPaises);
        paises.setThreshold(1);

        //**********Spinner hobbies********8
        String[] hobbiesArray = getResources().getStringArray(R.array.hobbies);
        ArrayAdapter<String> adapterHobbies = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,hobbiesArray);
        hobbies.setAdapter(adapterHobbies);

        btnMostrar.setOnClickListener(this);

    }//End onCreate

    @Override
    public void onClick(View v) {
        String sex = "";
        String hobbie;
        String mostrarTodo="";
        int hobby;

        if (v.getId() == R.id.mostrar){

            switch (sexo.getCheckedRadioButtonId()) {
                case R.id.Masculino:
                    sex = getResources().getString(R.string.masculino);
                    break;
                case R.id.Femenino:
                    sex = getString(R.string.femenino);
                    break;
            } //End switch
        }//End if

        hobby= hobbies.getSelectedItemPosition();
        hobbie = hobbies.getItemAtPosition(hobby).toString();

        if(nombre.getText().toString().isEmpty()){
            alertar(getResources().getString(R.string.nombreVacio));
            return;
        }//End if

        if(pais.getText().toString().isEmpty()){
            alertar(getResources().getString(R.string.paisVacio));
            return;
        }//End if

        if(telefono.getText().toString().isEmpty()){
            alertar(getResources().getString(R.string.telefonoVacio));
            return;
        }//End if

        if(email.getText().toString().isEmpty()){
            alertar(getResources().getString(R.string.correoVacio));
            return;
        }//End if

        mostrarTodo = mostrarTodo +getString(R.string.nombre).replace("(*)",":")+" "+ nombre.getText().toString() + "\n";
        mostrarTodo = mostrarTodo +getString(R.string.apellido)+": "+ apellido.getText().toString() + "\n";
        mostrarTodo = mostrarTodo +getString(R.string.sexo)+": "+ sex + "\n";
        mostrarTodo = mostrarTodo +getString(R.string.pais).replace("(*)",":")+" "+ pais.getText().toString() + "\n";

        String day= Integer.toString(fecha.getDayOfMonth());
        String month=Integer.toString(fecha.getMonth()+1);
        String year=Integer.toString(fecha.getYear());
        mostrarTodo = mostrarTodo +getString(R.string.fecha_de_nacimiento).replace("(*)",":")+" "+ day +"/"+month+"/"+year + "\n";
        mostrarTodo = mostrarTodo +getString(R.string.telefono).replace("(*)",":")+" "+ telefono.getText().toString() + "\n";
        mostrarTodo = mostrarTodo +getString(R.string.direccion)+": "+ direccion.getText().toString() + "\n";
        mostrarTodo = mostrarTodo +getString(R.string.correo_electronico).replace("(*)",":")+" "+ email.getText().toString() + "\n";
        mostrarTodo = mostrarTodo +getString(R.string.hobby)+": "+ hobbie + "\n";

        if(favorito.isChecked()){
            mostrarTodo = mostrarTodo + getString(R.string.fav);
        }else{
            mostrarTodo = mostrarTodo + getString(R.string.noFav);
        }//End if(favorito.isChecked())

        datos.setText(mostrarTodo);
    }//End onClick

    public void alertar(String mensaje){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(getResources().getString(R.string.atencion));
        alerta.setMessage(mensaje);
        alerta.setPositiveButton(getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.create();
        alerta.show();
    }//End alertar
}//End main activity
