package mis.berritus.cloud.service.sonar.constant;

public interface SonarConstant {
    String API_MEASURES_SEARCH = "/api/measures/search?projectKeys=${}" +
            "&metricKeys=alert_status,bugs,reliability_rating,vulnerabilities,security_rating,code_smells," +
            "sqale_rating,duplicated_lines_density,coverage,ncloc,ncloc_language_distribution";

    int DEFAULT_PAGES_NUM = 1;

    int DEFAULT_PAGE_SIZE= 500;
}
