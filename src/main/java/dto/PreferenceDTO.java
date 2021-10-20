package dto;

public class PreferenceDTO {
    private String preferenceId;
    private String initPoint;

    public PreferenceDTO(String preferenceId, String initPoint) {
        this.preferenceId = preferenceId;
        this.initPoint = initPoint;
    }

    public PreferenceDTO() {
    }

    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getInitPoint() {
        return initPoint;
    }

    public void setInitPoint(String initPoint) {
        this.initPoint = initPoint;
    }


    @Override
    public String toString() {
        return "PreferenceDTO{" +
                "preferenceId='" + preferenceId + '\'' +
                ", initPoint='" + initPoint + '\'' +
                '}';
    }
}
