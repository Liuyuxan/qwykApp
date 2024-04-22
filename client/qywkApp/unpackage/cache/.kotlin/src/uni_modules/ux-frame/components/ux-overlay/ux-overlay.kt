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
open class GenUniModulesUxFrameComponentsUxOverlayUxOverlay : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        this.`$watch`(fun(): Any? {
            return this.show;
        }
        , fun() {
            this.anim();
        }
        );
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", utsMapOf("class" to "ux-overlay"), utsArrayOf(
            createElementVNode("view", utsMapOf("ref" to "ux-overlay", "class" to "ux-overlay__mask transform", "style" to normalizeStyle(_ctx.style), "onClick" to _ctx.close), null, 12, utsArrayOf(
                "onClick"
            )),
            renderSlot(_ctx.`$slots`, "default")
        ));
    }
    open var show: Boolean by `$props`;
    open var maskClose: Boolean by `$props`;
    open var opacity: Number by `$props`;
    open var fixedOpacity: Boolean by `$props`;
    open var zIndex: Number by `$props`;
    open var sw: Number by `$data`;
    open var sh: Number by `$data`;
    open var style: String by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("sw" to uni_getSystemInfoSync().screenWidth as Number, "sh" to uni_getSystemInfoSync().screenHeight as Number, "style" to computed<String>(fun(): String {
            return "z-index: " + this.zIndex + "; background-color: rgba(0,0,0," + this.opacity + ")";
        }
        ));
    }
    override fun `$initMethods`() {
        this.anim = fun() {
            var el = this.`$refs`["ux-overlay"] as Element;
            if (this.show) {
                el?.style?.setProperty("width", "" + this.sw + "px");
                el?.style?.setProperty("height", "" + this.sh + "px");
                if (this.fixedOpacity) {
                    el?.style?.setProperty("background-color", "rgba(0,0,0," + this.opacity + ")");
                }
            } else {
                if (this.fixedOpacity) {
                    el?.style?.setProperty("background-color", "rgba(0,0,0,0)");
                }
                setTimeout(fun(){
                    el?.style?.setProperty("width", "" + 0 + "px");
                    el?.style?.setProperty("height", "" + 0 + "px");
                }
                , 200);
            }
        }
        ;
        this.close = fun() {
            if (this.maskClose) {
                this.`$emit`("close");
            }
        }
        ;
    }
    open lateinit var anim: () -> Unit;
    open lateinit var close: () -> Unit;
    companion object {
        var name = "ux-overlay";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-overlay" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%")), "ux-overlay__mask" to utsMapOf(".ux-overlay " to utsMapOf("position" to "fixed", "zIndex" to 10001, "top" to 0, "right" to 0, "width" to 0, "height" to 0, "backgroundColor" to "rgba(0,0,0,0)")), "transform" to padStyleMapOf(utsMapOf("transitionProperty" to "backgroundColor", "transitionDuration" to "0.4s", "transitionTimingFunction" to "linear")), "@TRANSITION" to utsMapOf("transform" to utsMapOf("property" to "backgroundColor", "duration" to "0.4s", "timingFunction" to "linear")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf("close" to null);
        var props = normalizePropsOptions(utsMapOf("show" to utsMapOf("type" to "Boolean", "default" to false), "maskClose" to utsMapOf("type" to "Boolean", "default" to true), "opacity" to utsMapOf("type" to "Number", "default" to 0.5), "fixedOpacity" to utsMapOf("type" to "Boolean", "default" to true), "zIndex" to utsMapOf("type" to "Number", "default" to 10001)));
        var propsNeedCastKeys = utsArrayOf(
            "show",
            "maskClose",
            "opacity",
            "fixedOpacity",
            "zIndex"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
