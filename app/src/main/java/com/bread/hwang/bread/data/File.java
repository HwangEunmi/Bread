package com.bread.hwang.bread.data;

/**
 * Created by Hwang on 2016-10-20.
 */

public class File {
    private int number;
    private Board boardNumber;
    private String name;
    private String filePath;
    private String type;
    private double size;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Board getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(Board boardNumber) {
        this.boardNumber = boardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
