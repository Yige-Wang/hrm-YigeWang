package hrm.wyg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hrm.wyg.domain.Employee;
import hrm.wyg.query.EmployeeQuery;
import hrm.wyg.service.IEmployeeService;
import hrm.wyg.util.AjaxResult;
import hrm.wyg.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    public IEmployeeService employeeService;

    /**
    * 保存和修改公用的
    * @param employee  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/employee/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Employee employee){
        try {
            if(employee.getId()!=null){
                employeeService.updateById(employee);
            }else{
                employeeService.save(employee);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/employee/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            employeeService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    @RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
    public Employee get(@PathVariable("id")Long id)
    {
        return employeeService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/employee/list",method = RequestMethod.GET)
    public List<Employee> list(){

        return employeeService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/employee/page",method = RequestMethod.POST)
    public PageList<Employee> page(@RequestBody EmployeeQuery query)
    {
        Page<Employee> page = employeeService.page(new Page<Employee>(query.getPageNum(), query.getPageSize()));
        return new PageList<>(page.getTotal(),page.getRecords());
    }

}
