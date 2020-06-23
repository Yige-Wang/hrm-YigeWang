package hrm.wyg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hrm.wyg.domain.Systemdictionary;
import hrm.wyg.domain.SystemdictionaryItem;
import hrm.wyg.mapper.SystemdictionaryItemMapper;
import hrm.wyg.mapper.SystemdictionaryMapper;
import hrm.wyg.service.ISystemdictionaryItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YigeWang
 * @since 2020-06-19
 */
@Service
public class SystemdictionaryItemServiceImpl extends ServiceImpl<SystemdictionaryItemMapper, SystemdictionaryItem> implements ISystemdictionaryItemService {
    @Autowired
    private SystemdictionaryMapper systemdictionaryMapper;

    @Override
    public List<SystemdictionaryItem> getBySn(String sn) {
        Systemdictionary systemdictionary = systemdictionaryMapper.selectOne(new QueryWrapper<Systemdictionary>().eq("sn", sn));
        if (systemdictionary!=null){
            return baseMapper.selectList(new QueryWrapper<SystemdictionaryItem>().eq("parent_id",systemdictionary.getId()));
        }
        return null;
    }
}
