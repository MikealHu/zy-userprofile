package cn.zy.userprofile.common.utils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/23 17:51
 */
public class StringUtils {

//    public static void parseInt(String str) {
//
//        String lower = str.toLowerCase(Locale.ROOT).trim();
//
//        try {
//            Matcher m = Pattern.compile("([0-9]+)([a-z]+)?").matcher(lower);
//            Matcher fractionMatcher = Pattern.compile("([0-9]+\\.[0-9]+)([a-z]+)?").matcher(lower);
//
//            if (m.matches()) {
//                long val = Long.parseLong(m.group(1));
//                String suffix = m.group(2);
//
//                // Check for invalid suffixes
//                if (suffix != null && !byteSuffixes.containsKey(suffix)) {
//                    throw new NumberFormatException("Invalid suffix: \"" + suffix + "\"");
//                }
//
//                // If suffix is valid use that, otherwise none was provided and use the default passed
//                return unit.convertFrom(val, suffix != null ? byteSuffixes.get(suffix) : unit);
//            } else if (fractionMatcher.matches()) {
//                throw new NumberFormatException("Fractional values are not supported. Input was: "
//                        + fractionMatcher.group(1));
//            } else {
//                throw new NumberFormatException("Failed to parse byte string: " + str);
//            }
//
//        } catch (NumberFormatException e) {
//            String byteError = "Size must be specified as bytes (b), " +
//                    "kibibytes (k), mebibytes (m), gibibytes (g), tebibytes (t), or pebibytes(p). " +
//                    "E.g. 50b, 100k, or 250m.";
//
//            throw new NumberFormatException(byteError + "\n" + e.getMessage());
//        }
//    }


    public static void main(String[] args) {
        String lower = "123h".toLowerCase(Locale.ROOT).trim();

        Matcher m = Pattern.compile("([0-9]+)([a-z]+)?").matcher(lower);
        Matcher fractionMatcher = Pattern.compile("([0-9]+\\.[0-9]+)([a-z]+)?").matcher(lower);

        System.out.println(m.matches());
        System.out.println(fractionMatcher.matches());
    }
}
