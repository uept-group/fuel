package tech.uept.fuel.admin.basic.util;

import java.util.List;
import org.springframework.util.CollectionUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import tech.uept.fuel.admin.basic.model.MyPage;

public class PageUtils {

    public static MyPage to(IPage<?> ipage) {
        MyPage myPage = new MyPage();
        myPage.setTotal((int) ipage.getPages());
        myPage.setList(ipage.getRecords());
        return myPage;
    }

    public static MyPage listToPage(List<?> list, int pageNo, int pageSize) {
        MyPage myPage = new MyPage();
        if (CollectionUtils.isEmpty(list)) {
            return myPage;
        }
        int start = (pageNo - 1) * pageSize;
        int end = pageNo * pageSize;
        if (list.size() < end) {
            end=list.size();
        }
        myPage.setTotal(list.size());
        myPage.setList(list.subList(start, end));
        return myPage;
    }
}
