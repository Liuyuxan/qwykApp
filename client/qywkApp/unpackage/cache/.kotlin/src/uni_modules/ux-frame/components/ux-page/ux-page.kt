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
open class GenUniModulesUxFrameComponentsUxPageUxPage : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_ux_placeholder = resolveEasyComponent("ux-placeholder", GenUniModulesUxFrameComponentsUxPlaceholderUxPlaceholderClass);
        return createElementVNode("view", utsMapOf("class" to "ux-page", "style" to normalizeStyle(utsArrayOf(
            _ctx.style
        ))), utsArrayOf(
            if (_ctx.backgroundImage != "") {
                createElementVNode("image", utsMapOf("key" to 0, "class" to "ux-page__image", "src" to _ctx.backgroundImage, "mode" to "heightFit"), null, 8, utsArrayOf(
                    "src"
                ));
            } else {
                createCommentVNode("v-if", true);
            }
            ,
            createVNode(_component_ux_placeholder, utsMapOf("navbar" to _ctx.navbar), null, 8, utsArrayOf(
                "navbar"
            )),
            renderSlot(_ctx.`$slots`, "default"),
            createVNode(_component_ux_placeholder, utsMapOf("tabbar" to _ctx.tabbar), null, 8, utsArrayOf(
                "tabbar"
            ))
        ), 4);
    }
    open var navbar: Boolean by `$props`;
    open var tabbar: Boolean by `$props`;
    open var colors: UTSArray<Any?> by `$props`;
    open var backgroundImage: String by `$props`;
    open var backgroundClip: String by `$props`;
    open var style: Any? by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("style" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any>();
            if (this.backgroundImage != "") {
                if (this.backgroundClip != "") {
                    css.set("background-clip", this.backgroundClip);
                }
                return css;
            } else {
                var colors = utsArrayOf<String>();
                this.colors.forEach(fun(e: Any?){
                    colors.push(e!! as String);
                }
                );
                if (colors.length == 0) {
                    return "";
                }
                if (colors.length == 1) {
                    return "background-color:" + colors[0] + ";";
                }
                return "background: linear-gradient(to bottom, " + colors.join(",") + ");";
            }
        }
        ));
    }
    companion object {
        var name = "ux-page";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-page" to padStyleMapOf(utsMapOf("flex" to 1)), "ux-page__image" to utsMapOf(".ux-page " to utsMapOf("position" to "absolute", "top" to 0, "bottom" to 0, "right" to 0, "left" to 0)));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf("navbar" to utsMapOf("type" to "Boolean", "default" to false), "tabbar" to utsMapOf("type" to "Boolean", "default" to false), "colors" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<String> {
            return utsArrayOf<String>();
        }
        ), "backgroundImage" to utsMapOf("type" to "String", "default" to ""), "backgroundClip" to utsMapOf("type" to "String", "default" to "")));
        var propsNeedCastKeys = utsArrayOf(
            "navbar",
            "tabbar",
            "colors",
            "backgroundImage",
            "backgroundClip"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
