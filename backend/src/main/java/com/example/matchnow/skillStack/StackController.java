package com.example.matchnow.skillStack;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class StackController {

    private final StackService stackService;

    @PostMapping(value="/skill")
    public ResponseEntity<?> addSkillStack(@RequestBody List<AddSkillStackDTO> stackDTOList, Principal principal) {
        ResponseEntity<?> responseEntity;
        try {

            stackService.addMySkill(stackDTOList, principal.getName());
            responseEntity = new ResponseEntity<>("기술 등록 성공", HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @DeleteMapping(value="/skill")
    public ResponseEntity<?> deleteMySkills(@RequestBody List<DeleteSkillStackDTO> deleteDtoList) {
        ResponseEntity<?> responseEntity;

        try {
            stackService.deleteMySkill(deleteDtoList);
            responseEntity = new ResponseEntity<>("해당 기술을 성공적으로 삭제했습니다.", HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
}
