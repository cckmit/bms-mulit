package cn.amigosoft.common.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * 属性数据类型
 *
 * @author HenryYan
 */
public class ImgUtils {
    public static final String FORMAT = "JPG";

    /**
     *  本体图片生成字节流
     *
     * @param output       输出流
     * @throws Exception
     */
    public static byte[] localEncodeByte(String path, ByteArrayOutputStream output) throws Exception {
        BufferedImage image = ImageIO.read(new FileInputStream(path));
        ImageIO.write(image, FORMAT, output);
        output.flush();
        byte[] imageInByte = output.toByteArray();
        output.close();

        return imageInByte;
    }
}