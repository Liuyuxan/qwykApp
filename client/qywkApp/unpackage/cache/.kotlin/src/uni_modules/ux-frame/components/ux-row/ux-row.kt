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
open class GenUniModulesUxFrameComponentsUxRowUxRow : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", utsMapOf("class" to "ux-row", "style" to normalizeStyle(_ctx.style), "onClick" to _ctx.onClick), utsArrayOf(
            renderSlot(_ctx.`$slots`, "default")
        ), 12, utsArrayOf(
            "onClick"
        ));
    }
    open var margin: UTSArray<Any> by `$props`;
    open var mt: Number by `$props`;
    open var mr: Number by `$props`;
    open var mb: Number by `$props`;
    open var ml: Number by `$props`;
    open var padding: UTSArray<Any> by `$props`;
    open var pt: Number by `$props`;
    open var pr: Number by `$props`;
    open var pb: Number by `$props`;
    open var pl: Number by `$props`;
    open var xstyle: UTSArray<Any> by `$props`;
    open var disabled: Boolean by `$props`;
    open var gutter: Number by `$props`;
    open var justify: String by `$props`;
    open var align: String by `$props`;
    open var _justify: String by `$data`;
    open var _alignItem: String by `$data`;
    open var style: UTSArray<Any> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("_justify" to computed<String>(fun(): String {
            if (this.justify == "left" || this.justify == "start") {
                return "flex-start";
            } else if (this.justify == "right" || this.justify == "end") {
                return "flex-end";
            } else if (this.justify == "around" || this.justify == "between") {
                return "space-" + this.justify;
            }
            return this.justify;
        }
        ), "_alignItem" to computed<String>(fun(): String {
            if (this.align == "top" || this.align == "start") {
                return "flex-start";
            } else if (this.align == "bottom" || this.align == "end") {
                return "flex-end";
            } else if (this.align == "around" || this.align == "between") {
                return "space-" + this.align;
            }
            return this.align;
        }
        ), "style" to computed<UTSArray<Any>>(fun(): UTSArray<Any> {
            var css = Map<String, Any?>();
            css.set("justify-content", this._justify);
            css.set("align-items", this._alignItem);
            css = `$ux`.Util.xStyle(css, this.margin, this.mt, this.mr, this.mb, this.ml, this.padding, this.pt, this.pr, this.pb, this.pl);
            if (this.gutter > 0) {
                css.set("padding-left", "" + -this.gutter / 2 + "px");
                css.set("padding-right", "" + -this.gutter / 2 + "px");
            }
            return utsArrayOf(
                css,
                this.xstyle
            );
        }
        ));
    }
    override fun `$initMethods`() {
        this.onClick = fun(e: MouseEvent) {
            this.`$emit`("click", e);
        }
        ;
    }
    open lateinit var onClick: (e: MouseEvent) -> Unit;
    companion object {
        var name = "ux-row";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-row" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf("click" to null);
        var props = normalizePropsOptions(utsMapOf("margin" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf();
        }
        ), "mt" to utsMapOf("type" to "Number", "default" to 0), "mr" to utsMapOf("type" to "Number", "default" to 0), "mb" to utsMapOf("type" to "Number", "default" to 0), "ml" to utsMapOf("type" to "Number", "default" to 0), "padding" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf();
        }
        ), "pt" to utsMapOf("type" to "Number", "default" to 0), "pr" to utsMapOf("type" to "Number", "default" to 0), "pb" to utsMapOf("type" to "Number", "default" to 0), "pl" to utsMapOf("type" to "Number", "default" to 0), "xstyle" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf<Any>();
        }
        ), "disabled" to utsMapOf("type" to "Boolean", "default" to false), "gutter" to utsMapOf("type" to "Number", "default" to 0), "justify" to utsMapOf("type" to "String", "default" to "start"), "align" to utsMapOf("type" to "String", "default" to "center")));
        var propsNeedCastKeys = utsArrayOf(
            "margin",
            "mt",
            "mr",
            "mb",
            "ml",
            "padding",
            "pt",
            "pr",
            "pb",
            "pl",
            "xstyle",
            "disabled",
            "gutter",
            "justify",
            "align"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
