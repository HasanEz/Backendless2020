package com.example.backendless2020.models;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.*;
import com.backendless.geo.GeoPoint;

import java.util.List;
import java.util.Date;

public class Orders
{
    private String ownerId;
    private Integer orderNumber;
    private Date created;
    private Date updated;
    private String objectId;
    private Boolean orderDone;
    private String clientName;
    public String getOwnerId()
    {
        return ownerId;
    }

    public Integer getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber( Integer orderNumber )
    {
        this.orderNumber = orderNumber;
    }

    public Date getCreated()
    {
        return created;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public Boolean getOrderDone()
    {
        return orderDone;
    }

    public void setOrderDone( Boolean orderDone )
    {
        this.orderDone = orderDone;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName( String clientName )
    {
        this.clientName = clientName;
    }


    public Orders save()
    {
        return Backendless.Data.of( Orders.class ).save( this );
    }

    public void saveAsync( AsyncCallback<Orders> callback )
    {
        Backendless.Data.of( Orders.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( Orders.class ).remove( this );
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( Orders.class ).remove( this, callback );
    }

    public static Orders findById( String id )
    {
        return Backendless.Data.of( Orders.class ).findById( id );
    }

    public static void findByIdAsync( String id, AsyncCallback<Orders> callback )
    {
        Backendless.Data.of( Orders.class ).findById( id, callback );
    }

    public static Orders findFirst()
    {
        return Backendless.Data.of( Orders.class ).findFirst();
    }

    public static void findFirstAsync( AsyncCallback<Orders> callback )
    {
        Backendless.Data.of( Orders.class ).findFirst( callback );
    }

    public static Orders findLast()
    {
        return Backendless.Data.of( Orders.class ).findLast();
    }

    public static void findLastAsync( AsyncCallback<Orders> callback )
    {
        Backendless.Data.of( Orders.class ).findLast( callback );
    }

    public static List<Orders> find( DataQueryBuilder queryBuilder )
    {
        return Backendless.Data.of( Orders.class ).find( queryBuilder );
    }

    public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Orders>> callback )
    {
        Backendless.Data.of( Orders.class ).find( queryBuilder, callback );
    }
}