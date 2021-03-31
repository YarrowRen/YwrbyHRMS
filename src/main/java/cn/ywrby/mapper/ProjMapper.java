package cn.ywrby.mapper;

import cn.ywrby.domain.Project;

import java.util.List;

public interface ProjMapper {

    public List<Project> findAll();

    public Long insert(Project project);
}
