# aes-rsa-java AES+RSA结合应用java示例

## 加密流程

1. 客户端(client)生成uuid唯一标识参数及type参数(0-静态秘钥 1-动态秘钥)通过服务端获取RSA公钥
2. client生成动态AES密钥(aesKey)
3. client使用sever的RSA公钥对aesKey进行加密,绑定token-aeskey，生成对应关系
4. client使用aesKey对JSON对象中的单参数进行加密各自得到密文
5. client请求报文传输给服务器端

服务器端进行请求响应时将上面流程反过来即可
# 解密流程
1.服务端需要提前知道加密参数，用DecryptMethod注解接口方法，
2.用EncryptField 符号注解 DTO中的被加密的密文字段
3.通过切面处理，获取从request header 里获取token，根据token获取对应aeskey，
4.再通过aeskey解密密文字段。





