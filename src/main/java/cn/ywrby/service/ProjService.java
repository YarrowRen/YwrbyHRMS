package cn.ywrby.service;

import cn.ywrby.domain.Project;

import java.util.List;

public interface ProjService {
    public List<Project> list();

    public void save(Project project);
}
