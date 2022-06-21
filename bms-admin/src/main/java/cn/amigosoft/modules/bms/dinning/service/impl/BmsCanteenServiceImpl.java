package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.DateUtils;
import cn.amigosoft.common.utils.QrCodeUtil;
import cn.amigosoft.common.utils.ResourceDownLoadUtils;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.dinning.dao.BmsCanteenDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsCanteenDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsCanteenEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsCanteenService;
import cn.amigosoft.modules.bms.weixin.service.BmsWxService;
import cn.amigosoft.modules.oss.cloud.OSSFactory;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 食堂表
 */
@Service
public class BmsCanteenServiceImpl extends CrudServiceImpl<BmsCanteenDao, BmsCanteenEntity, BmsCanteenDTO> implements BmsCanteenService {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BmsWxService wxService;

    @Override
    public QueryWrapper<BmsCanteenEntity> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");

        QueryWrapper<BmsCanteenEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name);
        wrapper.eq(BmsConstant.DEL_COLUMN_NAME, BmsConstant.NOT_DEL);
        return wrapper;
    }

    @Override
    public PageData<BmsCanteenDTO> page(Map<String, Object> params) {
        PageData<BmsCanteenDTO> page = super.page(params);
        List<BmsCanteenDTO> list = page.getList();
        for (BmsCanteenDTO canteenDTO : list) {
            Long linkUserId = canteenDTO.getLinkUserId();
            if (linkUserId != null) {
                SysUserEntity linkUser = userDao.getById(linkUserId);
                if (linkUser != null) {
                    canteenDTO.setLinkUser(linkUser.getRealName());
                    canteenDTO.setLinkUserPhone(linkUser.getMobile());
                }
            }
        }
        return page;
    }

    @Override
    public BmsCanteenDTO selectCanteenById(Long id) {
        BmsCanteenEntity canteen = baseDao.selectById(id);
        BmsCanteenDTO canteenDTO = completeLinkUser(canteen);
        return canteenDTO;
    }

    private BmsCanteenDTO completeLinkUser(BmsCanteenEntity canteen) {
        BmsCanteenDTO canteenDTO = ConvertUtils.sourceToTarget(canteen, currentDtoClass());
        if (canteen != null) {
            Long linkUserId = canteen.getLinkUserId();
            if (linkUserId != null) {
                SysUserEntity linkUser = userDao.getById(linkUserId);
                if (linkUser != null) {
                    canteenDTO.setLinkUser(linkUser.getRealName());
                    canteenDTO.setLinkUserPhone(linkUser.getMobile());
                }
            }
        }
        return canteenDTO;
    }

    @Override
    public List<BmsCanteenDTO> export(Map<String, Object> params) {
        List<BmsCanteenEntity> list = baseDao.selectList(getWrapper(params));
        List<BmsCanteenDTO> result = new ArrayList<>();
        for (BmsCanteenEntity canteen : list) {
            BmsCanteenDTO canteenDTO = completeLinkUser(canteen);
            if (canteenDTO != null) {
                result.add(canteenDTO);
            }
        }
        return result;
    }

    @Override
    public List<BmsCanteenDTO> baseInfoList() {
        QueryWrapper<BmsCanteenEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", BmsConstant.NOT_DEL);
        List<BmsCanteenEntity> list = baseDao.selectList(queryWrapper);
        List<BmsCanteenDTO> result = new ArrayList<>();
        BmsCanteenDTO dto = new BmsCanteenDTO();
        dto.setId(BmsConstant.EMPTY_ID);
        dto.setName(BmsConstant.EMPTY_NAME);
        result.add(dto);
        for (BmsCanteenEntity canteenEntity : list) {
            BmsCanteenDTO canteenDTO = new BmsCanteenDTO();
            canteenDTO.setId(canteenEntity.getId());
            canteenDTO.setName(canteenEntity.getName());
            result.add(canteenDTO);
        }
        return result;
    }

    @Override
    public void downloadQRCodeZip(Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) {
        String id = (String) params.get("id");
        BmsCanteenEntity entity = baseDao.selectById(Long.parseLong(id));
        if (entity != null) {
            ArrayList<String> urls = new ArrayList<>();
            String qrCodeUrl = entity.getQrCodeUrl();
            urls.add(qrCodeUrl);
            List<String> name = new ArrayList();
            name.add(entity.getName());
            String dataStr = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
            ResourceDownLoadUtils.downloadZipForPatrolPolicy(response, urls, name, entity.getName() + "【二维码】" + dataStr);
        }
    }

    @Override
    public void save(BmsCanteenDTO dto) {
        super.save(dto);
        Long id = dto.getId();
        try {
            String qrCodeUrl = wxService.getWeixinQrCode(String.valueOf(id), "pages/dining/paid/paidResult");
            BmsCanteenEntity entity = new BmsCanteenEntity();
            entity.setId(id);
            entity.setQrCodeUrl(qrCodeUrl);
            baseDao.updateById(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(BmsCanteenDTO dto) {
        if (StringUtils.isEmpty(dto.getQrCodeUrl())) {
            String qrCodeUrl = wxService.getWeixinQrCode(String.valueOf(dto.getId()), "pages/dining/paid/paidResult");
            dto.setQrCodeUrl(qrCodeUrl);
        }
        super.update(dto);

    }
}
