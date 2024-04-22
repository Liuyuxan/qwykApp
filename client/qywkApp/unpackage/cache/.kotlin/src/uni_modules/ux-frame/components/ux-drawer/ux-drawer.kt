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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById;
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync;
open class GenUniModulesUxFrameComponentsUxDrawerUxDrawer : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onMounted(fun() {}, instance);
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_ux_overlay = resolveEasyComponent("ux-overlay", GenUniModulesUxFrameComponentsUxOverlayUxOverlayClass);
        return createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
            "ux-drawer",
            utsArrayOf(
                if (_ctx.fixed) {
                    "fixed";
                } else {
                    "";
                }
            )
        )), "onTouchstart" to _ctx.touchstart, "onTouchend" to _ctx.touchend), utsArrayOf(
            createVNode(_component_ux_overlay, utsMapOf("show" to _ctx.opened, "z-index" to _ctx.zIndex, "opacity" to _ctx.maskOpacity, "maskClose" to _ctx.maskClose, "fixedOpacity" to false, "onClose" to _ctx.close), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                return utsArrayOf(
                    renderSlot(_ctx.`$slots`, "default"),
                    createElementVNode("view", utsMapOf("id" to _ctx.myId, "class" to normalizeClass(utsArrayOf(
                        "ux-drawer__slot--" + _ctx.direction,
                        "transform"
                    )), "style" to normalizeStyle(_ctx.slotStyle)), utsArrayOf(
                        renderSlot(_ctx.`$slots`, "drawer")
                    ), 14, utsArrayOf(
                        "id"
                    ))
                );
            }
            ), "_" to 3), 8, utsArrayOf(
                "show",
                "z-index",
                "opacity",
                "maskClose",
                "onClose"
            ))
        ), 42, utsArrayOf(
            "onTouchstart",
            "onTouchend"
        ));
    }
    open var touch: Boolean by `$props`;
    open var direction: String by `$props`;
    open var fixed: Boolean by `$props`;
    open var width: String by `$props`;
    open var height: String by `$props`;
    open var background: String by `$props`;
    open var border: Boolean by `$props`;
    open var opacity: Number by `$props`;
    open var zIndex: Number by `$props`;
    open var maskClose: Boolean by `$props`;
    open var disabled: Boolean by `$props`;
    open var myId: Any? by `$data`;
    open var opened: Boolean by `$data`;
    open var touched: Boolean by `$data`;
    open var pos: Number by `$data`;
    open var dis: Number by `$data`;
    open var sw: Number by `$data`;
    open var sh: Number by `$data`;
    open var slotStyle: String by `$data`;
    open var defaultDis: Number by `$data`;
    open var maskOpacity: Number by `$data`;
    open var slotLen: Number by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("myId" to ("ux-drawer_" + `$ux`.Random.uuid()), "opened" to false, "touched" to false, "pos" to 0, "dis" to -100, "sw" to uni_getSystemInfoSync().screenWidth as Number, "sh" to uni_getSystemInfoSync().screenHeight as Number, "slotStyle" to computed<String>(fun(): String {
            var style = "";
            if (this.direction == "right") {
                style = "width: " + this.slotLen + "px;";
            } else if (this.direction == "top") {
                style = "height: " + this.slotLen + "px;";
            } else if (this.direction == "left") {
                style = "width: " + this.slotLen + "px;";
            } else if (this.direction == "bottom") {
                style = "height: " + this.slotLen + "px;";
            }
            if (this.border) {
                style += "border-" + this.direction + ": 1px solid " + `$ux`.theme.border + ";";
            }
            return "" + style + "background-color: " + this.background + ";";
        }
        ), "defaultDis" to computed<Number>(fun(): Number {
            if (this.direction == "right") {
                return -this.slotLen / this.sw * 100;
            } else if (this.direction == "top") {
                return -this.slotLen / this.sh * 100;
            } else if (this.direction == "left") {
                return -this.slotLen / this.sw * 100;
            } else if (this.direction == "bottom") {
                return -this.slotLen / this.sh * 100;
            }
            return 0;
        }
        ), "maskOpacity" to computed<Number>(fun(): Number {
            var v = 1 - this.dis / this.defaultDis;
            if (v > this.opacity) {
                v = this.opacity;
            }
            if (v < 0) {
                v = 0;
            }
            return v;
        }
        ), "slotLen" to computed<Number>(fun(): Number {
            var w = "";
            var len: Number = 0;
            if (this.direction == "right") {
                w = this.width.toString().toLowerCase();
                len = this.sw;
            } else if (this.direction == "top") {
                w = this.height.toString().toLowerCase();
                len = this.sh;
            } else if (this.direction == "left") {
                w = this.width.toString().toLowerCase();
                len = this.sw;
            } else if (this.direction == "bottom") {
                w = this.height.toString().toLowerCase();
                len = this.sh;
            }
            if (w.includes("%")) {
                return len * (parseInt(w.replace("%", "")) / 100);
            }
            if (w.includes("rpx")) {
                var n = parseInt(w.replace("rpx", ""));
                return Math.floor(n / ((750 as Number) / len));
            }
            if (w.includes("px")) {
                return parseInt(w.replace("px", ""));
            }
            return len;
        }
        ));
    }
    override fun `$initMethods`() {
        this.touchstart = fun(e: TouchEvent) {
            if (this.disabled) {
                return;
            }
            if (!this.touch) {
                return;
            }
            if (this.direction == "right") {
                this.rightStart(e);
            } else if (this.direction == "top") {
                this.topStart(e);
            } else if (this.direction == "left") {
                this.leftStart(e);
            } else if (this.direction == "bottom") {
                this.bottomStart(e);
            }
        }
        ;
        this.touchend = fun(e: TouchEvent) {
            if (this.disabled) {
                return;
            }
            if (!this.touch) {
                return;
            }
            if (!this.touched) {
                return;
            }
            if (this.direction == "right") {
                this.rightEnd(e);
            } else if (this.direction == "top") {
                this.topEnd(e);
            } else if (this.direction == "left") {
                this.leftEnd(e);
            } else if (this.direction == "bottom") {
                this.bottomEnd(e);
            }
        }
        ;
        this.rightStart = fun(e: TouchEvent) {
            var x = e.changedTouches[0].screenX;
            var y = e.changedTouches[0].screenY;
            this.pos = x;
            if (this.opened) {
                if (x < this.slotLen - 100 || x > this.slotLen + 100) {
                    this.touched = false;
                    return;
                }
            } else {
                if (x > this.slotLen * 0.3 || y < 100 || y > this.sh - 100) {
                    this.touched = false;
                    return;
                }
            }
            this.touched = true;
        }
        ;
        this.rightEnd = fun(e: TouchEvent) {
            var dis = e.changedTouches[0].screenX - this.pos;
            if (this.opened) {
                if (dis > -20) {
                    return;
                }
                this.close();
            } else {
                if (dis < 20) {
                    return;
                }
                this.open();
            }
        }
        ;
        this.leftStart = fun(e: TouchEvent) {
            var x = this.sw - e.changedTouches[0].screenX;
            var y = e.changedTouches[0].screenY;
            this.pos = x;
            if (this.opened) {
                if (x < this.slotLen - 100 || x > this.slotLen + 100) {
                    this.touched = false;
                    return;
                }
            } else {
                if (x > this.slotLen * 0.3 || y < 100 || y > this.sh - 100) {
                    this.touched = false;
                    return;
                }
            }
            this.touched = true;
        }
        ;
        this.leftEnd = fun(e: TouchEvent) {
            var dis = this.sw - e.changedTouches[0].screenX - this.pos;
            if (this.opened) {
                if (dis > -20) {
                    return;
                }
                this.close();
            } else {
                if (dis < 20) {
                    return;
                }
                this.open();
            }
        }
        ;
        this.topStart = fun(e: TouchEvent) {
            var x = e.changedTouches[0].screenX;
            var y = this.sh - e.changedTouches[0].screenY;
            this.pos = y;
            if (this.opened) {
                if (y < this.slotLen - 100 || y > this.slotLen + 100) {
                    this.touched = false;
                    return;
                }
            } else {
                if (y > this.slotLen * 0.3 || x < 20 || x > this.sw - 20) {
                    this.touched = false;
                    return;
                }
            }
            this.touched = true;
        }
        ;
        this.topEnd = fun(e: TouchEvent) {
            var dis = this.sh - e.changedTouches[0].screenY - this.pos;
            if (this.opened) {
                if (dis > -20) {
                    return;
                }
                this.close();
            } else {
                if (dis < 20) {
                    return;
                }
                this.open();
            }
        }
        ;
        this.bottomStart = fun(e: TouchEvent) {
            var x = e.changedTouches[0].screenX;
            var y = e.changedTouches[0].screenY;
            this.pos = y;
            if (this.opened) {
                if (y < this.slotLen - 100 || y > this.slotLen + 100) {
                    this.touched = false;
                    return;
                }
            } else {
                if (y > this.slotLen * 0.3 || x < 20 || x > this.sw - 20) {
                    this.touched = false;
                    return;
                }
            }
            this.touched = true;
        }
        ;
        this.bottomEnd = fun(e: TouchEvent) {
            var dis = e.changedTouches[0].screenY - this.pos;
            if (this.opened) {
                if (dis > -20) {
                    return;
                }
                this.close();
            } else {
                if (dis < 20) {
                    return;
                }
                this.open();
            }
        }
        ;
        this.open = fun() {
            if (this.disabled) {
                return;
            }
            if (this.direction == "right" || this.direction == "left") {
                uni_getElementById(this.myId as String)?.style?.setProperty("transform", "translateX(" + 0 + "%)");
            } else if (this.direction == "top" || this.direction == "bottom") {
                uni_getElementById(this.myId as String)?.style?.setProperty("transform", "translateY(" + 0 + "%)");
            }
            this.dis = 0;
            setTimeout(fun(){
                this.opened = true;
                this.`$emit`("change", this.opened);
            }
            , 100);
        }
        ;
        this.close = fun() {
            if (this.disabled) {
                return;
            }
            if (this.direction == "right") {
                uni_getElementById(this.myId as String)?.style?.setProperty("transform", "translateX(" + -100 + "%)");
            } else if (this.direction == "top") {
                uni_getElementById(this.myId as String)?.style?.setProperty("transform", "translateY(" + 100 + "%)");
            } else if (this.direction == "left") {
                uni_getElementById(this.myId as String)?.style?.setProperty("transform", "translateX(" + 100 + "%)");
            } else if (this.direction == "bottom") {
                uni_getElementById(this.myId as String)?.style?.setProperty("transform", "translateY(" + -100 + "%)");
            }
            this.dis = this.defaultDis;
            setTimeout(fun(){
                this.opened = false;
                this.`$emit`("change", this.opened);
            }
            , 100);
        }
        ;
    }
    open lateinit var touchstart: (e: TouchEvent) -> Unit;
    open lateinit var touchend: (e: TouchEvent) -> Unit;
    open lateinit var rightStart: (e: TouchEvent) -> Unit;
    open lateinit var rightEnd: (e: TouchEvent) -> Unit;
    open lateinit var leftStart: (e: TouchEvent) -> Unit;
    open lateinit var leftEnd: (e: TouchEvent) -> Unit;
    open lateinit var topStart: (e: TouchEvent) -> Unit;
    open lateinit var topEnd: (e: TouchEvent) -> Unit;
    open lateinit var bottomStart: (e: TouchEvent) -> Unit;
    open lateinit var bottomEnd: (e: TouchEvent) -> Unit;
    open lateinit var open: () -> Unit;
    open lateinit var close: () -> Unit;
    companion object {
        var name = "ux-drawer";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-drawer__slot--right" to utsMapOf(".ux-drawer " to utsMapOf("position" to "fixed", "left" to 0, "top" to 0, "bottom" to 0, "width" to "85%", "transform" to "translate(-100%, 0)", "zIndex" to 200000)), "ux-drawer__slot--top" to utsMapOf(".ux-drawer " to utsMapOf("position" to "fixed", "left" to 0, "right" to 0, "bottom" to 0, "height" to "40%", "transform" to "translate(0, 100%)", "zIndex" to 200000)), "ux-drawer__slot--left" to utsMapOf(".ux-drawer " to utsMapOf("position" to "fixed", "right" to 0, "top" to 0, "bottom" to 0, "width" to "85%", "transform" to "translate(100%, 0)", "zIndex" to 200000)), "ux-drawer__slot--bottom" to utsMapOf(".ux-drawer " to utsMapOf("position" to "fixed", "left" to 0, "right" to 0, "top" to 0, "height" to "40%", "transform" to "translate(0, -100%)", "zIndex" to 200000)), "transform" to utsMapOf(".ux-drawer " to utsMapOf("transitionProperty" to "transform", "transitionDuration" to "0.3s", "transitionTimingFunction" to "ease-in-out")), "fixed" to padStyleMapOf(utsMapOf("position" to "fixed", "left" to 0, "top" to 0, "bottom" to 0, "right" to 0)), "@TRANSITION" to utsMapOf("transform" to utsMapOf("property" to "transform", "duration" to "0.3s", "timingFunction" to "ease-in-out")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf("change" to null);
        var props = normalizePropsOptions(utsMapOf("touch" to utsMapOf("type" to "Boolean", "default" to true), "direction" to utsMapOf("type" to "String", "default" to "right"), "fixed" to utsMapOf("type" to "Boolean", "default" to false), "width" to utsMapOf("type" to "String", "default" to "85%"), "height" to utsMapOf("type" to "String", "default" to "40%"), "background" to utsMapOf("type" to "String", "default" to "transparent"), "border" to utsMapOf("type" to "Boolean", "default" to false), "opacity" to utsMapOf("type" to "Number", "default" to 0.6), "zIndex" to utsMapOf("type" to "Number", "default" to 20000), "maskClose" to utsMapOf("type" to "Boolean", "default" to true), "disabled" to utsMapOf("type" to "Boolean", "default" to false)));
        var propsNeedCastKeys = utsArrayOf(
            "touch",
            "direction",
            "fixed",
            "width",
            "height",
            "background",
            "border",
            "opacity",
            "zIndex",
            "maskClose",
            "disabled"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
