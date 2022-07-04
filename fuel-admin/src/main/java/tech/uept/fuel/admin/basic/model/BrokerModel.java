package tech.uept.fuel.admin.basic.model;

public class BrokerModel {

    // 名称
    private String name;

    // 编号，broker编号，如果是0则是主节点，如果是1，2，3，4则都是从节点。一般情况下，都是一个0，一个1，也就是1主1从
    private long no;

    private String address;

    private int namesrvId;

    private String namesrvCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNamesrvId() {
        return namesrvId;
    }

    public void setNamesrvId(int namesrvId) {
        this.namesrvId = namesrvId;
    }

    public String getNamesrvCode() {
        return namesrvCode;
    }

    public void setNamesrvCode(String namesrvCode) {
        this.namesrvCode = namesrvCode;
    }

}
