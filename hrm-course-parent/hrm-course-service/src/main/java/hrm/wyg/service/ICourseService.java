package hrm.wyg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import hrm.wyg.controller.vo.CourseAddVo;
import hrm.wyg.domain.Course;
import hrm.wyg.query.CourseQuery;
import hrm.wyg.util.PageList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author solargen
 * @since 2020-06-18
 */
public interface ICourseService extends IService<Course> {
    PageList<Course> page(CourseQuery query);

    void add(CourseAddVo courseAddVo);
}
