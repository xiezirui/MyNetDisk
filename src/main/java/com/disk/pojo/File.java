package com.disk.pojo;

public class File {
    private String id;
    private String fileName;
    private String fileAddress;
    private String fileSize;
    private String fileFormat;

    public File() {
    }

    public File(String id, String fileName, String fileAddress, String fileSize, String fileFormat) {
        this.id = id;
        this.fileName = fileName;
        this.fileAddress = fileAddress;
        this.fileSize = fileSize;
        this.fileFormat = fileFormat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override
    public String toString() {
        return "File{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileAddress='" + fileAddress + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileFormat='" + fileFormat + '\'' +
                '}';
    }
}
