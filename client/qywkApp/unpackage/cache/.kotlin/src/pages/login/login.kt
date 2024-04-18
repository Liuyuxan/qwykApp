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
import io.dcloud.uniapp.extapi.reLaunch as uni_reLaunch;
import io.dcloud.uniapp.extapi.request as uni_request;
import io.dcloud.uniapp.extapi.setStorageSync as uni_setStorageSync;
import io.dcloud.uniapp.extapi.showToast as uni_showToast;
open class GenPagesLoginLogin : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): VNode? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_button = resolveComponent("button");
        return createElementVNode("view", utsMapOf("class" to "login"), utsArrayOf(
            createElementVNode("image", utsMapOf("class" to "bg-img", "src" to "/static/image/login/bg.png", "mode" to "widthFix")),
            createElementVNode("view", utsMapOf("class" to "window flex flex-column align-center"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "top flex align-center"), utsArrayOf(
                    createElementVNode("image", utsMapOf("src" to "/static/image/login/chacha.png", "mode" to "widthFix", "style" to "width: 36rpx;margin-right: 10rpx;")),
                    createElementVNode("text", utsMapOf("class" to "title justify-start"), "登录")
                )),
                createElementVNode("input", utsMapOf("modelValue" to _ctx.loginInfo.username, "onInput" to fun(`$event`: InputEvent): Any {
                    _ctx.loginInfo.username = `$event`.detail.value;
                    return `$event`.detail.value;
                }
                , "class" to "uni-input space", "placeholder" to "账号/手机号"), null, 40, utsArrayOf(
                    "modelValue",
                    "onInput"
                )),
                createElementVNode("input", utsMapOf("modelValue" to _ctx.loginInfo.password, "onInput" to fun(`$event`: InputEvent): Any {
                    _ctx.loginInfo.password = `$event`.detail.value;
                    return `$event`.detail.value;
                }
                , "class" to "uni-input space", "password" to "", "type" to "text", "placeholder" to "密码"), null, 40, utsArrayOf(
                    "modelValue",
                    "onInput"
                )),
                createVNode(_component_button, utsMapOf("class" to "space", "hover-class" to "btn-hover", "onClick" to _ctx.clickLogin, "style" to "width: 600rpx;height: 76rpx;border-radius: 32rpx;color: #fff;line-height: 76rpx;text-align: center;background-color: #b19983;"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        "登录"
                    );
                }
                ), "_" to 1), 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("class" to "sub space flex justify-between"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "forget"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "忘记密码")
                    )),
                    createElementVNode("view", utsMapOf("class" to "sign-up"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "手机注册")
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
        return utsMapOf("loginInfo" to TloginInfo(username = "", password = ""));
    }
    override fun `$initMethods`() {
        this.clickLogin = fun() {
            if ((this.loginInfo.username.length <= 0) || (this.loginInfo.password.length <= 0)) {
                return;
            }
            console.log(this.loginInfo, " at pages/login/login.uvue:47");
            uni_request<IResponse<IToken>>(RequestOptions(url = BASE_URL + "/qywk/login/normal", method = "POST", data = let {
                object : UTSJSONObject() {
                    var username = it.loginInfo.username
                    var password = it.loginInfo.password
                }
            }, dataType = "json", success = fun(res){
                var r = res.data;
                console.log("登录返回信息", r, " at pages/login/login.uvue:59");
                if (r!!.code == 200) {
                    uni_setStorageSync("token", r!!.data!!.token);
                    uni_reLaunch(ReLaunchOptions(url = "/pages/index/index"));
                } else {
                    uni_showToast(ShowToastOptions(title = "账号或密码错误", icon = "none"));
                }
            }
            , fail = fun(err){
                console.log(err, " at pages/login/login.uvue:75");
                uni_showToast(ShowToastOptions(title = "账号或密码错误", icon = "none"));
            }
            ));
        }
        ;
    }
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
                return utsMapOf("login" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "#f2eee9")), "bg-img" to utsMapOf(".login " to utsMapOf("width" to "750rpx", "position" to "absolute", "top" to 0)), "window" to utsMapOf(".login " to utsMapOf("boxSizing" to "border-box", "width" to "650rpx", "height" to "766rpx", "padding" to "30rpx", "margin" to "300rpx auto 0 auto", "backgroundColor" to "#f5f5f5", "borderRadius" to "48rpx")), "space" to utsMapOf(".login .window " to utsMapOf("marginBottom" to "62rpx")), "top" to utsMapOf(".login .window " to utsMapOf("width" to "600rpx", "marginBottom" to "64rpx")), "title" to utsMapOf(".login .window .top " to utsMapOf("fontSize" to 16, "color" to "#937152")), "uni-input" to utsMapOf(".login .window " to utsMapOf("width" to "600rpx", "height" to "76rpx", "borderRadius" to "32rpx", "margin" to "0 auto", "paddingLeft" to 10, "fontSize" to 12, "backgroundColor" to "#f2eee9", "fontSize::placeholder" to "28rpx", "marginLeft::placeholder" to "100rpx")), "btn-hover" to utsMapOf(".login .window " to utsMapOf("backgroundColor" to "#94806e")), "sub" to utsMapOf(".login .window " to utsMapOf("width" to "600rpx")), "text" to utsMapOf(".login .window .sub " to utsMapOf("fontSize" to 12, "color" to "#937152")), "icons" to utsMapOf(".login .window " to utsMapOf("width" to "500rpx", "height" to "56rpx")), "img-icon" to utsMapOf(".login .window .icons " to utsMapOf("width" to "56rpx", "height" to "56rpx")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
