package hrm.wyg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hrm.wyg.client.CatchClient;
import hrm.wyg.domain.CourseType;
import hrm.wyg.mapper.CourseTypeMapper;
import hrm.wyg.service.ICourseTypeService;
import hrm.wyg.util.AjaxResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author solargen
 * @since 2020-06-18
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {
    @Autowired
    private CatchClient catchClient;
    public static String KEY="coursetype:tree";

    /**
     * 返回课程类型  包含子类型
     * @return
     */
    @Override
    public List<CourseType> loadTypeTree() {
        List<CourseType> courseTypes = new ArrayList<>();
        while (true) {
            //直接从redis获取值
            AjaxResult ajaxResult = catchClient.get(KEY);
            String courseTypeJSON = (String) ajaxResult.getResultObj();
            if (StringUtils.isNotEmpty(courseTypeJSON)){
                JSONObject.parseArray(courseTypeJSON,CourseType.class);
                return courseTypes;
            }
            try {
                //如果不能获取到值，则加锁存值，防止击穿
                 ajaxResult = catchClient.setnx("courseTypeKey", "1");
                 Integer result = (Integer) ajaxResult.getResultObj();
                 if (result !=null &&result==1){
                     courseTypes = loadTCourseTypeByMap();
                     courseTypeJSON = JSONObject.toJSONString(courseTypes);
                     catchClient.setex(KEY,10*60,courseTypeJSON);
                     break;
                 }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //最后关闭值
                catchClient.deleteKey("courseTypeKey");
            }
        }
        return courseTypes;
    }

    /**
     * 重写增删改的方法
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public boolean save(CourseType entity) {
        boolean result = super.save(entity);
        synchronizedOperation();
        return result;
    }

    @Override
    @Transactional
    public boolean removeById(Serializable id) {
        boolean result = super.removeById(id);
        synchronizedOperation();
        return result;
    }

    @Override
    @Transactional
    public boolean updateById(CourseType entity) {
        boolean b = super.updateById(entity);
        synchronizedOperation();
        return b;
    }

    /**
     * 加载课程类型
     * @return
     */
    public List<CourseType> loadTCourseTypeByMap(){
        //查询所有的课程类型
        List<CourseType> courseTypeList = baseMapper.selectList(null);
        //创建一个集合存放所有的一级类型
        List<CourseType> firstLevleTypes = new ArrayList<>();
        //创建一个Map存放所有类型，Map的key为类型的id，Map的value为当前类型对象
        Map<Long,CourseType> courseTypeMap = new HashMap<>();

        //往Map中放值
        for (CourseType courseType : courseTypeList) {
            courseTypeMap.put(courseType.getId(),courseType);
        }

        for (CourseType courseType : courseTypeList) {
            if(courseType.getPid()==0){
                firstLevleTypes.add(courseType);
            }else{
                //如果pid不为0，则嵌套循环查询他的父类型，添加到父类型的children集合中
                CourseType parent = courseTypeMap.get(courseType.getPid());
                if(parent!=null){
                    parent.getChildren().add(courseType);
                }
            }
        }
        return firstLevleTypes;
    }

    /**
     * 增删改需要执行的操作
     */
    private void synchronizedOperation(){
        List<CourseType> courseTypes = loadTCourseTypeByMap();
        String str = JSONObject.toJSONString(courseTypes);
        catchClient.setex(KEY,10*60,str);
    }
}
