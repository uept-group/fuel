package tech.uept.fuel.admin.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import tech.uept.fuel.admin.api.config.Response;
import tech.uept.fuel.admin.basic.model.LoginUserModel;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class LoginAction {


	@PostMapping(value = "/login")
	public Response login(@RequestBody LoginUserModel loginUserModel) {
		String username = loginUserModel.getUsername();
		String password = loginUserModel.getPassword();
		Response r = new Response();
		JSONObject o = new JSONObject();
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			o.put("token", UUID.randomUUID().toString());
			r.setData(o);
			r.setCode(Response.success);
		} else {
			r.setCode(Response.systemError);
		}
		return r;
	}

}
