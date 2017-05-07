using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Team4;

/// <summary>
/// Summary description for Data
/// </summary>
public class Data
{
    //List Items
    public static List<String> ListItems()
    {
        using (Team4Model m = new Team4Model())
        {
            return m.ItemListings.Select<ItemListing, String>(c => c.ID).ToList<String>();
        }
    }

    //Get Item By ID
    public static ItemListing GetItem(string id)
    {
        using (Team4Model m = new Team4Model())
        {
            return m.ItemListings.Where
                    (p => p.ID == id).ToList<ItemListing>()[0];
        }
    }

    //Get Category List
    public static List<String> ListCategory()
    {
        using (Team4Model m = new Team4Model())
        {
            var temp = m.ItemListings.Select(c => c.Category).Distinct();
            return temp.ToList<String>();
        }
    }

    //Insert and SELL
    public static void SellItem(ItemListing c)
    {
        using (Team4Model m = new Team4Model())
        {
            m.ItemListings.Add(c);
            m.SaveChanges();
        }
    }

    //Update and Buy
    public static void BuyItem(ItemListing c)
    {
        using (Team4Model m = new Team4Model())
        {
            m.Entry(c).State = System.Data.Entity.EntityState.Modified;
            m.SaveChanges();
        }
    }

    //Get all lists by Category
    public static List<ItemListing> ListItemsByCategory(String category)
    {
        using (Team4Model m = new Team4Model())
        {
            return m.ItemListings.Where(c => c.Category == category).ToList<ItemListing>();
        }
    }

    //Get the items name by Category
    public static List<String> ListItemNameByCategory(String category)
    {
        using (Team4Model m = new Team4Model())
        {
            return m.ItemListings.Where(c => c.Category == category).Select(c => c.ProductName).ToList<String>();
        }
    }

}