package hrm.wyg.controller;

import hrm.wyg.controller.vo.CourseAddVo;
import hrm.wyg.domain.Course;
import hrm.wyg.query.CourseQuery;
import hrm.wyg.service.ICourseService;
import hrm.wyg.util.AjaxResult;
import hrm.wyg.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    public ICourseService courseService;

    /**
     * 删除对象信息
     * @param courseAddVo
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AjaxResult add(@RequestBody CourseAddVo courseAddVo){
        try {
            System.out.println(courseAddVo);
            courseService.add(courseAddVo);
            return AjaxResult.me().setSuccess(true).setMessage("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败").setSuccess(false);
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
            courseService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Course get(@PathVariable("id")Long id)
    {
        return courseService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Course> list(){

        return courseService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public PageList<Course> page(@RequestBody CourseQuery query)
    {
        return courseService.page(query);
    }
}
