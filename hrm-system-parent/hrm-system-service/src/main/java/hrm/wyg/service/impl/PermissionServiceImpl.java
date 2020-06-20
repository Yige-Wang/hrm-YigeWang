package hrm.wyg.service.impl;

import hrm.wyg.domain.Permission;
import hrm.wyg.mapper.PermissionMapper;
import hrm.wyg.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YigeWang
 * @since 2020-06-19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
