package hrm.wyg.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hrm.wyg.domain.Role;
import hrm.wyg.query.RoleQuery;
import hrm.wyg.service.IRoleService;
import hrm.wyg.util.AjaxResult;
import hrm.wyg.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    public IRoleService roleService;

    /**
    * 保存和修改公用的
    * @param role  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Role role){
        try {
            if(role.getId()!=null){
                roleService.updateById(role);
            }else{
                roleService.save(role);
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
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            roleService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Role get(@PathVariable("id")Long id)
    {
        return roleService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Role> list(){

        return roleService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public PageList<Role> page(@RequestBody RoleQuery query)
    {
        Page<Role> page = roleService.page(new Page<Role>(query.getPageNum(), query.getPageSize()));
        return new PageList<>(page.getTotal(),page.getRecords());
    }
}
