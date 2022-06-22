package com.example.matchnow.message;

import com.example.matchnow.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SendMessageDTO {

    private String sender;

    private String receiver;

    private String title;

    private String mainText;

    private String date;

    public Message toEntity(User sender, User receiver) {
        Message entity = Message.builder()
                .sender(sender)
                .recevier(receiver)
                .title(title)
                .mainText(mainText)
                .sendDate(date)
                .build();

        sender.getSendMessageList().add(entity);
        receiver.getReceivedMessageList().add(entity);

        return entity;
    }

    public SendMessageDTO(Message entity) {
        this.sender = entity.getSender().getUsername();
        this.receiver = entity.getRecevier().getUsername();
        this.title = entity.getTitle();
        this.mainText = entity.getMainText();
        this.date = entity.getSendDate();
    }
}
