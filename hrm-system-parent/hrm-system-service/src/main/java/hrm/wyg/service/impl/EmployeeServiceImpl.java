package hrm.wyg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hrm.wyg.domain.Employee;
import hrm.wyg.mapper.EmployeeMapper;
import hrm.wyg.service.IEmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
}
