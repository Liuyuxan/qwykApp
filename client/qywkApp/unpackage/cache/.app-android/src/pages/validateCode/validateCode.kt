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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo;
import io.dcloud.uniapp.extapi.request as uni_request;
import io.dcloud.uniapp.extapi.showToast as uni_showToast;
open class GenPagesValidateCodeValidateCode : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", utsMapOf("class" to "validateCode"), utsArrayOf(
            createElementVNode("image", utsMapOf("class" to "bg-img", "src" to "/static/image/login/bg.png", "mode" to "widthFix")),
            createElementVNode("view", utsMapOf("class" to "window flex flex-column align-center"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "top flex align-center"), utsArrayOf(
                    createElementVNode("image", utsMapOf("src" to "/static/image/login/chacha.png", "mode" to "widthFix", "style" to normalizeStyle(utsMapOf("width" to "36rpx", "margin-right" to "10rpx"))), null, 4),
                    createElementVNode("text", utsMapOf("class" to "title justify-start"), "注册")
                )),
                createElementVNode("input", utsMapOf("modelValue" to _ctx.numberPhone, "onInput" to fun(`$event`: InputEvent){
                    _ctx.numberPhone = `$event`.detail.value;
                }
                , "class" to "uni-input space", "placeholder" to "手机号"), null, 40, utsArrayOf(
                    "modelValue",
                    "onInput"
                )),
                createElementVNode("button", utsMapOf("class" to "space", "hover-class" to "btn-hover", "onClick" to _ctx.clickRegiter, "style" to normalizeStyle(utsMapOf("width" to "600rpx", "height" to "76rpx", "border-radius" to "32rpx", "color" to "#fff", "line-height" to "76rpx", "text-align" to "center", "background-color" to "#b19983"))), "发送验证码", 12, utsArrayOf(
                    "onClick"
                ))
            ))
        ));
    }
    open var numberPhone: String by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("numberPhone" to "" as String);
    }
    override fun `$initMethods`() {
        this.clickRegiter = fun() {
            if (this.numberPhone.length <= 0) {
                return;
            }
            if (this.numberPhone.length != 11) {
                return;
            }
            console.log(this.numberPhone, " at pages/validateCode/validateCode.uvue:32");
            uni_request<IResponse<ICode>>(RequestOptions(url = BASE_URL + ("/user/login/sent/code?tel=" + this.numberPhone), method = "POST", success = fun(res){
                var r = res.data;
                console.log("验证码返回信息", r, " at pages/validateCode/validateCode.uvue:39");
                if (r!!.code == 200) {
                    var code = r!!.data!!.code;
                    console.log(code, " at pages/validateCode/validateCode.uvue:42");
                    uni_showToast(ShowToastOptions(title = "获取验证码成功，已帮您自动填写", icon = "none"));
                    uni_navigateTo(NavigateToOptions(url = "/pages/register/register?code=" + code + "&tel=" + this.numberPhone));
                } else {
                    uni_showToast(ShowToastOptions(title = "网络超时", icon = "none"));
                }
            }
            , fail = fun(err){
                console.log(err, " at pages/validateCode/validateCode.uvue:59");
                uni_showToast(ShowToastOptions(title = err.errMsg, icon = "none"));
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
                return utsMapOf("validateCode" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "#f2eee9")), "bg-img" to utsMapOf(".validateCode " to utsMapOf("width" to "750rpx", "position" to "absolute", "top" to 0)), "window" to utsMapOf(".validateCode " to utsMapOf("boxSizing" to "border-box", "width" to "650rpx", "height" to "466rpx", "paddingTop" to "30rpx", "paddingRight" to "30rpx", "paddingBottom" to "30rpx", "paddingLeft" to "30rpx", "marginTop" to "300rpx", "marginRight" to "auto", "marginBottom" to 0, "marginLeft" to "auto", "backgroundColor" to "#f5f5f5", "borderRadius" to "48rpx")), "space" to utsMapOf(".validateCode .window " to utsMapOf("marginBottom" to "62rpx")), "top" to utsMapOf(".validateCode .window " to utsMapOf("width" to "600rpx", "marginBottom" to "64rpx")), "title" to utsMapOf(".validateCode .window .top " to utsMapOf("fontSize" to 16, "color" to "#937152")), "uni-input" to utsMapOf(".validateCode .window " to utsMapOf("width" to "600rpx", "height" to "76rpx", "borderRadius" to "32rpx", "marginTop" to 0, "marginRight" to "auto", "marginBottom" to 0, "marginLeft" to "auto", "paddingLeft" to 10, "fontSize" to 12, "backgroundColor" to "#f2eee9", "fontSize::placeholder" to "28rpx", "marginLeft::placeholder" to "100rpx")), "btn-hover" to utsMapOf(".validateCode .window " to utsMapOf("backgroundColor" to "#94806e")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
