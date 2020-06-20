package hrm.wyg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hrm.wyg.domain.Employee;
import hrm.wyg.service.IEmployeeService;
import hrm.wyg.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author solargen
 * @version v1.0.0
 * @date 2020-06-16
 */
@RestController
//@CrossOrigin({"http://localhost:6001","http://127.0.0.1:6001"})
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody Employee employee){

        //参数判断
        if(StringUtils.isEmpty(employee.getUsername())){
            return AjaxResult.me().setSuccess(false).setMessage("用户名不能为空!");
        }
        if(StringUtils.isEmpty(employee.getPassword())){
            return AjaxResult.me().setSuccess(false).setMessage("密码不能为空!");
        }

        //业务处理
        Employee loginUser = employeeService.getOne(
                new QueryWrapper<Employee>()
                        .eq("username", employee.getUsername()).eq("password",employee.getPassword())
        );
        if(loginUser == null){
            return AjaxResult.me().setSuccess(false).setMessage("用户名或密码错误！");
        }

        //登录成功的处理
        loginUser.setPassword(null);
        return AjaxResult.me().setSuccess(true).setMessage("登录成功!").setResultObj(loginUser);

    }

}
