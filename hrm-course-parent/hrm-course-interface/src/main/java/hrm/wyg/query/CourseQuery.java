package hrm.wyg.query;

import lombok.Data;

/**
 * <p>
 *  查询参数对象
 * </p>
 *
 * @author solargen
 * @since 2020-06-18
 */
@Data
public class CourseQuery extends BaseQuery {
    private Integer status;
}