package ec.edu.intsuperior.formularioos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner1;
    private EditText nombre,apellido,cedula,provincia,direccion;
    private RadioButton masculino,femenino;
    private Button btnAgregar,btnBuscar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner1 = (Spinner) findViewById(R.id.spPais);
        String []opciones={"Ecuador","Colombia","Venezuela","Peru"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);
        nombre=(EditText)findViewById(R.id.etNombre);
        apellido=(EditText)findViewById(R.id.etApellido);
        cedula=(EditText)findViewById(R.id.etCedula);
        provincia=(EditText)findViewById(R.id.etProvincia);
        direccion=(EditText)findViewById(R.id.etDireccion);
        masculino=(RadioButton)findViewById(R.id.rbtnMasculino);
        femenino=(RadioButton)findViewById(R.id.rbtnFemenino);
        btnAgregar=(Button)findViewById(R.id.btnIngresar);
        btnBuscar=(Button)findViewById(R.id.btnBuscar);



    btnAgregar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        ejecutarServico("http://192.168.11.6/formulario/insertardatos.php");
        }
    });
    btnBuscar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buscar("http://192.168.11.6/formulario/buscardatos.php?cedula="+cedula.getText()+"");
        }
    });
    }

    private void ejecutarServico(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("nombres",nombre.getText().toString());
                parametros.put("apellidos",apellido.getText().toString());
                parametros.put("cedula",cedula.getText().toString());
                parametros.put("sexo",sexo());
                parametros.put("pais",String.valueOf(spinner1.getSelectedItemPosition()));
                parametros.put("provincia",provincia.getText().toString());
                parametros.put("direccion",direccion.getText().toString());
                return parametros;
            }
        };
         requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public String sexo() {
        String sexo="";
        if (masculino.isChecked() == true) {
            sexo = "Masculino";
        } else if (femenino.isChecked() == true) {
            sexo = "Femenino";
        }
        return sexo;

    }
    private void buscar(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nombre.setText(jsonObject.getString("nombres"));
                        apellido.setText(jsonObject.getString("apellidos"));
                        cedula.setText(jsonObject.getString("cedula"));
                        provincia.setText(jsonObject.getString("provincia"));
                        direccion.setText(jsonObject.getString("direccion"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(getApplicationContext(),"Error de conexion",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}


