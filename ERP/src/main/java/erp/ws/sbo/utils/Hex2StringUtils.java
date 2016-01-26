package erp.ws.sbo.utils;

import java.io.ByteArrayOutputStream;   
  
public class Hex2StringUtils {  
  
  
    /* 
     * 16���������ַ��� 
     */  
    private static String hexString = "0123456789ABCDEF";  
  
    /* 
     * ���ַ��������16��������,�����������ַ����������ģ� 
     */  
    public static String encode(String str) {  
        // ����Ĭ�ϱ����ȡ�ֽ�����  
        byte[] bytes = str.getBytes();  
        StringBuilder sb = new StringBuilder(bytes.length * 2);  
        // ���ֽ�������ÿ���ֽڲ���2λ16��������  
        for (int i = 0; i < bytes.length; i++) {  
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));  
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));  
        }  
        return sb.toString();  
    }  
  
    /* 
     * ��16�������ֽ�����ַ���,�����������ַ����������ģ� 
     */  
    public static String decode(String bytes) {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream(  
                bytes.length() / 2);  
        // ��ÿ2λ16����������װ��һ���ֽ�  
        for (int i = 0; i < bytes.length(); i += 2)  
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString  
                    .indexOf(bytes.charAt(i + 1))));  
        return new String(baos.toByteArray());  
    }  
  
    public static String byte2HexStr(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1)  
                hs = hs + "0" + stmp;  
            else  
                hs = hs + stmp;  
            // if (n<b.length-1) hs=hs+":";  
        }  
        return hs.toUpperCase();  
    }  
  
    public static String byteHexStr(byte b) {  
        String hs = "";  
        String stmp = "";  
        // /for (int n = 0; n < b.length; n++) {  
        stmp = (Integer.toHexString(b & 0XFF));  
        if (stmp.length() == 1)  
            hs = "0" + stmp;  
        else  
            hs = stmp;  
        // if (n<b.length-1) hs=hs+":";  
        // }  
        return hs.toUpperCase();  
    }  
  
    public static String byte2HexStrByi(byte[] b, int start, int end) {  
        String hs = "";  
        String stmp = "";  
        for (int n = start; n < end; n++) {  
            stmp = (Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1)  
                hs = hs + decode("0" + stmp);  
            else  
                hs = hs + decode(stmp);  
            // if (n<b.length-1) hs=hs+":";  
        }  
        return hs.toUpperCase();  
    }  
}  
