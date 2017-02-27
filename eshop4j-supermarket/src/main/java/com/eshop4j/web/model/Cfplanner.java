package com.eshop4j.web.model;

import java.io.Serializable;
import java.util.Date;

public class Cfplanner implements Serializable{


    private String fNumber;

    private String fName;

    private String fEmail;

    private String fMobile;

    private String fQrcode;

    private String fIdcard;

    private String fPassword;

    private String fSignPwd;

    private String fParentId;

    private Byte fIsLock;

    private String fRemark;

    private String fEmpNo;

    private String fDepartment;

    private String fCustomerId;

    private Byte fSettlementType;

    private Byte fEnable;

    private String fAncestor;

    private Byte fIsCfp;

    private String fCfpLevel;

    private Date fCfpRegTime;

    private Date fCfpBenormalTime;

    private Date fCfpUpdateTime;

    private Byte fIsPartner;

    private String fPartnerLevel;

    private Date fPartnerRegTime;

    private Date fPartnerUpTime;

    private Date fJoinTime;

    private Date fCreateTime;

    private Date fUpdateTime;

    private Byte fDelStatus;

    private Byte fType;

    private Byte fLocked;

    private byte[] fHeadImage;

    public String getfNumber() {
        return fNumber;
    }

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber == null ? null : fNumber.trim();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail == null ? null : fEmail.trim();
    }

    public String getfMobile() {
        return fMobile;
    }

    public void setfMobile(String fMobile) {
        this.fMobile = fMobile == null ? null : fMobile.trim();
    }

    public String getfQrcode() {
        return fQrcode;
    }

    public void setfQrcode(String fQrcode) {
        this.fQrcode = fQrcode == null ? null : fQrcode.trim();
    }

    public String getfIdcard() {
        return fIdcard;
    }

    public void setfIdcard(String fIdcard) {
        this.fIdcard = fIdcard == null ? null : fIdcard.trim();
    }

    public String getfPassword() {
        return fPassword;
    }

    public void setfPassword(String fPassword) {
        this.fPassword = fPassword == null ? null : fPassword.trim();
    }

    public String getfSignPwd() {
        return fSignPwd;
    }

    public void setfSignPwd(String fSignPwd) {
        this.fSignPwd = fSignPwd == null ? null : fSignPwd.trim();
    }

    public String getfParentId() {
        return fParentId;
    }

    public void setfParentId(String fParentId) {
        this.fParentId = fParentId == null ? null : fParentId.trim();
    }

    public Byte getfIsLock() {
        return fIsLock;
    }

    public void setfIsLock(Byte fIsLock) {
        this.fIsLock = fIsLock;
    }

    public String getfRemark() {
        return fRemark;
    }

    public void setfRemark(String fRemark) {
        this.fRemark = fRemark == null ? null : fRemark.trim();
    }

    public String getfEmpNo() {
        return fEmpNo;
    }

    public void setfEmpNo(String fEmpNo) {
        this.fEmpNo = fEmpNo == null ? null : fEmpNo.trim();
    }

    public String getfDepartment() {
        return fDepartment;
    }

    public void setfDepartment(String fDepartment) {
        this.fDepartment = fDepartment == null ? null : fDepartment.trim();
    }

    public String getfCustomerId() {
        return fCustomerId;
    }

    public void setfCustomerId(String fCustomerId) {
        this.fCustomerId = fCustomerId == null ? null : fCustomerId.trim();
    }

    public Byte getfSettlementType() {
        return fSettlementType;
    }

    public void setfSettlementType(Byte fSettlementType) {
        this.fSettlementType = fSettlementType;
    }

    public Byte getfEnable() {
        return fEnable;
    }

    public void setfEnable(Byte fEnable) {
        this.fEnable = fEnable;
    }

    public String getfAncestor() {
        return fAncestor;
    }

    public void setfAncestor(String fAncestor) {
        this.fAncestor = fAncestor == null ? null : fAncestor.trim();
    }

    public Byte getfIsCfp() {
        return fIsCfp;
    }

    public void setfIsCfp(Byte fIsCfp) {
        this.fIsCfp = fIsCfp;
    }

    public String getfCfpLevel() {
        return fCfpLevel;
    }

    public void setfCfpLevel(String fCfpLevel) {
        this.fCfpLevel = fCfpLevel == null ? null : fCfpLevel.trim();
    }

    public Date getfCfpRegTime() {
        return fCfpRegTime;
    }

    public void setfCfpRegTime(Date fCfpRegTime) {
        this.fCfpRegTime = fCfpRegTime;
    }

    public Date getfCfpBenormalTime() {
        return fCfpBenormalTime;
    }

    public void setfCfpBenormalTime(Date fCfpBenormalTime) {
        this.fCfpBenormalTime = fCfpBenormalTime;
    }

    public Date getfCfpUpdateTime() {
        return fCfpUpdateTime;
    }

    public void setfCfpUpdateTime(Date fCfpUpdateTime) {
        this.fCfpUpdateTime = fCfpUpdateTime;
    }

    public Byte getfIsPartner() {
        return fIsPartner;
    }

    public void setfIsPartner(Byte fIsPartner) {
        this.fIsPartner = fIsPartner;
    }

    public String getfPartnerLevel() {
        return fPartnerLevel;
    }

    public void setfPartnerLevel(String fPartnerLevel) {
        this.fPartnerLevel = fPartnerLevel == null ? null : fPartnerLevel.trim();
    }

    public Date getfPartnerRegTime() {
        return fPartnerRegTime;
    }

    public void setfPartnerRegTime(Date fPartnerRegTime) {
        this.fPartnerRegTime = fPartnerRegTime;
    }

    public Date getfPartnerUpTime() {
        return fPartnerUpTime;
    }

    public void setfPartnerUpTime(Date fPartnerUpTime) {
        this.fPartnerUpTime = fPartnerUpTime;
    }

    public Date getfJoinTime() {
        return fJoinTime;
    }

    public void setfJoinTime(Date fJoinTime) {
        this.fJoinTime = fJoinTime;
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Date getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(Date fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }

    public Byte getfDelStatus() {
        return fDelStatus;
    }

    public void setfDelStatus(Byte fDelStatus) {
        this.fDelStatus = fDelStatus;
    }

    public Byte getfType() {
        return fType;
    }

    public void setfType(Byte fType) {
        this.fType = fType;
    }

    public Byte getfLocked() {
        return fLocked;
    }

    public void setfLocked(Byte fLocked) {
        this.fLocked = fLocked;
    }

    public byte[] getfHeadImage() {
        return fHeadImage;
    }

    public void setfHeadImage(byte[] fHeadImage) {
        this.fHeadImage = fHeadImage;
    }
}