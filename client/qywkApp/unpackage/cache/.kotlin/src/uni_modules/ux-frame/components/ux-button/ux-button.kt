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
open class GenUniModulesUxFrameComponentsUxButtonUxButton : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_ux_loading = resolveEasyComponent("ux-loading", GenUniModulesUxFrameComponentsUxLoadingUxLoadingClass);
        val _component_ux_icon = resolveEasyComponent("ux-icon", GenUniModulesUxFrameComponentsUxIconUxIconClass);
        return createElementVNode("view", utsMapOf("ref" to "uxButton", "class" to "ux-button transform", "style" to normalizeStyle(_ctx.style), "hover-class" to "none", "onClick" to _ctx.click, "onLongpress" to _ctx.longpress, "onTouchstart" to _ctx.touchstart, "onTouchend" to _ctx.touchend, "onTouchmove" to _ctx.touchmove, "onTouchcancel" to _ctx.touchcancel), utsArrayOf(
            if (isTrue(_ctx.loading)) {
                createVNode(_component_ux_loading, utsMapOf("key" to 0, "type" to _ctx.loadingType, "color" to _ctx.loadingColor, "text-size" to _ctx.loadingSize), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        toDisplayString(_ctx.loadingText)
                    );
                }), "_" to 1), 8, utsArrayOf(
                    "type",
                    "color",
                    "text-size"
                ));
            } else {
                createElementVNode(Fragment, utsMapOf("key" to 1), utsArrayOf(
                    if (_ctx.icon != "") {
                        createVNode(_component_ux_icon, utsMapOf("key" to 0, "type" to _ctx.icon, "size" to _ctx.iconSize, "color" to if (_ctx.iconColor == "") {
                            _ctx._color;
                        } else {
                            _ctx.iconColor;
                        }, "custom-font" to _ctx.customFont, "custom-family" to _ctx.customFamily), null, 8, utsArrayOf(
                            "type",
                            "size",
                            "color",
                            "custom-font",
                            "custom-family"
                        ));
                    } else {
                        createCommentVNode("v-if", true);
                    }
                    ,
                    if (isTrue(_ctx.text)) {
                        createElementVNode("text", utsMapOf("key" to 1, "style" to normalizeStyle(utsArrayOf(
                            _ctx.textStyle
                        ))), toDisplayString(_ctx.text), 5);
                    } else {
                        createCommentVNode("v-if", true);
                    }
                ), 64);
            }
        ), 44, utsArrayOf(
            "onClick",
            "onLongpress",
            "onTouchstart",
            "onTouchend",
            "onTouchmove",
            "onTouchcancel"
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
    open var type: String by `$props`;
    open var text: String by `$props`;
    open var color: String by `$props`;
    open var size: Number by `$props`;
    open var bold: Boolean by `$props`;
    open var background: String by `$props`;
    open var corner: Number by `$props`;
    open var plain: Boolean by `$props`;
    open var loading: Boolean by `$props`;
    open var loadingType: String by `$props`;
    open var loadingText: String by `$props`;
    open var loadingSize: Number by `$props`;
    open var loadingColor: String by `$props`;
    open var icon: String by `$props`;
    open var iconSize: Number by `$props`;
    open var iconColor: String by `$props`;
    open var customFont: String by `$props`;
    open var customFamily: String by `$props`;
    open var direction: String by `$props`;
    open var path: String by `$props`;
    open var throttleTime: Number by `$props`;
    open var hoverStartTime: Number by `$props`;
    open var hoverStayTime: Number by `$props`;
    open var hover: Boolean by `$props`;
    open var stopPropagation: Boolean by `$props`;
    open var _color: String by `$data`;
    open var _background: String by `$data`;
    open var _border: String by `$data`;
    open var style: UTSArray<Any> by `$data`;
    open var textStyle: Any? by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("_color" to computed<String>(fun(): String {
            if (this.plain) {
                if (this.type == "text") {
                    return if (this.color == "") {
                        `$ux`.theme.main!!;
                    } else {
                        this.color;
                    };
                } else if (this.type == "info") {
                    return if (this.color == "") {
                        `$ux`.theme.main!!;
                    } else {
                        this.color;
                    };
                } else if (this.type == "primary") {
                    return `$ux`.theme.primary!!;
                } else if (this.type == "success") {
                    return `$ux`.theme.success!!;
                } else if (this.type == "warn") {
                    return `$ux`.theme.warn!!;
                } else if (this.type == "error") {
                    return `$ux`.theme.error!!;
                }
            } else {
                if (this.type == "text") {
                    return if (this.color == "") {
                        `$ux`.theme.main!!;
                    } else {
                        this.color;
                    };
                } else if (this.type == "info") {
                    return if (this.color == "") {
                        `$ux`.theme.main!!;
                    } else {
                        this.color;
                    };
                } else if (this.type == "primary") {
                    return "#ffffff";
                } else if (this.type == "success") {
                    return "#ffffff";
                } else if (this.type == "warn") {
                    return "#ffffff";
                } else if (this.type == "error") {
                    return "#ffffff";
                }
            }
            return "transparent";
        }
        ), "_background" to computed<String>(fun(): String {
            if (this.background != "") {
                return this.background;
            }
            if (this.plain) {
                return "transparent";
            } else {
                if (this.type == "text") {
                    ;
                } else if (this.type == "info") {
                    ;
                } else if (this.type == "primary") {
                    return `$ux`.theme.primary!!;
                } else if (this.type == "success") {
                    return `$ux`.theme.success!!;
                } else if (this.type == "warn") {
                    return `$ux`.theme.warn!!;
                } else if (this.type == "error") {
                    return `$ux`.theme.error!!;
                }
            }
            return "transparent";
        }
        ), "_border" to computed<String>(fun(): String {
            if (this.type == "text") {
                ;
            } else if (this.type == "info") {
                return `$ux`.theme.border!!;
            } else if (this.type == "primary") {
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
        ), "style" to computed<UTSArray<Any>>(fun(): UTSArray<Any> {
            var css = Map<String, Any?>();
            css.set("flex-direction", this.direction);
            if (this.disabled) {
                css.set("border", "1rpx solid " + `$ux`.Color.hexToRgba(this._border, 0.6));
                css.set("background-color", `$ux`.Color.hexToRgba(this._background, 0.6));
            } else {
                css.set("border", "1rpx solid " + this._border);
                css.set("background-color", this._background);
            }
            css.set("box-sizing", "border-box");
            css.set("border-radius", `$ux`.Util.addUnit(this.corner));
            css.set("padding", "6px 10px");
            css = `$ux`.Util.xStyle(css, this.margin, this.mt, this.mr, this.mb, this.ml, this.padding, this.pt, this.pr, this.pb, this.pl);
            return utsArrayOf(
                css,
                this.xstyle
            );
        }
        ), "textStyle" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            css.set("font-size", `$ux`.Util.addUnit(this.size));
            css.set("color", if (this.disabled) {
                `$ux`.theme.disabled;
            } else {
                this._color;
            }
            );
            css.set("font-weight", if (this.bold) {
                "bold";
            } else {
                "normal";
            }
            );
            if (this.icon != "") {
                if (this.direction == "row") {
                    css.set("margin-left", "4px");
                } else {
                    css.set("margin-top", "4px");
                }
            }
            return css;
        }
        ));
    }
    override fun `$initMethods`() {
        this.click = fun(e: MouseEvent) {
            if (this.disabled || this.loading) {
                return;
            }
            var f = fun(): Unit {
                if (this.path != "") {
                    uni_navigateTo(NavigateToOptions(url = this.path));
                } else {
                    this.`$emit`("click");
                }
                if (this.stopPropagation) {
                    e.stopPropagation();
                }
            }
            ;
            `$ux`.Util.throttle(f, this.throttleTime);
        }
        ;
        this.longpress = fun(e: TouchEvent) {
            if (this.disabled || this.loading) {
                return;
            }
            this.`$emit`("longpress", e);
        }
        ;
        this.touchstart = fun(e: TouchEvent) {
            if (this.disabled) {
                return;
            }
            this.`$emit`("touchstart", e);
            this.startHover();
        }
        ;
        this.touchend = fun(e: TouchEvent) {
            if (this.disabled) {
                return;
            }
            this.`$emit`("touchend", e);
            this.endHover();
        }
        ;
        this.touchmove = fun(e: TouchEvent) {
            if (this.disabled) {
                return;
            }
            this.`$emit`("touchmove", e);
        }
        ;
        this.touchcancel = fun(e: TouchEvent) {
            if (this.disabled) {
                return;
            }
            this.`$emit`("touchcancel", e);
            this.endHover();
        }
        ;
        this.startHover = fun() {
            if (!this.hover) {
                return;
            }
            setTimeout(fun(){
                if (this._background == "" || this._background == "transparent" || this.plain) {
                    (this.`$refs`["uxButton"] as Element).style.setProperty("background-color", "#e5e5e5");
                } else {
                    var color = `$ux`.Color.hexToRgba(this._background, 0.6) as String;
                    (this.`$refs`["uxButton"] as Element).style.setProperty("background-color", color);
                }
            }
            , this.hoverStartTime);
        }
        ;
        this.endHover = fun() {
            if (!this.hover) {
                return;
            }
            setTimeout(fun(){
                if (this._background == "" || this.plain) {
                    (this.`$refs`["uxButton"] as Element).style.setProperty("background-color", "transparent");
                } else {
                    (this.`$refs`["uxButton"] as Element).style.setProperty("background-color", this._background);
                }
            }
            , this.hoverStayTime);
        }
        ;
    }
    open lateinit var click: (e: MouseEvent) -> Unit;
    open lateinit var longpress: (e: TouchEvent) -> Unit;
    open lateinit var touchstart: (e: TouchEvent) -> Unit;
    open lateinit var touchend: (e: TouchEvent) -> Unit;
    open lateinit var touchmove: (e: TouchEvent) -> Unit;
    open lateinit var touchcancel: (e: TouchEvent) -> Unit;
    open lateinit var startHover: () -> Unit;
    open lateinit var endHover: () -> Unit;
    companion object {
        var name = "ux-button";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-button" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "center")), "transform" to padStyleMapOf(utsMapOf("transitionProperty" to "backgroundColor", "transitionDelay" to "0.2s")), "@TRANSITION" to utsMapOf("transform" to utsMapOf("property" to "backgroundColor", "delay" to "0.2s")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf("click" to null, "longpress" to null, "touchstart" to null, "touchend" to null, "touchmove" to null, "touchcancel" to null);
        var props = normalizePropsOptions(utsMapOf("margin" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf();
        }
        ), "mt" to utsMapOf("type" to "Number", "default" to 0), "mr" to utsMapOf("type" to "Number", "default" to 0), "mb" to utsMapOf("type" to "Number", "default" to 0), "ml" to utsMapOf("type" to "Number", "default" to 0), "padding" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf();
        }
        ), "pt" to utsMapOf("type" to "Number", "default" to 0), "pr" to utsMapOf("type" to "Number", "default" to 0), "pb" to utsMapOf("type" to "Number", "default" to 0), "pl" to utsMapOf("type" to "Number", "default" to 0), "xstyle" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf<Any>();
        }
        ), "disabled" to utsMapOf("type" to "Boolean", "default" to false), "type" to utsMapOf("type" to "String", "default" to "info"), "text" to utsMapOf("type" to "String", "default" to ""), "color" to utsMapOf("type" to "String", "default" to ""), "size" to utsMapOf("type" to "Number", "default" to 12), "bold" to utsMapOf("type" to "Boolean", "default" to false), "background" to utsMapOf("type" to "String", "default" to ""), "corner" to utsMapOf("type" to "Number", "default" to 5), "plain" to utsMapOf("type" to "Boolean", "default" to false), "loading" to utsMapOf("type" to "Boolean", "default" to false), "loadingType" to utsMapOf("type" to "String", "default" to "spinner"), "loadingText" to utsMapOf("type" to "String", "default" to "加载中..."), "loadingSize" to utsMapOf("type" to "Number", "default" to 12), "loadingColor" to utsMapOf("type" to "String", "default" to "#999999"), "icon" to utsMapOf("type" to "String", "default" to ""), "iconSize" to utsMapOf("type" to "Number", "default" to 14), "iconColor" to utsMapOf("type" to "String", "default" to ""), "customFont" to utsMapOf("type" to "String", "default" to ""), "customFamily" to utsMapOf("type" to "String", "default" to ""), "direction" to utsMapOf("type" to "String", "default" to "row"), "path" to utsMapOf("type" to "String", "default" to ""), "throttleTime" to utsMapOf("type" to "Number", "default" to 0), "hoverStartTime" to utsMapOf("type" to "Number", "default" to 0), "hoverStayTime" to utsMapOf("type" to "Number", "default" to 100), "hover" to utsMapOf("type" to "Boolean", "default" to true), "stopPropagation" to utsMapOf("type" to "Boolean", "default" to false)));
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
            "type",
            "text",
            "color",
            "size",
            "bold",
            "background",
            "corner",
            "plain",
            "loading",
            "loadingType",
            "loadingText",
            "loadingSize",
            "loadingColor",
            "icon",
            "iconSize",
            "iconColor",
            "customFont",
            "customFamily",
            "direction",
            "path",
            "throttleTime",
            "hoverStartTime",
            "hoverStayTime",
            "hover",
            "stopPropagation"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
