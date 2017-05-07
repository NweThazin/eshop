package team4.com.team4eshop;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team4.com.team4eshop.model.SellProduct;

public class SellItem extends Activity {

    final static int[] view = {R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4, R.id.editText5};
    final static String[] key = {"ID", "Name", "Description", "Price", "Category"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        SellProduct p = new SellProduct();
        new AsyncTask<SellProduct, Void, Void>() {
            @Override
            protected Void doInBackground(SellProduct... params) {
                SellProduct.insertProduct(params[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                Toast.makeText(getApplicationContext(), "Enter Item to be sold", Toast.LENGTH_LONG).show();
            }
        }.execute(p);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SellProduct c = new SellProduct();
                for (int i = 0; i < view.length; i++) {
                    EditText t = (EditText) findViewById(view[i]);
                    c.put(key[i], t.getText().toString());
                }
                new AsyncTask<SellProduct, Void, Void>() {
                    @Override
                    protected Void doInBackground(SellProduct... params) {
                        SellProduct.insertProduct(params[0]);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                }.execute(c);
            }
        });
    }
}
