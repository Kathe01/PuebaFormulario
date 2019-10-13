package ec.edu.intsuperior.formularioos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class formularioPro extends AppCompatActivity {
    private EditText etCodigo,etNombreP,etStock,etPrecioCosto,etPrecioVenta;
    private TextView tvGanancia;
    private Button btnAgregar,btnBuscar,btnBorrar;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_producto);
        etCodigo=(EditText)findViewById(R.id.etCodigo);
        etNombreP=(EditText)findViewById(R.id.etNombreP);
        etStock=(EditText)findViewById(R.id.etStock);
        etPrecioCosto=(EditText)findViewById(R.id.etPrecioCosto);
        etPrecioVenta=(EditText)findViewById(R.id.etPrecioVenta);
        tvGanancia=(TextView)findViewById(R.id.tvGanancia);
        btnAgregar=(Button)findViewById(R.id.btnAgregar);
        btnBuscar=(Button)findViewById(R.id.btnBuscar);
        btnBorrar=(Button)findViewById(R.id.btnBorrar);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServico("http://192.168.11.6/formulario/insertarProducto.php");
            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServico("http://192.168.11.6/formulario/borrar_Pro.php");
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buscar("http://192.168.11.6/formulario/buscarProducto.php?codigo="+etCodigo.getText());
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
                parametros.put("codigo",etCodigo.getText().toString());
                parametros.put("nombre",etNombreP.getText().toString());
                parametros.put("stock",etStock.getText().toString());
                parametros.put("precioCosto",etPrecioCosto.getText().toString());
                parametros.put("precioVenta",etPrecioVenta.getText().toString());
                parametros.put("ganancia",tvGanancia.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
   private void buscar(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        etCodigo.setText(jsonObject.getString("codigo"));
                        etNombreP.setText(jsonObject.getString("nombre"));
                        etStock.setText(jsonObject.getString("stock"));
                        etPrecioCosto.setText(jsonObject.getString("precioCosto"));
                        etPrecioVenta.setText(jsonObject.getString("precioVenta"));
                        tvGanancia.setText(jsonObject.getString("ganancia"));
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
   public  void ganancia(View v){
       float g=Float.parseFloat(etPrecioVenta.getText().toString())-Float.parseFloat(etPrecioCosto.getText().toString());
       tvGanancia.setText(String.valueOf(g));
   }
   public void borrar(String URL){
       JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {
               JSONObject jsonObject = null;
               for (int i = 0; i < response.length(); i--) {
                   try {
                       jsonObject = response.getJSONObject(i);
                       etCodigo.setText(jsonObject.getString("codigo"));
                       etNombreP.setText(jsonObject.getString("nombre"));
                       etStock.setText(jsonObject.getString("stock"));
                       etPrecioCosto.setText(jsonObject.getString("precioCosto"));
                       etPrecioVenta.setText(jsonObject.getString("precioVenta"));
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
