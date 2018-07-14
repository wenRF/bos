package cn.itcast.bos.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.bos.dao.IUserDao;
import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.IFunctionService;

public class BosRealm extends AuthorizingRealm{

	@Resource
	private IUserDao userDao;
	@Resource
	private IFunctionService functionService;
	//授权方法
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalcollection) {
		
		//获取当前的登录对象
		User user=(User) principalcollection.getPrimaryPrincipal();
		List<Function>list=functionService.findFunctionByUser(user);
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		if(null!=list&&list.size()>0){
			for (Function funt : list) {
				sai.addStringPermission(funt.getCode());
			}
		}
		return sai;
	}

	//认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken) throws AuthenticationException {
		//根据用户名查出用户对象
		UsernamePasswordToken upt=(UsernamePasswordToken) authenticationtoken;
		String username = upt.getUsername();
		
		//离线查询
		
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("username", username));
		List<User> list = userDao.findByCriteria(dc);
		
		if(list!=null && list.size()>0){
			//2.如果查到用户，将该用户信息封装到AuthenticationInfo对象中，给安全管理器进行认证
			//2.1参数1：可以是任何类型的数据，该参数上的值可以在任何地址获取到，一般放用户对象
			//2.2参数2：需要进行认证的密码
			//2.3参数3：当前调用的Realm的类名称
			SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(list.get(0), list.get(0).getPassword(), this.getName());
			
			return sai;
		}
		//查不到直接返回null,安全管理器会认为该用户不存在，直接报异常
		return null;
	}

}
