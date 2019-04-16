package mis.berritus.cloud.app.common.utils;

public class SonarValueUtil {

    public static String getMetric(String key) {
        switch (key) {
            case "alert_status":
                return "状态";
            case "bugs":
                return "Bugs";
            case "vulnerabilities":
                return "漏洞";
            case "code_smells":
                return "异味";
            case "coverage":
                return "覆盖率(%)";
            case "duplicated_lines_density":
                return "重复(%)";
            default:
                return "";
        }
    }


}
