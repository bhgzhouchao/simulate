package com.zjt.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "base_data")
public class BaseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String month;

    @Column(name = "num_people")
    private Integer numPeople;

    private BigDecimal amount;

    private String remarks;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month
     */
    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    /**
     * @return num_people
     */
    public Integer getNumPeople() {
        return numPeople;
    }

    /**
     * @param numPeople
     */
    public void setNumPeople(Integer numPeople) {
        this.numPeople = numPeople;
    }

    /**
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}