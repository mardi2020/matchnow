package com.example.matchnow.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value="/comment/{id}")
    public ResponseEntity<?> findAllComment(@PathVariable Long id) {
        try {
            List<ResponseCommentDTO> allComment = commentService.findAllComment(id);
            return new ResponseEntity<>(allComment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/comment")
    public ResponseEntity<?> postComment(@RequestBody PostCommentDTO postCommentDTO, Principal principal) {
        try {
            commentService.postComment(postCommentDTO, principal.getName());
            return new ResponseEntity<>(principal.getName() + "님의 댓글"+ postCommentDTO.getText()+ " 추가 성공!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value="/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return new ResponseEntity<>("댓글을 삭제했습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
