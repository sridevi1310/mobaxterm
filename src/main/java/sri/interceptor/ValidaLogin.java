/*
 * interceptor class to verify sessions before
*/
package sri.interceptor;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class ValidaLogin implements Interceptor{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("Usuario")==null){  
            return "expired";   // if no session or expired; send to it
        }  
        else{
            return invocation.invoke(); // or ; just continue the stack
            
        }  
	}

}


