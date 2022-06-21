package cn.amigosoft.modules.bms.csv;

import java.util.Map;

public interface BmsImporter {

    BmsFileImportResult start() throws Exception;

    Map<String, Object> getParam();
}
