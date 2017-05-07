package team4.com.team4eshop.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imran on 22/12/2016.
 */

public class SellProduct extends java.util.HashMap<String,String> {

    final static String host = "http://10.10.28.3/Team4/Service.svc";

    public SellProduct(String ID, String Name, String Description, Double Price, String Category, Integer Quantity) {
        put("ID", ID);
        put("Name", Name);
        put("Description", Description);
        put("Price", String.valueOf(Price));
        put("Category", Category);
        put("Quantity", String.valueOf(Quantity));

    }

    public SellProduct(){}

    public static List<String> listProduct() {
        List<String> list = new ArrayList<String>();
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host+"/ItemListing");
            for (int i=0; i<a.length(); i++) {
                String c = a.getString(i);
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }


    public static void insertProduct(SellProduct prd) {
        JSONObject jproduct = new JSONObject();
        try {
            jproduct.put("ID", prd.get("ID"));
            jproduct.put("Name", prd.get("Name"));
            jproduct.put("Description", prd.get("Description"));
            jproduct.put("Price", Double.parseDouble(prd.get("Price")));
            jproduct.put("Category", prd.get("Category"));
            jproduct.put("Quantity", Integer.parseInt(prd.get("Quantity")));
        } catch (Exception e) {
            String result = JSONParser.postStream(host+"/SellItem", jproduct.toString());
        }
    }
}
