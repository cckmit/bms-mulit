package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.ImportUtils;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.modules.bms.dinning.dao.BmsHolidayDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsHolidayDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsHolidayEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsHolidayExcel;
import cn.amigosoft.modules.bms.dinning.service.BmsHolidayService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 节假日表
 */
@Service
public class BmsHolidayServiceImpl extends CrudServiceImpl<BmsHolidayDao, BmsHolidayEntity, BmsHolidayDTO> implements BmsHolidayService {

    @Override
    public QueryWrapper<BmsHolidayEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsHolidayEntity> wrapper = new QueryWrapper<>();
        Object searchBeginDate = params.get("searchBeginDate");
        if (searchBeginDate != null && StringUtils.isNotEmpty(searchBeginDate.toString())) {
            wrapper.ge("holiday", searchBeginDate);
        }

        Object searchEndDate = params.get("searchEndDate");
        if (searchEndDate != null && StringUtils.isNotEmpty(searchEndDate.toString())) {
            wrapper.le("holiday", searchEndDate);
        }
        Object remark = params.get("remark");
        if (remark != null && StringUtils.isNotEmpty(remark.toString())) {
            wrapper.like("remark", remark);
        }
        return wrapper;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importExcel(MultipartFile file, HttpServletResponse response) {
        // 定义变量
        // 错误list
        List<BmsHolidayExcel> excelList;
        // 设置excel导入参数
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        try {
            excelList = ExcelImportUtil.importExcel(file.getInputStream(), BmsHolidayExcel.class, params);
        } catch (Exception e) {
            ImportUtils.errorParseExcel(response);
            return;
        }
        // 当导入Excel中不存在数据时
        if (excelList.size() == 0) {
            ImportUtils.noDataImport(response);
            return;
        }
        List<BmsHolidayExcel> errorList = new ArrayList<>();
        for (BmsHolidayExcel excel : excelList) {
            try {
                if (StringUtil.isBlank(excel.getHoliday())) {
                    throw new Exception("假日不能为空");
                }
                excel.setHoliday(excel.getHoliday().trim());
            } catch (Exception e) {
                e.printStackTrace();
                errorList.add(excel);
            }
        }
        // 导入excel中存在错误数据
        if (errorList.size() > 0) {
            ImportUtils.errorImport(0, errorList.size(), response);
        } else {
            List<BmsHolidayEntity> list = ConvertUtils.sourceToTarget(excelList, BmsHolidayEntity.class);
            baseDao.batchDeleteHoliday(list);
            baseDao.batchInsertHoliday(list);
            // 导入成功
            ImportUtils.successImport(excelList.size(), response);
        }
    }
}
