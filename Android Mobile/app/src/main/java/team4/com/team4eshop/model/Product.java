package team4.com.team4eshop.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by E0046636 on 12/21/2016.
 */

public class Product extends HashMap<String, String> {
    final static String host = "http://10.10.28.3/Team4/Service.svc/";

    public Product() {}

    public Product(String ID, String ProductName, String Description, Double Price, String Category, String Status, Integer Quantity) {
        put("ID", ID);
        put("ProductName", ProductName);
        put("Description", Description);
        put("Category", Category);
        put("Status", Status);
        put("Price", Price.toString());
        put("Quantity", Quantity.toString());
    }

    public static List<Product> listProduct(String categoryName) {
        List<Product> list = new ArrayList<Product>();
        try {
            JSONArray array = JSONParser.getJSONArrayFromUrl(host+"ItemListing/Category/tolist/"+categoryName);
            for (int i=0; i<array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                Product product = new Product(
                        o.getString("ID"),
                        o.getString("Name"),
                        o.getString("Description"),
                        o.getDouble("Price"),
                        o.getString("Category"),
                        o.getString("Status"),
                        o.getInt("Quantity"));
                list.add(product);
            }
        } catch (Exception e) {
            Log.d("Product",e.toString());
        }
        return list;

    }

    public static String BuyItem(Product product) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("ID", product.get("ID"));
            //jo.put("Name", product.get("ProductName"));
            //jo.put("Description", product.get("Description"));
            jo.put("Quantity", Integer.parseInt(product.get("Quantity")));
        } catch (Exception e) {
        }
        String result = JSONParser.postStream(host+"/BuyItem", jo.toString());
        return result;
    }
}
