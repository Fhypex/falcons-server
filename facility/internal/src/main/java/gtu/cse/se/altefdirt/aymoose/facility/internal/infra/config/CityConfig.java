package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "cities")
public class CityConfig {

    private List<CityConfigItem> cities;

    public List<CityConfigItem> getCities() {
        return cities;
    }

    public void setCities(List<CityConfigItem> cities) {
        this.cities = cities;
    }

    public static class CityConfigItem {
        private String name;
        private List<DistrictConfigItem> districts;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DistrictConfigItem> getDistricts() {
            return districts;
        }

        public void setDistricts(List<DistrictConfigItem> districts) {
            this.districts = districts;
        }

        public static class DistrictConfigItem {
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
