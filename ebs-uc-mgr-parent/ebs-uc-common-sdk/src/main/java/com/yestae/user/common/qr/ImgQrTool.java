package com.yestae.user.common.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 内嵌图片的二维码生成器
 *
 * @author lichunxi
 */
public class ImgQrTool {

    private static Logger log = LoggerFactory.getLogger(ImgQrTool.class);

    // 镶嵌的图片宽度的一般
    private static final int IMAGE_WIDTH = 80;
    private static final int IMAGE_HEIGHT = 80;
    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
    private static final int FRAME_WIDTH = 2;
    
    private static final String LOGO_BASE_64 = "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UM3XAfssPEnywrZouKteSmHTYp5lLJrtO2emsFjorXQhwp6WMRtLubu1xx1k5J8VsURWHopJLCIq9IeyXO9aYt7LRQ1FbJDVb72QML3Bu4RnA4nj2KvsWjNTyydGzTt33s440cgx4kjgrrooShl5Md+ijdPvbMW0xPgtdHFK3dkZCxrh2ENAKykRTNiWNiPNsWgDrS1Qy0L2R3WiDjDvDhKDzYT1cRwPIHPblVkvemb3Y3EXa1VlKP15IjuHwdyPkVd5FCUE9zHqNFC592cMo3bLdd7y5tNbaWtrTyEcLHPA9XALuanZDfqPR0l1qKaeS5OkaI7fTs6SRrDnLnYzx5cB28exWrRcVaKo9Ngl7nkqTs20pqGLXdjnmsdzighq45JJJaV7GsaDkkkjAVtkRSjHtNWn06oTSeSF9rWyF16rKm96ac0XCU789I8hrZTji5h6nHrB4HnkdcGXbSeoLQT+UrNX07R8d0Dtz6QGPtV20XHWmU3aCFj7k8MoWyN737jGOc88N0DJXQ2vQ2qLpumisNwex3J74TGw/vOwPtV00UfSKY9Mj5kcTsf0zW6U0ZFQXTcFY+Z872MdvBm9gBues4A5LtkRWJY2PShBQiorwERF0kEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQH//2Q==";

    // 二维码写码器
    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();

