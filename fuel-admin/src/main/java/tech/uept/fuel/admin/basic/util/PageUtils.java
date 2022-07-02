package tech.uept.fuel.admin.basic.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

import tech.uept.fuel.admin.basic.model.MyPage;

public class PageUtils {

    public static MyPage to(IPage<?> ipage) {
        MyPage myPage = new MyPage();
        myPage.setTotal((int) ipage.getPages());
        myPage.setList(ipage.getRecords());
        return myPage;
    }
}
