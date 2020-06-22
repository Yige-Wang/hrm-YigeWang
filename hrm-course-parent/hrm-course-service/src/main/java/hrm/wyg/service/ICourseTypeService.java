package hrm.wyg.service;

import hrm.wyg.domain.CourseType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author solargen
 * @since 2020-06-18
 */
public interface ICourseTypeService extends IService<CourseType> {

    List<CourseType> loadTypeTree();
}
