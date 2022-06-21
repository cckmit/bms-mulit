package cn.amigosoft.common.utils.asersa.controller;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.asersa.EncryUtil;
import cn.amigosoft.common.utils.asersa.annotation.aes.AESDecryptMethod;
import cn.amigosoft.common.utils.asersa.annotation.rsa.RSADecryptMethod;
import cn.amigosoft.common.utils.asersa.dto.AesTokenDTO;
import cn.amigosoft.common.utils.asersa.dto.TestAesDTO;
import cn.amigosoft.common.utils.asersa.dto.TestDTO;
import cn.amigosoft.common.constant.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 交换公钥
 *
 * @author xuefenghai xuefh@ffcs.cn
 * @since 1.0.0 2019-03-01
 */
@RestController
@RequestMapping("common/rsa")
@Api(tags = "获取服务端公钥")
@Slf4j
public class RSAController {

    @Autowired
    private EncryUtil encryUtil;

    /**
     * @author xuefeng
     * @Date 2019/3/21 9:21
     */
    @PostMapping("publicKey")
    @ApiOperation("获取服务端公钥")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "uuid", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型0-静态公钥，1-动态公钥", paramType = "query", required = true, dataType = "String")
    })
    public Result<Object> publicKeyGet(@ApiIgnore @RequestParam Map<String, Object> params) {
        //uuid不能为空
        int type = Integer.parseInt(String.valueOf(params.get("type")));
        String uuid = String.valueOf(params.get("uuid"));
        String serverPublicKey = encryUtil.getPublicKey(uuid, type);
        return new Result<Object>().ok(serverPublicKey);
    }

    /**
     * token与aes秘钥绑定
     *
     * @return
     */
    @PostMapping("transferKey")
    @ApiOperation("aeskey与rsa_uuid绑定")
    @RSADecryptMethod
    public Result<Object> tokenAeskeyBind(HttpServletRequest request, @RequestBody AesTokenDTO aesTokenDTO) {
        //从header中获取token
        String token = request.getHeader(Constant.TOKEN_HEADER);
        log.info("transferKey token==>{},aeskey==>{}", token, aesTokenDTO.getAeskey());
        encryUtil.bindAeskeyByToken(token, aesTokenDTO.getAeskey());
        return new Result<Object>();
    }

    //    @PostMapping("encryptTest")
    @ApiOperation("测试报文加密")
    public Result<Object> encryptTest(@RequestBody TestDTO testDTO) {
        return new Result<Object>().ok(testDTO);
    }

    //    @PostMapping("decryptTest")
    @ResponseBody
    @ApiOperation("测试报文解密")
    public Result<Object> decryptTest(@RequestBody TestDTO testDTO) throws Exception {
        return new Result<Object>().ok(testDTO);
    }

    @PostMapping("decryptTest2")
    @ResponseBody
    @ApiOperation("测试报文解密")
    @AESDecryptMethod
    public Result<Object> decryptTest2(@RequestBody TestAesDTO testAesDTO) throws Exception {
        return new Result<Object>().ok(testAesDTO);
    }

}