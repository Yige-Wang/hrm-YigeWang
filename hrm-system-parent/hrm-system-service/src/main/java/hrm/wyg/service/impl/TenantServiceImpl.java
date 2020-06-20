package hrm.wyg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hrm.wyg.controller.vo.RegisterVo;
import hrm.wyg.domain.*;
import hrm.wyg.mapper.*;
import hrm.wyg.service.ITenantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YigeWang
 * @since 2020-06-19
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {
    @Autowired
    private TenantMealMapper tenantMealMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MealPermissionMapper mealPermissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;
    @Transactional
    @Override
    public void register(RegisterVo registerVo) {
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(registerVo,tenant);
        baseMapper.insert(tenant);
        TenantMeal tenantMeal = new TenantMeal();
        tenantMeal.setTenantId(tenant.getId());
        tenantMeal.setMealId(registerVo.getMealId());
        tenantMealMapper.insert(tenantMeal);
        Role role = new Role();
        role.setName("Yige");
        role.setSn("admin");
        role.setTenantId(tenant.getId());
        roleMapper.insert(role);
        List<MealPermission> mealPermissions = mealPermissionMapper.selectList(
                new QueryWrapper<MealPermission>().eq("meal_id", registerVo.getMealId())
        );
        List<Long> permissionIds = mealPermissions.stream().map(MealPermission::getPermissionId).collect(Collectors.toList());
        rolePermissionMapper.batchInsert(role.getId(),permissionIds);
        Employee employee = new Employee();
        employee.setUsername(registerVo.getUsername());
        employee.setPassword(registerVo.getPassword());
        employee.setInputTime(System.currentTimeMillis());
        employee.setState(1);
        employee.setTenantId(tenant.getId());
        employeeMapper.insert(employee);
        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setEmployeeId(employee.getId());
        employeeRole.setRoleId(role.getId());
        employeeRoleMapper.insert(employeeRole);
    }
}
