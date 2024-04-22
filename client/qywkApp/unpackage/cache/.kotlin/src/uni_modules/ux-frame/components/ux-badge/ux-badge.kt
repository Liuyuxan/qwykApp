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
open class GenUniModulesUxFrameComponentsUxBadgeUxBadge : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return if (_ctx.value != 0) {
            createElementVNode("view", utsMapOf("key" to 0, "class" to "ux-badge", "style" to normalizeStyle(utsArrayOf(
                _ctx.style,
                _ctx.xstyle
            ))), utsArrayOf(
                if (isTrue(!_ctx.dot)) {
                    createElementVNode("text", utsMapOf("key" to 0, "class" to "ux-badge__value", "style" to normalizeStyle(utsArrayOf(
                        _ctx.textStyle
                    ))), toDisplayString(_ctx._value), 5);
                } else {
                    createCommentVNode("v-if", true);
                }
            ), 4);
        } else {
            createCommentVNode("v-if", true);
        }
        ;
    }
    open var type: String by `$props`;
    open var value: Number by `$props`;
    open var max: Number by `$props`;
    open var overflow: String by `$props`;
    open var shape: String by `$props`;
    open var dot: Boolean by `$props`;
    open var inverted: Boolean by `$props`;
    open var fixed: Boolean by `$props`;
    open var top: Number by `$props`;
    open var right: Number by `$props`;
    open var left: Number by `$props`;
    open var bottom: Number by `$props`;
    open var background: String by `$props`;
    open var size: Number by `$props`;
    open var xstyle: UTSArray<Any> by `$props`;
    open var _color: String by `$data`;
    open var _background: String by `$data`;
    open var style: Any? by `$data`;
    open var textStyle: Any? by `$data`;
    open var _value: String by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("_color" to computed<String>(fun(): String {
            if (this.inverted) {
                if (this.type == "primary") {
                    return `$ux`.theme.primary!!;
                } else if (this.type == "success") {
                    return `$ux`.theme.success!!;
                } else if (this.type == "warn") {
                    return `$ux`.theme.warn!!;
                } else if (this.type == "error") {
                    return `$ux`.theme.error!!;
                }
            }
            return "#ffffff";
        }
        ), "_background" to computed<String>(fun(): String {
            if (this.inverted) {
                return "transparent";
            }
            if (this.background != "") {
                return this.background;
            }
            if (this.type == "primary") {
                return `$ux`.theme.primary!!;
            } else if (this.type == "success") {
                return `$ux`.theme.success!!;
            } else if (this.type == "warn") {
                return `$ux`.theme.warn!!;
            } else if (this.type == "error") {
                return `$ux`.theme.error!!;
            }
            return "transparent";
        }
        ), "style" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            css.set("background-color", this._background);
            if (this.fixed) {
                css.set("position", "absolute");
                if (this.top != 0) {
                    css.set("top", `$ux`.Util.addUnit(this.top));
                }
                if (this.right != 0) {
                    css.set("right", `$ux`.Util.addUnit(this.right));
                }
                if (this.bottom != 0) {
                    css.set("bottom", `$ux`.Util.addUnit(this.bottom));
                }
                if (this.left != 0) {
                    css.set("left", `$ux`.Util.addUnit(this.left));
                }
            } else {
                if (this.top != 0) {
                    css.set("margin-top", `$ux`.Util.addUnit(this.top));
                }
                if (this.right != 0) {
                    css.set("margin-right", `$ux`.Util.addUnit(this.right));
                }
                if (this.bottom != 0) {
                    css.set("margin-bottom", `$ux`.Util.addUnit(this.bottom));
                }
                if (this.left != 0) {
                    css.set("margin-left", `$ux`.Util.addUnit(this.left));
                }
            }
            if (this.dot) {
                css.set("width", `$ux`.Util.addUnit(8));
                css.set("height", `$ux`.Util.addUnit(8));
                css.set("border-radius", `$ux`.Util.addUnit(15));
            } else {
                css.set("padding", "2px 6px");
                if (this.shape == "lt") {
                    css.set("border-radius", "2px 10px 10px 10px");
                } else if (this.shape == "rt") {
                    css.set("border-radius", "10px 2px 10px 10px");
                } else if (this.shape == "rb") {
                    css.set("border-radius", "10px 10px 2px 10px");
                } else if (this.shape == "lb") {
                    css.set("border-radius", "10px 10px 10px 2px");
                } else if (this.shape == "ltrb") {
                    css.set("border-radius", "2px 10px 2px 10px");
                } else if (this.shape == "rtlb") {
                    css.set("border-radius", "10px 2px 10px 2px");
                } else {
                    css.set("border-radius", "15px");
                }
            }
            return css;
        }
        ), "textStyle" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            css.set("font-size", `$ux`.Util.addUnit(this.size));
            css.set("color", this._color);
            return css;
        }
        ), "_value" to computed<String>(fun(): String {
            if (this.value == 0) {
                return "";
            }
            if (this.overflow == "format") {
                if (this.value > 9999) {
                    return "" + Math.floor(this.value / 1e4 * 100) / 100 + "w";
                } else if (this.value > 999) {
                    return "" + Math.floor(this.value / 1e3 * 100) / 100 + "k";
                } else if (this.value > this.max) {
                    return "" + this.max + "+";
                }
            } else {
                if (this.value > this.max) {
                    return "" + this.max + "+";
                }
            }
            return "" + this.value;
        }
        ));
    }
    companion object {
        var name = "ux-badge";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-badge" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "justifyContent" to "center", "alignContent" to "center")), "ux-badge__value" to padStyleMapOf(utsMapOf("fontSize" to 10, "color" to "#FFFFFF", "textAlign" to "center")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf("type" to utsMapOf("type" to "String", "default" to "error"), "value" to utsMapOf("type" to "Number", "default" to 0), "max" to utsMapOf("type" to "Number", "default" to 99), "overflow" to utsMapOf("type" to "String", "default" to "normal"), "shape" to utsMapOf("type" to "String", "default" to "normal"), "dot" to utsMapOf("type" to "Boolean", "default" to false), "inverted" to utsMapOf("type" to "Boolean", "default" to false), "fixed" to utsMapOf("type" to "Boolean", "default" to true), "top" to utsMapOf("type" to "Number", "default" to 0), "right" to utsMapOf("type" to "Number", "default" to 0), "left" to utsMapOf("type" to "Number", "default" to 0), "bottom" to utsMapOf("type" to "Number", "default" to 0), "background" to utsMapOf("type" to "String", "default" to ""), "size" to utsMapOf("type" to "Number", "default" to 10), "xstyle" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf<Any>();
        }
        )));
        var propsNeedCastKeys = utsArrayOf(
            "type",
            "value",
            "max",
            "overflow",
            "shape",
            "dot",
            "inverted",
            "fixed",
            "top",
            "right",
            "left",
            "bottom",
            "background",
            "size",
            "xstyle"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
