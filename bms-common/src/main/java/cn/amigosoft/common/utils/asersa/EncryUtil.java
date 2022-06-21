package cn.amigosoft.common.utils.asersa;

import cn.amigosoft.common.redis.RedisUtils;
import cn.amigosoft.common.utils.JsonUtil;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.exception.RenException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class EncryUtil {
	@Autowired
	private RedisUtils redisUtils;
	@Value("${ffcs.redis.open: false}")
	private boolean open;
	/**
	 * Local Cache  1个小时过期
	 */
	Cache<String, String> localCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(60*24, TimeUnit.MINUTES).build();
	/**
	 * Local Cache  24个小时过期
	 */
	Cache<String, String> localCache2 = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(60*24, TimeUnit.MINUTES).build();

	private void setCache(String key, String value){
		key = RSAConstant.getRSAKey(key);
		if(open){
			redisUtils.set(key, value, RedisUtils.DEFAULT_EXPIRE);
		}else{
			localCache.put(key, value);
		}
	}

	private String getCache(String key){
		key = RSAConstant.getRSAKey(key);
		if(open){
			String rsa = (String)redisUtils.get(key, RedisUtils.DEFAULT_EXPIRE);
			return rsa;
		}

		String rsa = localCache.getIfPresent(key);
		return rsa;
	}
	private void setAeskeyCache(String key, String value){
		key = RSAConstant.getAESKey(key);
		if(open){
			redisUtils.set(key, value, RedisUtils.DEFAULT_EXPIRE);
		}else{
			localCache2.put(key, value);
		}
	}

	private String getAeskeyCache(String key){
		key = RSAConstant.getAESKey(key);
		if(open){
			String aes = (String)redisUtils.get(key, RedisUtils.DEFAULT_EXPIRE);
			return aes;
		}

		String aes = localCache2.getIfPresent(key);
		return aes;
	}
	/**
	 *
	 * 动态获取随机公钥
	 * 服务端公钥
	 * @param uuid
	 * @param type
	 */
	public String getPublicKey(String uuid, int type) {
		String publicKey="";
		//如果不传type,则采用静态RSA公私秘钥
		Map<String,String> map=new HashMap<>();
		if(RSAConstant.RSAKeyType.DYNAMIC.getType()==type){
			try {
				map= RSA.generateKeyPair();
			} catch (Exception e) {
				log.error("生成动态RSA秘钥对失败",e);
			}
		}else{
			map.put("publicKey", RSAConstant.SERVER_PUB_KEY);
			map.put("privateKey", RSAConstant.SERVER_PRI_KEY);
		}
		//保存到缓存
		setCache(uuid, JsonUtil.entityToString(map));
		publicKey=map.get("publicKey");
		return publicKey;
	}
	/**
	 * 通过UUID获取私钥
	 * 服务端私钥
	 */
	public String getPrivateKey(String uuid) {
		//获取服务端公私钥及客户端公钥信息
		String rsaMapStr=getCache(uuid);
		Map<String,String> rsaMap= JsonUtil.StringToEntity(rsaMapStr,Map.class);
		if(null==rsaMap||rsaMap.size()<1){
			log.error("通过uuid:{}获取RSA秘钥对不存在",uuid);
			throw new RenException(ErrorCode.RSAKEY_INVALID);
		}
		return rsaMap.get("privateKey");
	}

	/**
	 *
	 * 获取24个小时刷新一次的公钥
	 * 服务端公钥
	 */
	public String getPublicKey() {
		String rsaMapStr=getCache(RSAConstant.UUID);
		Map<String,String> map= JsonUtil.StringToEntity(rsaMapStr,Map.class);
		if(null==map||map.size()<1){
			try {
				map= RSA.generateKeyPair();
			} catch (Exception e) {
				log.error("生成RSA秘钥对失败",e);
			}
			//保存到缓存
			setCache(RSAConstant.UUID, JsonUtil.entityToString(map));
		}
		return map.get("publicKey");
	}

	/**
	 * 获取24个小时刷新的私钥
	 * 服务端私钥
	 */
	public String getPrivateKey() {
		//获取服务端公私钥及客户端公钥信息
		String rsaMapStr=getCache(RSAConstant.UUID);
		Map<String,String> rsaMap= JsonUtil.StringToEntity(rsaMapStr,Map.class);
		if(null==rsaMap||rsaMap.size()<1){
			log.error("获取静态RSA秘钥对不存在");
			throw new RenException(ErrorCode.RSAKEY_INVALID);
		}
		return rsaMap.get("privateKey");
	}
	/**
	 * 绑定
	 */
	public void bindAeskeyByToken(String token,String aeskey){
		setAeskeyCache(token,aeskey);
	}

	/**
	 * 根据token解密
	 * @param encryptValue
	 * @param token
	 * @return
	 */
	public String AESDecryptByToken(String encryptValue, String token){
		String aeskey=getAeskeyCache(token);
		if(StringUtil.isBlank(aeskey)){
			log.error("通过token:{}获取aeskey不存在",token);
			throw new RenException(ErrorCode.AESKEY_INVALID);
		}
		//有向量解密
		return AES.decrypt(encryptValue,aeskey,aeskey);
	}

	/**
	 * rsa解密
	 * @param cryptograph
	 * @return
	 */
	public String RSADecrypt(String cryptograph) throws Exception {
		return  RSA.decrypt(cryptograph,this.getPrivateKey());
	}

	/**
	 * rsa加密
	 *
	 * @param source
	 * @return
	 */
	public String RSAEncrypt( String source) throws Exception {
		return  RSA.encrypt(source,this.getPublicKey());
	}
	/**
	 * rsa解密
	 *
	 * @param uuid
	 * @param cryptograph
	 * @return
	 */
	public String RSADecrypt(String uuid, String cryptograph) throws Exception {
		//获取服务端私钥
		String pri=getPrivateKey(uuid);
		return  RSA.decrypt(cryptograph,pri);
	}



	/**
	 * rsa加密
	 *
	 * @param uuid
	 * @param source
	 * @return
	 */
	public String RSAEncrypt(String uuid, String source) throws Exception {
		//获取服务端公私钥及客户端公钥信息
		String rsaMapStr=getCache(uuid);
		Map<String,String> rsaMap= JsonUtil.StringToEntity(rsaMapStr,Map.class);
		if(null==rsaMap||rsaMap.size()<1){
			log.error("通过uuid:{}获取公钥不存在",uuid);
			throw new RenException("用户未获取公钥");
		}
		String pub= rsaMap.get("publicKey");
		return  RSA.encrypt(source,pub);
	}

	/**
	 * aes解密
	 * @param encryptValue
	 * @param aeskey
	 * @return
	 */
	public String AESDecrypt(String encryptValue, String aeskey){
		return AES.decrypt(encryptValue,aeskey,aeskey);
	}

	/**
	 * aes加密
	 * @param data
	 * @param aeskey
	 * @return
	 */
	public String AESEncrypt(String data, String aeskey){
		return AES.encrypt(data,aeskey,aeskey);
	}


}
