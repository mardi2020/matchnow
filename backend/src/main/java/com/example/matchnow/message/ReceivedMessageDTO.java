package com.example.matchnow.message;

import lombok.Data;

import java.util.Date;

@Data
public class ReceivedMessageDTO {

    private Long messageId;

    private String title;

    private String mainText;

    private String sender;

    private String sendDate;

    public ReceivedMessageDTO(Message entity) {
        this.messageId = entity.getMessageId();
        this.title = entity.getTitle();
        this.mainText = entity.getMainText();
        this.sender = entity.getSender().getUsername();
        this.sendDate = entity.getSendDate();
    }
}
