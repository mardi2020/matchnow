package com.example.matchnow.project;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping(value="/project")
    public ResponseEntity<?> findAllProjectPost() {
        ResponseEntity<?> responseEntity;

        try {
            List<PostedProjectDTO> projects = projectService.findAll();

            responseEntity = new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping(value="project")
    public ResponseEntity<?> uploadProjectPost(@Validated @RequestBody UploadProjectDTO uploadProjectDTO, Principal principal) {
        ResponseEntity<?> responseEntity;
        try {
            String email = principal.getName();
            String username = projectService.uploadProjectPost(uploadProjectDTO, email);
            responseEntity = new ResponseEntity<>(username + "님의 "
                    + uploadProjectDTO.getTitle() + "글 등록 성공!", HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PatchMapping(value="project/{id}")
    public ResponseEntity<?> updateProjectPost(@PathVariable Long id, @Validated @RequestBody UpdateProjectDTO updateProjectDTO) {
        ResponseEntity<?> responseEntity;

        try {
            projectService.updateProjectPost(id, updateProjectDTO);
            responseEntity = new ResponseEntity<>("게시글을 성공적으로 수정했습니다.", HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @DeleteMapping(value="project/{id}")
    public ResponseEntity<?> deleteProjectPost(@PathVariable Long id){
        ResponseEntity<?> responseEntity;

        try {
            projectService.deleteProjectPost(id);
            responseEntity = new ResponseEntity<>("게시글을 성공적으로 삭제했습니다.", HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @GetMapping(value="project/{id}")
    public ResponseEntity<?> detailProjectPost(@PathVariable Long id) {
        ResponseEntity<?> responseEntity;

        try {
            DetailedProjectDTO detailedProjectDTO = projectService.detailProjectPost(id);
            responseEntity = new ResponseEntity<>(detailedProjectDTO, HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PatchMapping(value="project/state/{id}")
    public ResponseEntity<?> changePostState(@PathVariable Long id, @RequestBody Map<String, Integer> param) {
        ResponseEntity<?> responseEntity;

        try {
            projectService.changePostState(id, param.get("state"));
            responseEntity = new ResponseEntity<>("성공적으로 수정하였습니다.", HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @GetMapping(value="/project/category")
    public ResponseEntity<?> findAllByCategory(@RequestParam("category") String category) {
        try {
            List<PostedProjectDTO> projects = projectService.filteringPost(category);
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/project/search")
    public ResponseEntity<?> searchByKeyword(@RequestParam("keyword") String keyword,
                                             @PageableDefault(size=6, sort="projectId", direction = Sort.Direction.DESC) Pageable pageable) {
        try {
            Page<PostedProjectDTO> posts = projectService.searchByKeyword(keyword, pageable);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/project/page")
    public ResponseEntity<?> projectsByPage(@PageableDefault(size=6, sort= "projectId", direction = Sort.Direction.DESC)
                                                        Pageable pageable) {
        try {
            Page<PostedProjectDTO> page = projectService.postByPage(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
