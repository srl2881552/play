<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include  file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript">
function doRest()
{
	var mobile=$("#mobile").val();
	var email=$("#email").val();
	var pass=$("#pass").val();
	var nick=$("#nick").val();
	if(mobile==null||mobile=='')
	{
		alert("请输入您的手机号");
		return;
	}
	if(email==null||email=='')
	{
		alert("请输入您的邮箱");
		return;
	}
	if(pass==null||pass=='')
	{
		alert("请输入您的登录密钥");
		return;
	}
	if(nick==null||nick=='')
	{
		alert("请输入您的昵称");
		return;
	}
	$.ajax({  
			type:'post',      
			url:'<%=request.getContextPath()%>/user/doRegister',  
			data:'mobile='+mobile+'&email='+email+'&password='+pass+'&nickName='+nick,  
			cache:false,  
			dataType:'json',  
			success:function(data){
				if(data.code!=0)
				{
					$("#errMsg").text(data.msg);
				}else{
					window.location.href='<%=request.getContextPath()%>/';
				}
			}  
			});
	
}
</script>
 <!-- registration -->
            <section class="registration">
                <div class="row secBg">
                    <div class="large-12 columns">
                        <div class="login-register-content">
                            <div class="row collapse borderBottom">
                                <div class="medium-6 large-centered medium-centered">
                                    <div class="page-heading text-center">
                                        <h3>注册用户</h3>
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
                                                <p id="errMsg"><i class="fa fa-exclamation-triangle"></i> There are some errors in your form.</p>
                                            </div>
                                            <div class="input-group">
                                                <span class="input-group-label"><i class="fa fa-user"></i></span>
                                                <input class="input-group-field" type="text" placeholder="输入您的手机号" required id="mobile" name="mobile">
                                            </div>

                                            <div class="input-group">
                                                <span class="input-group-label"><i class="fa fa-envelope"></i></span>
                                                <input class="input-group-field" type="email" placeholder="输入您的邮箱" required id="email" name="email">
                                            </div>

                                            <div class="input-group">
                                                <span class="input-group-label"><i class="fa fa-lock"></i></span>
                                                <input type="password" placeholder="输入您的登录密钥" required id="pass" name="pass">
                                            </div>
                                            <div class="input-group">
                                                <span class="input-group-label"><i class="fa fa-user"></i></span>
                                                <input type="text" placeholder="输入您的昵称" required id="nick" name="nick">
                                            </div>
                                            <span class="form-error">your email is invalid</span>
                                            <button class="button expanded" type="submit" name="submit" onclick="doRest();">注册</button>
                                            <p class="loginclick"> <a href="<%=request.getContextPath() %>/user/login.html">登录</a><!-- <a href="login.html">Already have acoount?</a> --></p>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
<%@ include  file="/WEB-INF/views/common/foot.jsp"%>