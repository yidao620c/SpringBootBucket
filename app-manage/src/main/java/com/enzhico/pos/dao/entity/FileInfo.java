package com.enzhico.pos.dao.entity;

/**
 * FileInfo
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/10
 */
public class FileInfo {
    private String filename;

    public FileInfo() {
    }

    public FileInfo(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
