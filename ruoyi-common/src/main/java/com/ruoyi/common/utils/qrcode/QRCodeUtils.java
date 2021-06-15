package com.ruoyi.common.utils.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ruoyi.common.exception.QRCodeException;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 二维码生成工具
 *
 * @author Zhenxi Chen
 * @date 2021/6/15
 */
public class QRCodeUtils {

    /**
     * 生成二维码方法
     * @param content 用于生成的数据
     * @param width 二维码的宽度
     * @param height 二维码的高度
     * @return
     */
    public static byte[] createQRCode(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", stream);
        } catch (WriterException e) {
            throw new QRCodeException();
        } catch (IOException e) {
            throw new QRCodeException();
        }
        return stream.toByteArray();
    }
}
