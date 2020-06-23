package hrm.wyg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hrm.wyg.domain.Course;
import hrm.wyg.query.CourseQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author solargen
 * @since 2020-06-18
 */
public interface CourseMapper extends BaseMapper<Course> {


    IPage<Course> selectCourseByQuery(Page<Course> coursePage,@Param("query") CourseQuery query);
}
