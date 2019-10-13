package ec.edu.intsuperior.formularioos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class inventario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ventas) {

            Intent i = new Intent(this, Ventas.class);
            startActivity(i);
        }
        if (id == R.id.cerrar) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        if (id == R.id.compras) {
            Intent i = new Intent(this, Compras.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}