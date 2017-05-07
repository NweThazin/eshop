package team4.com.team4eshop.model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by E0046636 on 12/21/2016.
 */

public class Category extends HashMap<String, String> {

    final static String host = "http://10.10.28.3/Team4/Service.svc/";

    public Category(String categoryName) {
        put("Category", categoryName);
    }

    public static List<Category> listCategory() {
        List<Category> list = new ArrayList<Category>();
        try {
            JSONArray array = JSONParser.getJSONArrayFromUrl(host+"ItemListing/Category");
            for (int i=0; i<array.length(); i++) {
                String string = array.getString(i);
                list.add(new Category(string));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
