package cn.ywrby.service.impl;

import cn.ywrby.domain.Department;
import cn.ywrby.mapper.DeptMapper;
import cn.ywrby.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Department> deptList() {
        List<Department> departmentList=deptMapper.findAll();
        return departmentList;
    }
}
