package com.example.backendless2020.models;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.*;
import com.backendless.geo.GeoPoint;

import java.util.List;
import java.util.Date;

public class DemoTable
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


    public DemoTable save()
    {
        return Backendless.Data.of( DemoTable.class ).save( this );
    }

    public void saveAsync( AsyncCallback<DemoTable> callback )
    {
        Backendless.Data.of( DemoTable.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( DemoTable.class ).remove( this );
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( DemoTable.class ).remove( this, callback );
    }

    public static DemoTable findById( String id )
    {
        return Backendless.Data.of( DemoTable.class ).findById( id );
    }

    public static void findByIdAsync( String id, AsyncCallback<DemoTable> callback )
    {
        Backendless.Data.of( DemoTable.class ).findById( id, callback );
    }

    public static DemoTable findFirst()
    {
        return Backendless.Data.of( DemoTable.class ).findFirst();
    }

    public static void findFirstAsync( AsyncCallback<DemoTable> callback )
    {
        Backendless.Data.of( DemoTable.class ).findFirst( callback );
    }

    public static DemoTable findLast()
    {
        return Backendless.Data.of( DemoTable.class ).findLast();
    }

    public static void findLastAsync( AsyncCallback<DemoTable> callback )
    {
        Backendless.Data.of( DemoTable.class ).findLast( callback );
    }

    public static List<DemoTable> find( DataQueryBuilder queryBuilder )
    {
        return Backendless.Data.of( DemoTable.class ).find( queryBuilder );
    }

    public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<DemoTable>> callback )
    {
        Backendless.Data.of( DemoTable.class ).find( queryBuilder, callback );
    }
}