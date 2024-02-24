package com.happlay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happlay.entity.Employee;
import com.happlay.mapper.EmployeeMapper;
import com.happlay.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
