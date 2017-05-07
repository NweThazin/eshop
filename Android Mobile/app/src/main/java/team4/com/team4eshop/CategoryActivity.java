package team4.com.team4eshop;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

import team4.com.team4eshop.model.Category;

public class CategoryActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AsyncTask<Void, Void, List<Category>>() {
            @Override
            protected List<Category> doInBackground(Void... params) {
                return Category.listCategory();
            }
            @Override
            protected void onPostExecute(List<Category> result) {
                //ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(getApplicationContext(), android.R.layout.simple_list_item_1,result);
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), result,
                        android.R.layout.simple_list_item_1,
                        new String[]{"Category"},
                        new int[]{ android.R.id.text1});
                setListAdapter(adapter);
            }
        }.execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v,
                                   int position, long id) {
        Category category = (Category) getListAdapter().getItem(position);
        Intent intent = new Intent(this, ProductActivity.class);
        //intent.putExtra("Category",category);
        intent.putExtra("Category", category.get("Category"));
        startActivity(intent);
    }
}
