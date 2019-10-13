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

public class Ventas<stringRequest> extends AppCompatActivity {
    private static final String URL = null;
    private EditText etCodigo, etCantidad;
    private TextView tvNombre, tvStock, tvPrecioVenta, tvTotalPagar;
    private Button btnBuscar, btnVender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        etCodigo = (EditText) findViewById(R.id.etCodigo);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvStock = (TextView) findViewById(R.id.tvStock);
        tvPrecioVenta = (TextView) findViewById(R.id.tvPrecioVenta);
        tvTotalPagar = (TextView) findViewById(R.id.tvTotalPagar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnVender = (Button) findViewById(R.id.btnVender);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar("http://192.168.11.6/formulario/buscarProducto.php?codigo=" + etCodigo.getText());
            }
        });

        btnVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar("http://192.168.11.6/formulario/modificarProducto.php?codigo=" + etCodigo.getText());
            }
        });
    }

    private void ejecutarServico(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("codigo", etCodigo.getText().toString());
                parametros.put("nombre", tvNombre.getText().toString());
                parametros.put("stock", tvStock.getText().toString());
                parametros.put("precioVenta", tvPrecioVenta.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void buscar(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        etCodigo.setText(jsonObject.getString("codigo"));
                        tvNombre.setText(jsonObject.getString("nombre"));
                        tvStock.setText(jsonObject.getString("stock"));
                        tvPrecioVenta.setText(jsonObject.getString("precioVenta"));
                        tvTotalPagar.setText(jsonObject.getString("totalPagar"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void totalPagar(View v) {
        int st = Integer.parseInt(tvStock.getText().toString());
        int cantidad = Integer.parseInt(etCantidad.getText().toString());
        float pv = Float.parseFloat(tvPrecioVenta.getText().toString());
        if (cantidad > st) {
            Toast.makeText(this, "La cantidad ingresada es superior al Stock",
                    Toast.LENGTH_SHORT).show();
        } else {
            tvTotalPagar.setText(String.valueOf(cantidad * pv));
        }
    }

    public void modificacion(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        etCodigo.setText(jsonObject.getString("codigo"));
                        tvNombre.setText(jsonObject.getString("nombre"));
                        tvStock.setText(jsonObject.getString("stock"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    String URL = etCodigo.getText().toString();
                    int url = (Integer.parseInt(tvStock.getText().toString())-Integer.parseInt(etCantidad.getText().toString()));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    public void vender (View view){
        float v =Float.parseFloat(tvStock.getText().toString())-Float.parseFloat(etCantidad.getText().toString());
        tvTotalPagar.setText(String.valueOf(v));
            Toast.makeText(this, "Venta Exitosa!",
                    Toast.LENGTH_SHORT).show();

       // tvGanancia.setText(String.valueOf(g));

    /*public void vender (View view) {
        int st = Integer.parseInt(tvStock.getText().toString());
        int can = Integer.parseInt(etcantidad.getText().toString());
        int act = st - can;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 4);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = etprod.getText().toString();
        int sto = (Integer.parseInt(tvStock.getText().toString()) - Integer.parseInt(etcantidad.getText().toString()));

        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("stocki", sto);
        int cant = bd.update("productos", registro, "codigo=" + cod, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Venta Exitosa!",
                    Toast.LENGTH_SHORT).show();
            etprod.setText("");
            etcantidad.setText("");
            tvtotal.setText("");
            tvPrecio.setText("");
            tvStock.setText("");
            tvnom.setText("");
        } else {
            Toast.makeText(this, "ocurrio un problema en la actualizacion del producto",
                    Toast.LENGTH_SHORT).show();
        }*/

    }

}





