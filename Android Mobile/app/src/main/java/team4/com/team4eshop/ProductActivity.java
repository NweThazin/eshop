package team4.com.team4eshop;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

import team4.com.team4eshop.model.Product;

public class ProductActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final String categoryName = intent.getStringExtra("Category");

        new AsyncTask<Void, Void, List<Product>>() {
            @Override
            protected List<Product> doInBackground(Void... params) {
                return Product.listProduct(categoryName);
            }
            @Override
            protected void onPostExecute(List<Product> result) {
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), result,
                        android.R.layout.simple_list_item_2,
                        new String[]{"ProductName", "Status"},
                        new int[]{ android.R.id.text1, android.R.id.text2});
                setListAdapter(adapter);
            }
        }.execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        Product product = (Product) getListAdapter().getItem(position);
        String status = product.get("Status");
        if (status.equals("Available")) {
            Intent intent = new Intent(this, ProductDetailsActivity.class);
            intent.putExtra("ID", product.get("ID"));
            intent.putExtra("ProductName", product.get("ProductName"));
            intent.putExtra("Description", product.get("Description"));
            intent.putExtra("Category", product.get("Category"));
            intent.putExtra("Status", product.get("Status"));
            intent.putExtra("Price", product.get("Price"));
            intent.putExtra("Quantity", product.get("Quantity"));

            startActivity(intent);
        }
    }
}
