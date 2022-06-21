package cn.amigosoft.modules.bms.csv;

import lombok.Data;

@Data
public class BmsFileImportResult {

    /**
     * 总条数
     */
    private int allCount = 0;

    /**
     * 导入成功数量
     */
    private int successCount = 0;

    /**
     * 导入失败数量
     */
    private int errorCount = 0;

}
