package hrm.wyg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hrm.wyg.domain.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YigeWang
 * @since 2020-06-19
 */
@Component
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    void batchInsert(@Param("roleId") Long id,@Param("permissionIds") List<Long> permissionIds);
}
