package org.nypl.mss;

import java.util.Date;

public class FileModel {
    private String fileName;
    private String cid;
    private String did;
    private String fid;
    private String collectionName;
    private String text;
    private String fileType;
    private Date modDate;
    private String uid;
    private Long fileSize;
    private String path;
    
    FileModel(){

    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
    
    @Override
    public String toString(){        
        StringBuilder sb = new StringBuilder();
        sb.append("uid\t").append(this.getUid())
            .append("\ncid\t").append(this.getCid())
            .append("\ndid\t").append(this.getDid())
            .append("\nfid\t").append(this.getFid())
            .append("\nfName\t").append(this.getFileName())
            .append("\nfType\t").append(this.getFileType())
            .append("\npath\t").append(this.getPath())
            .append("\nsize\t").append(this.getFileSize())
            .append("\nmDate\t").append(this.getModDate())
            .append("\n");
        return sb.toString();
    }

    public void setUid() {
        uid = getCid() + "." + getDid() + "." + getFid();
    }
    
}
