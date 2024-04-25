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
import io.dcloud.uniapp.extapi.request as uni_request;
import io.dcloud.uniapp.extapi.setStorageSync as uni_setStorageSync;
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading;
import io.dcloud.uniapp.extapi.showToast as uni_showToast;
open class GenPagesRegisterRegister : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onLoad(fun(options: OnLoadOptions) {
            console.log(options, " at pages/register/register.uvue:86");
            this.registerLoginInfo.tel = options["tel"] ?: "";
            this.registerLoginInfo.code = options["code"] ?: "";
            console.log(this.registerLoginInfo, " at pages/register/register.uvue:89");
        }
        , instance);
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", utsMapOf("class" to "validateCode"), utsArrayOf(
            createElementVNode("image", utsMapOf("class" to "bg-img", "src" to "/static/image/bg/bg_6.png", "mode" to "widthFix")),
            createElementVNode("view", utsMapOf("class" to "window flex flex-column align-center"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "top flex align-center"), utsArrayOf(
                    createElementVNode("image", utsMapOf("src" to "/static/image/login/chacha.png", "mode" to "widthFix", "style" to normalizeStyle(utsMapOf("width" to "36rpx", "margin-right" to "10rpx"))), null, 4),
                    createElementVNode("text", utsMapOf("class" to "title"), "注册并登录")
                )),
                createElementVNode("input", utsMapOf("modelValue" to _ctx.registerLoginInfo.password, "onInput" to fun(`$event`: InputEvent){
                    _ctx.registerLoginInfo.password = `$event`.detail.value;
                }
                , "class" to "uni-input space", "placeholder" to "密码:必须包含英文,数字"), null, 40, utsArrayOf(
                    "modelValue",
                    "onInput"
                )),
                createElementVNode("input", utsMapOf("modelValue" to _ctx.registerLoginInfo.code, "onInput" to fun(`$event`: InputEvent){
                    _ctx.registerLoginInfo.code = `$event`.detail.value;
                }
                , "class" to "uni-input space", "placeholder" to "验证码"), null, 40, utsArrayOf(
                    "modelValue",
                    "onInput"
                )),
                createElementVNode("button", utsMapOf("class" to "space btn", "hover-class" to "btn-is-hover", "onClick" to _ctx.clickRegiter), "注册并登录", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ));
    }
    open var registerLoginInfo: registerLoginInfoType by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("registerLoginInfo" to registerLoginInfoType(tel = "", password = "", code = ""));
    }
    override fun `$initMethods`() {
        this.clickRegiter = fun() {
            if (this.registerLoginInfo.tel.length <= 0) {
                return;
            }
            console.log(this.registerLoginInfo, " at pages/register/register.uvue:41");
            uni_showLoading(ShowLoadingOptions(title = "登陆中"));
            uni_request<IResponse<IToken>>(RequestOptions(url = BASE_URL + "/user/login/register", method = "POST", data = let {
                object : UTSJSONObject() {
                    var tel = it.registerLoginInfo.tel
                    var password = it.registerLoginInfo.password
                    var code = it.registerLoginInfo.code
                }
            }, success = fun(res){
                var r = res.data;
                if (r == null) {
                    return;
                }
                console.log("注册登录返回信息", r, " at pages/register/register.uvue:56");
                if (r!!.code == 200) {
                    console.log(r, " at pages/register/register.uvue:58");
                    uni_setStorageSync("token", r!!.data!!.token);
                    uni_navigateTo(NavigateToOptions(url = "/pages/healthQuestion/healthQuestion"));
                } else {
                    uni_showToast(ShowToastOptions(title = r.message, icon = "none"));
                }
            }
            , fail = fun(err){
                console.log(err, " at pages/register/register.uvue:72");
                uni_showToast(ShowToastOptions(title = "网络超时", icon = "none"));
            }
            , complete = fun(_){
                uni_hideLoading();
            }
            ));
        }
        ;
    }
    open lateinit var clickRegiter: () -> Unit;
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
                return utsMapOf("validateCode" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "#f2eee9")), "bg-img" to utsMapOf(".validateCode " to utsMapOf("width" to "650rpx", "position" to "absolute", "left" to "50%", "transform" to "translateX(-50%)", "bottom" to "-12%")), "window" to utsMapOf(".validateCode " to utsMapOf("boxSizing" to "border-box", "width" to "650rpx", "height" to "566rpx", "paddingTop" to "30rpx", "paddingRight" to "30rpx", "paddingBottom" to "30rpx", "paddingLeft" to "30rpx", "marginTop" to "300rpx", "marginRight" to "auto", "marginBottom" to 0, "marginLeft" to "auto", "backgroundColor" to "rgba(255,255,255,0.6)", "borderRadius" to "48rpx")), "space" to utsMapOf(".validateCode .window " to utsMapOf("marginBottom" to "62rpx")), "top" to utsMapOf(".validateCode .window " to utsMapOf("width" to "600rpx", "marginBottom" to "64rpx")), "title" to utsMapOf(".validateCode .window .top " to utsMapOf("fontSize" to 16, "color" to "#937152")), "uni-input" to utsMapOf(".validateCode .window " to utsMapOf("width" to "600rpx", "height" to "76rpx", "borderRadius" to "32rpx", "marginTop" to 0, "marginRight" to "auto", "marginBottom" to 0, "marginLeft" to "auto", "paddingLeft" to 10, "fontSize" to 12, "backgroundColor" to "#f2eee9", "fontSize::placeholder" to "28rpx", "marginLeft::placeholder" to "100rpx")), "btn" to padStyleMapOf(utsMapOf("width" to "600rpx", "height" to "76rpx", "borderRadius" to "32rpx", "color" to "#ffffff", "lineHeight" to "76rpx", "backgroundColor" to "#b19983")), "btn-is-hover" to padStyleMapOf(utsMapOf("backgroundColor" to "#94806e")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
