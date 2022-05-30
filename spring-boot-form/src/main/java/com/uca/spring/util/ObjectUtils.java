package com.uca.spring.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.security.*;
import java.math.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

public class ObjectUtils {

    private static String YEAR_PATTERN = "yyyy";

    private final static String DATE_PATTERN = "dd/MM/yyyy";



    public static String regexStringMatch(String search) {
        String word = "";
        if (search == null || word.equals("")) {
            word = search;
        } else {
            String[] words = search.split(" ");

            for (String word1 : words) {
                word = word + "+*" + word1 + "*";
            }
        }
        return word;

    }
    
    public static String getSubReportPath(ServletContext servletContext,String folder) {
        String path = servletContext.getRealPath("");
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        path = path + "resources" + File.separator + "reports" + File.separator;
        path = path + folder + File.separator;
        return path;
    }

    public static byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;

    }

    public static String getExtension(String nameFile) {
        Integer n = nameFile.lastIndexOf(".");
        Integer fileLength = nameFile.length();
        String extension = nameFile.substring(n + 1, fileLength);
        return extension;
    }

    public static String obtenerEdad(String fechaNacimiento) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento, fmt);
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fechaNac, ahora);
            return periodo.getYears() + " años, " + periodo.getMonths() + " meses y " + periodo.getDays() + "días";
        } catch (Exception e) {
            return "";
        }

    }

    public static FileDTO getFile(MultipartFile mpf) {
        FileDTO file = new FileDTO();
        if (mpf != null) {
            byte[] archivo;
            try {
                archivo = mpf.getBytes();
                String nameFile = mpf.getOriginalFilename().toString();
                Integer n = nameFile.lastIndexOf(".");
                Integer fileLength = nameFile.length();
                String extension = nameFile.substring(n + 1, fileLength);
                nameFile = nameFile.substring(0, n);

                file.setName(nameFile);
                file.setExtension(extension);
                file.setArchivo(archivo);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }

        }
        return file;

    }

    public static String getFormattedNumber(Number value, String pattern) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormat formatter = (DecimalFormat) format;
        formatter.applyPattern(pattern);
        return formatter.format(value);
    }

    public static Date getFormattedDate(String strdate) {
        Date c = null;
        if (strdate != null && !strdate.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT-6"));
            try {
                Date date = formatter.parse(strdate);
                c = date;
            } catch (ParseException e) {
                System.out.println("date not parse for string -->" + strdate);
            }
        }
        return c;
    }

    public static Date getFormattedDate2(String strdate) {
        Date c = null;
        if (strdate != null && !strdate.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = formatter.parse(strdate);
                c = date;
            } catch (ParseException e) {
                System.out.println("date not parse for string -->" + strdate);
            }
        }
        return c;
    }

    public static String ltrim(String s) {
        int i = 0;
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
            i++;
        }
        return s.substring(i);
    }

    public static String rtrim(String s) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            i--;
        }
        return s.substring(0, i + 1);
    }

    public static String lrtrim(String s) {
        return ltrim(rtrim(s));
    }

    public static String getFormattedDate(Date date) {
        return getFormattedDate(date, DATE_PATTERN);
    }

    public static String getFormattedDate(Calendar cal) {
        return getFormattedDate(new Date(cal.getTimeInMillis()), DATE_PATTERN);
    }

    public static String getFormattedDateYear(Date date) {
        return getFormattedDate(date, YEAR_PATTERN);
    }

    public static String getFormattedDate(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }

    }

    public static BigDecimal getFormattedBigDecimal(String text) {

        try {
            if (text != null && !text.equals("")) {
                BigDecimal bg;
                MathContext mc = new MathContext(2);
                bg = new BigDecimal(text, mc);
                return bg;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("BigDecimal not parse for string -->" + text);
            return null;
        }
    }

    public static Integer getFormattedInteger(String text) {
        try {
            if (text != null && !text.equals("")) {

                return Integer.valueOf(text);
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("BigDecimal not parse for string -->" + text);
            return null;
        }
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null || obj.toString().length() < 1
                || obj.toString().equals(StringConstants.EMPTY_STRING)) {
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmptyOrZero(Object obj) {
        if (obj == null
                || obj.toString().length() < 1
                || obj.toString().equals(StringConstants.EMPTY_STRING)
                || ((obj instanceof Long && obj.equals(0L))
                || (obj instanceof Integer && obj.equals(0))
                || (obj instanceof Float && obj.equals(0.00)) || (obj instanceof Double && obj
                .equals(0.00)))) {
            return true;
        }

        return false;
    }

    public static Object nvl(Object obj, Object val) {
        if (isNullOrEmpty(obj)) {
            return val;
        }
        return obj;
    }

    public static String emptyWhenNull(String str) {
        return (String) nvl(str, "N/A");
    }

    public static String maskedNit(String nit) {
        if (isNullOrEmpty(nit)) {
            return nit;
        }

        if (nit.length() != 14) {
            return nit;
        }
        String temp = nit.substring(0, 4) + "-" + nit.substring(4, 10) + "-" + nit.substring(10, 13) + "-" + nit.substring(13);
        return temp;
    }

    public static String md5(String url) {
        String s = "";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(url.getBytes(), 0, url.length());
            s = new BigInteger(1, m.digest()).toString(16);
            System.out.println("MD5: " + s);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ObjectUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public static Long getDateToLong(Date fecha) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MMM/yyyy");
        long milliseconds = 0;
        milliseconds = fecha.getTime();
        return milliseconds;
    }

    public static String getTypeOfMonth(String month) {
        String typeOfMonth;
        switch (month) {
            case "01":
                typeOfMonth = "ENERO";
                break;
            case "02":
                typeOfMonth = "FEBRERO";
                break;
            case "03":
                typeOfMonth = "MARZO";
                break;
            case "04":
                typeOfMonth = "ABRIL";
                break;
            case "05":
                typeOfMonth = "MAYO";
                break;
            case "06":
                typeOfMonth = "JUNIO";
                break;
            case "07":
                typeOfMonth = "JULIO";
                break;
            case "08":
                typeOfMonth = "AGOSTO";
                break;
            case "09":
                typeOfMonth = "SEPTIEMBRE";
                break;
            case "10":
                typeOfMonth = "OCTUBRE";
                break;
            case "11":
                typeOfMonth = "NOVIEMBRE";
                break;
            case "12":
                typeOfMonth = "DICIEMBRE";
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + month);
        }
        return typeOfMonth;
    }
    
    public static  String getCredentialsUser() {
        try {
            InetAddress inetAddress = InetAddress. getLocalHost();
            return  inetAddress. getHostAddress() + "  " + inetAddress. getHostName();
        } catch (UnknownHostException ex) {
            return ex.getMessage() + " ERROR AL OBTENER CREEDENCIALES";
        }
    
    }

}
