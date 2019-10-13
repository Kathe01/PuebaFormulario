package ec.edu.intsuperior.formularioos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class Login extends AppCompatActivity {
    public EditText etUsuario, etContraseña;
    public Button btnIngresar;

    private Spinner spTipo;
    JSONArray ja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spTipo = (Spinner) findViewById(R.id.spTipo);
        String[] opciones = {"Persona", "Producto", "Inventario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spTipo.setAdapter(adapter);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContraseña = (EditText) findViewById(R.id.etContraseña);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
    }

        public void ingresar (View v){

            String u = etUsuario.getText().toString();
            String c = etContraseña.getText().toString();
            if (etUsuario.getText().toString().equals("") || etContraseña.getText().toString().equals("")) {
                Toast.makeText(this, "No ha ingresado datos requeridos",
                        Toast.LENGTH_SHORT).show();
            } else if (etUsuario.getText().toString().equals("admin") && etContraseña.getText().toString().equals("12345")) {
                if (spTipo.getSelectedItem().toString().equals("Persona")) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else if (spTipo.getSelectedItem().toString().equals("Producto")) {
                    Intent intent = new Intent(this, formularioPro.class);
                    startActivity(intent);
                } else if (spTipo.getSelectedItem().toString().equals("Inventario")) {
                    Intent intent = new Intent(this, inventario.class);
                    startActivity(intent);
                }

            } else {
                Toast.makeText(this, "Usuario o contrañea incorrecto",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


