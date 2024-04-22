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
open class GenPagesHealthHealth : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_ux_button = resolveEasyComponent("ux-button", GenUniModulesUxFrameComponentsUxButtonUxButtonClass);
        return createElementVNode("view", utsMapOf("class" to "health"), utsArrayOf(
            createVNode(_component_ux_button, utsMapOf("type" to "info", "text" to "默认", "mr" to 10)),
            createElementVNode("image", utsMapOf("class" to "title fixed", "style" to normalizeStyle(utsMapOf("top" to "10%", "left" to "5%")), "src" to "/static/image/health/title.png", "mode" to "widthFix"), null, 4),
            createElementVNode("view", utsMapOf("class" to "link fixed", "style" to normalizeStyle(utsMapOf("top" to "10%", "right" to "-7%")), "onClick" to _ctx.clickOption), utsArrayOf(
                createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "460rpx")), "class" to "img", "src" to "/static/image/health/prescription.png", "mode" to "widthFix"), null, 4),
                createElementVNode("image", utsMapOf("class" to "img-btn", "src" to "/static/image/showWindow/unlocked_Pattern.png", "mode" to "widthFix")),
                createElementVNode("text", utsMapOf("class" to "text"), "名方")
            ), 12, utsArrayOf(
                "onClick"
            )),
            createElementVNode("view", utsMapOf("class" to "link fixed", "style" to normalizeStyle(utsMapOf("top" to "46%", "left" to "-4%")), "onClick" to _ctx.clickOption), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "img", "src" to "/static/image/health/eat.png", "mode" to "widthFix")),
                createElementVNode("image", utsMapOf("class" to "img-btn", "src" to "/static/image/showWindow/unlocked_Pattern.png", "mode" to "widthFix")),
                createElementVNode("text", utsMapOf("class" to "text"), "药膳")
            ), 12, utsArrayOf(
                "onClick"
            )),
            createElementVNode("view", utsMapOf("class" to "link fixed", "style" to normalizeStyle(utsMapOf("top" to "66%", "right" to "-4%")), "onClick" to _ctx.clickOption), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "img", "src" to "/static/image/health/tea.png", "mode" to "widthFix")),
                createElementVNode("text", utsMapOf("class" to "text"), "茶饮"),
                createElementVNode("image", utsMapOf("class" to "img-btn", "src" to "/static/image/showWindow/unlocked_Pattern.png", "mode" to "widthFix"))
            ), 12, utsArrayOf(
                "onClick"
            ))
        ));
    }
    override fun `$initMethods`() {
        this.clickOption = fun() {
            uni_navigateTo(NavigateToOptions(url = "/pages/healthDetail/healthDetail"));
        }
        ;
    }
    open lateinit var clickOption: () -> Unit;
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
                return utsMapOf("fixed" to padStyleMapOf(utsMapOf("position" to "fixed", "zIndex" to 9)), "health" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "#f2eee9")), "title" to utsMapOf(".health " to utsMapOf("width" to "250rpx")), "link" to utsMapOf(".health " to utsMapOf("display" to "flex", "flexDirection" to "column", "justifyContent" to "center", "alignItems" to "center")), "img" to utsMapOf(".health " to utsMapOf("width" to "400rpx")), "img-btn" to utsMapOf(".health " to utsMapOf("width" to "224rpx")), "text" to utsMapOf(".health " to utsMapOf("position" to "absolute", "bottom" to 0, "left" to "50%", "transform" to "translateX(-50%)", "color" to "#7a927a", "fontSize" to "48rpx")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
