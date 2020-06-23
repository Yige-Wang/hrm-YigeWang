package hrm.wyg.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hrm.wyg.client.SystemdictionaryItemClient;
import hrm.wyg.controller.vo.CourseAddVo;
import hrm.wyg.domain.Course;
import hrm.wyg.domain.CourseDetail;
import hrm.wyg.domain.SystemdictionaryItem;
import hrm.wyg.mapper.CourseDetailMapper;
import hrm.wyg.mapper.CourseMapper;
import hrm.wyg.query.CourseQuery;
import hrm.wyg.service.ICourseService;
import hrm.wyg.util.PageList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author solargen
 * @since 2020-06-18
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private SystemdictionaryItemClient systemdictionaryItemClient;
    @Autowired
    private CourseDetailMapper courseDetailMapper;
    @Override
    public PageList<Course> page(CourseQuery query) {
        IPage<Course> page = baseMapper.selectCourseByQuery(new Page<Course>(query.getPageNum(),query.getPageSize()),query);
        PageList<Course> pageList = new PageList<>();
        pageList.setTotal(page.getTotal());
        pageList.setRows(page.getRecords());
        return pageList;
    }

    @Override
    public void add(CourseAddVo courseAddVo) {
        System.out.println(courseAddVo);
        Course course = new Course();
        BeanUtils.copyProperties(courseAddVo,course);
        SystemdictionaryItem systemdictionaryItem = systemdictionaryItemClient.get(courseAddVo.getGrade());
        if (systemdictionaryItem!=null){
            course.setGradeName(systemdictionaryItem.getName());
        }
        course.setTenantId(26L);
        course.setTenantName("源码时代");
        course.setUserId(42L);
        course.setUserName("admin");
        course.setStatus(0);
        baseMapper.insert(course);
        CourseDetail courseDetail = new CourseDetail();
        BeanUtils.copyProperties(courseAddVo,courseDetail);
        courseDetail.setCourseId(course.getId());
        courseDetailMapper.insert(courseDetail);
    }
}
