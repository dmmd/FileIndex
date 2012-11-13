package org.nypl.mss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String language;
    private String content;
    private List<String> locations = new ArrayList<String>();
    private List<String> names = new ArrayList<String>();
    private List<String> orgs = new ArrayList<String>();
    private List<String> dates = new ArrayList<String>();
    
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void addLocation(String loc) {
        locations.add(loc);
    }
    
    public boolean hasLoc(String name){
        return locations.contains(name);
    }
    
    public List<String> getNames() {
        return names;
    }

    public void addName(String name) {
        names.add(name);
    }
    
    public boolean hasName(String name){
        return names.contains(name);
    }
    
    public List<String> getOrgs() {
        return orgs;
    }

    public void addOrg(String name) {
        orgs.add(name);
    }
    
    public boolean hasOrg(String name){
        return orgs.contains(name);
    }
    
    public List<String> getDates() {
        return dates;
    }

    public void addDate(String name) {
        dates.add(name);
    }
    
    public boolean hasDate(String name){
        return dates.contains(name);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
            .append("\nlang\t").append(this.getLanguage());
        
            
            if(!names.isEmpty()){
                sb.append("\nNames");
                for(String name: names){
                    sb.append("\n\t").append(name);
                }
            }
            if(!orgs.isEmpty()){
                sb.append("\nOrganizations");
                for(String org: orgs){
                    sb.append("\n\t").append(org);
                }
            }
            
            if(!locations.isEmpty()){
                sb.append("\nLocations");
                for(String location: locations){
                    sb.append("\n\t").append(location);
                }
            }
            
            if(!dates.isEmpty()){
                sb.append("\nDates");
                for(String date: dates){
                    sb.append("\n\t").append(date);
                }
            }
            sb.append("\n");
        return sb.toString();
    }

    public void setUid() {
        uid = getCid() + "." + getDid() + "." + getFid();
    }
    
}
