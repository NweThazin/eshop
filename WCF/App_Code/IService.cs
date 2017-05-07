using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using Team4;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService" in both code and config file together.
[ServiceContract]
public interface IService
{
    [OperationContract]
    [WebGet(UriTemplate = "/ItemListing", ResponseFormat = WebMessageFormat.Json)]
    string[] List();

    [OperationContract]
    [WebGet(UriTemplate = "/ItemListing/{id}", ResponseFormat = WebMessageFormat.Json)]
    WCFItem GetItem(string id);

    [OperationContract]
    [WebGet(UriTemplate = "/ItemListing/Category", ResponseFormat = WebMessageFormat.Json)]
    string[] ListCategory();

    [OperationContract]
    [WebInvoke(UriTemplate = "/BuyItem", Method = "POST",
        RequestFormat = WebMessageFormat.Json,
        ResponseFormat = WebMessageFormat.Json)]
    void BuyItem(WCFItem item);

    [OperationContract]
    [WebInvoke(UriTemplate = "/SellItem", Method = "POST",
        RequestFormat = WebMessageFormat.Json,
        ResponseFormat = WebMessageFormat.Json)]
    void SellItem(WCFItem item);

    [OperationContract]
    [WebGet(UriTemplate = "/ItemListing/Category/tolist/{category}", ResponseFormat = WebMessageFormat.Json)]
    List<WCFItem> ListByCategory(String category);

    [OperationContract]
    [WebGet(UriTemplate = "/ItemListing/Category/{category}", ResponseFormat = WebMessageFormat.Json)]
    string[] ListItemNameByCategory(String category);
}
[DataContract]
public class WCFItem
{
    string id;
    string productName;
    string description;
    decimal? price;
    string category;
    string status;
    int? quantity;

    public static WCFItem Make(string id, string productName, string description, decimal? price, string category, string status, int? quantity)
    {
        WCFItem item = new WCFItem();
        item.id = id;
        item.productName = productName;
        item.description = description;
        item.price = price;
        item.category = category;
        item.status = status;
        item.quantity = quantity;
        return item;
    }

    [DataMember]
    public string ID
    {
        get { return id; }
        set { id = value; }
    }

    [DataMember]
    public string Name
    {
        get { return productName; }
        set { productName = value; }
    }

    [DataMember]
    public string Description
    {
        get { return description; }
        set { description = value; }
    }

    [DataMember]
    public decimal? Price
    {
        get { return price; }
        set { price = value; }
    }

    [DataMember]
    public string Category
    {
        get { return category; }
        set { category = value; }
    }

    [DataMember]
    public string Status
    {
        get { return status; }
        set { status = value; }
    }

    [DataMember]
    public int? Quantity
    {
        get { return quantity; }
        set { quantity = value; }
    }
}

