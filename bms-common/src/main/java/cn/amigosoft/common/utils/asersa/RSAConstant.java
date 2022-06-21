package cn.amigosoft.common.utils.asersa;

public interface RSAConstant {
    /**
     * 获取RSA公钥类型（0、静态 1、动态）
     */
    enum RSAKeyType {
        STATIC(0),
        DYNAMIC(1);

        private int type;

        RSAKeyType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }

    /**
     * 服务端静态私钥
     */
    String  SERVER_PRI_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDTKRrspAdZlvcdlnt0wb+XDa9BRD4OY28rcNVaKFk1I9KUm687evV59d1MFxZ+BIWa/BlQVftLtB/7sdubJnVIbOij5dEPasjESbwT0kPVm0YrSR1C6RR08UAdaziR4triuwxA6nnetUF0UoONlcRC0YEfibACYuBkoOrBUF7wQMU7MpGWBLO4/doli+JkcF26wr5r/GnJ8+KMxj1/5OnfnKc2xQTG9h5gFzqFBSH1UWXK3jMB1qtXo9qq3KR65z+cVxEAf/0Z27rEM4VuITHlOEiEXM9Zc6LP/2fXTtn3Ab+J1pRVOcPqUo9f0ZXnoCUqw2wOS7v+dD0TZtG6j6VxAgMBAAECggEAT+v98dU/puwZygwb3Bw9DMo0LArNRhrih9mkFwSwhwlw+ahXUbb/VFL3mIYgkD/b5cZHOn3gqE2b0WMHEJkkyO3Xu7kPQLuVE54o3oO2AxJLI7T/q0SW5pAtSTU86xKm8CwFvkhBKkcGaCWW/y7RYszzB2HQyYZtEglJ8TMfS+U3wmu2UjmLTi2Fpgu1G32gBrTXk4P0DYfFix45FkeYM+DbnaypF5XGtUtg+Bgkhg++EGVmeGnIZtGtxCu+MqlfwbJ4wZe8skhloleduosciGmONnivyRknCvsrPV4jwyt7U+Qo3QS8RQAq9X2sUbsGOMxOyyGHocNnuROvmnl4AQKBgQD1F9Q4TisKVFtl3GILL2X+uoXwggOALfOAiRVAPyPNHctZlUTg/5iNTMfzykoBfnnT7loi20+nAh0LE8ovmqUcY44+kk/T5Wvxh1VetEFjwIgpcA2gSyI5MdnUUtubecsmKOo8gCCLnpcrqRkXJJjBT4EjO3Jw10IA3pgyjCTGgQKBgQDcjrULKlsLWQvj6A3e2kZEFOaIScWlDj2s8UBEQ1u/7f0b0ZIRof8PET6jDT4BSKz8cJRMcd1MWCWtuujxlbJ4zxmlwJecVG3jUgML3DOt3Z6+3GzCfIqmCvwN+ZLYGKMyVbsTV/9f2MHR0P16tkeNmCuSwVI98Jm0GDRtL+nG8QKBgQDrWdp7xvQlkyPBCINNYlJ67Pm5Hd/msjllcD1r2DW0vauLdn8R397JsLVBMTsnfB/YlsUPO7GdGbZx8KryU3nBbQgmpbDqNSAWanJb4pP4CeW2hNdQ2VUfvvMBpB8liSq+lgN33UFjUQKw9kwVoRreZk4HWwpluQ60bwuPXmviAQKBgQCrDy+RKleUlIqpR1hCqE3pDcDCoVzeKA5jrHTovMlTMXSmd8LvVBIrEHfhJCsb+c7Yt0P9ii5VwOPwO0/h1vWlxxIgQSAVSlI0BW6Ba1bVmVY+t1zO9qLeJxTYjeF7IpioOF+woAZbwxSH86w02Vzjj8rztmZfzdSYHlHDAW8yMQKBgQDxzQkymVNVUEvQ6IQMM55C14KSa6oL95uZmjaSHatq/0kHGdDZ4WS7OCb4KhWGV8+Du5LIywfAyZ878clwDx6kdKZ4tuDDFu7RTuwsSoqV8fP+jmru8isBa340pAuQheVNZcF90qn3BQMU8xCKekIJMujckDtOnNqyg2fPIwXH5w==";
    /**
     * 服务端静态公钥
     */
    String SERVER_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0yka7KQHWZb3HZZ7dMG/lw2vQUQ+DmNvK3DVWihZNSPSlJuvO3r1efXdTBcWfgSFmvwZUFX7S7Qf+7HbmyZ1SGzoo+XRD2rIxEm8E9JD1ZtGK0kdQukUdPFAHWs4keLa4rsMQOp53rVBdFKDjZXEQtGBH4mwAmLgZKDqwVBe8EDFOzKRlgSzuP3aJYviZHBdusK+a/xpyfPijMY9f+Tp35ynNsUExvYeYBc6hQUh9VFlyt4zAdarV6Paqtykeuc/nFcRAH/9Gdu6xDOFbiEx5ThIhFzPWXOiz/9n107Z9wG/idaUVTnD6lKPX9GV56AlKsNsDku7/nQ9E2bRuo+lcQIDAQAB";

    /**
     * RSA 服务端及客户端加密公私钥 缓存Key
     */
    public static String getRSAKey(String uuid){
        return "sys:rsa:" + uuid;
    }
    /**
     * RSA uuid 固定值
     */
    String UUID="abcdefg_uuid_123456";

    /**
     * AES 密钥 缓存Key
     */
    public static String getAESKey(String token){
        return "sys:aes:" + token;
    }

    /**
     * sign header
     */
    String SIGN_HEADER = "sign";

    /**
     * rsa_uuid header
     */
    String RSA_UUID_HEADER = "Rsa-Uuid";
}
