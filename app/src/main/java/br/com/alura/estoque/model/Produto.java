package br.com.alura.estoque.model;

import android.text.format.DateFormat;

import java.math.BigDecimal;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Produto {

    @PrimaryKey(autoGenerate = true)
    private final long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int quantity;
    private final String unity;
    private final String productCode;
    private final String gtin;
    private final String rfid;
    private final String name_categorie;


    public Produto(long id, String name,String description, BigDecimal price, int quantity, String unity, String productCode, String gtin, String rfid, String name_categorie) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unity = unity;
        this.price = price;
        this.productCode = productCode;
        this.gtin = gtin;
        this.rfid = rfid;
        this.name_categorie = name_categorie;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnity() {
        return unity;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public String getProductCode() {
        return productCode;
    }

    public String getGtin() {
        return gtin;
    }

    public String getRfid() {
        return rfid;
    }

    public String getName_categorie() {
        return name_categorie;
    }
}