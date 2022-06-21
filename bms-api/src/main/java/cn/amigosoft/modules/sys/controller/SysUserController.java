/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.amigosoft.common.annotation.LogOperation;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.*;
import cn.amigosoft.common.utils.asersa.AESEncryptor;
import cn.amigosoft.common.validator.AssertUtils;
import cn.amigosoft.common.validator.ValidatorUtils;
import cn.amigosoft.modules.security.password.PasswordUtils;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dto.PasswordDTO;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.entity.SysUserEntity;
import cn.amigosoft.modules.sys.excel.SysUserExcel;
import cn.amigosoft.modules.sys.service.SysRoleUserService;
import cn.amigosoft.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("bms/sysUser")
@Api(tags = "用户管理")
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleUserService sysRoleUserService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "username", value = "账号", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("sys:user:page")
    public Result<PageData<SysUserDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<SysUserDTO> page = sysUserService.page(params);
        return new Result<PageData<SysUserDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("sys:user:info")
    public Result<SysUserDTO> get(@PathVariable("id") Long id) {
        SysUserDTO data = sysUserService.get(id);

        //用户角色列表
        List<Long> roleIdList = sysRoleUserService.getRoleIdList(id);
        data.setRoleIdList(roleIdList);

        return new Result<SysUserDTO>().ok(data);
    }

    @GetMapping("info")
    @ApiOperation("登录用户信息")
    public Result<SysUserDTO> info() {
        SysUserDTO data = ConvertUtils.sourceToTarget(SecurityUser.getUser(), SysUserDTO.class);
        return new Result<SysUserDTO>().ok(data);
    }

    @GetMapping("info-chat")
    @ApiOperation("登录用户信息")
    public Result<SysUserDTO> infoChat() {
        try {
            SysUserDTO data = ConvertUtils.sourceToTarget(SecurityUser.getUser(), SysUserDTO.class);
            Long userId = data.getId();
            String userIdStr = "20_" + userId;
            String encrypt = AESEncryptor.encrypt("guiyang-amigo", userIdStr);
            data.setIdKey(encrypt);
            return new Result<SysUserDTO>().ok(data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().error();
        }
    }

    @PutMapping("password")
    @ApiOperation("修改密码")
    @LogOperation("修改密码")
    public Result password(@RequestBody PasswordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto);

        UserDetail user = SecurityUser.getUser();

        //原密码不正确
        if (!PasswordUtils.matches(dto.getPassword(), user.getPassword())) {
            return new Result().error(ErrorCode.PASSWORD_ERROR);
        }

        sysUserService.updatePassword(user.getId(), dto.getNewPassword());

        return new Result();
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:user:save")
    public Result<Object> save(@RequestBody SysUserDTO dto) {
        //效验数据
        // ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        Result<Object> object = new Result<>();
        object.setMsg("操作成功");

        String errorInfo = sysUserService.validDto(dto);

        if (!"".equals(errorInfo)) {
            object.setCode(200);
            object.setMsg(errorInfo);
            return object;
        }

        sysUserService.save(dto);

        return object;
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:user:update")
    public Result<Object> update(@RequestBody SysUserDTO dto) {
        //效验数据
        // ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        Result<Object> object = new Result<>();
        object.setMsg("操作成功");

        String errorInfo = sysUserService.validDto(dto);

        if (!"".equals(errorInfo)) {
            object.setCode(200);
            object.setMsg(errorInfo);
            return object;
        }

        // 是否需要修改密码
        if (dto.getRePassword() != null) {
            sysUserService.updatePassword(dto.getId(), dto.getRePassword());
        }
        sysUserService.update(dto);

        return object;
    }

    @PutMapping("delete")
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysUserService.deleteBatchIds(Arrays.asList(ids));

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("sys:user:export")
    @ApiImplicitParam(name = "username", value = "账号", paramType = "query", dataType = "String")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysUserDTO> list = sysUserService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, SysUserExcel.class);
    }

    @GetMapping("getUserList")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "username", value = "账号", paramType = "query", dataType = "String")
    })
//	@RequiresPermissions("sys:user:getUserList")
    public Result<PageData<SysUserEntity>> getUserList(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<SysUserEntity> page = sysUserService.pageUser(params);
        return new Result<PageData<SysUserEntity>>().ok(page);
    }

    /**
     * 员工人员excel导入
     *
     * @param file 导入excel
     */
    @PostMapping("import")
    @ApiOperation(value = "上传文件")
//    @RequiresPermissions("device:device:export")
    public void importExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) {

        // 将excel转换成list
        List<SysUserExcel> excelList;
        // 设置excel导入参数
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        try {
            excelList = ExcelImportUtil.importExcel(file.getInputStream(), SysUserExcel.class, params);
        } catch (Exception e) {
            log.error("Excel解析失败", e);
            ImportUtils.errorParseExcel(response);
            return;
        }
        // 当导入Excel中不存在数据时
        if (excelList.size() == 0) {
            ImportUtils.noDataImport(response);
            return;
        }
        // 执行导入逻辑操作
        List<SysUserExcel> errorList = sysUserService.importExcel(excelList);
        // 导入excel中存在错误数据
        if (errorList.size() > 0) {
            String dataStr = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
            String fileName = "用户导入错误信息-" + dataStr;
            // 导入的Excel中存在错误信息，下载错误数据
            ImportUtils.errorImport(excelList.size(), response, errorList, SysUserExcel.class, fileName);
        } else {
            // 导入成功
            ImportUtils.successImport(excelList.size(), response);
        }
    }

    @GetMapping("selectUserByRoleKey")
    @ApiOperation("根据角色标识查询用户列表")
    @LogOperation("根据角色标识查询用户列表")
//    @RequiresPermissions("sys:user:info")
    public Result<List<SysUserDTO>> selectUserByRoleKey(String roleKey) {
        if (StringUtils.isEmpty(roleKey)) {
            return new Result<List<SysUserDTO>>().error("角色标识符为空");
        }
        List<SysUserDTO> list = sysUserService.selectUserByRoleKey(roleKey);
        return new Result<List<SysUserDTO>>().ok(list);
    }


    @GetMapping("userDetailInfo")
    @ApiOperation("信息")
    //@RequiresPermissions("activity:sysuser:info")
    public Result<SysUserDTO> getUserDetailInfo() {
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        System.out.println(creator);
        SysUserDTO data = sysUserService.getUserDetailInfo();

        return new Result<SysUserDTO>().ok(data);
    }


    @GetMapping("userInfo")
    @ApiOperation("信息")
    //@RequiresPermissions("activity:sysuser:info")
    public Result<SysUserEntity> getUserInfo() {
        SysUserEntity data = sysUserService.getSysUserInfo();

        return new Result<SysUserEntity>().ok(data);
    }

    @PostMapping("/updateAvatar")
    @ApiOperation("更新头像")
    public Result updateAvatar(@RequestBody SysUserDTO userDTO) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        if (userId == null) {
            return new Result();
        }
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setId(userId);
        userEntity.setHeadUrl(userDTO.getHeadUrl());
        sysUserService.updateById(userEntity);
        return new Result();
    }

    @GetMapping("/isSysUser")
    @ApiOperation("判断用户")
    public Result<SysUserEntity> isSysUser(String realName, String mobile) {
        Result result = sysUserService.isSysUer(realName, mobile);
        return result;
    }

}