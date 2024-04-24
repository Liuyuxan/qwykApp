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
open class GenPagesTabbarTabbar : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onMounted(fun() {
            this.`$nextTick`(fun(){
                this.acAnimate = "animate-ac";
            }
            );
        }
        , instance);
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_showWindow = resolveEasyComponent("showWindow", GenComponentsShowWindowShowWindowClass);
        val _component_community = resolveEasyComponent("community", GenComponentsCommunityCommunityClass);
        val _component_home = resolveEasyComponent("home", GenComponentsHomeHomeClass);
        val _component_healthComponent = resolveEasyComponent("healthComponent", GenComponentsHealthComponentHealthComponentClass);
        val _component_myPage = resolveEasyComponent("myPage", GenComponentsMyPageMyPageClass);
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            withDirectives(createElementVNode("image", utsMapOf("class" to "gif center", "style" to normalizeStyle(utsMapOf("width" to "100%", "bottom" to "2%")), "src" to "/static/animate/leaf_animate.gif", "mode" to "widthFix"), null, 4), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.isShowGif
                )
            )),
            withDirectives(createElementVNode("image", utsMapOf("class" to "gif center", "style" to normalizeStyle(utsMapOf("width" to "60%", "bottom" to "38%")), "src" to "/static/animate/energy_animate.gif", "mode" to "widthFix"), null, 4), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.isShowGif
                )
            )),
            withDirectives(createElementVNode("view", null, utsArrayOf(
                createVNode(_component_showWindow)
            ), 512), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.tabIndex == 0
                )
            )),
            withDirectives(createElementVNode("view", null, utsArrayOf(
                createVNode(_component_community)
            ), 512), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.tabIndex == 1
                )
            )),
            withDirectives(createElementVNode("view", null, utsArrayOf(
                createVNode(_component_home)
            ), 512), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.tabIndex == 2
                )
            )),
            withDirectives(createElementVNode("view", null, utsArrayOf(
                createVNode(_component_healthComponent)
            ), 512), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.tabIndex == 3
                )
            )),
            withDirectives(createElementVNode("view", null, utsArrayOf(
                createVNode(_component_myPage)
            ), 512), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.tabIndex == 4
                )
            )),
            createElementVNode("view", utsMapOf("class" to "tabbar"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "tab-item", "onClick" to fun(){
                    _ctx.changeTab(0);
                }
                ), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "tab-icon", "src" to if (0 === _ctx.tabIndex) {
                        _ctx.tabs[0].selectedIcon;
                    } else {
                        _ctx.tabs[0].unselectedIcon;
                    }
                    , "mode" to "widthFix"), null, 8, utsArrayOf(
                        "src"
                    )),
                    createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "tab-text",
                        if (0 === _ctx.tabIndex) {
                            "active-text";
                        } else {
                            "";
                        }
                    ))), toDisplayString(_ctx.tabs[0].name), 3)
                ), 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("class" to "tab-item", "onClick" to fun(){
                    _ctx.changeTab(1);
                }
                ), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "tab-icon", "src" to if (1 === _ctx.tabIndex) {
                        _ctx.tabs[1].selectedIcon;
                    } else {
                        _ctx.tabs[1].unselectedIcon;
                    }
                    , "mode" to "widthFix"), null, 8, utsArrayOf(
                        "src"
                    )),
                    createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "tab-text",
                        if (1 === _ctx.tabIndex) {
                            "active-text";
                        } else {
                            "";
                        }
                    ))), toDisplayString(_ctx.tabs[1].name), 3)
                ), 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                    "tab-item animate-base",
                    utsArrayOf(
                        _ctx.acAnimate
                    )
                )), "onTouchstart" to _ctx.handlerTouchstart, "onTouchmove" to _ctx.handlerTouchmove, "onTouchend" to _ctx.handlerTouchend, "style" to normalizeStyle(utsArrayOf(
                    if (_ctx.tabIndex == 2) {
                        "display: visible;";
                    } else {
                        "display: none;";
                    }
                ))), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "bg-img"), utsArrayOf(
                        if (isTrue(!_ctx.isShowGif)) {
                            createElementVNode("image", utsMapOf("key" to 0, "class" to "center-icon", "src" to _ctx.tabs[2].selectedIcon, "mode" to "widthFix"), null, 8, utsArrayOf(
                                "src"
                            ));
                        } else {
                            createElementVNode("image", utsMapOf("key" to 1, "class" to "center-icon", "style" to normalizeStyle(utsMapOf("width" to "200%")), "src" to "/static/animate/tabbar_animate.gif", "mode" to "widthFix"), null, 4);
                        }
                    ))
                ), 46, utsArrayOf(
                    "onTouchstart",
                    "onTouchmove",
                    "onTouchend"
                )),
                if (_ctx.tabIndex !== 2) {
                    createElementVNode("view", utsMapOf("key" to 0, "class" to "tab-item", "onClick" to fun(){
                        _ctx.changeTab(2);
                    }), utsArrayOf(
                        createElementVNode("image", utsMapOf("class" to "tab-icon", "src" to _ctx.tabs[2].unselectedIcon, "mode" to "widthFix"), null, 8, utsArrayOf(
                            "src"
                        )),
                        createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                            "tab-text",
                            if (2 === _ctx.tabIndex) {
                                "active-text";
                            } else {
                                "";
                            }
                        ))), toDisplayString(_ctx.tabs[2].name), 3)
                    ), 8, utsArrayOf(
                        "onClick"
                    ));
                } else {
                    createCommentVNode("v-if", true);
                }
                ,
                createElementVNode("view", utsMapOf("class" to "tab-item", "onClick" to fun(){
                    _ctx.changeTab(3);
                }
                ), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "tab-icon", "src" to if (3 === _ctx.tabIndex) {
                        _ctx.tabs[3].selectedIcon;
                    } else {
                        _ctx.tabs[3].unselectedIcon;
                    }
                    , "mode" to "widthFix"), null, 8, utsArrayOf(
                        "src"
                    )),
                    createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "tab-text",
                        if (3 === _ctx.tabIndex) {
                            "active-text";
                        } else {
                            "";
                        }
                    ))), toDisplayString(_ctx.tabs[3].name), 3)
                ), 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("class" to "tab-item", "onClick" to fun(){
                    _ctx.changeTab(4);
                }
                ), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "tab-icon", "src" to if (4 === _ctx.tabIndex) {
                        _ctx.tabs[4].selectedIcon;
                    } else {
                        _ctx.tabs[4].unselectedIcon;
                    }
                    , "mode" to "widthFix"), null, 8, utsArrayOf(
                        "src"
                    )),
                    createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "tab-text",
                        if (4 === _ctx.tabIndex) {
                            "active-text";
                        } else {
                            "";
                        }
                    ))), toDisplayString(_ctx.tabs[4].name), 3)
                ), 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ), 4);
    }
    open var acAnimate: String by `$data`;
    open var isShowGif: Boolean by `$data`;
    open var tabIndex: Number by `$data`;
    open var centerIndex: Boolean by `$data`;
    open var direction: String by `$data`;
    open var loop: Number by `$data`;
    open var clickShow: Boolean by `$data`;
    open var longClickShow: Boolean by `$data`;
    open var tabs: UTSArray<UxTab> by `$data`;
    open var pageUrls: UTSArray<String> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("acAnimate" to "", "isShowGif" to false, "tabIndex" to 2, "centerIndex" to true, "direction" to "right", "loop" to 0, "clickShow" to false, "longClickShow" to false, "tabs" to utsArrayOf<UxTab>(UxTab(name = "展架", selectedIcon = "/static/image/tabBar/选择1.png", unselectedIcon = "/static/image/tabBar/未选择1.png"), UxTab(name = "集市", selectedIcon = "/static/image/tabBar/选择2.png", unselectedIcon = "/static/image/tabBar/未选择2.png"), UxTab(name = "植物", selectedIcon = "/static/image/tabBar/选择3.png", unselectedIcon = "/static/image/tabBar/未选择3.png"), UxTab(name = "膳食", selectedIcon = "/static/image/tabBar/选择4.png", unselectedIcon = "/static/image/tabBar/未选择4.png"), UxTab(name = "我的", selectedIcon = "/static/image/tabBar/选择5.png", unselectedIcon = "/static/image/tabBar/未选择5.png")), "pageUrls" to utsArrayOf(
            "/pages/showWindow/showWindow",
            "/pages/community/community",
            "/pages/health/health",
            "/pages/myPage/myPage"
        ));
    }
    override fun `$initMethods`() {
        this.changeTab = fun(index: Number) {
            if (index === this.tabIndex) {
                return;
            }
            this.tabIndex = index;
            if (index !== 2) {
                this.`$nextTick`(fun(){
                    this.acAnimate = "";
                });
            } else {
                this.`$nextTick`(fun(){
                    this.acAnimate = "animate-ac";
                }
                );
            }
        }
        ;
        this.handlerTouchstart = fun() {
            this.loop = setTimeout(fun(){
                this.loop = 0;
                console.log("长按", " at pages/tabbar/tabbar.uvue:126");
                this.isShowGif = true;
                setTimeout(fun(){
                    this.isShowGif = false;
                }
                , 1500);
                this.clickShow = false;
                this.longClickShow = true;
            }
            , 500);
        }
        ;
        this.handlerTouchmove = fun() {
            clearTimeout(this.loop);
            this.loop = 0;
        }
        ;
        this.handlerTouchend = fun() {
            clearTimeout(this.loop);
            console.log("离开长按", " at pages/tabbar/tabbar.uvue:145");
            this.isShowGif = false;
            if (this.loop !== 0) {
                console.log("单击", " at pages/tabbar/tabbar.uvue:150");
                if (this.centerIndex === true) {
                    return;
                }
                this.centerIndex = true;
                this.tabIndex = 2;
                console.log(this.tabIndex, this.centerIndex, " at pages/tabbar/tabbar.uvue:155");
                this.clickShow = true;
                this.longClickShow = false;
            }
        }
        ;
    }
    open lateinit var changeTab: (index: Number) -> Unit;
    open lateinit var handlerTouchstart: () -> Unit;
    open lateinit var handlerTouchmove: () -> Unit;
    open lateinit var handlerTouchend: () -> Unit;
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ), utsArrayOf(
                    GenApp.styles
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("gif" to padStyleMapOf(utsMapOf("position" to "fixed", "left" to "50%", "transform" to "translate(-50%)")), "center-img" to padStyleMapOf(utsMapOf("width" to 40)), "animate-base" to padStyleMapOf(utsMapOf("transform" to "scale(0.1)", "transitionProperty" to "transform", "transitionDuration" to "0.2s")), "animate-ac" to padStyleMapOf(utsMapOf("transform" to "scale(1)", "transitionProperty" to "transform", "transitionDuration" to "0.2s")), "tabbar" to padStyleMapOf(utsMapOf("position" to "fixed", "zIndex" to 1000, "bottom" to 0, "left" to 0, "right" to 0, "backgroundColor" to "#ffffff", "width" to "100%", "height" to "7%", "overflow" to "visible", "display" to "flex", "flexDirection" to "row", "justifyContent" to "space-around", "alignItems" to "center")), "tab-item" to utsMapOf(".tabbar " to utsMapOf("width" to "20%", "height" to "100%", "overflow" to "visible", "display" to "flex", "flexDirection" to "column", "justifyContent" to "center", "alignItems" to "center")), "bg-img" to utsMapOf(".tabbar .tab-item " to utsMapOf("width" to 70, "height" to 70, "borderRadius" to 70, "backgroundColor" to "#f2eee9", "display" to "flex", "justifyContent" to "center", "alignItems" to "center", "position" to "absolute", "bottom" to 16)), "center-icon" to utsMapOf(".tabbar .tab-item .bg-img " to utsMapOf("width" to 52)), "tab-icon" to utsMapOf(".tabbar .tab-item " to utsMapOf("width" to 24)), "tab-text" to utsMapOf(".tabbar .tab-item " to utsMapOf("marginTop" to 4, "fontSize" to 12, "color" to "#808080")), "active-text" to utsMapOf(".tabbar .tab-item " to utsMapOf("color" to "#937152")), "@TRANSITION" to utsMapOf("animate-base" to utsMapOf("property" to "transform", "duration" to "0.2s"), "animate-ac" to utsMapOf("property" to "transform", "duration" to "0.2s")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
