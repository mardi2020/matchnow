package com.example.matchnow.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Modifying
    @Query(value="update matchnow.projects set state=?2 where project_id=?1", nativeQuery = true)
    void changePostState(Long id, int state); // 마감, 모집, 취소됨

    @Query(value="SELECT project FROM Project project WHERE project.projectId=?1")
    List<Project> detailProjectPost(Long id);

    List<Project> findByCategory(Type category);

    Page<Project> findByTitleContaining(String keyword, Pageable pageable); // LIKE %keyword%

//    void addTeamMember(List<User> members);
}
