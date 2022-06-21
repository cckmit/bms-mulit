package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dao.BmsEvaluationUserDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationDetailDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationTreeDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationUserDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsEvaluationUserEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsEvaluationUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 评价用户关联表
 */
@Service
public class BmsEvaluationUserServiceImpl extends CrudServiceImpl<BmsEvaluationUserDao, BmsEvaluationUserEntity, BmsEvaluationUserDTO> implements BmsEvaluationUserService {

    @Autowired
    private BmsEvaluationUserDao evaluationUserDao;

    @Override
    public QueryWrapper<BmsEvaluationUserEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsEvaluationUserEntity> wrapper = new QueryWrapper<>();

        return wrapper;
    }


    @Override
    public Result<PageData<BmsEvaluationUserDTO>> getPage(Map<String, Object> params) {
        IPage<BmsEvaluationUserEntity> page = getPage(params, "e.create_date", false);
        List<BmsEvaluationUserDTO> list = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsEvaluationUserDTO>>().ok(getPageData(list, page.getTotal(), BmsEvaluationUserDTO.class));
    }

    @Override
    public BmsEvaluationUserDTO selectEvaluationDetail(Long id) {
        return baseDao.selectEvaluationDetail(id);
    }

    @Override
    public List<BmsEvaluationTreeDTO> getEvaluationStatistics(Long id) {
        List<String> list = evaluationUserDao.selectContentByEvaluationId(id);
        Map<String, Map<String, Integer>> map = new LinkedHashMap<>();
        for (String json : list) {
            List<BmsEvaluationDetailDTO> array = JSONObject.parseArray(json, BmsEvaluationDetailDTO.class);
            for (BmsEvaluationDetailDTO detail : array) {
                String title = detail.getTitle();
                List<Map<String, String>> options = detail.getOptions();
                if (options != null && options.size() > 0) {
                    int optionSize = options.size();
                    String checkedValue = detail.getCheckedValue();
                    Map<String, Integer> optionMap = map.get(title);
                    if (optionMap == null) {
                        optionMap = new LinkedHashMap<>(optionSize);
                        for (Map<String, String> option : options) {
                            String name = option.get("name");
                            if (name.equals(checkedValue)) {
                                optionMap.put(name, 1);
                            } else {
                                optionMap.put(name, 0);
                            }
                        }
                        map.put(title, optionMap);
                    } else {
                        for (Map<String, String> option : options) {
                            String name = option.get("name");
                            if (name.equals(checkedValue)){
                                Integer count = optionMap.get(name);
                                count += 1;
                                optionMap.put(name, count);
                            }
                        }
                        map.put(title, optionMap);
                    }
                }
            }
        }

        List<BmsEvaluationTreeDTO> result = new ArrayList<>();
        Set<String> keySet = map.keySet();
        int index = 0;
        int optionIndex = 10000;
        for (String key : keySet) {
            BmsEvaluationTreeDTO treeDTO = new BmsEvaluationTreeDTO();
            treeDTO.setId((long) index);
            treeDTO.setPid(0L);
            treeDTO.setContent(key);
            Map<String, Integer> optionMap = map.get(key);
            Set<String> optionKeySet = optionMap.keySet();
            List<BmsEvaluationTreeDTO> childList = new ArrayList<>();
            for (String optionKey : optionKeySet) {
                BmsEvaluationTreeDTO optionTreeDTO = new BmsEvaluationTreeDTO();
                optionTreeDTO.setId((long) optionIndex);
                optionTreeDTO.setPid((long) index);
                optionTreeDTO.setContent(optionKey);
                optionTreeDTO.setCount(optionMap.get(optionKey));
                optionTreeDTO.setChildren(null);
                childList.add(optionTreeDTO);
                optionIndex++;
            }
            treeDTO.setChildren(childList);
            result.add(treeDTO);
            index++;
        }
        return result;
    }
}