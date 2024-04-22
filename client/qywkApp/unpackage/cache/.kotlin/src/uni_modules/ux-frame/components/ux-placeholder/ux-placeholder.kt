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
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync;
open class GenUniModulesUxFrameComponentsUxPlaceholderUxPlaceholder : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return if (_ctx._height > 0) {
            createElementVNode("view", utsMapOf("key" to 0, "class" to "ux-placeholder", "style" to normalizeStyle(_ctx.style)), utsArrayOf(
                renderSlot(_ctx.`$slots`, "default")
            ), 4);
        } else {
            createCommentVNode("v-if", true);
        }
        ;
    }
    open var height: Number by `$props`;
    open var statusbar: Boolean by `$props`;
    open var navbar: Boolean by `$props`;
    open var tabbar: Boolean by `$props`;
    open var safearea: Boolean by `$props`;
    open var background: String by `$props`;
    open var statusBarHeight: Any? by `$data`;
    open var navbarHeight: Any? by `$data`;
    open var tabbarHeight: Any? by `$data`;
    open var safeAreaHeight: Any? by `$data`;
    open var _height: Number by `$data`;
    open var style: String by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("statusBarHeight" to uni_getSystemInfoSync().statusBarHeight, "navbarHeight" to (uni_getSystemInfoSync().statusBarHeight + 44), "tabbarHeight" to (uni_getSystemInfoSync().safeAreaInsets.bottom + 54), "safeAreaHeight" to uni_getSystemInfoSync().safeAreaInsets.bottom, "_height" to computed<Number>(fun(): Number {
            if (this.height > 0) {
                return this.height;
            }
            if (this.statusbar) {
                return this.statusBarHeight as Number;
            }
            if (this.navbar) {
                return this.navbarHeight as Number;
            }
            if (this.tabbar) {
                return this.tabbarHeight as Number;
            }
            if (this.safearea) {
                return this.safeAreaHeight as Number;
            }
            return 0;
        }
        ), "style" to computed<String>(fun(): String {
            return "height: " + this._height + "px;background-color: " + this.background + ";";
        }
        ));
    }
    companion object {
        var name = "ux-placeholder";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-placeholder" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 54)));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf("height" to utsMapOf("type" to "Number", "default" to 0), "statusbar" to utsMapOf("type" to "Boolean", "default" to false), "navbar" to utsMapOf("type" to "Boolean", "default" to false), "tabbar" to utsMapOf("type" to "Boolean", "default" to false), "safearea" to utsMapOf("type" to "Boolean", "default" to false), "background" to utsMapOf("type" to "String", "default" to "transparent")));
        var propsNeedCastKeys = utsArrayOf(
            "height",
            "statusbar",
            "navbar",
            "tabbar",
            "safearea",
            "background"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
