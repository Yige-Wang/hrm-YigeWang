package hrm.wyg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hrm.wyg.domain.Employee;
import hrm.wyg.service.EmployeeService;
import hrm.wyg.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/login")
    public AjaxResult login(@RequestBody Employee employee){
        if (StringUtils.isEmpty(employee.getUsername())){
            return AjaxResult.me().setSuccess(false).setMessage("用户名不能为空");
        }
        if (StringUtils.isEmpty(employee.getPassword())){
            return AjaxResult.me().setSuccess(false).setMessage("密码不能为空");
        }
        Employee employee1 = employeeService.getOne(new QueryWrapper<Employee>().eq("username", employee.getUsername()).eq("password", employee.getPassword()));
        if (employee1==null){
            return AjaxResult.me().setSuccess(false).setMessage("密码或用户名错误");
        }
        employee1.setPassword(null);
        return AjaxResult.me().setSuccess(true).setMessage("登录成功").setResultObj(employee1);

    }
    @GetMapping("/afsdwewdaewd")
    public String asdsa(){
        return null;
    }
}
