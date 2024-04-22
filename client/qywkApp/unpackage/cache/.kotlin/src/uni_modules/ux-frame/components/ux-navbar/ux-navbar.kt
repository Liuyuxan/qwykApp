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
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack;
open class GenUniModulesUxFrameComponentsUxNavbarUxNavbar : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_ux_placeholder = resolveEasyComponent("ux-placeholder", GenUniModulesUxFrameComponentsUxPlaceholderUxPlaceholderClass);
        val _component_ux_icon = resolveEasyComponent("ux-icon", GenUniModulesUxFrameComponentsUxIconUxIconClass);
        return createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
            "ux-navbar",
            if (_ctx.fixed) {
                "ux-navbar__fixed";
            } else {
                "";
            }
        )), "style" to normalizeStyle(utsArrayOf(
            _ctx.style
        ))), utsArrayOf(
            createVNode(_component_ux_placeholder, utsMapOf("statusbar" to true)),
            createElementVNode("view", utsMapOf("class" to "ux-navbar__content"), utsArrayOf(
                if (isTrue(_ctx.alignLeft)) {
                    createElementVNode("view", utsMapOf("key" to 0, "class" to "ux-navbar__title_left"), utsArrayOf(
                        if (isTrue(_ctx.`$slots`["left"] == null && _ctx.goback)) {
                            createElementVNode("view", utsMapOf("key" to 0, "id" to "goback", "class" to "ux-navbar__goback", "onClick" to fun(){
                                _ctx.onBack();
                            }), utsArrayOf(
                                createVNode(_component_ux_icon, utsMapOf("type" to "arrowleft", "size" to 22, "color" to _ctx.color), null, 8, utsArrayOf(
                                    "color"
                                )),
                                if (_ctx.gobackText != "") {
                                    createElementVNode("text", utsMapOf("key" to 0, "class" to "ux-navbar__goback__text"), toDisplayString(_ctx.gobackText), 1);
                                } else {
                                    createCommentVNode("v-if", true);
                                }
                            ), 8, utsArrayOf(
                                "onClick"
                            ));
                        } else {
                            createCommentVNode("v-if", true);
                        },
                        if (_ctx.`$slots`["left"] != null) {
                            createElementVNode("view", utsMapOf("key" to 1, "class" to "ux-navbar__goback"), utsArrayOf(
                                renderSlot(_ctx.`$slots`, "left")
                            ));
                        } else {
                            createCommentVNode("v-if", true);
                        },
                        renderSlot(_ctx.`$slots`, "title", UTSJSONObject(), fun(): UTSArray<Any> {
                            return utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "ux-navbar__title__content", "style" to normalizeStyle(utsArrayOf(
                                    _ctx.titleStyle
                                ))), toDisplayString(_ctx.title), 5)
                            );
                        })
                    ));
                } else {
                    createElementVNode("view", utsMapOf("key" to 1, "class" to "ux-navbar__title"), utsArrayOf(
                        if (isTrue(_ctx.`$slots`["left"] == null && _ctx.goback)) {
                            createElementVNode("view", utsMapOf("key" to 0, "id" to "goback", "class" to "ux-navbar__goback", "onClick" to fun(){
                                _ctx.onBack();
                            }), utsArrayOf(
                                createVNode(_component_ux_icon, utsMapOf("type" to "arrowleft", "size" to 22, "color" to _ctx.color, "xstyle" to utsArrayOf(
                                    "margin-left: 14px"
                                )), null, 8, utsArrayOf(
                                    "color"
                                )),
                                if (_ctx.gobackText != "") {
                                    createElementVNode("text", utsMapOf("key" to 0, "class" to "ux-navbar__goback__text"), toDisplayString(_ctx.gobackText), 1);
                                } else {
                                    createCommentVNode("v-if", true);
                                }
                            ), 8, utsArrayOf(
                                "onClick"
                            ));
                        } else {
                            createCommentVNode("v-if", true);
                        }
                        ,
                        if (_ctx.`$slots`["left"] != null) {
                            createElementVNode("view", utsMapOf("key" to 1, "class" to "ux-navbar__goback"), utsArrayOf(
                                renderSlot(_ctx.`$slots`, "left")
                            ));
                        } else {
                            createCommentVNode("v-if", true);
                        }
                        ,
                        renderSlot(_ctx.`$slots`, "title", UTSJSONObject(), fun(): UTSArray<Any> {
                            return utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "ux-navbar__title__content", "style" to normalizeStyle(utsArrayOf(
                                    _ctx.titleStyle
                                ))), toDisplayString(_ctx.title), 5)
                            );
                        }
                        )
                    ));
                }
                ,
                createElementVNode("view", utsMapOf("class" to "ux-navbar__btn"), utsArrayOf(
                    renderSlot(_ctx.`$slots`, "right")
                ))
            ))
        ), 6);
    }
    open var title: String by `$props`;
    open var alignLeft: Boolean by `$props`;
    open var color: String by `$props`;
    open var size: Number by `$props`;
    open var bold: Boolean by `$props`;
    open var border: Boolean by `$props`;
    open var borderColor: String by `$props`;
    open var colors: UTSArray<Any?> by `$props`;
    open var goback: Boolean by `$props`;
    open var gobackText: String by `$props`;
    open var zIndex: Number by `$props`;
    open var fixed: Boolean by `$props`;
    open var height: Any? by `$data`;
    open var style: Any? by `$data`;
    open var titleStyle: Any? by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("height" to (uni_getSystemInfoSync().statusBarHeight + 44), "style" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            css.set("height", "" + this.height + "px");
            css.set("z-index", this.zIndex);
            if (this.border) {
                css.set("border-bottom", "1rpx solid " + this.borderColor);
            }
            var colors = utsArrayOf<String>();
            this.colors.forEach(fun(e: Any?){
                colors.push(e!! as String);
            }
            );
            if (colors.length == 0) {
                css.set("background-color", if (`$ux`.theme.theme == null || `$ux`.theme.theme!! == "") {
                    "#FFFFFF";
                } else {
                    `$ux`.theme.theme!!;
                });
            } else if (colors.length == 1) {
                css.set("background-color", colors[0]);
            } else {
                css.set("background", "linear-gradient(to bottom, " + colors.join(",") + ")");
            }
            return css;
        }
        ), "titleStyle" to computed<Any?>(fun(): Any? {
            var css = Map<String, Any?>();
            css.set("color", this.color);
            css.set("font-size", `$ux`.Util.addUnit(this.size));
            css.set("height", "" + `$ux`.Util.addUnit(this.size + 4));
            css.set("font-weight", if (this.bold) {
                "bold";
            } else {
                "normal";
            }
            );
            return css;
        }
        ));
    }
    override fun `$initMethods`() {
        this.onBack = fun() {
            uni_navigateBack(NavigateBackOptions(delta = 1));
        }
        ;
    }
    open lateinit var onBack: () -> Unit;
    companion object {
        var name = "ux-navbar";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-navbar" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 44, "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "flex-start", "zIndex" to 10000)), "ux-navbar__fixed" to padStyleMapOf(utsMapOf("position" to "fixed", "top" to 0)), "ux-navbar__content" to utsMapOf(".ux-navbar " to utsMapOf("flex" to 1, "width" to "100%", "height" to 44)), "ux-navbar__title" to utsMapOf(".ux-navbar .ux-navbar__content " to utsMapOf("position" to "absolute", "top" to 0, "left" to 0, "right" to 0, "height" to 44, "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "center")), "ux-navbar__goback" to utsMapOf(".ux-navbar .ux-navbar__content .ux-navbar__title " to utsMapOf("position" to "absolute", "left" to 0, "minWidth" to 40, "height" to 40, "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "flex-start"), ".ux-navbar .ux-navbar__content .ux-navbar__title_left " to utsMapOf("paddingLeft" to 14, "paddingRight" to 5, "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "flex-start")), "ux-navbar__goback__text" to utsMapOf(".ux-navbar .ux-navbar__content .ux-navbar__title .ux-navbar__goback " to utsMapOf("fontSize" to 14, "marginLeft" to 8, "color" to "#000000", "paddingBottom" to 1), ".ux-navbar .ux-navbar__content .ux-navbar__title_left .ux-navbar__goback " to utsMapOf("fontSize" to 14, "marginLeft" to 8, "color" to "#000000", "paddingBottom" to 1)), "ux-navbar__title__content" to utsMapOf(".ux-navbar .ux-navbar__content .ux-navbar__title " to utsMapOf("maxWidth" to 200, "height" to 18, "fontSize" to 16, "color" to "#333333", "fontWeight" to "bold", "textOverflow" to "ellipsis", "textAlign" to "center"), ".ux-navbar .ux-navbar__content .ux-navbar__title_left " to utsMapOf("marginLeft" to 5, "width" to 150, "height" to 18, "fontSize" to 16, "color" to "#333333", "fontWeight" to "bold", "textOverflow" to "ellipsis")), "ux-navbar__title_left" to utsMapOf(".ux-navbar .ux-navbar__content " to utsMapOf("position" to "absolute", "top" to 0, "left" to 0, "right" to 0, "height" to 44, "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "flex-start")), "ux-navbar__btn" to utsMapOf(".ux-navbar .ux-navbar__content " to utsMapOf("position" to "absolute", "right" to 0, "width" to "50%", "height" to 44, "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "flex-end")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf("title" to utsMapOf("type" to "String", "default" to ""), "alignLeft" to utsMapOf("type" to "Boolean", "default" to false), "color" to utsMapOf("type" to "String", "default" to `$ux`.theme.title), "size" to utsMapOf("type" to "Number", "default" to 15), "bold" to utsMapOf("type" to "Boolean", "default" to true), "border" to utsMapOf("type" to "Boolean", "default" to true), "borderColor" to utsMapOf("type" to "String", "default" to `$ux`.theme.border), "colors" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<String> {
            return utsArrayOf<String>();
        }
        ), "goback" to utsMapOf("type" to "Boolean", "default" to true), "gobackText" to utsMapOf("type" to "String", "default" to ""), "zIndex" to utsMapOf("type" to "Number", "default" to 10000), "fixed" to utsMapOf("type" to "Boolean", "default" to false)));
        var propsNeedCastKeys = utsArrayOf(
            "title",
            "alignLeft",
            "color",
            "size",
            "bold",
            "border",
            "borderColor",
            "colors",
            "goback",
            "gobackText",
            "zIndex",
            "fixed"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
