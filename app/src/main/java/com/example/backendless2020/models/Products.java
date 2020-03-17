package com.example.backendless2020.models;



import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.*;
import com.backendless.geo.GeoPoint;

import java.util.List;
import java.util.Date;

public class Products
{
    private Date created;
    private String ownerId;
    private String Category;
    private Date updated;
    private String Product;
    private String objectId;
    private Double Price;
    public Date getCreated()
    {
        return created;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public String getCategory()
    {
        return Category;
    }

    public void setCategory( String Category )
    {
        this.Category = Category;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public String getProduct()
    {
        return Product;
    }

    public void setProduct( String Product )
    {
        this.Product = Product;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public Double getPrice()
    {
        return Price;
    }

    public void setPrice( Double Price )
    {
        this.Price = Price;
    }


    public Products save()
    {
        return Backendless.Data.of( Products.class ).save( this );
    }

    public void saveAsync( AsyncCallback<Products> callback )
    {
        Backendless.Data.of( Products.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( Products.class ).remove( this );
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( Products.class ).remove( this, callback );
    }

    public static Products findById( String id )
    {
        return Backendless.Data.of( Products.class ).findById( id );
    }

    public static void findByIdAsync( String id, AsyncCallback<Products> callback )
    {
        Backendless.Data.of( Products.class ).findById( id, callback );
    }

    public static Products findFirst()
    {
        return Backendless.Data.of( Products.class ).findFirst();
    }

    public static void findFirstAsync( AsyncCallback<Products> callback )
    {
        Backendless.Data.of( Products.class ).findFirst( callback );
    }

    public static Products findLast()
    {
        return Backendless.Data.of( Products.class ).findLast();
    }

    public static void findLastAsync( AsyncCallback<Products> callback )
    {
        Backendless.Data.of( Products.class ).findLast( callback );
    }

    public static List<Products> find( DataQueryBuilder queryBuilder )
    {
        return Backendless.Data.of( Products.class ).find( queryBuilder );
    }

    public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Products>> callback )
    {
        Backendless.Data.of( Products.class ).find( queryBuilder, callback );
    }
}
