package com.bread.hwang.bread.data;

/**
 * Created by Hwang on 2016-10-20.
 */

public class Reply {
    private int number;
    private String content;
    private User userNumber;
    private Board boardNumber;
    private String regDate;
    private String updateDate;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(User userNumber) {
        this.userNumber = userNumber;
    }

    public Board getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(Board boardNumber) {
        this.boardNumber = boardNumber;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
