package cn.amigosoft.modules.bms.other.controller;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.other.dto.BmsTreeSelectDTO;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dto.SysDeptDTO;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.service.SysDeptService;
import cn.amigosoft.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 公共管理
 */
@RestController
@RequestMapping("bms/common")
@Api(tags = "公共管理")
public class BmsCommonController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysDeptService deptService;

    @GetMapping("userTree")
    @ApiOperation("公共管理-员工树")
    public Result<List<SysUserDTO>> userTree(String value, Long deptId, String mobile, Long id) {
        List<SysUserDTO> list = userService.selectBaseUserInfo(value, deptId, mobile, id);

        return new Result<List<SysUserDTO>>().ok(list);
    }

    @GetMapping("deptTree")
    @ApiOperation("公共管理-部门树")
    public Result<List<BmsTreeSelectDTO>> deptTree() {
        List<SysDeptDTO> list = deptService.list(new HashMap<>(1));
        List<BmsTreeSelectDTO> result = new ArrayList<>();
        for (SysDeptDTO dept : list) {
            result.add(changeDeptToTree(dept));
        }
        return new Result<List<BmsTreeSelectDTO>>().ok(result);
    }

    private BmsTreeSelectDTO changeDeptToTree(SysDeptDTO dept) {
        BmsTreeSelectDTO treeSelectDTO = new BmsTreeSelectDTO();
        treeSelectDTO.setTitle(dept.getName());
        String id = String.valueOf(dept.getId());
        treeSelectDTO.setValue(id);
        treeSelectDTO.setKey(id);
        List<SysDeptDTO> children = dept.getChildren();
        if (children != null && children.size() > 0) {
            List<BmsTreeSelectDTO> list = new ArrayList<>();
            for (SysDeptDTO child : children) {
                list.add(changeDeptToTree(child));
            }
            treeSelectDTO.setChildren(list);
        }

        return treeSelectDTO;
    }

    @GetMapping("visitVerifyUser")
    @ApiOperation("公共管理-访客报备主管审批人员")
    public Result<List<SysUserDTO>> visitVerifyUser() {
        List<SysUserDTO> list = userService.selectUserByPermission(BmsConstant.PERMISSION_VISIT_VERIFY);
        List<SysUserDTO> result = filterCurrentDeptUser(list);
        return new Result<List<SysUserDTO>>().ok(result);
    }

    @GetMapping("visitVerifyGuardUser")
    @ApiOperation("公共管理-访客报备保安审批人员")
    public Result<List<SysUserDTO>> visitVerifyGuardUser() {
        List<SysUserDTO> list = userService.selectUserByPermission(BmsConstant.PERMISSION_VISIT_GUARD_VERIFY);
        return new Result<List<SysUserDTO>>().ok(list);
    }

    @GetMapping("managerVerifyUser")
    @ApiOperation("公共管理-接待餐主管审核人员")
    public Result<List<SysUserDTO>> managerVerifyUser() {
        List<SysUserDTO> list = userService.selectUserByPermission(BmsConstant.PERMISSION_MANAGER_VERIFY);
        List<SysUserDTO> result = filterCurrentDeptUser(list);
        return new Result<List<SysUserDTO>>().ok(result);
    }

    @GetMapping("officeVerifyUser")
    @ApiOperation("公共管理-接待餐办公室审核人员")
    public Result<List<SysUserDTO>> officeVerifyUser() {
        List<SysUserDTO> list = userService.selectUserByPermission(BmsConstant.PERMISSION_OFFICE_VERIFY);
        return new Result<List<SysUserDTO>>().ok(list);
    }

    @GetMapping("repairUser")
    @ApiOperation("公共管理-资产内部维修人员")
    public Result<List<SysUserDTO>> repairUser() {
        List<SysUserDTO> list = userService.selectUserByPermission(BmsConstant.PERMISSION_ASSETS_REPAIR);
        return new Result<List<SysUserDTO>>().ok(list);
    }

    @GetMapping("externalRepairUser")
    @ApiOperation("公共管理-资产外部维修人员")
    public Result<List<SysUserDTO>> externalRepairUser() {
        List<SysUserDTO> list = userService.selectUserByPermission(BmsConstant.PERMISSION_ASSETS_EXTERNAL_REPAIR);
        return new Result<List<SysUserDTO>>().ok(list);
    }

    @GetMapping("assetsVerifyUser")
    @ApiOperation("公共管理-报修申请审核人员")
    public Result<List<SysUserDTO>> assetsVerifyUser() {
        List<SysUserDTO> list = userService.selectUserByPermission(BmsConstant.PERMISSION_ASSETS_REPAIR_VERIFY);
        return new Result<List<SysUserDTO>>().ok(list);
    }

    @GetMapping("addressManageUser")
    @ApiOperation("公共管理-访问地点管理人员")
    public Result<List<SysUserDTO>> addressManageUser() {
        List<SysUserDTO> list = userService.selectUserByPermission(BmsConstant.PERMISSION_ADDRESS_MANAGE);
        return new Result<List<SysUserDTO>>().ok(list);
    }

    private List<SysUserDTO> filterCurrentDeptUser(List<SysUserDTO> list) {
        UserDetail user = SecurityUser.getUser();
        Long userDeptId = user.getDeptId();
        List<SysUserDTO> result = new ArrayList<>();
        for (SysUserDTO dto : list) {
            if (userDeptId.equals(dto.getDeptId())) {
                result.add(dto);
            }
        }
        return result;
    }
}
