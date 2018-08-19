package com.zjt.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "business_data")
public class BusinessData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String month;

    private String dept;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "dept_examine")
    private String deptExamine;

    @Column(name = "leader_examine")
    private String leaderExamine;

    @Column(name = "dept_examine_date")
    private Date deptExamineDate;

    @Column(name = "leader_examine_date")
    private Date leaderExamineDate;

    @Column(name = "submit_date")
    private Date submitDate;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private Date updateTime;
    
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
     * @return dept
     */
    public String getDept() {
        return dept;
    }

    /**
     * @param dept
     */
    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    /**
     * @return total_price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return dept_examine
     */
    public String getDeptExamine() {
        return deptExamine;
    }

    /**
     * @param deptExamine
     */
    public void setDeptExamine(String deptExamine) {
        this.deptExamine = deptExamine == null ? null : deptExamine.trim();
    }

    /**
     * @return leader_examine
     */
    public String getLeaderExamine() {
        return leaderExamine;
    }

    /**
     * @param leaderExamine
     */
    public void setLeaderExamine(String leaderExamine) {
        this.leaderExamine = leaderExamine == null ? null : leaderExamine.trim();
    }

    /**
     * @return dept_examine_date
     */
    public Date getDeptExamineDate() {
        return deptExamineDate;
    }

    /**
     * @param deptExamineDate
     */
    public void setDeptExamineDate(Date deptExamineDate) {
        this.deptExamineDate = deptExamineDate;
    }

    /**
     * @return leader_examine_date
     */
    public Date getLeaderExamineDate() {
        return leaderExamineDate;
    }

    /**
     * @param leaderExamineDate
     */
    public void setLeaderExamineDate(Date leaderExamineDate) {
        this.leaderExamineDate = leaderExamineDate;
    }

    /**
     * @return submit_date
     */
    public Date getSubmitDate() {
        return submitDate;
    }

    /**
     * @param submitDate
     */
    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    /**
     * @return create_user
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_user
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
}