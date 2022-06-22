package com.example.matchnow.project;

import com.example.matchnow.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateProjectDTO {

    @NotBlank(message="제목이 필요합니다.") // null, "", " " 허용하지 않음
    @Size(min = 1, max = 15, message="제목의 길이는 1부터 15까지 허용합니다.")
    private String title;

    @NotNull(message = "본문이 비어있습니다.")
    @Size(min = 1)
    private String mainText;

    private String image;

    private Type category;


    public Project toEntity(Long id, User user) {
        return Project.builder()
                .projectId(id)
                .user(user)
                .title(title)
                .mainText(mainText)
                .inputImage(image)
                .category(category)
                .build();
    }
}
