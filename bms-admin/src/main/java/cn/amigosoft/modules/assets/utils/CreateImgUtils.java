package cn.amigosoft.modules.assets.utils;

import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.modules.oss.cloud.OSSFactory;
import gui.ava.html.image.generator.HtmlImageGenerator;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CreateImgUtils {

    public static String getcreateImgUrl(String htmlstr) {
        if (StringUtil.isBlank(htmlstr)) {
            return null;
        }
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml(htmlstr);

        BufferedImage bufferedImage = getGrayPicture(imageGenerator.getBufferedImage());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", outputStream);
            String base64Img = Base64.encodeBase64String(outputStream.toByteArray());
            String s = OSSFactory.build().uploadSuffix(new BASE64Decoder().decodeBuffer(base64Img), "jpg", "jpg");
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException a) {
                    a.printStackTrace();
                }
            }
            return null;
        }
    }

    public static BufferedImage getGrayPicture(BufferedImage originalImage) {
        BufferedImage grayPicture;
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();

        grayPicture = new BufferedImage(imageWidth, imageHeight,
                BufferedImage.TYPE_INT_RGB);
        ColorConvertOp cco = new ColorConvertOp(ColorSpace
                .getInstance(ColorSpace.CS_GRAY), null);
        cco.filter(originalImage, grayPicture);
        return grayPicture;
    }

    public static void main(String[] args) {
        String aa = "<table style='font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;'>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td colspan='2' style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;'>\n" +
                "\t\t\t\t\t责任人：王一峰</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td colspan='2' style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;'>\n" +
                "\t\t\t\t\t资产名称：A栋客厅1</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;'>\n" +
                "\t\t\t\t\t资产编号：SB202105310001</td>\n" +
                "\t\t\t\t<td rowspan=\"2\" style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;width:100px;'>\n" +
                "\t\t\t\t\t<img src=\"https://qr.api.cli.im/newqr/create?data=ddd&level=H&transparent=false&bgcolor=%23FFFFFF&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&logoshape=no&size=260&kid=cliim&key=55d77345ba83165b8f39d7d8f0ba07aa\" style='width: 100%;'>\t\n" +
                "\t\t\t\t</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;'>\n" +
                "\t\t\t\t\t所在区域：A栋客厅1</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</table>";
        String s = getcreateImgUrl(aa);
        System.out.println(s);
    }
}
