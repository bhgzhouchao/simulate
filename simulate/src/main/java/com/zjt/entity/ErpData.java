package com.zjt.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "erp_data")
public class ErpData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subject_id")
    private String subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    private String month;

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
     * @return subject_id
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    /**
     * @return subject_name
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @param subjectName
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
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