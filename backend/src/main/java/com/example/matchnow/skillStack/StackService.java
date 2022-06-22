package com.example.matchnow.skillStack;


import com.example.matchnow.user.User;
import com.example.matchnow.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StackService {

    private final StackRepository stackRepository;

    private final UserRepository userRepository;

    @Transactional
    public void addMySkill(List<AddSkillStackDTO> stackList, String email){
        User user = userRepository.findByEmail(email).get();
        for (AddSkillStackDTO skillStack : stackList) {
            stackRepository.save(skillStack.toEntity(user));
        }
    }

    @Transactional
    public void deleteMySkill(List<DeleteSkillStackDTO> skillStackDTOList) {
        for (DeleteSkillStackDTO deleteSkillStackDTO : skillStackDTOList) {
            SkillStack skillStack = stackRepository.findById(deleteSkillStackDTO.getSkillStackId()).get();

            stackRepository.delete(skillStack);
        }
    }
}

