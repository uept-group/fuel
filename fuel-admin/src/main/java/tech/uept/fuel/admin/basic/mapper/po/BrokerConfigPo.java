package tech.uept.fuel.admin.basic.mapper.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("broker_config")
public class BrokerConfigPo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("`key`")
    private String key;

    @TableField("`default_value`")
    private String defaultValue;

    private String note;

    @TableField("sort_no")
    private Integer sortNo;

    @TableField("hot_update")
    private String hotUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getHotUpdate() {
        return hotUpdate;
    }

    public void setHotUpdate(String hotUpdate) {
        this.hotUpdate = hotUpdate;
    }

}