    /**
     * 生成带图片的二维码
     *
     * @param content       二维码的内容
     * @param width         宽度
     * @param height        高度
     * @param srcImagePath  被镶嵌的图片的地址
     * @param destImagePath 生成二维码图片的地址
     * @author fengshuonan
     * @since 2.3.0
     */
    public static void encode(String content, int width, int height, String srcImagePath, String destImagePath) {
        try {
            ImageIO.write(genBarcode(content, width, height, srcImagePath), "jpg", new File(destImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成带图片的二维码
     *
     * @param content       二维码的内容
     * @param width         宽度
     * @param height        高度
     * @param srcImagePath  被镶嵌的图片的地址
     * @param destImagePath 生成二维码图片的地址
     * @author fengshuonan
     * @since 2.3.0
     */
    public static void encode(String content, int width, int height, String srcImagePath, OutputStream outputStream) {
        try {
            ImageIO.write(genBarcode(content, width, height, srcImagePath), "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建不带参数的二维码
     *
     * @author fengshuonan
     * @since 2.3.0
     */
    public static void createSimpleQr(String content, int width, int height, String destImagePath) {

        FileOutputStream output = null;

        try {
            String format = "jpg";// 图像类型
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
            File dest = new File(destImagePath);
            output = new FileOutputStream(dest);
            MatrixToImageWriter.writeToStream(bitMatrix, format, output);// 输出图像
        } catch (Exception e) {
            log.error("生成二维码出错！ImgQrTool：createSimpleQr()", e);
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                log.error("生成二维码出错！ImgQrTool：createSimpleQr()", e);
            }
        }
    }

    private static BufferedImage genBarcode(String content, int width, int height, String srcImagePath)
            throws WriterException, IOException {
        // 读取源图像
        BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH, IMAGE_HEIGHT, true);
        int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
        for (int i = 0; i < scaleImage.getWidth(); i++) {
            for (int j = 0; j < scaleImage.getHeight(); j++) {
                srcPixels[i][j] = scaleImage.getRGB(i, j);
            }
        }

        Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.MARGIN, 1);// 二维码整体白框

        // 生成二维码
        BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hint);

        // 二维矩阵转为一维像素数组
        int halfW = matrix.getWidth() / 2;
        int halfH = matrix.getHeight() / 2;
        int[] pixels = new int[width * height];

        for (int y = 0; y < matrix.getHeight(); y++) {
            for (int x = 0; x < matrix.getWidth(); x++) {
                // 读取图片
                if (x > halfW - IMAGE_HALF_WIDTH && x < halfW + IMAGE_HALF_WIDTH && y > halfH - IMAGE_HALF_WIDTH
                        && y < halfH + IMAGE_HALF_WIDTH) {
                    pixels[y * width + x] = srcPixels[x - halfW + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
                }
                // 在图片四周形成边框
                else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)
                        || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH
                        && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)
                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH
                        && y < halfH - IMAGE_HALF_WIDTH + FRAME_WIDTH)
                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                        && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH
                        && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
                    pixels[y * width + x] = 0xfffffff;
                } else {
                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                    pixels[y * width + x] = matrix.get(x, y) ? 0xff000000 : 0xfffffff;
                }
            }
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.getRaster().setDataElements(0, 0, width, height, pixels);

        return image;
    }

    /**
     * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
     *
     * @param srcImageFile 源文件地址
     * @param height       目标高度
     * @param width        目标宽度
     * @param hasFiller    比例不对时是否需要补白：true为补白; false为不补白;
     * @throws IOException
     */
    @SuppressWarnings("restriction")
	private static BufferedImage scale(String srcImageFile, int height, int width, boolean hasFiller)
            throws IOException {
        double ratio = 0.0; // 缩放比例
        File file = new File(srcImageFile);
        BufferedImage srcImage = null;
        if(file.exists()){
        	srcImage = ImageIO.read(file);
        }else{
        	InputStream in = ImgQrTool.class.getResourceAsStream(srcImageFile);
        	if(in != null){
        		srcImage = ImageIO.read(in);
        	}else{
        		srcImage =  ImageIO.read(new ByteArrayInputStream(new sun.misc.BASE64Decoder().decodeBuffer(LOGO_BASE_64)));
        	}
        }
        Image destImage = srcImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        // 计算比例
        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
            if (srcImage.getHeight() > srcImage.getWidth()) {
                ratio = (new Integer(height)).doubleValue() / srcImage.getHeight();
            } else {
                ratio = (new Integer(width)).doubleValue() / srcImage.getWidth();
            }
            AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
            destImage = op.filter(srcImage, null);
        }
        if (hasFiller) {// 补白
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = image.createGraphics();
            graphic.setColor(Color.white);
            graphic.fillRect(0, 0, width, height);
            if (width == destImage.getWidth(null))
                graphic.drawImage(destImage, 0, (height - destImage.getHeight(null)) / 2, destImage.getWidth(null),
                        destImage.getHeight(null), Color.white, null);
            else
                graphic.drawImage(destImage, (width - destImage.getWidth(null)) / 2, 0, destImage.getWidth(null),
                        destImage.getHeight(null), Color.white, null);
            graphic.dispose();
            destImage = image;
        }
        return (BufferedImage) destImage;
    }

