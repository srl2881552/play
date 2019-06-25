<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include  file="/WEB-INF/views/common/head.jsp"%>
<!-- registration -->
<script type="text/javascript">
function doLogin()
{
	var login=$("#login").val();
	var password=$("#password").val();
	
	if(login==null||login=='')
	{
		alert("请输入您的手机号");
		return;
	}
	if(password==null||password=='')
	{
		alert("请输入您的密码");
		return;
	}
	
	$.ajax({  
			type:'post',      
			url:'<%=request.getContextPath()%>/user/doLogin',  
			data:'login='+login+'&password='+password,  
			cache:false,  
			dataType:'json',  
			success:function(data){
				if(data.code!=0)
				{
					alert(data.msg);
				}else{
					window.location.href='<%=request.getContextPath()%>/';
				}
			}  
			});
	
}
</script>

            <section class="registration">
                <div class="row secBg">
                    <div class="large-12 columns">
                        <div class="login-register-content">
                            <div class="row collapse borderBottom">
                                <div class="medium-6 large-centered medium-centered">
                                    <div class="page-heading text-center">
                                        <h3>用户登录</h3>
                                        <p></p>
                                    </div>
                                </div>
                            </div>
                            <div class="row" data-equalizer data-equalize-on="medium" id="test-eq">
                                <div class="large-4 large-offset-1 medium-6 columns">
                                    <div class="social-login" data-equalizer-watch>
                                        <h5 class="text-center">Login via Social Profile</h5>
                                        <div class="social-login-btn facebook">
                                            <a href="#"><i class="fa fa-facebook"></i>login via facebook</a>
                                        </div>
                                        <div class="social-login-btn twitter">
                                            <a href="#"><i class="fa fa-twitter"></i>login via twitter</a>
                                        </div>
                                        <div class="social-login-btn g-plus">
                                            <a href="#"><i class="fa fa-google-plus"></i>login via google plus</a>
                                        </div>
                                        <div class="social-login-btn linkedin">
                                            <a href="#"><i class="fa fa-linkedin"></i>login via linkedin</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="large-2 medium-2 columns show-for-large">
                                    <div class="middle-text text-center hide-for-small-only" data-equalizer-watch>
                                        <p>
                                            <i class="fa fa-arrow-left arrow-left"></i>
                                            <span>OR</span>
                                            <i class="fa fa-arrow-right arrow-right"></i>
                                        </p>
                                    </div>
                                </div>
                                
                                <div class="large-4 medium-6 columns end">
                                    <div class="register-form">
                                        <h5 class="text-center">创建您的请求</h5>
                                        <form method="post">
                                            <div data-abide-error class="alert callout" style="display: none;">
                                                <p><i class="fa fa-exclamation-triangle"></i> There are some errors in your form.</p>
                                            </div>
                                            <div class="input-group">
                                                <span class="input-group-label"><i class="fa fa-user"></i></span>
                                                <input class="input-group-field" name="login" id="login" type="text" placeholder="输入用户名/(手机号)" required>
                                                <span class="form-error">username is required</span>
                                            </div>

                                            <div class="input-group">
                                                <span class="input-group-label"><i class="fa fa-lock"></i></span>
                                                <input type="password" id="password" name="password" placeholder="输入登录密码" required>
                                                <span class="form-error">password is required</span>
                                            </div>
                                            
                                            <button class="button expanded" type="button" name="submit" onclick="doLogin();">登录</button>
                                            <p class="loginclick"><!-- <a href="login-forgot-pass.html">Forgot Password</a>--><a href="<%=request.getContextPath() %>/user/register.html">注册新用户</a></p>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
<%@ include  file="/WEB-INF/views/common/foot.jsp"%>