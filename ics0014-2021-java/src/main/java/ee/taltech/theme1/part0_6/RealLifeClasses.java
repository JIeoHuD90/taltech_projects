package ee.taltech.theme1.part0_6;

import java.time.LocalDateTime;

public class RealLifeClasses {

    public static void main(String[] args) {
        LocalDateTime latest = DateUtil.getLatest(date1(), date2());

        Dashboard dashboard = new Dashboard();
        dashboard.setCode("code");
        dashboard.setNameEn("eng name");
        dashboard.setNameEt("et name");
        dashboard.setCreatedAt(latest);

        //and then it might be saved
        //dashboardService.save(dashboard);
    }

    private static LocalDateTime date1() {
        return LocalDateTime.now();
    }

    private static LocalDateTime date2() {
        return LocalDateTime.now().minusHours(2);
    }
}
