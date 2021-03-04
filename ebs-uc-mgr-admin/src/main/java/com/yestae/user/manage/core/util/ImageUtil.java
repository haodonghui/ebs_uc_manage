package com.yestae.user.manage.core.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.yestae.user.common.util.DateUtil;

@Component
public class ImageUtil {
	
	
	/**
	 * 文件上传
	 * 
	 * @param file：MultipartFile类型文件
	 * @param filePath：文件存放路径
	 * @return 文件路径+文件名
	 */
	public String uploadFile(MultipartFile file, String filePath) throws IOException {

		String fName = file.getOriginalFilename();
		String format = String.valueOf(fName.substring(fName.lastIndexOf(".")));
		String fileName = new Date().getTime() + format;
		
		return this.uploadFile(file, filePath, fileName) + "/" + fileName;
	}
	
	/**
	 * 文件上传
	 * 
	 * @param file：MultipartFile类型文件
	 * @param filePath：文件存放路径
	 * @param isThumb：是否创建缩略图
	 * @return 文件路径
	 */
	public String uploadFile(MultipartFile file, String filePath, String fileName) throws IOException {
		
		String path = DateUtil.format(new Date(), "/YYYY/MM/dd");
		File tempFile = new File(filePath + path, fileName);
		// 如果文件夹不存在则创建
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		if (!tempFile.exists()) {
			tempFile.createNewFile();
		}
		file.transferTo(tempFile);
		
		return path;
	}
	public static void main(String[] args) {
		String path = DateUtil.format(new Date(), "/YYYY/MM/dd");
		File tempFile = new File("/data" + path, "a.jpg");
		// 如果文件夹不存在则创建
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
	}

	/***
	 * 按指定的比例缩放图片
	 * 
	 * @param sourceImagePath
	 *            源地址
	 * @param destinationPath
	 *            改变大小后图片的地址
	 * @param fileName
	 *            改变大小后图片的名称
	 * @param scale
	 *            缩放比例，如1.2
	 * @return 
	 *            文件名       
	 * @throws IOException 
	 */
	public String scaleImage(String sourceImagePath, String destinationPath, String fileName, double scale) throws IOException {

		String format = String.valueOf(sourceImagePath.substring(sourceImagePath.lastIndexOf(".") + 1));
		
		if(fileName == null){
			fileName = new Date().getTime() + "." + format;
		}
		
		File file = new File(sourceImagePath);
		
		BufferedImage bufferedImage;
		bufferedImage = ImageIO.read(file);
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();

		width = parseDoubleToInt(width * scale);
		height = parseDoubleToInt(height * scale);

		Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = outputImage.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		
		File tempFile = new File(destinationPath, fileName);

		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		
		ImageIO.write(outputImage, format, tempFile);

		return fileName;
	}

	/***
	 * 将图片缩放到指定的高度或者宽度
	 * 
	 * @param sourceImagePath
	 *            图片源地址
	 * @param destinationPath
	 *            压缩完图片的地址
	 * @param width
	 *            缩放后的宽度
	 * @param height
	 *            缩放后的高度
	 * @param auto
	 *            是否自动保持图片的原高宽比例
	 * @param format
	 *            图图片格式 例如 jpg
	 * @return 
	 *            文件名
	 * @throws IOException 
	 */
	public String scaleImageWithParams(String sourceImagePath, String destinationPath, String fileName, int width, int height,
		boolean auto) throws IOException {

		String format = String.valueOf(sourceImagePath.substring(sourceImagePath.lastIndexOf(".") + 1));
		
		if(fileName == null){
			fileName = new Date().getTime() + "." + format;
		}
		
		File file = new File(sourceImagePath);
		BufferedImage bufferedImage = null;
		bufferedImage = ImageIO.read(file);
		if (auto) {
			ArrayList<Integer> paramsArrayList = getAutoWidthAndHeight(bufferedImage, width, height);
			width = paramsArrayList.get(0);
			height = paramsArrayList.get(1);
		}

		Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = outputImage.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		
		File tempFile = new File(destinationPath, fileName);
		// 如果文件夹不存在则创建
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		
		ImageIO.write(outputImage, format, tempFile);

		return fileName;
	}
	
	/***
	 * 
	 * @param bufferedImage
	 *            要缩放的图片对象
	 * @param width_scale
	 *            要缩放到的宽度
	 * @param height_scale
	 *            要缩放到的高度
	 * @return 一个集合，第一个元素为宽度，第二个元素为高度
	 */
	private ArrayList<Integer> getAutoWidthAndHeight(BufferedImage bufferedImage, int width_scale,
			int height_scale) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		double scale_w = getDot2Decimal(width_scale, width);

		double scale_h = getDot2Decimal(height_scale, height);
		if (scale_w < scale_h) {
			arrayList.add(parseDoubleToInt(scale_w * width));
			arrayList.add(parseDoubleToInt(scale_w * height));
		} else {
			arrayList.add(parseDoubleToInt(scale_h * width));
			arrayList.add(parseDoubleToInt(scale_h * height));
		}
		return arrayList;

	}

	/***
	 * 返回两个数a/b的小数点后三位的表示
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private double getDot2Decimal(int a, int b) {

		BigDecimal bigDecimal_1 = new BigDecimal(a);
		BigDecimal bigDecimal_2 = new BigDecimal(b);
		BigDecimal bigDecimal_result = bigDecimal_1.divide(bigDecimal_2, new MathContext(4));
		Double double1 = new Double(bigDecimal_result.toString());
		return double1;
	}
	
	/**
	 * 将double类型的数据转换为int，四舍五入原则
	 * 
	 * @param sourceDouble
	 * @return
	 */
	private int parseDoubleToInt(double sourceDouble) {
		int result = 0;
		result = (int) sourceDouble;
		return result;
	}
	
}
