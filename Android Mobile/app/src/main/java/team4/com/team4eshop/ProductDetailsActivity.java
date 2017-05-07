package team4.com.team4eshop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team4.com.team4eshop.model.Product;

public class ProductDetailsActivity extends Activity {

    private String productID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        // Save the productID
        productID = intent.getStringExtra("ID");
        // Write to screen
        int[] id = {R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4};
        String[] keys = {"ProductName", "Description", "Price", "Status"};
        for (int i=0; i<keys.length; i++) {
            EditText e = (EditText) findViewById(id[i]);
            e.setText(intent.getStringExtra(keys[i]));
        }
        Button button = (Button) findViewById(R.id.buttonBuy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ProductDetailsActivity.this)
                        .setTitle("Buying product")
                        .setMessage("Are you sure you want to buy this product?")
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ProductDetailsActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                Product product = new Product();
                                product.put("ID", productID);
                                product.put("Quantity", "1");
                                processingBuying(product);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(ProductDetailsActivity.this, "No", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    private void processingBuying(Product p) {
        new AsyncTask<Product, Void, Void>() {
            @Override
            protected Void doInBackground(Product... params) {
                Product.BuyItem(params[0]);
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                finish();
            }
        }.execute(p);
    }

}
