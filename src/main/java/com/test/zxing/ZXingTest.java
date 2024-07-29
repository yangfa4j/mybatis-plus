package com.test.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.curator.shaded.com.google.common.base.Stopwatch;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ZXingTest {

    private static int width = 200;
    private static int height = 200;

    private static int margin = 0;   //白边大小，取值范围0~4
    private static ErrorCorrectionLevel level = ErrorCorrectionLevel.H;  //二维码容错率

    public static void main(String[] args) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String content = "https://payment2.m.1688.com/page/batchPaymentCashier.html?cashierOrderNo=3D04EB5820254EB3E52197427EB75749";
        BufferedImage encode = ZXingTest.encode(content, width, height);
        ImageIO.write(encode, "png", new File("/Users/yangfa/Desktop/qrcode.png"));
        stopwatch.stop();
        System.out.println("执行时间: " + stopwatch.elapsed(TimeUnit.SECONDS) + " 秒");
    }

    public static BufferedImage encode(String content, int width, int height) throws WriterException, IOException {
        Map<EncodeHintType, Object> encodeHints = new HashMap<EncodeHintType, Object>();
        encodeHints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        encodeHints.put(EncodeHintType.MARGIN, margin);
        encodeHints.put(EncodeHintType.ERROR_CORRECTION, level);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, encodeHints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

}

