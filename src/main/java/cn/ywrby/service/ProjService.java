package cn.ywrby.service;

import cn.ywrby.domain.Project;

import java.util.List;

public interface ProjService {
    //获取所有项目集合
    public List<Project> list();

    //存储项目对象到数据库
    public void save(Project project);
}
