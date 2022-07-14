package tech.uept.fuel.admin.basic.model;

public class BrokerConfigModel {

    private String key;

    private String value;

    private String defaultValue;

    private String note;

    private String hotUpdate;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getHotUpdate() {
        return hotUpdate;
    }

    public void setHotUpdate(String hotUpdate) {
        this.hotUpdate = hotUpdate;
    }

}
