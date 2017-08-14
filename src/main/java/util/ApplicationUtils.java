package util;

public class ApplicationUtils {
        private ApplicationUtils() {

        }

        public static ApplicationUtils getInstance() {
            return new ApplicationUtils();
        }

        public String getRootPath() {
            // 因为类名为"Application"，因此" Application.class"一定能找到
            String result = ApplicationUtils.class.getResource("ApplicationUtils.class")
                    .toString();
            int index = result.indexOf("target");
            if (index == -1) {
                index = result.indexOf("bin");
            }
            result = result.substring(0, index);
            if (result.startsWith("jar")) {
                // 当class文件在jar文件中时，返回"jar:file:/F:/ ..."样的路径
                result = result.substring(10);
            } else if (result.startsWith("file")) {
                // 当class文件在jar文件中时，返回"file:/F:/ ..."样的路径
                result = result.substring(6);
            }
            return result;
        }

        public static void main(String[] arg0) {
            String rootPath = ApplicationUtils.getInstance().getRootPath() + "classes/";
            System.out.println(rootPath);
        }
    }
