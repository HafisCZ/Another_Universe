package utilities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class GTextureLoader {
	private HashMap<String, GTexture> table = new HashMap<String, GTexture>();
	private ColorModel glAlphaColorModel;
	private ColorModel glColorModel;
	private IntBuffer textureIDBuffer = BufferUtils.createIntBuffer(1);
	
	public GTextureLoader() {
		glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
				new int[] {8,8,8,8},
				true,
				false,
				ComponentColorModel.TRANSLUCENT,
				DataBuffer.TYPE_BYTE);
		glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
				new int[] {8,8,8,0},
				false,
				false,
				ComponentColorModel.OPAQUE,
				DataBuffer.TYPE_BYTE);
	}
	
	private int createTextureID(){
		GL11.glGenTextures(textureIDBuffer);
		return textureIDBuffer.get(0);
	}
	
	public GTexture getTexture(String resourceName) throws IOException {
		GTexture tex = table.get(resourceName);
		if (tex != null){
			return tex;
		}
		tex = getTexture(resourceName,GL11.GL_TEXTURE_2D,GL11.GL_RGBA,GL11.GL_LINEAR,GL11.GL_LINEAR);
		table.put(resourceName, tex);
		return tex;
	}
	
	public GTexture getTexture(String resourceName, int target, int dstPixelFormat,int minFilter,int magFilter)throws IOException{
		int srcPixelFormat;
		int textureID = createTextureID();
		GTexture texture = new GTexture(target,textureID);
		GL11.glBindTexture(target, textureID);
		BufferedImage bufferedImage = loadImage(resourceName);
		texture.setWidth(bufferedImage.getWidth());
		texture.setHeight(bufferedImage.getHeight());
		if (bufferedImage.getColorModel().hasAlpha()) {
			srcPixelFormat = GL11.GL_RGBA;
		} else {
			srcPixelFormat = GL11.GL_RGB;
		}
		ByteBuffer textureBuffer = convertImageData(bufferedImage,texture);
		if (target == GL11.GL_TEXTURE_2D) {
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MIN_FILTER, minFilter);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MAG_FILTER, magFilter);
		}
		GL11.glTexImage2D(target,0,dstPixelFormat,get2Fold(bufferedImage.getWidth()),get2Fold(bufferedImage.getHeight()),0,srcPixelFormat,GL11.GL_UNSIGNED_BYTE,textureBuffer );
		return texture;	
	}
	
	private static int get2Fold(int fold){
		int ret = 2;
		while(ret < fold){
			ret *= 2;
		}
		return ret;
	}
	
	private ByteBuffer convertImageData(BufferedImage bufferedImage,GTexture texture) {
		ByteBuffer imageBuffer;
		WritableRaster raster;
		BufferedImage texImage;
		int texWidth = 2;
		int texHeight = 2;
		while (texWidth < bufferedImage.getWidth()) {

			texWidth *= 2;
		}
		while (texHeight < bufferedImage.getHeight()) {
			texHeight *= 2;
		}
		texture.setTextureHeight(texHeight);
		texture.setTextureWidth(texWidth);
		if (bufferedImage.getColorModel().hasAlpha()) {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,texWidth,texHeight,4,null);
			texImage = new BufferedImage(glAlphaColorModel,raster,false,new Hashtable());
		} else {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,texWidth,texHeight,3,null);
			texImage = new BufferedImage(glColorModel,raster,false,new Hashtable());
		}
		Graphics g = texImage.getGraphics();
		g.setColor(new Color(0f,0f,0f,0f));
		g.fillRect(0,0,texWidth,texHeight);
		g.fillRect(0,0,texWidth,texHeight);
		g.drawImage(bufferedImage,0,0,null);
		byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();
		imageBuffer = ByteBuffer.allocateDirect(data.length);
		imageBuffer.order(ByteOrder.nativeOrder());
		imageBuffer.put(data, 0, data.length);
		imageBuffer.flip();
		return imageBuffer;	
	}
	
	private BufferedImage loadImage(String ref) throws IOException {
		URL url = GTextureLoader.class.getClassLoader().getResource(ref);
		if (url == null) {
			throw new IOException("Cannot find: " + ref);
		}
		Image img = new ImageIcon(url).getImage();
		BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return bufferedImage;
	}
	
}
