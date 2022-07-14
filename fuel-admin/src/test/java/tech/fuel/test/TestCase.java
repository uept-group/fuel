package tech.fuel.test;

import java.util.List;

import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.junit.Test;

public class TestCase {

    @Test
    public void test() throws Exception {

        DefaultMQAdminExt admin = new DefaultMQAdminExt("test");
        admin.setNamesrvAddr("ip_form_host_file:9876,127.0.0.1:9876");
        admin.start();
        List<String> list = admin.getNameServerAddressList();
        String s = admin.getNamesrvAddr();
        System.out.println(list);
        System.out.println(s);

    }
}
