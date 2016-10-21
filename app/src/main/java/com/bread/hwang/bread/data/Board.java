package com.bread.hwang.bread.data;

import java.io.Serializable;

/**
 * Created by Hwang on 2016-10-20.
 */

public class Board implements Serializable {
    private int number;
    private String content;
    private String regDate;
    private String updateDate;
    private User userNumber;
    private int fileCount;
    private int replyCount;
    private int imageCount;
    private int audioCount;
    private int videoCount;
    private String imagePath;

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int audioCount, int imageCount, int videoCount) {
        this.fileCount = audioCount + imageCount + videoCount;
    }

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

    public User getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(User userNumber) {
        this.userNumber = userNumber;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public int getAudioCount() {
        return audioCount;
    }

    public void setAudioCount(int audioCount) {
        this.audioCount = audioCount;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
