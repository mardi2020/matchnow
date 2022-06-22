package com.example.matchnow.message;


import com.example.matchnow.user.User;
import com.example.matchnow.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    @Transactional
    public List<String> sendMessage(SendMessageDTO sendMessageDTO, String myEmail) {
        User from = userRepository.findByEmail(myEmail).get();
        User to = userRepository.findByUsername(sendMessageDTO.getReceiver()).get();

        messageRepository.save(sendMessageDTO.toEntity(from, to));

        return Arrays.asList(from.getUsername(), to.getUsername());
    }

    public List<ReceivedMessageDTO> findAllRecv(String email) {
        List<Message> messages = messageRepository.findAllRecv(email);

        List<ReceivedMessageDTO> receivedMessageDTOList = new ArrayList<>();
        for (Message message : messages) {
            receivedMessageDTOList.add(new ReceivedMessageDTO(message));
        }
        return receivedMessageDTOList;
    }

    public List<SendMessageDTO> findAllSend(String email) {
        List<Message> messages = messageRepository.findAllSend(email);

        List<SendMessageDTO> sendList = new ArrayList<>();
        for (Message message : messages) {
            sendList.add(new SendMessageDTO(message));
        }
        return sendList;
    }

    @Transactional
    public void deleteMessage(Long messageId) {
        messageRepository.deleteMessage(messageId);
    }
}
