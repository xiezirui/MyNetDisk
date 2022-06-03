package com.disk.pojo;

public class Share {
    private String uid;
    private String fileAddress;
    private String filePassword;
    private String fileName;
    private String fileShareAddress;

    public Share() {
    }

    public Share(String uid, String fileAddress, String filePassword, String fileName, String fileShareAddress) {
        this.uid = uid;
        this.fileAddress = fileAddress;
        this.filePassword = filePassword;
        this.fileName = fileName;
        this.fileShareAddress = fileShareAddress;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getFilePassword() {
        return filePassword;
    }

    public void setFilePassword(String filePassword) {
        this.filePassword = filePassword;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileShareAddress() {
        return fileShareAddress;
    }

    public void setFileShareAddress(String fileShareAddress) {
        this.fileShareAddress = fileShareAddress;
    }

    @Override
    public String toString() {
        return "Share{" +
                "uid='" + uid + '\'' +
                ", fileAddress='" + fileAddress + '\'' +
                ", filePassword='" + filePassword + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileShareAddress='" + fileShareAddress + '\'' +
                '}';
    }
}
