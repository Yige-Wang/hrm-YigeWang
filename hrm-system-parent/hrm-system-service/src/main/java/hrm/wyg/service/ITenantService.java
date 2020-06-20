package hrm.wyg.service;

import hrm.wyg.controller.vo.RegisterVo;
import hrm.wyg.domain.Tenant;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YigeWang
 * @since 2020-06-19
 */
public interface ITenantService extends IService<Tenant> {
    void register(RegisterVo registerVo);
}
