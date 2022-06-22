package com.example.matchnow.comment;

import com.example.matchnow.project.Project;
import com.example.matchnow.project.ProjectRepository;
import com.example.matchnow.user.User;
import com.example.matchnow.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    public List<ResponseCommentDTO> findAllComment(Long ProjectId) {
        List<Comment> comments = commentRepository.findAllByProject(ProjectId);

        List<ResponseCommentDTO> responseCommentDTOS = new ArrayList<>();

        for (Comment comment : comments) {
            responseCommentDTOS.add(new ResponseCommentDTO(comment));
        }
        return responseCommentDTOS;
    }

    @Transactional
    public Comment postComment(PostCommentDTO postCommentDTO, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException("해당 유저가 존재하지 않습니다.")
        );

        Project project = projectRepository.findById(postCommentDTO.getProjectId()).orElseThrow(
                () -> new NoSuchElementException("해당 게시글이 존재하지 않습니다.")
        );

        Comment comment = postCommentDTO.toEntity(project, user);
        commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        commentRepository.delete(comment);
    }
}
