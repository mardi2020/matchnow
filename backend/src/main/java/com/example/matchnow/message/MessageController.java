package com.example.matchnow.message;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping(value = "/message")
    public ResponseEntity<?> sendMessage(@RequestBody SendMessageDTO sendMessageDTO, Principal principal) {
        ResponseEntity<?> responseEntity;

        try {
            List<String> sendTo = messageService.sendMessage(sendMessageDTO, principal.getName());
            responseEntity = new ResponseEntity<>(sendTo.get(0) + "님의 메세지가 " + sendTo.get(1) + "님께 전송 완료!", HttpStatus.OK);
        }catch (Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping(value="/message/recv")
    public ResponseEntity<?> findAllRecv(Principal principal) {
        ResponseEntity<?> responseEntity;

        try {
            String email = principal.getName();
            List<ReceivedMessageDTO> messages = messageService.findAllRecv(email);
            responseEntity = new ResponseEntity<>(messages, HttpStatus.OK);
        }catch (Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping(value="/message/send")
    public ResponseEntity<?> findAllSend(Principal principal) {
        ResponseEntity<?> responseEntity;

        try {
            String email = principal.getName();
            List<SendMessageDTO> messages = messageService.findAllSend(email);
            responseEntity = new ResponseEntity<>(messages, HttpStatus.OK);
        }catch (Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @DeleteMapping(value="/message/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        ResponseEntity<?> responseEntity;

        try {
            messageService.deleteMessage(id);
            responseEntity = new ResponseEntity<>("메시지를 삭제했습니다.", HttpStatus.OK);
        }catch (Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

}
