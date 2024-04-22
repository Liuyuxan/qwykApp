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
import io.dcloud.uniapp.extapi.loadFontFace as uni_loadFontFace;
open class GenUniModulesUxFrameComponentsUxIconUxIcon : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onMounted(fun() {
            this.load();
        }
        , instance);
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return if (isTrue(_ctx.isFont)) {
            createElementVNode("text", utsMapOf("key" to 0, "class" to "ux-icon", "style" to normalizeStyle(utsArrayOf(
                _ctx.style,
                _ctx.xstyle
            )), "onClick" to _ctx.onClick), toDisplayString(_ctx.icon), 13, utsArrayOf(
                "onClick"
            ));
        } else {
            createElementVNode("image", utsMapOf("key" to 1, "class" to "ux-icon", "style" to normalizeStyle(utsArrayOf(
                _ctx.imgStyle
            )), "src" to _ctx.icon), null, 12, utsArrayOf(
                "src"
            ));
        }
        ;
    }
    open var type: String by `$props`;
    open var color: String by `$props`;
    open var size: Number by `$props`;
    open var customFont: String by `$props`;
    open var customFamily: String by `$props`;
    open var xstyle: UTSArray<Any> by `$props`;
    open var icon: Any by `$data`;
    open var source: String by `$data`;
    open var family: String by `$data`;
    open var isFont: Boolean by `$data`;
    open var style: Any? by `$data`;
    open var imgStyle: Any? by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("icon" to computed<Any>(fun(): Any {
            return icons[this.type] ?: this.type;
        }
        ), "source" to computed<String>(fun(): String {
            return if (this.customFont == "") {
                "/uni_modules/ux-frame/static/uxframe.ttf";
            } else {
                this.customFont;
            }
            ;
        }
        ), "family" to computed<String>(fun(): String {
            return if (this.customFamily == "") {
                "uxframe";
            } else {
                this.customFamily;
            }
            ;
        }
        ), "isFont" to computed<Boolean>(fun(): Boolean {
            var icon = this.icon!! as String;
            if (icon == "") {
                return true;
            }
            if (`$ux`.Verify.isURL(icon)) {
                return false;
            }
            if (icon!!.indexOf("/") != -1) {
                return false;
            }
            return true;
        }
        ), "style" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            if (this.color != "") {
                css.set("color", this.color);
            }
            if (this.size > 10) {
                css.set("font-size", `$ux`.Util.addUnit(this.size));
            }
            if (this.family != "") {
                css.set("font-family", this.family);
            }
            return css;
        }
        ), "imgStyle" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            css.set("width", `$ux`.Util.addUnit(this.size));
            css.set("height", `$ux`.Util.addUnit(this.size));
            return css;
        }
        ));
    }
    override fun `$initMethods`() {
        this.load = fun() {
            if (this.source != "") {
                uni_loadFontFace(LoadFontFaceOptions(global = false, family = this.family, source = "url('" + this.source + "')", success = fun(_){}, fail = fun(err){
                    console.error("[ux-icon]配置错误: 加载字体库失败", err, " at uni_modules/ux-frame/components/ux-icon/index.uts:110");
                }
                ));
            }
        }
        ;
        this.onClick = fun(e: MouseEvent) {
            this.`$emit`("click", e);
        }
        ;
    }
    open lateinit var load: () -> Unit;
    open lateinit var onClick: (e: MouseEvent) -> Unit;
    companion object {
        var name = "ux-icon";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf());
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf("click" to null);
        var props = normalizePropsOptions(utsMapOf("type" to utsMapOf("type" to "String", "default" to ""), "color" to utsMapOf("type" to "String", "default" to "#33333"), "size" to utsMapOf("type" to "Number", "default" to 14), "customFont" to utsMapOf("type" to "String", "default" to ""), "customFamily" to utsMapOf("type" to "String", "default" to ""), "xstyle" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf<Any>();
        }
        )));
        var propsNeedCastKeys = utsArrayOf(
            "type",
            "color",
            "size",
            "customFont",
            "customFamily",
            "xstyle"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
