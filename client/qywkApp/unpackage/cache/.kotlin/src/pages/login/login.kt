@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME")
package uni.UNIB7338A2;
import io.dcloud.uniapp.*;
import io.dcloud.uniapp.extapi.*;
import io.dcloud.uniapp.framework.*;
import io.dcloud.uniapp.runtime.*;
import io.dcloud.uniapp.vue.*;
import io.dcloud.uniapp.vue.shared.*;
import io.dcloud.unicloud.*;
import io.dcloud.uts.*;
import io.dcloud.uts.Map;
import io.dcloud.uts.Set;
import io.dcloud.uts.UTSAndroid;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.async;
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading;
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo;
import io.dcloud.uniapp.extapi.reLaunch as uni_reLaunch;
import io.dcloud.uniapp.extapi.request as uni_request;
import io.dcloud.uniapp.extapi.setStorageSync as uni_setStorageSync;
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading;
import io.dcloud.uniapp.extapi.showToast as uni_showToast;
open class GenPagesLoginLogin : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", utsMapOf("class" to "login"), utsArrayOf(
            createElementVNode("image", utsMapOf("class" to "bg-img", "src" to "/static/image/login/bg.png", "mode" to "widthFix")),
            createElementVNode("view", utsMapOf("class" to "window flex flex-column align-center"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "top flex align-center"), utsArrayOf(
                    createElementVNode("image", utsMapOf("src" to "/static/image/login/chacha.png", "mode" to "widthFix", "style" to normalizeStyle(utsMapOf("width" to "36rpx", "margin-right" to "10rpx"))), null, 4),
                    createElementVNode("text", utsMapOf("class" to "title"), "登录")
                )),
                createElementVNode("input", utsMapOf("modelValue" to _ctx.loginInfo.userId, "onInput" to fun(`$event`: InputEvent){
                    _ctx.loginInfo.userId = `$event`.detail.value;
                }
                , "class" to "uni-input space", "placeholder" to "账号/手机号"), null, 40, utsArrayOf(
                    "modelValue",
                    "onInput"
                )),
                createElementVNode("input", utsMapOf("modelValue" to _ctx.loginInfo.password, "onInput" to fun(`$event`: InputEvent){
                    _ctx.loginInfo.password = `$event`.detail.value;
                }
                , "class" to "uni-input space", "password" to "", "type" to "text", "placeholder" to "密码"), null, 40, utsArrayOf(
                    "modelValue",
                    "onInput"
                )),
                createElementVNode("button", utsMapOf("class" to "space", "hover-class" to "btn-hover", "onClick" to _ctx.clickLogin, "style" to normalizeStyle(utsMapOf("width" to "600rpx", "height" to "76rpx", "border-radius" to "32rpx", "color" to "#fff", "line-height" to "76rpx", "text-align" to "center", "background-color" to "#b19983"))), "登录", 12, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("class" to "sub space flex justify-between"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "forget"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "忘记密码")
                    )),
                    createElementVNode("view", utsMapOf("class" to "sign-up"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text", "onClick" to _ctx.register), "注册账号", 8, utsArrayOf(
                            "onClick"
                        ))
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "icons flex justify-around"), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "img-icon", "src" to "/static/image/login/QQ.png", "mode" to "widthFix")),
                    createElementVNode("image", utsMapOf("class" to "img-icon", "src" to "/static/image/login/weixin.png", "mode" to "widthFix")),
                    createElementVNode("image", utsMapOf("class" to "img-icon", "src" to "/static/image/login/weibo.png", "mode" to "widthFix"))
                ))
            ))
        ));
    }
    open var loginInfo: TloginInfo by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("loginInfo" to TloginInfo(userId = "", password = ""));
    }
    override fun `$initMethods`() {
        this.register = fun() {
            uni_navigateTo(NavigateToOptions(url = "/pages/validateCode/validateCode"));
        }
        ;
        this.clickLogin = fun() {
            if ((this.loginInfo.userId.length <= 0) || (this.loginInfo.password.length <= 0)) {
                return;
            }
            uni_showLoading(ShowLoadingOptions(title = "登录中"));
            console.log(this.loginInfo, " at pages/login/login.uvue:51");
            uni_request<IResponse<IToken>>(RequestOptions(url = BASE_URL + "/user/login/normal", method = "POST", data = let {
                object : UTSJSONObject() {
                    var userId = it.loginInfo.userId
                    var password = it.loginInfo.password
                }
            }, success = fun(res){
                var r = res.data;
                if (r == null) {
                    return;
                }
                console.log("登录返回信息", r, " at pages/login/login.uvue:63");
                if (r.code == 200) {
                    uni_setStorageSync("token", r.data!!.token);
                    uni_reLaunch(ReLaunchOptions(url = "/pages/tabbar/tabbar"));
                } else {
                    uni_showToast(ShowToastOptions(title = "账号或密码错误", icon = "none"));
                }
            }
            , fail = fun(err){
                console.log(err, " at pages/login/login.uvue:79");
                uni_showToast(ShowToastOptions(title = "账号或密码错误", icon = "none"));
            }
            , complete = fun(_){
                uni_hideLoading();
            }
            ));
        }
        ;
    }
    open lateinit var register: () -> Unit;
    open lateinit var clickLogin: () -> Unit;
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ), utsArrayOf(
                    GenApp.styles
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("login" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "#f2eee9")), "bg-img" to utsMapOf(".login " to utsMapOf("width" to "750rpx", "position" to "absolute", "top" to 0)), "window" to utsMapOf(".login " to utsMapOf("boxSizing" to "border-box", "width" to "650rpx", "height" to "766rpx", "paddingTop" to "30rpx", "paddingRight" to "30rpx", "paddingBottom" to "30rpx", "paddingLeft" to "30rpx", "marginTop" to "300rpx", "marginRight" to "auto", "marginBottom" to 0, "marginLeft" to "auto", "backgroundColor" to "#f5f5f5", "borderRadius" to "48rpx")), "space" to utsMapOf(".login .window " to utsMapOf("marginBottom" to "62rpx")), "top" to utsMapOf(".login .window " to utsMapOf("width" to "600rpx", "marginBottom" to "64rpx")), "title" to utsMapOf(".login .window .top " to utsMapOf("fontSize" to 16, "color" to "#937152")), "uni-input" to utsMapOf(".login .window " to utsMapOf("width" to "600rpx", "height" to "76rpx", "borderRadius" to "32rpx", "marginTop" to 0, "marginRight" to "auto", "marginBottom" to 0, "marginLeft" to "auto", "paddingLeft" to 10, "fontSize" to 12, "backgroundColor" to "#f2eee9", "fontSize::placeholder" to "28rpx", "marginLeft::placeholder" to "100rpx")), "btn-hover" to utsMapOf(".login .window " to utsMapOf("backgroundColor" to "#94806e")), "sub" to utsMapOf(".login .window " to utsMapOf("width" to "600rpx")), "text" to utsMapOf(".login .window .sub " to utsMapOf("fontSize" to 12, "color" to "#937152")), "icons" to utsMapOf(".login .window " to utsMapOf("width" to "500rpx", "height" to "56rpx")), "img-icon" to utsMapOf(".login .window .icons " to utsMapOf("width" to "56rpx", "height" to "56rpx")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
