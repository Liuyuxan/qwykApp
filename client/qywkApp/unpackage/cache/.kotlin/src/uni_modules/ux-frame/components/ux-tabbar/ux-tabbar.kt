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
open class GenUniModulesUxFrameComponentsUxTabbarUxTabbar : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onMounted(fun() {
            this.tabIndex = this.index;
            this.drawer = (this.`$refs`["ux-tabbar"] as Element).getDrawableContext();
            this.draw();
            this.initTabs();
        }
        , instance);
        this.`$watch`(fun(): Any? {
            return this.data;
        }
        , fun() {
            this.initTabs();
        }
        );
        this.`$watch`(fun(): Any? {
            return this.type;
        }
        , fun() {
            this.initTabs();
            this.reRender();
        }
        );
        this.`$watch`(fun(): Any? {
            return this.anim;
        }
        , fun() {
            if (this.type == "special") {
                if (this.anim != "none" && this.anim != "scroll") {
                    this.anim = "none";
                    console.warn("[ux-tabbar]配置警告: 类型[special]仅支持动效[none/scroll]", " at uni_modules/ux-frame/components/ux-tabbar/index.uts:181");
                }
            }
            this.reRender();
        }
        );
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_ux_icon = resolveEasyComponent("ux-icon", GenUniModulesUxFrameComponentsUxIconUxIconClass);
        val _component_ux_badge = resolveEasyComponent("ux-badge", GenUniModulesUxFrameComponentsUxBadgeUxBadgeClass);
        val _component_ux_placeholder = resolveEasyComponent("ux-placeholder", GenUniModulesUxFrameComponentsUxPlaceholderUxPlaceholderClass);
        return createElementVNode("view", utsMapOf("class" to "ux-tabbar", "style" to normalizeStyle(utsArrayOf(
            _ctx.tabbarStyle
        ))), utsArrayOf(
            createElementVNode("view", utsMapOf("ref" to "ux-tabbar", "class" to "ux-tabbar__content", "style" to normalizeStyle(_ctx.contentStyle)), utsArrayOf(
                if (isTrue(_ctx.render)) {
                    createElementVNode(Fragment, utsMapOf("key" to 0), RenderHelpers.renderList(_ctx.tabs, fun(it, i, _, _): VNode {
                        return createElementVNode("view", utsMapOf("id" to it.id!!, "class" to normalizeClass(utsArrayOf(
                            "ux-tabbar__item__" + _ctx.anim,
                            "transform__" + _ctx.anim
                        )), "key" to i, "onClick" to fun(){
                            _ctx.onTab(i);
                        }, "onTouchstart" to fun(){
                            return _ctx.touchstart(i);
                        }, "onTouchend" to fun(){
                            return _ctx.touchend(i);
                        }, "onTouchcancel" to fun(){
                            return _ctx.touchend(i);
                        }), utsArrayOf(
                            createElementVNode("view", utsMapOf("id" to ("" + _ctx.myId + "-hover-" + i), "class" to "ux-tabbar__hover", "style" to normalizeStyle(_ctx.hoverStyle(i))), null, 12, utsArrayOf(
                                "id"
                            )),
                            createElementVNode("view", utsMapOf("id" to ("" + _ctx.myId + "-icon-" + i), "class" to normalizeClass(utsArrayOf(
                                "ux-tabbar__icon",
                                "transform__" + _ctx.anim
                            ))), utsArrayOf(
                                createVNode(_component_ux_icon, utsMapOf("type" to if (_ctx.tabIndex == i) {
                                    it.selectedIcon;
                                } else {
                                    it.unselectedIcon;
                                }, "size" to _ctx.iconSize, "color" to if (_ctx.tabIndex == i) {
                                    it.selectedColor;
                                } else {
                                    it.unselectedColor;
                                }, "custom-font" to _ctx.customFont, "custom-family" to _ctx.customFamily), null, 8, utsArrayOf(
                                    "type",
                                    "size",
                                    "color",
                                    "custom-font",
                                    "custom-family"
                                ))
                            ), 10, utsArrayOf(
                                "id"
                            )),
                            createElementVNode("text", utsMapOf("id" to ("" + _ctx.myId + "-name-" + i), "class" to normalizeClass(utsArrayOf(
                                "ux-tabbar__name",
                                "transform__" + _ctx.anim
                            )), "style" to normalizeStyle(utsArrayOf(
                                _ctx.textStyle(i)
                            ))), toDisplayString(it.name), 15, utsArrayOf(
                                "id"
                            )),
                            createElementVNode("view", utsMapOf("id" to ("" + _ctx.myId + "-point-" + i), "class" to normalizeClass(utsArrayOf(
                                "ux-tabbar__point",
                                "transform__" + _ctx.anim
                            )), "style" to normalizeStyle(utsArrayOf(
                                _ctx.pointStyle(i)
                            ))), null, 14, utsArrayOf(
                                "id"
                            )),
                            createVNode(_component_ux_badge, utsMapOf("value" to it.badge, "dot" to it.reddot, "top" to if (_ctx.anim == "push") {
                                0;
                            } else {
                                10;
                            }, "right" to 10), null, 8, utsArrayOf(
                                "value",
                                "dot",
                                "top"
                            ))
                        ), 42, utsArrayOf(
                            "id",
                            "onClick",
                            "onTouchstart",
                            "onTouchend",
                            "onTouchcancel"
                        ));
                    }), 128);
                } else {
                    createCommentVNode("v-if", true);
                }
            ), 4),
            if (_ctx.type == "special") {
                createElementVNode("view", utsMapOf("key" to 0, "class" to "ux-tabbar__center", "style" to normalizeStyle(utsArrayOf(
                    _ctx.centerStyle
                )), "onClick" to fun(){
                    _ctx.centerTab();
                }, "onTouchstart" to _ctx.ctouchstart, "onTouchend" to _ctx.ctouchend, "onTouchcancel" to _ctx.ctouchend), utsArrayOf(
                    createElementVNode("view", utsMapOf("id" to ("" + _ctx.myId + "-hover-c"), "class" to "ux-tabbar__hover", "style" to normalizeStyle(_ctx.centerHoverStyle)), null, 12, utsArrayOf(
                        "id"
                    )),
                    renderSlot(_ctx.`$slots`, "center")
                ), 44, utsArrayOf(
                    "onClick",
                    "onTouchstart",
                    "onTouchend",
                    "onTouchcancel"
                ));
            } else {
                createCommentVNode("v-if", true);
            }
            ,
            createVNode(_component_ux_placeholder, utsMapOf("safearea" to true))
        ), 4);
    }
    open var type: String by `$props`;
    open var anim: String by `$props`;
    open var index: Number by `$props`;
    open var data: UTSArray<Any?> by `$props`;
    open var selectedColor: String by `$props`;
    open var unselectedColor: String by `$props`;
    open var iconSize: Number by `$props`;
    open var fontSize: Number by `$props`;
    open var fontBold: Boolean by `$props`;
    open var customFont: String by `$props`;
    open var customFamily: String by `$props`;
    open var border: Boolean by `$props`;
    open var borderColor: String by `$props`;
    open var corner: Number by `$props`;
    open var background: String by `$props`;
    open var centerColor: String by `$props`;
    open var zIndex: Number by `$props`;
    open var fixed: Boolean by `$props`;
    open var hover: Boolean by `$props`;
    open var tabs: UTSArray<UxTab> by `$data`;
    open var tabIndex: Number by `$data`;
    open var isPress: Boolean by `$data`;
    open var timer: Number by `$data`;
    open var render: Boolean by `$data`;
    open var radius: Number by `$data`;
    open var drawer: DrawableContext? by `$data`;
    open var height: Any? by `$data`;
    open var myId: String by `$data`;
    open var tabbarStyle: Any? by `$data`;
    open var contentStyle: String by `$data`;
    open var centerStyle: Any? by `$data`;
    open var centerHoverStyle: String by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("tabs" to utsArrayOf<UxTab>(), "tabIndex" to 0, "isPress" to false, "timer" to 0, "render" to true, "radius" to 30, "drawer" to null as DrawableContext?, "height" to (uni_getSystemInfoSync().safeAreaInsets.bottom + 54), "myId" to computed<String>(fun(): String {
            return "ux-tabbar_" + this.type + "_" + `$ux`.Random.uuid();
        }
        ), "tabbarStyle" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            if (this.fixed) {
                css.set("position", "fixed");
                css.set("bottom", 0);
            }
            css.set("height", "" + ((this.height as Number) + (if (this.type == "special") {
                this.radius;
            } else {
                0;
            }
            )) + "px");
            if (this.border && this.type == "default") {
                css.set("border-top", "1rpx solid " + this.borderColor);
            }
            css.set("background-color", "" + (if (this.type == "default") {
                this.background;
            } else {
                "transparent";
            }
            ));
            css.set("z-index", this.zIndex);
            if (this.corner > 0 && this.type == "default") {
                css.set("border-top-left-radius", "" + this.corner + "px");
                css.set("border-top-right-radius", "" + this.corner + "px");
            }
            return css;
        }
        ), "contentStyle" to computed<String>(fun(): String {
            return if (this.type == "special") {
                "margin-top: " + this.radius + "px";
            } else {
                "";
            }
            ;
        }
        ), "centerStyle" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            css.set("width", "" + this.radius * 2 + "px");
            css.set("height", "" + this.radius * 2 + "px");
            css.set("bottom", "" + (54 - this.radius - 5) + "px");
            css.set("background-color", this.centerColor);
            return css;
        }
        ), "centerHoverStyle" to computed<String>(fun(): String {
            return "background-color: #999999";
        }
        ));
    }
    override fun `$initMethods`() {
        this.textStyle = fun(i: Number): Any? {
            var css = Map<String, Any?>();
            css.set("color", "" + (if (this.tabIndex == i) {
                this.tabs[i].selectedColor;
            } else {
                this.tabs[i].unselectedColor;
            }
            ));
            css.set("font-size", "" + this.fontSize + "px");
            css.set("font-weight", "" + (if (this.fontBold) {
                "bold";
            } else {
                "normal";
            }
            ));
            return css;
        }
        ;
        this.pointStyle = fun(i: Number): String {
            return "background-color: " + (if (this.tabIndex == i) {
                this.tabs[i].selectedColor;
            } else {
                this.tabs[i].unselectedColor;
            }
            );
        }
        ;
        this.hoverStyle = fun(i: Number): String {
            return "background-color: " + this.tabs[i].selectedColor;
        }
        ;
        this.reRender = fun() {
            this.resetDraw();
            this.render = false;
            this.`$nextTick`(fun(){
                this.render = true;
                setTimeout(fun(){
                    this.draw();
                    this.animTo(this.tabIndex);
                }
                , 20);
            }
            );
        }
        ;
        this.initTabs = fun() {
            var list = utsArrayOf<UxTab>();
            this.data.forEach(fun(e: Any?, i: Number){
                var data = e!! as UxTab;
                var item = UxTab(id = data.id ?: "ux-tabbar-" + this.type + "-" + this.anim + "-" + i, name = data.name, selectedIcon = data.selectedIcon ?: "", unselectedIcon = data.unselectedIcon ?: data.selectedIcon, selectedColor = data.selectedColor ?: this.selectedColor, unselectedColor = data.unselectedColor ?: this.unselectedColor, badge = data.badge ?: 0, reddot = data.reddot ?: false, btn = data.btn ?: false, index = i);
                list.push(item);
            }
            );
            this.tabs = list as UTSArray<UxTab>;
            if (this.anim == "scroll") {
                var index = this.tabs.findIndex(fun(e: UxTab): Boolean {
                    return e.selectedIcon == "";
                });
                if (index != -1) {
                    console.error("[ux-tabbar]配置错误: 动效[scroll]需配置selectedIcon", " at uni_modules/ux-frame/components/ux-tabbar/index.uts:266");
                    this.anim = "none";
                }
            } else {
                var a = this.tabs.findIndex(fun(e: UxTab): Boolean {
                    return e.selectedIcon != "" && e.unselectedIcon == "";
                }
                );
                var b = this.tabs.findIndex(fun(e: UxTab): Boolean {
                    return e.selectedIcon == "" && e.unselectedIcon != "";
                }
                );
                if (a != -1 || b != -1) {
                    console.error("[ux-tabbar]\u914D\u7F6E\u9519\u8BEF: \u52A8\u6548[" + this.anim + "]\u9700\u540C\u65F6\u914D\u7F6EselectedIcon & unselectedIcon", " at uni_modules/ux-frame/components/ux-tabbar/index.uts:274");
                }
            }
            if (this.type == "special") {
                if (this.anim != "none" && this.anim != "scroll") {
                    this.anim = "none";
                    console.warn("[ux-tabbar]配置警告: 类型[special]仅支持动效[none/scroll]", " at uni_modules/ux-frame/components/ux-tabbar/index.uts:282");
                }
                if (this.tabs.length % 2 != 0) {
                    console.error("[ux-tabbar]配置错误: 类型[special]tabs数量必须为偶数", " at uni_modules/ux-frame/components/ux-tabbar/index.uts:286");
                    this.type = "default";
                }
                this.tabs.splice(this.tabs.length / 2, 0, UxTab(id = "center", name = "", selectedIcon = "", unselectedIcon = "", selectedColor = "", unselectedColor = "", badge = 0, reddot = false, btn = false, index = -1));
            }
            setTimeout(fun(){
                this.animTo(this.tabIndex);
            }
            , 100);
        }
        ;
        this.resetDraw = fun() {
            this.drawer!!.reset();
            this.drawer!!.update();
        }
        ;
        this.draw = fun() {
            this.resetDraw();
            if (this.type == "default") {
                this.draw0();
            } else if (this.type == "special") {
                this.draw1();
            }
        }
        ;
        this.draw0 = fun() {};
        this.draw1 = fun() {
            var sw = uni_getSystemInfoSync().screenWidth;
            var width = this.radius + 10;
            var height = this.radius + 10;
            var startX = sw / 2 - width;
            var offset: Number = 20;
            this.drawer!!.beginPath();
            if (this.corner > 0) {
                var lc_s1: Number = 0;
                var lc_s2 = this.corner * 2;
                var lc_c1_1: Number = 0;
                var lc_c1_2: Number = 0;
                var lc_c2_1: Number = 0;
                var lc_c2_2: Number = 0;
                var lc_e1 = this.corner * 2;
                var lc_e2: Number = 0;
                this.drawer!!.moveTo(lc_s1, lc_s2);
                this.drawer!!.bezierCurveTo(lc_c1_1, lc_c1_2, lc_c2_1, lc_c2_2, lc_e1, lc_e2);
            } else {
                this.drawer!!.moveTo(0, 0);
            }
            var l_s1 = startX - offset;
            var l_s2: Number = 0;
            var l_c1_1 = startX;
            var l_c1_2 = l_s2;
            var l_c2_x = width * ((1 as Number) / 5);
            var l_c2_y = height;
            var l_c2_1 = startX + l_c2_x;
            var l_c2_2 = l_s2 + l_c2_y;
            var l_e1 = startX + width;
            var l_e2 = height;
            this.drawer!!.lineTo(l_s1, l_s2);
            this.drawer!!.bezierCurveTo(l_c1_1, l_c1_2, l_c2_1, l_c2_2, l_e1, l_e2);
            var r_c1_1 = startX + width * 2 - l_c2_x;
            var r_c1_2 = l_c2_2;
            var r_c2_1 = startX + width * 2;
            var r_c2_2 = l_c1_2;
            var r_e1 = startX + width * 2 + offset;
            var r_e2: Number = 0;
            this.drawer!!.bezierCurveTo(r_c1_1, r_c1_2, r_c2_1, r_c2_2, r_e1, r_e2);
            if (this.corner > 0) {
                var lc_s1 = sw - this.corner;
                var lc_c1_1 = sw;
                var lc_c1_2: Number = 0;
                var lc_c2_1 = sw;
                var lc_c2_2: Number = 0;
                var lc_e1 = sw;
                var lc_e2 = this.corner;
                this.drawer!!.bezierCurveTo(lc_c1_1, lc_c1_2, lc_c2_1, lc_c2_2, lc_e1, lc_e2);
            } else {
                this.drawer!!.lineTo(sw, 0);
            }
            this.drawer!!.lineTo(sw, 54);
            this.drawer!!.lineTo(0, 54);
            if (this.corner > 0) {
                this.drawer!!.lineTo(0, this.corner * 2);
            } else {
                this.drawer!!.lineTo(0, 0);
            }
            this.drawer!!.closePath();
            this.drawer!!.fillStyle = this.background;
            this.drawer!!.fill();
            if (this.border) {
                this.drawer!!.beginPath();
                if (this.corner > 0) {
                    var lc_s1: Number = 0;
                    var lc_s2 = this.corner * 2;
                    var lc_c1_1: Number = 0;
                    var lc_c1_2: Number = 0;
                    var lc_c2_1: Number = 0;
                    var lc_c2_2: Number = 0;
                    var lc_e1 = this.corner * 2;
                    var lc_e2: Number = 0;
                    this.drawer!!.moveTo(lc_s1, lc_s2);
                    this.drawer!!.bezierCurveTo(lc_c1_1, lc_c1_2, lc_c2_1, lc_c2_2, lc_e1, lc_e2);
                } else {
                    this.drawer!!.moveTo(0, 0);
                }
                var l_s1 = startX - offset;
                var l_s2: Number = 0;
                var l_c1_1 = startX;
                var l_c1_2 = l_s2;
                var l_c2_x = width * ((1 as Number) / 5);
                var l_c2_y = height;
                var l_c2_1 = startX + l_c2_x;
                var l_c2_2 = l_s2 + l_c2_y;
                var l_e1 = startX + width;
                var l_e2 = height;
                this.drawer!!.lineTo(l_s1, l_s2);
                this.drawer!!.bezierCurveTo(l_c1_1, l_c1_2, l_c2_1, l_c2_2, l_e1, l_e2);
                var r_c1_1 = startX + width * 2 - l_c2_x;
                var r_c1_2 = l_c2_2;
                var r_c2_1 = startX + width * 2;
                var r_c2_2 = l_c1_2;
                var r_e1 = startX + width * 2 + offset;
                var r_e2: Number = 0;
                this.drawer!!.bezierCurveTo(r_c1_1, r_c1_2, r_c2_1, r_c2_2, r_e1, r_e2);
                if (this.corner > 0) {
                    var lc_s1 = sw - this.corner;
                    var lc_c1_1 = sw;
                    var lc_c1_2: Number = 0;
                    var lc_c2_1 = sw;
                    var lc_c2_2: Number = 0;
                    var lc_e1 = sw;
                    var lc_e2 = this.corner;
                    this.drawer!!.bezierCurveTo(lc_c1_1, lc_c1_2, lc_c2_1, lc_c2_2, lc_e1, lc_e2);
                } else {
                    this.drawer!!.lineTo(sw, 0);
                }
                this.drawer!!.strokeStyle = this.borderColor;
                this.drawer!!.stroke();
            }
            this.drawer!!.update();
        }
        ;
        this.centerTab = fun() {
            this.`$emit`("center");
        }
        ;
        this.onTab = fun(i: Number) {
            if (this.tabs[i].btn!!) {
                this.`$emit`("click", this.tabs[i].index!!);
                return;
            }
            this.animTo(i);
            this.tabIndex = i;
            this.`$emit`("change", this.tabs[i].index!!);
        }
        ;
        this.animTo = fun(index: Number) {
            if (this.anim == "none") {
                this.noneAnim(this.tabIndex, index);
            } else if (this.anim == "scroll") {
                this.scrollAnim(this.tabIndex, index);
            } else if (this.anim == "push") {
                this.pushAnim(this.tabIndex, index);
            } else if (this.anim == "water") {
                this.waterAnim(this.tabIndex, index);
            }
        }
        ;
        this.noneAnim = fun(_old: Number, _new: Number) {};
        this.scrollAnim = fun(_old: Number, _new: Number) {
            var anim = fun(i: Number, show: Boolean): Unit {
                if (this.tabs[i].btn!!) {
                    return;
                }
                if (show) {
                    uni_getElementById("" + this.myId + "-icon-" + i)?.style?.setProperty("transform", "translate(0, -50%)");
                    uni_getElementById("" + this.myId + "-name-" + i)?.style?.setProperty("transform", "translate(0, -230%)");
                    uni_getElementById("" + this.myId + "-icon-" + i)?.style?.setProperty("opacity", 0);
                    uni_getElementById("" + this.myId + "-name-" + i)?.style?.setProperty("opacity", 1);
                    uni_getElementById("" + this.myId + "-point-" + i)?.style?.setProperty("opacity", 1);
                    uni_getElementById("" + this.myId + "-point-" + i)?.style?.setProperty("transform", "scale(1)");
                } else {
                    uni_getElementById("" + this.myId + "-icon-" + i)?.style?.setProperty("transform", "translate(0, 0%)");
                    uni_getElementById("" + this.myId + "-name-" + i)?.style?.setProperty("transform", "translate(0, 100%)");
                    uni_getElementById("" + this.myId + "-icon-" + i)?.style?.setProperty("opacity", 1);
                    uni_getElementById("" + this.myId + "-name-" + i)?.style?.setProperty("opacity", 0);
                    uni_getElementById("" + this.myId + "-point-" + i)?.style?.setProperty("opacity", 0);
                    uni_getElementById("" + this.myId + "-point-" + i)?.style?.setProperty("transform", "scale(0)");
                }
            }
            ;
            if (_old == _new) {
                anim(_new, true);
                run {
                    var i: Number = 0;
                    while(i < this.tabs.length){
                        if (i == _new) {
                            i++;
                            continue;
                        }
                        anim(i, false);
                        i++;
                    }
                }
            } else {
                anim(_old, false);
                anim(_new, true);
            }
        }
        ;
        this.pushAnim = fun(_old: Number, _new: Number) {
            var anim = fun(i: Number, show: Boolean): Unit {
                var per = (100 as Number) / this.tabs.length / 100 * 750;
                var width = per + ((5 as Number) / 100 * 750);
                var cwidth = (750 - 20 * 2 - width) / (this.tabs.length - 1);
                if (show) {
                    uni_getElementById(this.tabs[i].id!!)?.style?.setProperty("width", "" + width + "rpx");
                    var color = `$ux`.Color.hexToRgba(this.tabs[i].selectedColor, 0.2);
                    uni_getElementById(this.tabs[i].id!!)?.style?.setProperty("background-color", color);
                    uni_getElementById("" + this.myId + "-name-" + i)?.style?.setProperty("opacity", 1);
                } else {
                    uni_getElementById(this.tabs[i].id!!)?.style?.setProperty("width", "" + cwidth + "rpx");
                    uni_getElementById(this.tabs[i].id!!)?.style?.setProperty("background-color", "transparent");
                    uni_getElementById("" + this.myId + "-name-" + i)?.style?.setProperty("opacity", 0);
                }
            }
            ;
            anim(_new, true);
            run {
                var i: Number = 0;
                while(i < this.tabs.length){
                    if (i == _new) {
                        i++;
                        continue;
                    }
                    anim(i, false);
                    i++;
                }
            }
        }
        ;
        this.waterAnim = fun(_old: Number, _new: Number) {
            var w = uni_getSystemInfoSync().screenWidth / this.data.length;
            var width = w * 0.7;
            var height: Number = 12;
            var _height = height;
            var left = (w - width) / 2;
            var startX = w * _old + left;
            var endX = w * _new + left;
            var dropStart = height - 10;
            var dropHeight: Number = 35;
            var dropEnd = dropStart + dropHeight;
            var dropStep: Number = 2;
            var radius: Number = 5;
            var forward = startX < endX;
            var step = 5 * Math.abs(_new - _old);
            var moveAnim = fun(drop: Boolean): Unit {};
            moveAnim = fun(drop: Boolean): Unit {
                var per = if (forward) {
                    (startX / endX);
                } else {
                    (endX / startX);
                }
                ;
                per = if (per < 0.3) {
                    0.3;
                } else {
                    if (per > 1) {
                        1;
                    } else {
                        per;
                    }
                    ;
                }
                ;
                this.drawer!!.reset();
                this.drawer!!.beginPath();
                if (drop) {
                    var dp = 1 - (dropEnd - dropStart) / dropHeight;
                    height = if (dp < 0.5) {
                        _height * (1 + dp);
                    } else {
                        _height * dp;
                    }
                    ;
                    if (height < _height) {
                        height = _height;
                    }
                }
                var l_s1 = startX;
                var l_s2: Number = 0;
                var l_c1_x = width / 2 * ((2 as Number) / 3);
                var l_c1_y = height * ((1 as Number) / 5);
                var l_c1_1 = l_s1 + l_c1_x;
                var l_c1_2 = l_s2 + l_c1_y;
                var l_c2_x = width / 2 * ((4 as Number) / 5);
                var l_c2_y = height;
                var l_c2_1 = l_s1 + l_c2_x;
                var l_c2_2 = l_s2 + l_c2_y;
                var l_e1 = l_s1 + width / 2;
                var l_e2 = height;
                this.drawer!!.moveTo(l_s1, l_s2);
                this.drawer!!.bezierCurveTo(l_c1_1, l_c1_2, l_c2_1, l_c2_2, l_e1, l_e2);
                var r_c1_1 = startX + width - l_c2_x;
                var r_c1_2 = l_c2_2;
                var r_c2_1 = startX + width - l_c1_x;
                var r_c2_2 = l_c1_2;
                var r_e1 = startX + width;
                var r_e2: Number = 0;
                this.drawer!!.bezierCurveTo(r_c1_1, r_c1_2, r_c2_1, r_c2_2, r_e1, r_e2);
                this.drawer!!.closePath();
                this.drawer!!.fillStyle = `$ux`.Color.hexToRgba(this.tabs[_new].selectedColor!!, per);
                this.drawer!!.fill();
                if (drop) {
                    var dp = (dropEnd - dropStart) / dropHeight;
                    dp = if (dp < 0) {
                        0;
                    } else {
                        dp;
                    }
                    ;
                    var dx = startX + (width / 2) - radius;
                    var dy = dropStart;
                    this.drawer!!.beginPath();
                    this.drawer!!.moveTo(dx, dy);
                    this.drawer!!.arc(dx + radius, dy + radius, radius, 0, Math.PI * 2, false);
                    this.drawer!!.closePath();
                    this.drawer!!.fillStyle = `$ux`.Color.hexToRgba(this.tabs[_new].selectedColor!!, dp);
                    this.drawer!!.fill();
                }
                this.drawer!!.update();
                if (forward) {
                    if (startX < endX) {
                        setTimeout(fun(){
                            moveAnim(false);
                        }, 0);
                        startX += step;
                        if (endX - startX < step) {
                            startX = endX;
                        }
                    } else {
                        if (dropStart < dropEnd) {
                            setTimeout(fun(){
                                moveAnim(true);
                            }
                            , 0);
                        }
                        dropStart += dropStep;
                    }
                } else {
                    if (startX > endX) {
                        setTimeout(fun(){
                            moveAnim(false);
                        }, 0);
                        startX -= step;
                        if (startX - endX < step) {
                            startX = endX;
                        }
                    } else {
                        if (dropStart < dropEnd) {
                            setTimeout(fun(){
                                moveAnim(true);
                            }
                            , 0);
                        }
                        dropStart += dropStep;
                    }
                }
            }
            ;
            moveAnim(false);
        }
        ;
        this.touchstart = fun(i: Number) {
            if (!this.hover) {
                return;
            }
            if (this.isPress) {
                return;
            }
            this.timer = Date().getTime();
            this.isPress = true;
            this.hoverAnim("" + this.myId + "-hover-" + i, true);
        }
        ;
        this.touchend = fun(i: Number) {
            var offset = Date().getTime() - this.timer;
            if (offset < 110) {
                setTimeout(fun(){
                    this.isPress = false;
                    this.hoverAnim("" + this.myId + "-hover-" + i, false);
                }, 110 - offset);
            } else {
                this.isPress = false;
                this.hoverAnim("" + this.myId + "-hover-" + i, false);
            }
        }
        ;
        this.ctouchstart = fun() {
            if (!this.hover) {
                return;
            }
            if (this.isPress) {
                return;
            }
            this.timer = Date().getTime();
            this.isPress = true;
            this.hoverAnim("" + this.myId + "-hover-c", true);
        }
        ;
        this.ctouchend = fun() {
            var offset = Date().getTime() - this.timer;
            if (offset < 110) {
                setTimeout(fun(){
                    this.isPress = false;
                    this.hoverAnim("" + this.myId + "-hover-c", false);
                }, 110 - offset);
            } else {
                this.isPress = false;
                this.hoverAnim("" + this.myId + "-hover-c", false);
            }
        }
        ;
        this.hoverAnim = fun(id: String, show: Boolean) {
            uni_getElementById(id)?.style?.setProperty("opacity", if (show) {
                0.1;
            } else {
                0;
            }
            );
            uni_getElementById(id)?.style?.setProperty("transform", if (show) {
                "scale(1)";
            } else {
                "scale(0)";
            }
            );
        }
        ;
    }
    open lateinit var textStyle: (i: Number) -> Any?;
    open lateinit var pointStyle: (i: Number) -> String;
    open lateinit var hoverStyle: (i: Number) -> String;
    open lateinit var reRender: () -> Unit;
    open lateinit var initTabs: () -> Unit;
    open lateinit var resetDraw: () -> Unit;
    open lateinit var draw: () -> Unit;
    open lateinit var draw0: () -> Unit;
    open lateinit var draw1: () -> Unit;
    open lateinit var centerTab: () -> Unit;
    open lateinit var onTab: (i: Number) -> Unit;
    open lateinit var animTo: (index: Number) -> Unit;
    open lateinit var noneAnim: (_old: Number, _new: Number) -> Unit;
    open lateinit var scrollAnim: (_old: Number, _new: Number) -> Unit;
    open lateinit var pushAnim: (_old: Number, _new: Number) -> Unit;
    open lateinit var waterAnim: (_old: Number, _new: Number) -> Unit;
    open lateinit var touchstart: (i: Number) -> Unit;
    open lateinit var touchend: (i: Number) -> Unit;
    open lateinit var ctouchstart: () -> Unit;
    open lateinit var ctouchend: () -> Unit;
    open lateinit var hoverAnim: (id: String, show: Boolean) -> Unit;
    companion object {
        var name = "ux-tabbar";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-tabbar" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 54, "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "flex-start", "zIndex" to 10000)), "ux-tabbar__center" to utsMapOf(".ux-tabbar " to utsMapOf("position" to "absolute", "width" to 60, "height" to 60, "borderRadius" to 50, "backgroundColor" to "#ffffff", "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "center", "boxShadow" to "0 0 10px 2px #fdfdfd")), "ux-tabbar__content" to utsMapOf(".ux-tabbar " to utsMapOf("width" to "100%", "height" to 54, "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "space-around")), "ux-tabbar__item__none" to utsMapOf(".ux-tabbar .ux-tabbar__content " to utsMapOf("flex" to 1, "height" to "100%", "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "center")), "ux-tabbar__icon" to utsMapOf(".ux-tabbar .ux-tabbar__content .ux-tabbar__item__none " to utsMapOf("width" to 25, "height" to 25, "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "center"), ".ux-tabbar .ux-tabbar__content .ux-tabbar__item__scroll " to utsMapOf("position" to "absolute", "width" to 36, "height" to 36, "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "center"), ".ux-tabbar .ux-tabbar__content .ux-tabbar__item__push " to utsMapOf("height" to 36, "marginLeft" to "40rpx", "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "flex-start"), ".ux-tabbar .ux-tabbar__content .ux-tabbar__item__water " to utsMapOf("position" to "absolute", "top" to "30%", "width" to 36, "height" to 36, "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "center")), "ux-tabbar__name" to utsMapOf(".ux-tabbar .ux-tabbar__content .ux-tabbar__item__none " to utsMapOf("color" to "#000000", "fontSize" to 12), ".ux-tabbar .ux-tabbar__content .ux-tabbar__item__scroll " to utsMapOf("position" to "absolute", "top" to "100%", "color" to "#000000", "fontSize" to 18), ".ux-tabbar .ux-tabbar__content .ux-tabbar__item__push " to utsMapOf("marginLeft" to "20rpx", "color" to "#000000", "fontSize" to 18, "opacity" to 0), ".ux-tabbar .ux-tabbar__content .ux-tabbar__item__water " to utsMapOf("position" to "absolute", "top" to "100%", "color" to "#000000", "fontSize" to 18)), "ux-tabbar__item__scroll" to utsMapOf(".ux-tabbar .ux-tabbar__content " to utsMapOf("flex" to 1, "height" to "100%", "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "center")), "ux-tabbar__item__push" to utsMapOf(".ux-tabbar .ux-tabbar__content " to utsMapOf("width" to "150rpx", "height" to "70%", "borderRadius" to 54, "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "flex-start", "backgroundColor" to "rgba(0,0,0,0)")), "ux-tabbar__item__water" to utsMapOf(".ux-tabbar .ux-tabbar__content " to utsMapOf("flex" to 1, "height" to "100%", "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "center")), "ux-tabbar__point" to utsMapOf(".ux-tabbar .ux-tabbar__content " to utsMapOf("position" to "absolute", "bottom" to 5, "width" to 5, "height" to 5, "borderRadius" to 5, "opacity" to 0)), "ux-tabbar__hover" to padStyleMapOf(utsMapOf("position" to "absolute", "width" to 50, "height" to 50, "opacity" to 0, "transform" to "scale(0)", "borderRadius" to 50, "transitionProperty" to "transform,opacity", "transitionDuration" to "0.1s", "transitionTimingFunction" to "linear")), "transform__scroll" to padStyleMapOf(utsMapOf("transitionProperty" to "transform,opacity", "transitionDuration" to "0.3s", "transitionTimingFunction" to "ease-in-out")), "transform__push" to padStyleMapOf(utsMapOf("transitionProperty" to "transform,opacity,width,backgroundColor", "transitionDuration" to "0.2s", "transitionTimingFunction" to "linear")), "transform__water" to padStyleMapOf(utsMapOf("transitionProperty" to "transform,opacity", "transitionDuration" to "0.3s", "transitionTimingFunction" to "ease-in-out")), "@TRANSITION" to utsMapOf("ux-tabbar__hover" to utsMapOf("property" to "transform,opacity", "duration" to "0.1s", "timingFunction" to "linear"), "transform__scroll" to utsMapOf("property" to "transform,opacity", "duration" to "0.3s", "timingFunction" to "ease-in-out"), "transform__push" to utsMapOf("property" to "transform,opacity,width,backgroundColor", "duration" to "0.2s", "timingFunction" to "linear"), "transform__water" to utsMapOf("property" to "transform,opacity", "duration" to "0.3s", "timingFunction" to "ease-in-out")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf("click" to null, "center" to null, "change" to null);
        var props = normalizePropsOptions(utsMapOf("type" to utsMapOf("type" to "String", "default" to "default"), "anim" to utsMapOf("type" to "String", "default" to "none"), "index" to utsMapOf("type" to "Number", "default" to 0), "data" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf<Any>();
        }
        ), "selectedColor" to utsMapOf("type" to "String", "default" to `$ux`.theme.primary), "unselectedColor" to utsMapOf("type" to "String", "default" to `$ux`.theme.main), "iconSize" to utsMapOf("type" to "Number", "default" to 18), "fontSize" to utsMapOf("type" to "Number", "default" to 12), "fontBold" to utsMapOf("type" to "Boolean", "default" to false), "customFont" to utsMapOf("type" to "String", "default" to ""), "customFamily" to utsMapOf("type" to "String", "default" to ""), "border" to utsMapOf("type" to "Boolean", "default" to false), "borderColor" to utsMapOf("type" to "String", "default" to `$ux`.theme.border), "corner" to utsMapOf("type" to "Number", "default" to 0), "background" to utsMapOf("type" to "String", "default" to "#FFFFFF"), "centerColor" to utsMapOf("type" to "String", "default" to "#FFFFFF"), "zIndex" to utsMapOf("type" to "Number", "default" to 10000), "fixed" to utsMapOf("type" to "Boolean", "default" to true), "hover" to utsMapOf("type" to "Boolean", "default" to true)));
        var propsNeedCastKeys = utsArrayOf(
            "type",
            "anim",
            "index",
            "data",
            "selectedColor",
            "unselectedColor",
            "iconSize",
            "fontSize",
            "fontBold",
            "customFont",
            "customFamily",
            "border",
            "borderColor",
            "corner",
            "background",
            "centerColor",
            "zIndex",
            "fixed",
            "hover"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
