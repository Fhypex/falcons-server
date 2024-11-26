package gtu.cse.se.altefdirt.aymoose.shared.domain;

import java.util.List;

public class City {

    public static class District {
        private String name;

        // Constructors, Getters, Setters
        public District(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private String name;
    private List<District> districts;

    // Constructors, Getters, Setters
    public City(String name, List<District> districts) {
        this.name = name;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}