    /**
     * 生成上方带文字的二维码
     */
    public static void createQrWithFontsAbove(QrImage para) {
        try {
            // 首先生成二维码图片
            BufferedImage qrImage = genBarcode(para.getQrContent(), para.getQrWidth(), para.getQrHeight(), para.getQrIconFilePath());

            int qrImageWidth = qrImage.getWidth();
            int qrImageHeight = qrImage.getHeight();

            String[] splitStrLines = null;
            splitStrLines = splitStrLines(para.getWordContent(), qrImageWidth / para.getWordSize());
            int fontsImageHeight = splitStrLines.length * para.getWordSize() + 10; //防止文字遮挡

            //创建顶部文字的图片
            BufferedImage imageWithFonts = new BufferedImage(qrImageWidth, fontsImageHeight, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics fontsImageGraphics = imageWithFonts.getGraphics();
            fontsImageGraphics.fillRect(0, 0, qrImageWidth, fontsImageHeight);
            fontsImageGraphics.setColor(Color.black);
            fontsImageGraphics.setFont(new Font("宋体", Font.PLAIN, para.getWordSize()));

            //文字长度大于一行的长度，进行分行
            if (para.getWordContent().length() > qrImageWidth / para.getWordSize()) {//每行限制的文字个数
                for (int i = 0; i < splitStrLines.length; i++) {
                    fontsImageGraphics.drawString(splitStrLines[i], 0, para.getWordSize() * (i + 1));
                }
            } else {
                fontsImageGraphics.drawString(
                        para.getWordContent(),
                        ((qrImageWidth / para.getWordSize() - para.getWordContent().length()) / 2) * para.getWordSize() + 20, //总长度减去字长度除以2为向右偏移长度
                        para.getWordSize());
            }

            // 从图片中读取RGB
            int[] ImageArrayFonts = new int[qrImageWidth * fontsImageHeight];
            ImageArrayFonts = imageWithFonts.getRGB(0, 0, qrImageWidth, fontsImageHeight, ImageArrayFonts, 0, qrImageWidth);

            int[] ImageArrayQr = new int[qrImageWidth * qrImageHeight];
            ImageArrayQr = qrImage.getRGB(0, 0, qrImageWidth, qrImageHeight, ImageArrayQr, 0, qrImageWidth);

            // 生成新图片
            BufferedImage ImageNew = new BufferedImage(qrImageWidth, qrImageHeight + fontsImageHeight, BufferedImage.TYPE_INT_RGB);
            ImageNew.setRGB(0, 0, qrImageWidth, fontsImageHeight, ImageArrayFonts, 0, qrImageWidth);// 设置上半部分的RGB
            ImageNew.setRGB(0, fontsImageHeight, qrImageWidth, qrImageHeight, ImageArrayQr, 0, qrImageWidth);// 设置下半部分的RGB

            File outFile = new File(para.getFileOutputPath());
            ImageIO.write(ImageNew, "jpg", outFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 一行字符串拆分成多行
     */
    private static String[] splitStrLines(String str, int len) {
        int blocks = str.length() / len + 1;
        String[] strs = new String[blocks];
        for (int i = 0; i < blocks; i++) {
            if ((i + 1) * len > str.length()) {
                strs[i] = str.substring(i * len);
            } else {
                strs[i] = str.substring(i * len, (i + 1) * len);
            }
        }
        return strs;
    }

    public static void main(String[] args) throws IOException {
       /* for (int i = 1; i <= 1; i++) {
            QrImage para = new QrImage.Builder()
                    .setFileOutputPath("D:\\二维码\\test\\" + i + ".jpg")
                    .setQrContent("http://www.baidu.com?a=" + "123")
                    .setQrHeight(300)
                    .setQrWidth(300)
                    .setQrIconFilePath("D:\\二维码\\中间图标\\1.png")
                    .setTopWrodHeight(100)
                    .setWordContent("test" + 1)
                    .setWordSize(18).build();
            ImgQrTool.createQrWithFontsAbove(para);
        }*/
    	BufferedImage srcImage = null;
    	ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new sun.misc.BASE64Decoder().decodeBuffer(LOGO_BASE_64));
    	srcImage =  ImageIO.read(byteArrayInputStream);
    	if(srcImage != null){
    		System.out.println(srcImage.getHeight());
    	}
    	if(byteArrayInputStream != null){
    		System.out.println(111);
    		byteArrayInputStream.close();
    	}
    }
}
