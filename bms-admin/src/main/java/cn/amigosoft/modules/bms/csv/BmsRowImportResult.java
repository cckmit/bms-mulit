package cn.amigosoft.modules.bms.csv;

import lombok.Data;

@Data
public class BmsRowImportResult {

    /**
     * 记录 顺序 id
     */
    private Integer sortId;

    /**
     * 导入是否成功
     */
    private boolean isSuccess = true;

    /**
     * 导入失败对象数据
     */
    private Object errorObject;

    /**
     * 导入失败原因
     */
    private String errorText;

}