package cn.itcast.bos.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.utils.BosUtils;

public class BosInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invoke) throws Exception {
		//判断当前用户是否登录
		User user = BosUtils.loginUser();
		if(null == user){
			//未登录，跳转到登录界面
			return "login";
		}
		//已登录，放行
		return invoke.invoke();
	}

}
