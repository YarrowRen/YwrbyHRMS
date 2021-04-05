package cn.ywrby.mapper;

import cn.ywrby.domain.Project;

import java.util.List;

public interface ProjMapper {

    //查询所有项目的集合
    public List<Project> findAll();

    //保存项目
    public Long insert(Project project);
}
