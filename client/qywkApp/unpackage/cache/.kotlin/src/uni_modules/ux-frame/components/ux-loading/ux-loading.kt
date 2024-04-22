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
open class GenUniModulesUxFrameComponentsUxLoadingUxLoading : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onMounted(fun() {
            this.anim();
        }
        , instance);
        this.`$watch`(fun(): Any? {
            return this.type;
        }
        , fun(a: String, b: String) {
            this.anim();
        }
        );
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", utsMapOf("class" to "ux-loading"), utsArrayOf(
            createElementVNode("view", utsMapOf("ref" to "ux-loading", "class" to "ux-loading__icon", "style" to normalizeStyle(utsArrayOf(
                _ctx.style
            ))), null, 4),
            if (_ctx.`$slots`["default"] != null) {
                createElementVNode("text", utsMapOf("key" to 0, "class" to "ux-loading__text", "style" to normalizeStyle(utsArrayOf(
                    _ctx.textStyle
                ))), utsArrayOf(
                    renderSlot(_ctx.`$slots`, "default")
                ), 4);
            } else {
                createCommentVNode("v-if", true);
            }
        ));
    }
    open var type: String by `$props`;
    open var color: String by `$props`;
    open var size: Number by `$props`;
    open var textColor: String by `$props`;
    open var textSize: Number by `$props`;
    open var bold: Boolean by `$props`;
    open var width: Number by `$data`;
    open var height: Number by `$data`;
    open var timer: Number by `$data`;
    open var stop: Boolean by `$data`;
    open var style: Any? by `$data`;
    open var textStyle: Any? by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("width" to 16, "height" to 16, "timer" to 0, "stop" to false, "style" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            css.set("width", `$ux`.Util.addUnit(this.size));
            css.set("height", `$ux`.Util.addUnit(this.size));
            return css;
        }
        ), "textStyle" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            css.set("color", if (this.textColor == "") {
                this.color;
            } else {
                this.textColor;
            }
            );
            css.set("font-size", `$ux`.Util.addUnit(this.textSize));
            if (this.bold) {
                css.set("font-weight", "bold");
            }
            return css;
        }
        ));
    }
    override fun `$initMethods`() {
        this.anim = fun() {
            this.stop = false;
            setTimeout(fun(){
                if (this.`$refs`["ux-loading"] == null) {
                    return;
                }
                var el = (this.`$refs`["ux-loading"] as Element);
                var ctx = el.getDrawableContext();
                if (ctx == null) {
                    console.error("[ux-loading] 加载失败", " at uni_modules/ux-frame/components/ux-loading/index.uts:85");
                }
                var rect = el.getBoundingClientRect();
                this.width = rect.width;
                this.height = rect.height;
                if (this.type == "circular") {
                    this.drawCircular(ctx!!);
                } else {
                    this.drawSpinner(ctx!!);
                }
                setTimeout(fun(){
                    this.stop = true;
                }
                , 1000 * 60);
            }
            , 200);
        }
        ;
        this.drawCircular = fun(ctx: DrawableContext) {
            if (ctx == null) {
                return;
            }
            var startAngle: Number = 0;
            var endAngle: Number = 0;
            var startSpeed: Number = 0;
            var endSpeed: Number = 0;
            var rotate: Number = 0;
            val ARC_LENGTH: Number = 359;
            val PI = Math.PI / 180;
            val SPEED: Number = 0.018;
            val ROTATE_INTERVAL: Number = 0.09;
            val center = this.width / 2;
            val lineWidth = this.width / 10;
            var easeInOutCubic = fun(t: Number): Number {
                return if (t < 0.5) {
                    4 * t * t * t;
                } else {
                    (t - 1) * (2 * t - 2) * (2 * t - 2) + 1;
                }
                ;
            }
            ;
            var draw = fun(): Unit {};
            draw = fun(): Unit {
                clearTimeout(this.timer);
                if (this.stop) {
                    return;
                }
                ctx.reset();
                ctx.beginPath();
                ctx.arc(center, center, center - lineWidth, startAngle * PI + rotate, endAngle * PI + rotate);
                ctx.lineWidth = lineWidth;
                ctx.strokeStyle = this.color;
                ctx.stroke();
                if (endAngle < ARC_LENGTH && startAngle == 0) {
                    endSpeed += SPEED;
                    endAngle = Math.min(ARC_LENGTH, easeInOutCubic(endSpeed) * ARC_LENGTH);
                } else if (endAngle == ARC_LENGTH && startAngle < ARC_LENGTH) {
                    startSpeed += SPEED;
                    startAngle = Math.min(ARC_LENGTH, easeInOutCubic(startSpeed) * ARC_LENGTH);
                } else if (endAngle >= ARC_LENGTH && startAngle >= ARC_LENGTH) {
                    endSpeed = 0;
                    startSpeed = 0;
                    startAngle = 0;
                    endAngle = 0;
                }
                rotate += ROTATE_INTERVAL;
                ctx.update();
                this.timer = setTimeout(fun(){
                    draw();
                }
                , 20);
            }
            ;
            draw();
        }
        ;
        this.drawSpinner = fun(ctx: DrawableContext) {
            if (ctx == null) {
                return;
            }
            var steps: Number = 12;
            var step: Number = 0;
            var width = this.width;
            var lineWidth = width / 10;
            var length = width / 4 - lineWidth;
            var offset = width / 4;
            var draw = fun(): Unit {};
            draw = fun(): Unit {
                clearTimeout(this.timer);
                if (this.stop) {
                    return;
                }
                ctx.reset();
                run {
                    var i: Number = 0;
                    while(i < steps){
                        val stepAngle = (360 as Number) / steps;
                        val angle = stepAngle * i;
                        val opacity = ((steps - (step % steps)) * stepAngle + angle) % 360 + 30;
                        val sin = Math.sin(angle / 180 * Math.PI);
                        val cos = Math.cos(angle / 180 * Math.PI);
                        ctx.lineWidth = lineWidth;
                        ctx.lineCap = "round";
                        ctx.beginPath();
                        ctx.moveTo(width / 2 + offset * cos, width / 2 + offset * sin);
                        ctx.lineTo(width / 2 + (offset + length) * cos, width / 2 + (offset + length) * sin);
                        ctx.strokeStyle = `$ux`.Color.hexToRgba(this.color, (opacity / 360 * 255).toInt());
                        ctx.stroke();
                        i++;
                    }
                }
                ctx.update();
                step += 1;
                if (step >= steps) {
                    step = 0;
                }
                this.timer = setTimeout(fun(){
                    draw();
                }
                , 48);
            }
            ;
            draw();
        }
        ;
    }
    open lateinit var anim: () -> Unit;
    open lateinit var drawCircular: (ctx: DrawableContext) -> Unit;
    open lateinit var drawSpinner: (ctx: DrawableContext) -> Unit;
    companion object {
        var name = "ux-loading";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-loading" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "alignItems" to "center")), "ux-loading__icon" to padStyleMapOf(utsMapOf("width" to 20, "height" to 20)), "ux-loading__img" to padStyleMapOf(utsMapOf("width" to 20, "height" to 20)), "ux-loading__text" to padStyleMapOf(utsMapOf("marginLeft" to 8, "color" to "#999999", "fontSize" to 12)));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf("type" to utsMapOf("type" to "String", "default" to "spinner"), "color" to utsMapOf("type" to "String", "default" to "#999999"), "size" to utsMapOf("type" to "Number", "default" to 16), "textColor" to utsMapOf("type" to "String", "default" to ""), "textSize" to utsMapOf("type" to "Number", "default" to 12), "bold" to utsMapOf("type" to "Boolean", "default" to false)));
        var propsNeedCastKeys = utsArrayOf(
            "type",
            "color",
            "size",
            "textColor",
            "textSize",
            "bold"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
