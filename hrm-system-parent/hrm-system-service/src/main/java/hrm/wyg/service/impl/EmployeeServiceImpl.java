package hrm.wyg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hrm.wyg.domain.Employee;
import hrm.wyg.mapper.EmplloyeeMapper;
import hrm.wyg.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmplloyeeMapper, Employee> implements EmployeeService {
}
