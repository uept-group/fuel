package tech.uept.fuel.admin.domain;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.mapper.MemberMapper;
import tech.uept.fuel.admin.basic.mapper.po.MemberPo;
import tech.uept.fuel.admin.basic.model.MemberModel;
import tech.uept.fuel.admin.basic.util.BeanUtils;
import tech.uept.fuel.admin.basic.util.MD5;

@Service
public class MemeberService {

    @Resource
    private MemberMapper memberMapper;

    public void add(MemberModel model) {
        MemberPo po = BeanUtils.getCopy(model, new MemberPo());
        po.setPwd(MD5.MD5Encode(po.getPwd()));
        memberMapper.insert(po);
    }

    public void update(MemberModel model) {
        MemberPo po = BeanUtils.getCopy(model, new MemberPo());
        po.setPwd(MD5.MD5Encode(po.getPwd()));
        memberMapper.insert(po);
    }

    public void delete(int id) {
        memberMapper.deleteById(id);
    }
}
