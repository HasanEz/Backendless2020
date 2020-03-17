package com.example.backendless2020.models;


import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.*;
import com.backendless.geo.GeoPoint;

import java.util.List;
import java.util.Date;

public class OrderDetails
{
    private Date updated;
    private String objectId;
    private String product;
    private Integer amount;
    private String ownerId;
    private Double price;
    private Date created;
    public Date getUpdated()
    {
        return updated;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public String getProduct()
    {
        return product;
    }

    public void setProduct( String product )
    {
        this.product = product;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount( Integer amount )
    {
        this.amount = amount;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice( Double price )
    {
        this.price = price;
    }

    public Date getCreated()
    {
        return created;
    }


    public OrderDetails save()
    {
        return Backendless.Data.of( OrderDetails.class ).save( this );
    }

    public void saveAsync( AsyncCallback<OrderDetails> callback )
    {
        Backendless.Data.of( OrderDetails.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( OrderDetails.class ).remove( this );
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( OrderDetails.class ).remove( this, callback );
    }

    public static OrderDetails findById( String id )
    {
        return Backendless.Data.of( OrderDetails.class ).findById( id );
    }

    public static void findByIdAsync( String id, AsyncCallback<OrderDetails> callback )
    {
        Backendless.Data.of( OrderDetails.class ).findById( id, callback );
    }

    public static OrderDetails findFirst()
    {
        return Backendless.Data.of( OrderDetails.class ).findFirst();
    }

    public static void findFirstAsync( AsyncCallback<OrderDetails> callback )
    {
        Backendless.Data.of( OrderDetails.class ).findFirst( callback );
    }

    public static OrderDetails findLast()
    {
        return Backendless.Data.of( OrderDetails.class ).findLast();
    }

    public static void findLastAsync( AsyncCallback<OrderDetails> callback )
    {
        Backendless.Data.of( OrderDetails.class ).findLast( callback );
    }

    public static List<OrderDetails> find( DataQueryBuilder queryBuilder )
    {
        return Backendless.Data.of( OrderDetails.class ).find( queryBuilder );
    }

    public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<OrderDetails>> callback )
    {
        Backendless.Data.of( OrderDetails.class ).find( queryBuilder, callback );
    }
}