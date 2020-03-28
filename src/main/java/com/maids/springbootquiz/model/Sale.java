package com.maids.springbootquiz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
@EntityListeners(AuditingEntityListener.class)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    @Column(name = "sellerName", nullable = false)
    private String sellerName;

    @Column(name = "total", nullable = false)
    private double total;

    @OneToMany(mappedBy="sale", fetch = FetchType.LAZY)
    private List<SaleItem> saleItems = new ArrayList<SaleItem>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-M-yyyy hh:mm:ss")
    @Column(name = "created_date", nullable = false)
    private Date creationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<SaleItem> getSaleItem() {
        return saleItems;
    }

    public void setSaleItem(List<SaleItem> saleItem) {
        this.saleItems = saleItem;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
