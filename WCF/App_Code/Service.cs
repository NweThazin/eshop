using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using Team4;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service" in code, svc and config file together.
public class Service : IService
{
    //Get all lists of the items
    public string[] List()
    {
        return Data.ListItems().ToArray<string>();
    }

    //Get the one item object
    public WCFItem GetItem(string id)
    {
        ItemListing c = Data.GetItem(id);
        return WCFItem.Make(c.ID, c.ProductName, c.Description, c.Price, c.Category, c.Status,c.Quantity);
    }

    //Get the l
    public string[] ListCategory()
    {
        return Data.ListCategory().ToArray<string>();
    }

    //Insert and Sell
    public void SellItem(WCFItem item)
    {
        ItemListing i = new ItemListing
        {
            ID = item.ID,
            ProductName = item.Name,
            Description = item.Description,
            Price = Convert.ToDecimal(item.Price),
            Category = item.Category,
            Status = "Available",
            Quantity = 1,
        };
        Data.SellItem(i);
    }

    //Buy And Update
    public void BuyItem(WCFItem item)
    {
        ItemListing i = Data.GetItem(item.ID);
        if (Convert.ToInt32(item.Quantity) < Convert.ToInt32(i.Quantity))
        {
            ItemListing it = new ItemListing
            {
                ID = i.ID,
                ProductName = i.ProductName,
                Description = i.Description,
                Price = i.Price,
                Category = i.Category,
                Status = i.Status,
                Quantity = Convert.ToInt32(i.Quantity) - Convert.ToInt32(item.Quantity)
            };
            Data.BuyItem(it);
        }
        else if (Convert.ToInt32(item.Quantity) == Convert.ToInt32(i.Quantity))
        {
            ItemListing it = new ItemListing
            {
                ID = i.ID,
                ProductName = i.ProductName,
                Description = i.Description,
                Price = i.Price,
                Category = i.Category,
                Status = "Sold",
                Quantity = Convert.ToInt32(i.Quantity) - Convert.ToInt32(item.Quantity)
            };
            Data.BuyItem(it);
        }
    }

    //Show the list of Item objects by category
    public List<WCFItem> ListByCategory(String category)
    {
        List<WCFItem> item = new List<WCFItem>();
        List<ItemListing> i = Data.ListItemsByCategory(category);

        foreach (ItemListing it in i)
        {
            item.Add(WCFItem.Make(it.ID, it.ProductName, it.Description,it.Price, it.Category, it.Status, it.Quantity));
        }

        return item;
    }

    //Show the list of item names by Category
    public string[] ListItemNameByCategory(string category)
    {
        return Data.ListItemNameByCategory(category).ToArray<string>();
    }
}
